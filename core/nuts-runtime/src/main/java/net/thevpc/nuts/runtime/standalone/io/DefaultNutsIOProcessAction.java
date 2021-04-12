package net.thevpc.nuts.runtime.standalone.io;

import net.thevpc.nuts.*;
import net.thevpc.nuts.runtime.core.io.DefaultNutsProcessInfo;
import net.thevpc.nuts.runtime.core.util.CoreStringUtils;
import net.thevpc.nuts.runtime.standalone.util.NutsCollectionResult;
import net.thevpc.nuts.runtime.bundles.iter.IteratorBuilder;
import net.thevpc.nuts.runtime.bundles.iter.IteratorUtils;

import java.io.File;
import java.util.*;
import net.thevpc.nuts.runtime.standalone.util.NutsWorkspaceUtils;

public class DefaultNutsIOProcessAction implements NutsIOProcessAction {

    private String processType;
    private NutsWorkspace ws;
    private NutsSession session;
    private boolean failFast;

    public DefaultNutsIOProcessAction(NutsWorkspace ws) {
        this.ws = ws;
    }

    protected void checkSession() {
        NutsWorkspaceUtils.checkSession(ws, session);
    }

    @Override
    public boolean isFailFast() {
        return failFast;
    }

    @Override
    public NutsIOProcessAction setFailFast(boolean failFast) {
        this.failFast = failFast;
        return this;
    }

    @Override
    public NutsIOProcessAction failFast(boolean failFast) {
        return setFailFast(failFast);
    }

    @Override
    public NutsIOProcessAction failFast() {
        return failFast(true);
    }

    @Override
    public NutsSession getSession() {
        return session;
    }

    @Override
    public NutsIOProcessAction setSession(NutsSession session) {
        this.session = session;
        return this;
    }

    @Override
    public String getType() {
        return processType;
    }

    public NutsIOProcessAction setType(String processType) {
        this.processType = processType;
        return this;
    }

    @Override
    public NutsIOProcessAction type(String processType) {
        return setType(processType);
    }

    private static String getJpsJavaHome(NutsWorkspace ws, String version, NutsSession session) {
        List<String> detectedJavaHomes = new ArrayList<>();
        String jh = System.getProperty("java.home");
        detectedJavaHomes.add(jh);
        String v = getJpsJavaHome(jh);
        if (v != null) {
            return v;
        }
        NutsVersionFilter nvf = CoreStringUtils.isBlank(version) ? null : ws.version().parser().parse(version).filter();
        NutsSdkLocation[] availableJava = ws.sdks().setSession(session).find("java",
                java -> "jdk".equals(java.getPackaging()) && (nvf == null || nvf.acceptVersion(ws.version().parser().parse(java.getVersion()), session))
        );
        for (NutsSdkLocation java : availableJava) {
            detectedJavaHomes.add(java.getPath());
            v = getJpsJavaHome(java.getPath());
            if (v != null) {
                return v;
            }
        }
        throw new NutsExecutionException(session, "Unable to resolve a valid jdk installation. "
                + "Either run nuts with a valid JDK/SDK (not JRE) or register a valid one using nadmin tool. "
                + "All the followings are invalid : \n"
                + String.join("\n", detectedJavaHomes),
                 10);
    }

    private static String getJpsJavaHome(String base) {
        File jh = new File(base);
        if (new File(jh, ".." + File.separator + "bin" + File.separator + "jps").exists()) {
            return jh.getParent();
        }
        if (new File(jh, "bin" + File.separator + "jps").exists()) {
            return jh.getPath();
        }
        return null;
    }

    @Override
    public NutsResultList<NutsProcessInfo> getResultList() {
        checkSession();
        String processType = CoreStringUtils.trim(getType());
        if (processType.toLowerCase().startsWith("java#")) {
            return getResultListJava(processType.substring("java#".length()));
        } else if (processType.toLowerCase().equals("java")) {
            return getResultListJava("");
        } else {
            if (isFailFast()) {
                throw new NutsIllegalArgumentException(getSession(), "unsupported list processes of type : " + processType);
            }
            return new NutsCollectionResult<>(getSession(), "process-" + processType, Collections.emptyList());
        }
    }

    private NutsResultList<NutsProcessInfo> getResultListJava(String version) {
        checkSession();
        Iterator<NutsProcessInfo> it = IteratorBuilder.ofLazy(() -> {
            String cmd = "jps";
            NutsExecCommand b = null;
            boolean mainArgs = true;
            boolean vmArgs = true;
            String jdkHome = getJpsJavaHome(ws, version, session);
            if (jdkHome != null) {
                cmd = jdkHome + File.separator + "bin" + File.separator + cmd;
            }
            b = getSession().getWorkspace().exec()
                    .setExecutionType(NutsExecutionType.USER_CMD)
                    .addCommand(cmd)
                    .addCommand("-l" + (mainArgs ? "m" : "") + (vmArgs ? "v" : ""))
                    .setRedirectErrorStream(true)
                    .grabOutputString()
                    .setFailFast(isFailFast());
            b.getResult();
            if (b.getResult() == 0) {
                String out = b.getOutputString();
                String[] split = out.split("\n");
                return Arrays.asList(split).iterator();
            }
            return IteratorUtils.emptyIterator();
        }).map(line -> {
            int s1 = line.indexOf(' ');
            int s2 = line.indexOf(' ', s1 + 1);
            String pid = line.substring(0, s1).trim();
            String cls = line.substring(s1 + 1, s2 < 0 ? line.length() : s2).trim();
            String args = s2 >= 0 ? line.substring(s2 + 1).trim() : "";
            return (NutsProcessInfo) new DefaultNutsProcessInfo(
                    pid, cls, null, args
            );
        }).build();
        return new NutsCollectionResult<NutsProcessInfo>(getSession(), "process-" + getType(), it);
    }
}
