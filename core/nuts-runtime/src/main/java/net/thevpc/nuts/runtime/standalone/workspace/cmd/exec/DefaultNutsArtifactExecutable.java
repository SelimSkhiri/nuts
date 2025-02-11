/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.nuts.runtime.standalone.workspace.cmd.exec;

import net.thevpc.nuts.*;
import net.thevpc.nuts.cmdline.NutsArgument;
import net.thevpc.nuts.cmdline.NutsCommandLine;
import net.thevpc.nuts.io.NutsPrintStream;

import java.util.*;

/**
 * @author thevpc
 */
public class DefaultNutsArtifactExecutable extends AbstractNutsExecutableCommand {

    NutsDefinition def;
    String commandName;
    String[] appArgs;
    List<String> executorOptions;
    List<String> workspaceOptions;
    Map<String, String> env;
    String dir;
    boolean failFast;
    NutsSession session;
    NutsSession execSession;
    NutsExecutionType executionType;
    NutsRunAs runAs;
    DefaultNutsExecCommand execCommand;
    boolean autoInstall = true;

    public DefaultNutsArtifactExecutable(NutsDefinition def, String commandName, String[] appArgs, List<String> executorOptions,
                                         List<String> workspaceOptions, Map<String, String> env, String dir, boolean failFast,
                                         NutsSession session,
                                         NutsSession execSession,
                                         NutsExecutionType executionType, NutsRunAs runAs, DefaultNutsExecCommand execCommand) {
        super(commandName, def.getId().getLongName(), NutsExecutableType.ARTIFACT);
        this.def = def;
        this.runAs = runAs;
        //all these information areavailable, an exception would be thrown if not!
        def.getContent().get(session);
        def.getDependencies().get(session);
        def.getEffectiveDescriptor().get(session);
//        def.getInstallInformation();

        this.commandName = commandName;
        this.appArgs = appArgs;
        this.env = env;
        this.dir = dir;
        this.failFast = failFast;
        this.session = session;
        this.execSession = execSession;
        this.executionType = executionType;
        this.execCommand = execCommand;

        List<String> executorOptionsList = new ArrayList<>();
        NutsArtifactCall exc = def.getDescriptor().getExecutor();
        if(exc!=null){

        }
        for (String option : executorOptions) {
            NutsArgument a = NutsArgument.of(option);
            if (a.getKey().asString().get(session).equals("--nuts-auto-install")) {
                if (a.isKeyValue()) {
                    autoInstall = a.isNegated() != a.getBooleanValue().get(session);
                } else {
                    autoInstall = true;
                }
            } else {
                executorOptionsList.add(option);
            }
        }
        this.executorOptions = executorOptionsList;
        this.workspaceOptions = workspaceOptions;
    }

    @Override
    public NutsId getId() {
        return def.getId();
    }

    @Override
    public void execute() {
        NutsInstallStatus installStatus = def.getInstallInformation().get(session).getInstallStatus();
        if (!installStatus.isInstalled()) {
            if(autoInstall) {
                session.install().setSession(session).addId(def.getId()).run();
                NutsInstallStatus st = session.fetch().setSession(session).setId(def.getId()).getResultDefinition().getInstallInformation().get(session).getInstallStatus();
                if (!st.isInstalled()) {
                    throw new NutsUnexpectedException(execSession, NutsMessage.ofCstyle("auto installation of %s failed",def.getId()));
                }
            }else{
                throw new NutsUnexpectedException(execSession, NutsMessage.ofCstyle("you must install %s to be able to run it",def.getId()));
            }
        } else if (installStatus.isObsolete()) {
            if(autoInstall) {
                session.install()
                        .configure(true,"--reinstall")
                        .setSession(session).addId(def.getId()).run();
            }
        }
//        LinkedHashSet<NutsDependency> reinstall = new LinkedHashSet<>();
//        NutsDependencyFilter depFilter = NutsDependencyUtils.createJavaRunDependencyFilter(traceSession);
//        for (NutsDependency dependency : def.getDependencies()) {
//            if (depFilter.acceptDependency(def.getId(), dependency, traceSession)) {
//                NutsInstallStatus st = tracesession.fetch()
//                        .setSession(traceSession.copy().setFetchStrategy(NutsFetchStrategy.OFFLINE))
//                        .setId(dependency.toId()).getResultDefinition().getInstallInformation().getInstallStatus();
//                if (st.isObsolete() || st.isNonDeployed()) {
//                    reinstall.add(dependency);
//                }
//            }
//        }
//        if (!reinstall.isEmpty()) {
//            NutsInstallCommand iii = tracesession.install().setSession(traceSession).setStrategy(NutsInstallStrategy.REINSTALL);
//            for (NutsDependency nutsId : reinstall) {
//                iii.id(nutsId.toId());
//            }
//            iii.run();
//            for (NutsDependency dependency : reinstall) {
//                boolean optional = execsession.dependency().parser().parseOptional(dependency.getOptional());
//
//                NutsInstallStatus st = tracesession.fetch().setSession(traceSession.copy().setFetchStrategy(NutsFetchStrategy.OFFLINE))
//                        .setId(dependency.toId()).getResultDefinition().getInstallInformation().getInstallStatus();
//                if ((st.isObsolete() || st.isNonDeployed()) && !optional) {
//                    throw new NutsUnexpectedException(execSession, "unresolved dependency " + dependency + " has status " + st);
//                }
//            }
//        }
        execCommand.ws_execId(def, commandName, appArgs, executorOptions, workspaceOptions, env, dir, failFast, false, session, execSession, executionType,runAs, false);
    }

    @Override
    public void dryExecute() {
        if (autoInstall && !def.getInstallInformation().get(session).getInstallStatus().isInstalled()) {
            execSession.security().checkAllowed(NutsConstants.Permissions.AUTO_INSTALL, commandName);
            NutsPrintStream out = execSession.out();
            out.printf("[dry] ==install== %s%n", def.getId().getLongName());
        }
        execCommand.ws_execId(def, commandName, appArgs, executorOptions, workspaceOptions, env, dir, failFast, false, session, execSession, executionType,runAs, true);
    }

    @Override
    public String toString() {
        return "nuts " + getId().toString() + " " + NutsCommandLine.of(appArgs).toString();
    }

    @Override
    public NutsSession getSession() {
        return session;
    }
}
