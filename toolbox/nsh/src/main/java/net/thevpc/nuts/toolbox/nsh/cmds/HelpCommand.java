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
 *
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
package net.thevpc.nuts.toolbox.nsh.cmds;

import net.thevpc.nuts.*;
import net.thevpc.nuts.toolbox.nsh.AbstractNshBuiltin;
import net.thevpc.nuts.toolbox.nsh.NshExecutionContext;
import net.thevpc.nuts.toolbox.nsh.options.CommandNonOption;
import net.thevpc.common.strings.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import net.thevpc.jshell.JShellBuiltin;

/**
 * Created by vpc on 1/7/17.
 */
public class HelpCommand extends AbstractNshBuiltin {

    public HelpCommand() {
        super("help", DEFAULT_SUPPORT);
    }

    @Override
    public void exec(String[] args, NshExecutionContext context) {
        NutsCommandLine commandLine = cmdLine(args, context);
        List<String> commandNames = new ArrayList<>();
        NutsArgument a;
        boolean code = false;
        while (commandLine.hasNext()) {
            if (commandLine.next("--ntf") != null) {
                code = true;
                context.getSession().getTerminal().setMode(NutsTerminalMode.INHERITED);
            }else if (commandLine.peek().isNonOption()){
                commandNames.add(commandLine.nextNonOption(new CommandNonOption("command", context.getNutsShellContext())).required().getString());
            } else {
                context.configureLast(commandLine);
            }
        }
        final NutsFormatManager text = context.getWorkspace().formats();
        Function<String, String> ss = code ? new Function<String, String>() {
            @Override
            public String apply(String t) {
                return text.text().forPlain(t).toString();
            }
        } : x -> x;
        if (commandLine.isExecMode()) {
            if (commandNames.isEmpty()) {
                NutsText n = text.text().parser().parseResource("/net/thevpc/nuts/toolbox/nsh.ntf",
                        text.text().parser().createLoader(HelpCommand.class.getClassLoader())
                );
                String helpText = (n==null?"no help found":n.toString());
                context.out().println(ss.apply(helpText));
                context.out().println(context.workspace().formats().text().forStyled("AVAILABLE COMMANDS ARE:", NutsTextNodeStyle.primary(1)));
                JShellBuiltin[] commands = context.getGlobalContext().builtins().getAll();
                Arrays.sort(commands, new Comparator<JShellBuiltin>() {
                    @Override
                    public int compare(JShellBuiltin o1, JShellBuiltin o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                int max = 1;
                for (JShellBuiltin cmd : commands) {
                    int x = cmd.getName().length();
                    if (x > max) {
                        max = x;
                    }
                }
                for (JShellBuiltin cmd : commands) {
                    context.out().printf("%s : ", text.text().forStyled(StringUtils.alignLeft(cmd.getName(), max),NutsTextNodeStyle.primary(4)));
                    context.out().println(ss.apply(cmd.getHelpHeader())); //formatted
                }
            } else {
                for (String commandName : commandNames) {
                    JShellBuiltin command1 = context.getGlobalContext().builtins().find(commandName);
                    if (command1 == null) {
                        context.err().printf("command not found : %s\n", text.text().forStyled(commandName,NutsTextNodeStyle.error()));
                    } else {
                        String help = command1.getHelp();
                        context.out().printf("%s : %s\f", text.text().forStyled("COMMAND",NutsTextNodeStyle.primary(4)),"commandName");
                        context.out().println(ss.apply(help));
                    }
                }
            }
        }
    }
}
