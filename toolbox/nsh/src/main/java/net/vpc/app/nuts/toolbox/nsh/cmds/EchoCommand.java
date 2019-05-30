/**
 * ====================================================================
 * Nuts : Network Updatable Things Service
 * (universal package manager)
 * <p>
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 * <p>
 * Copyright (C) 2016-2017 Taha BEN SALAH
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * ====================================================================
 */
package net.vpc.app.nuts.toolbox.nsh.cmds;

import net.vpc.app.nuts.NutsCommand;
import net.vpc.app.nuts.NutsIllegalArgumentException;
import net.vpc.app.nuts.toolbox.nsh.AbstractNshCommand;
import net.vpc.app.nuts.toolbox.nsh.NutsCommandContext;

import java.io.PrintStream;

import net.vpc.app.nuts.NutsArgument;

/**
 * Created by vpc on 1/7/17.
 */
public class EchoCommand extends AbstractNshCommand {

    public EchoCommand() {
        super("echo", DEFAULT_SUPPORT);
    }

    public int exec(String[] args, NutsCommandContext context) throws Exception {
        NutsCommand cmdLine = cmdLine(args, context);
        boolean noTrailingNewLine = false;
        boolean plain = false;
        boolean first = true;
        PrintStream out = context.out();
        NutsArgument a;
        while (cmdLine.hasNext()) {
            if (cmdLine.peek().isOption()) {
                if (context.configureFirst(cmdLine)) {
                    //
                }else if ((a = cmdLine.nextBoolean("-n")) != null) {
                    noTrailingNewLine = a.getValue().getBoolean();
                } else if ((a = cmdLine.nextBoolean("-p")) != null) {
                    plain = a.getValue().getBoolean();
                } else {
                    throw new NutsIllegalArgumentException(context.getWorkspace(),"echo: Unsupported option " + a);
                }
            } else {
                if (cmdLine.isExecMode()) {
                    if (first) {
                        first = false;
                    } else {
                        out.print(" ");
                    }
                    if (plain) {
                        out.print(cmdLine.required().nextNonOption(cmdLine.createNonOption("value")).getString());
                    } else {
                        out.print(cmdLine.required().nextNonOption(cmdLine.createNonOption("value")).getString());
                    }
                }
            }
        }
        if (cmdLine.isExecMode()) {
            if (!noTrailingNewLine) {
                out.println();
            }
        }
        return 0;
    }
}
