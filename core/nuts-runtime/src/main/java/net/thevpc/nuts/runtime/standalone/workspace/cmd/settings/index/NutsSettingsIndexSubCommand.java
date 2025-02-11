/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.nuts.runtime.standalone.workspace.cmd.settings.index;

import net.thevpc.nuts.*;
import net.thevpc.nuts.cmdline.NutsArgument;
import net.thevpc.nuts.cmdline.NutsCommandLine;
import net.thevpc.nuts.runtime.standalone.workspace.cmd.settings.AbstractNutsSettingsSubCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thevpc
 */
public class NutsSettingsIndexSubCommand extends AbstractNutsSettingsSubCommand {

    @Override
    public boolean exec(NutsCommandLine cmdLine, Boolean autoSave, NutsSession session) {
        String name = "settings update stats";
        NutsArgument a;
        if (cmdLine.next("update stats").isPresent()) {
            List<String> repos = new ArrayList<>();
            while (cmdLine.hasNext()) {
                repos.add(cmdLine.next().flatMap(NutsValue::asString).get(session));
            }
            updateStatistics(session, repos.toArray(new String[0]));
            cmdLine.setCommandName(name).throwUnexpectedArgument(session);
            return true;
        } else {
            return false;
        }
    }

    private void updateStatistics(NutsSession session, String[] repos) {
        NutsWorkspaceConfigManager cfg = session.config();
        NutsUpdateStatisticsCommand cmd = session.updateStatistics()
                .setSession(session);
        for (String repo : repos) {
            cmd.add(repo);
        }
        cmd.run();
    }
}
