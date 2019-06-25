/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.nuts.core.commands;

import java.io.PrintStream;
import net.vpc.app.nuts.NutsSession;
import net.vpc.app.nuts.core.util.CoreNutsUtils;

/**
 *
 * @author vpc
 */
public class DefaultNutsInfoInternalExecutable extends DefaultInternalNutsExecutableCommand {

    public DefaultNutsInfoInternalExecutable(String[] args, NutsSession session) {
        super("info", args, session);
    }

    @Override
    public void execute() {
        if (CoreNutsUtils.isIncludesHelpOption(args)) {
            showDefaultHelp();
            return;
        }
        PrintStream out = getSession().out();
        getSession().getWorkspace().info().configure(false, args).println(out);
    }

}
