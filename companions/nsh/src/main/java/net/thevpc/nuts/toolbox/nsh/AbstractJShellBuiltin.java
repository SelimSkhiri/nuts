/**
 * ====================================================================
 * Nuts : Network Updatable Things Service
 * (universal package manager)
 * <br>
 * is a new Open Source Package Manager to help install packages and libraries
 * for runtime execution. Nuts is the ultimate companion for maven (and other
 * build managers) as it helps installing all package dependencies at runtime.
 * Nuts is not tied to java and is a good choice to share shell scripts and
 * other 'things' . Its based on an extensible architecture to help supporting a
 * large range of sub managers / repositories.
 * <br>
 * <p>
 * Copyright [2020] [thevpc]
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * <br>
 * ====================================================================
 */
package net.thevpc.nuts.toolbox.nsh;

import net.thevpc.nuts.*;
import net.thevpc.nuts.cmdline.NutsCommandAutoComplete;
import net.thevpc.nuts.cmdline.NutsCommandLine;
import net.thevpc.nuts.io.NutsMemoryPrintStream;
import net.thevpc.nuts.io.NutsPrintStream;
import net.thevpc.nuts.spi.NutsSupportLevelContext;
import net.thevpc.nuts.text.NutsTexts;
import net.thevpc.nuts.toolbox.nsh.bundles._IOUtils;
import net.thevpc.nuts.toolbox.nsh.jshell.JShellBuiltin;
import net.thevpc.nuts.toolbox.nsh.jshell.JShellExecutionContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vpc on 1/7/17.
 */
public abstract class AbstractJShellBuiltin implements JShellBuiltin {

    private static final Logger LOG = Logger.getLogger(AbstractJShellBuiltin.class.getName());
    private final String name;
    private final int supportLevel;
    private String help;
    private boolean enabled = true;

    public AbstractJShellBuiltin(String name, int supportLevel) {
        this.name = name;
        this.supportLevel = supportLevel;
    }

    protected NutsCommandLine cmdLine(String[] args, JShellExecutionContext context) {
        NutsSession session = context.getSession();
        return NutsCommandLine.of(args)
                .setAutoComplete(context.getShellContext().getAutoComplete())
                .setCommandName(getName());
    }

    @Override
    public int getSupportLevel(NutsSupportLevelContext param) {
        return supportLevel;
    }

    @Override
    public void autoComplete(JShellExecutionContext context, NutsCommandAutoComplete autoComplete) {
        NutsCommandAutoComplete oldAutoComplete = context.getShellContext().getAutoComplete();
        context.getShellContext().setAutoComplete(autoComplete);
        try {
            if (autoComplete == null) {
                throw new NutsIllegalArgumentException(context.getSession(),  NutsMessage.ofPlain("missing auto-complete"));
            }
            NutsCommandAutoCompleteComponent best = context.getSession().extensions().createServiceLoader(NutsCommandAutoCompleteComponent.class, JShellBuiltin.class, NutsCommandAutoCompleteComponent.class.getClassLoader())
                    .loadBest(AbstractJShellBuiltin.this);
            if (best != null) {
                best.autoComplete(this, context);
            } else {
                String[] args = autoComplete.getWords().toArray(new String[0]);
                try {
                    exec(args, context);
                } catch (Exception ex) {
                    //ignore
                }
            }
        } finally {
            context.getShellContext().setAutoComplete(oldAutoComplete);
        }
    }

    protected abstract void execImpl(String[] args, JShellExecutionContext context);

    public final void exec(String[] args, JShellExecutionContext context) {
        try {
            execImpl(args, context);
        } catch (NutsExecutionException ex) {
//            if(ex.getExitCode()!=0) {
//                context.err().println(ex.toString());
//            }
            throw ex;
        } catch (NutsException ex) {
            throw new NutsExecutionException(context.getSession(),ex.getFormattedMessage(),ex,254);
        } catch (Exception ex) {
            throw new NutsExecutionException(context.getSession(),
                    NutsMessage.ofNtf(NutsTexts.of(context.getSession()).ofText(ex).toString())
                    ,ex,254);
        }
    }

    @Override
    public String getHelp() {
        if (help == null) {
            try {
                URL resource = getClass().getResource("/net/thevpc/nuts/toolbox/nsh/cmd/" + getName() + ".ntf");
                if (resource != null) {
                    help = _IOUtils.loadString(resource);
                }
            } catch (Exception e) {
                LOG.log(Level.CONFIG, "Unable to load help for " + getName(), e);
            }
            if (help == null) {
                help = "```error no help found for command " + getName() + "```";
            }
        }
        return help;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getHelpHeader() {
        String h = getHelp();
        BufferedReader r = new BufferedReader(new StringReader(h));
        while (true) {
            String line = null;
            try {
                line = r.readLine();
            } catch (IOException e) {
                //
            }
            if (line == null) {
                break;
            }
            if (!NutsBlankable.isBlank(line)) {
                return line;
            }
        }
        return "No help";
    }

    protected void throwExecutionException(Object errObject,int errorCode,NutsSession session) {
        session=session.copy();
        NutsPrintStream printStream = NutsMemoryPrintStream.of(session);
        if (errObject != null) {
            printStream.printf(errObject);
        }else{
            printStream.printf("%s: command failed with code %s%n",getName(),errorCode);
        }
        throw new NutsExecutionException(session,NutsMessage.ofNtf(printStream.toString()), errorCode);
    }

}
