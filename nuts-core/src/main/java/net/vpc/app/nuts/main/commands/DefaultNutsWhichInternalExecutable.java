/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.nuts.main.commands;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.vpc.app.nuts.*;
import net.vpc.app.nuts.runtime.util.CoreNutsUtils;

/**
 *
 * @author vpc
 */
public class DefaultNutsWhichInternalExecutable extends DefaultInternalNutsExecutableCommand {

    private final NutsExecCommand execCommand;

    public DefaultNutsWhichInternalExecutable(String[] args, NutsSession session, NutsExecCommand execCommand) {
        super("which", args, session);
        this.execCommand = execCommand;
    }

    @Override
    public void execute() {
        if (CoreNutsUtils.isIncludesHelpOption(args)) {
            showDefaultHelp();
            return;
        }
        List<String> commands = new ArrayList<String>();
        NutsWorkspace ws = getSession().getWorkspace();
        NutsCommandLine commandLine = ws.commandLine().create(args);
        while (commandLine.hasNext()) {
            NutsArgument a = commandLine.peek();
            if (a.isOption()) {
                switch (a.getStringKey()) {
                    case "--help": {
                        commandLine.skipAll();
                        showDefaultHelp();
                        return;
                    }
                    default: {
                        commandLine.unexpectedArgument();
                    }
                }
            } else {
                commandLine.skip();
                commands.add(a.toString());
                commands.addAll(Arrays.asList(commandLine.toArray()));
                commandLine.skipAll();
            }
        }
        if (commands.isEmpty()) {
            throw new NutsIllegalArgumentException(ws, "which: missing commands");
        }
        for (String arg : this.args) {
            PrintStream out = getSession().out();
            try {
                NutsExecutableInformation p = execCommand.copy().session(getSession()).clearCommand().configure(false, arg).which();
                boolean showDesc = false;
                switch (p.getType()) {
                    case SYSTEM: {
                        out.printf("[[%s]] : ==system command== %s%n", arg, p.getDescription());
                        break;
                    }
                    case ALIAS: {
                        out.printf("[[%s]] : ==nuts alias== (owner %s ) : %s%n", arg, p.getId() == null ? null : new NutsString(ws.id().value(p.getId()).format()),
                                new NutsString(ws.commandLine().create(ws.config().findCommandAlias(p.getName(), getSession()).getCommand()).toString())
                        );
                        break;
                    }
                    case ARTIFACT: {
                        if (p.getId() == null) {
                            throw new NutsNotFoundException(ws, arg);
                        }
                        out.printf("[[%s]] : ==nuts component== %s%n", arg, new NutsString(ws.id().value(p.getId()).format())/*, p.getDescription()*/);
                        break;
                    }
                    case INTERNAL: {
                        out.printf("[[%s]] : ==internal command== %n", arg);
                        break;
                    }
                }
                if (showDesc) {
                    out.printf("\t %s%n", arg/*, p.getDescription()*/);
                }
            } catch (NutsNotFoundException ex) {
                out.printf("[[%s]] : @@not found@@%n", arg);
            }
        }
    }

}
