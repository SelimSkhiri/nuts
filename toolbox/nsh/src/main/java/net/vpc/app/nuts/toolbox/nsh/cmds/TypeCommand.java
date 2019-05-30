/**
 * ====================================================================
 *            Nuts : Network Updatable Things Service
 *                  (universal package manager)
 *
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 *
 * Copyright (C) 2016-2017 Taha BEN SALAH
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * ====================================================================
 */
package net.vpc.app.nuts.toolbox.nsh.cmds;

import java.util.ArrayList;
import java.util.List;
import net.vpc.app.nuts.NutsArgument;
import net.vpc.app.nuts.NutsCommand;
import net.vpc.app.nuts.toolbox.nsh.SimpleNshCommand;
import net.vpc.common.javashell.JShell;
import net.vpc.common.javashell.cmds.JavaShellCommand;

/**
 * Created by vpc on 1/7/17.
 */
public class TypeCommand extends SimpleNshCommand {

    public TypeCommand() {
        super("type", DEFAULT_SUPPORT);
    }

    private static class Config {

        List<String> commands = new ArrayList<>();
    }

    private static class ResultItem {

        String command;
        String type;
        String message;

        public ResultItem(String command, String type, String message) {
            this.command = command;
            this.type = type;
            this.message = message;
        }

        public ResultItem() {
        }

    }

    @Override
    protected Object createConfiguration() {
        return new Config();
    }

    @Override
    protected boolean configureFirst(NutsCommand commandLine, SimpleNshCommandContext context) {
        Config config = context.getConfigObject();
        NutsArgument a = commandLine.peek();
        if (a == null) {
            return false;
        }
        if (commandLine.hasNext()) {
            if (a.isNonOption()) {
                config.commands.add(a.getString());
                return true;
            }
        }
        return false;
    }

    @Override
    protected Object createResult(SimpleNshCommandContext context) {
        Config config = context.getConfigObject();
        JShell shell = context.getShell();
        List<ResultItem> result = new ArrayList<>();
        for (String cmd : config.commands) {
            JavaShellCommand ic = shell.findCommand(cmd);
            if (ic != null && ic.isEnabled()) {
                result.add(new ResultItem(
                        cmd,
                        "builtin",
                        cmd + " is a shell builtin"
                ));
            } else {
                String alias = shell.getAlias(cmd);
                if (alias != null) {
                    result.add(new ResultItem(
                            cmd,
                            "alias",
                            cmd + " is aliased to `" + alias + "`"
                    ));
                } else {
                    String pp = shell.which(cmd, context.consoleContext());
                    if (pp != null) {
                        result.add(new ResultItem(
                                cmd,
                                "command",
                                cmd + " is " + pp
                        ));
                    } else {
                        if (ic != null) {
                            result.add(new ResultItem(
                                    cmd,
                                    "error",
                                    cmd + " is disabled"
                            ));
                        } else {
                            result.add(new ResultItem(
                                    cmd,
                                    "error",
                                    cmd + " not found"
                            ));
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    protected void printObjectPlain(Object resultObject, SimpleNshCommandContext context) {
        List<ResultItem> result = (List<ResultItem>) resultObject;
        for (ResultItem resultItem : result) {
            context.out().println(resultItem.message);
        }
    }

}
