/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.nuts.toolbox.nsh.cmds.config;

import net.vpc.app.nuts.*;
import net.vpc.app.nuts.toolbox.nsh.NutsCommandContext;
import net.vpc.app.nuts.toolbox.nsh.NutsCommandSyntaxError;
import net.vpc.app.nuts.toolbox.nsh.cmds.ConfigCommand;
import net.vpc.app.nuts.toolbox.nsh.options.ArchitectureNonOption;
import net.vpc.common.commandline.CommandLine;
import net.vpc.common.commandline.DefaultNonOption;
import net.vpc.common.commandline.FolderNonOption;
import net.vpc.common.strings.StringUtils;

import java.io.PrintStream;

/**
 *
 * @author vpc
 */
public class WorkspaceConfigSubCommand extends AbstractConfigSubCommand {

    @Override
    public boolean exec(CommandLine cmdLine, ConfigCommand config, Boolean autoSave, NutsCommandContext context) {
        if (cmdLine.readAllOnce("show location")) {
            if (cmdLine.isExecMode()) {
                NutsSession session = context.getSession();
                PrintStream out = session.getTerminal().getFormattedOut();
                out.printf("%s\n", context.getWorkspace().getConfigManager().getWorkspaceLocation());
            }
            return true;
        }
        if (cmdLine.readAllOnce("check updates")) {
            if (cmdLine.isExecMode()) {
                NutsSession session = context.getSession();
                PrintStream out = context.getTerminal().getFormattedOut();
                if(context.getWorkspace().checkWorkspaceUpdates(
                        new NutsWorkspaceUpdateOptions()
                                .setEnableMajorUpdates(true)
                                .setLogUpdates(true)
                                .setUpdateExtensions(true)
                                .setApplyUpdates(false)
                        , context.getSession()).length==0){
                    out.printf("workspace **upto-date**\n");
                }
            }
            return true;
        }
        if (cmdLine.readAllOnce("update")) {
            boolean force = true;
            String version = null;
            if (cmdLine.isExecMode()) {
                NutsSession session = context.getSession();
                PrintStream out = context.getTerminal().getFormattedOut();
                if(context.getWorkspace().checkWorkspaceUpdates(
                        new NutsWorkspaceUpdateOptions()
                                .setEnableMajorUpdates(force)
                                .setForceBootAPIVersion(version)
                                .setLogUpdates(true)
                                .setUpdateExtensions(true)
                                .setApplyUpdates(true)
                        , context.getSession()).length==0){
                    out.printf("workspace **upto-date**\n");
                }
            }
            return true;
        }
        if (cmdLine.readAll("create workspace", "cw")) {
            boolean ignoreIdFound = false;
            boolean save = false;
            String archetype = null;
            String login = null;
            String password = null;
            boolean processed = false;
            while (cmdLine.hasNext()) {
                if (cmdLine.readAllOnce("-i", "--ignore")) {
                    ignoreIdFound = true;
                } else if (cmdLine.readAllOnce("-s", "--save")) {
                    save = true;
                } else if (cmdLine.readAllOnce("-h", "--archetype")) {
                    archetype = cmdLine.readRequiredNonOption(new ArchitectureNonOption("Archetype", context.getWorkspace())).getStringOrError();
                } else if (cmdLine.readAllOnce("-u", "--login")) {
                    login = cmdLine.readRequiredNonOption(new DefaultNonOption("Login")).getStringOrError();
                } else if (cmdLine.readAllOnce("-x", "--password")) {
                    password = cmdLine.readRequiredNonOption(new DefaultNonOption("Password")).getStringOrError();
                } else {
                    String ws = cmdLine.readRequiredNonOption(new DefaultNonOption("NewWorkspaceName")).getString();
                    if (cmdLine.isExecMode()) {
                        NutsWorkspace workspace = context.getWorkspace().openWorkspace(
                                new NutsWorkspaceOptions()
                                        .setWorkspace(ws)
                                        .setArchetype(archetype)
                                        .setCreateIfNotFound(true)
                                        .setSaveIfCreated(save)
                                        .setCreateIfNotFound(ignoreIdFound)
                        );
                        if (!StringUtils.isEmpty(login)) {
                            workspace.getSecurityManager().login(login, password);
                        }
                        ConfigCommand.trySave(context, workspace, null, autoSave, cmdLine);
                    }
                    processed = true;
                    cmdLine.unexpectedArgument("config create workspace");
                }
            }
            if (cmdLine.isExecMode()) {
                if (!processed) {
                    throw new NutsCommandSyntaxError("config: incorrect command : create workspace");
                }
            }
            return true;
        } else if (cmdLine.readAll("set workspace boot-version")) {
            String version = cmdLine.readRequiredNonOption(new DefaultNonOption("version")).getString();
            NutsBootConfig c = context.getWorkspace().getConfigManager().getWorkspaceBootConfig();
            c.setBootAPIVersion(version);
            context.getWorkspace().getConfigManager().setBootConfig(c);
            cmdLine.unexpectedArgument("config set workspace version");

        } else if (cmdLine.readAll("set workspace runtime-version","set workspace runtime-id")) {
            String version = cmdLine.readRequiredNonOption(new DefaultNonOption("version")).getString();
            NutsBootConfig c = context.getWorkspace().getConfigManager().getWorkspaceBootConfig();
            if(version.contains("#")) {
                c.setBootRuntime(NutsConstants.NUTS_ID_BOOT_RUNTIME + "#" + version);
            }else{
                c.setBootRuntime(version);
            }
            context.getWorkspace().getConfigManager().setBootConfig(c);
            cmdLine.unexpectedArgument("config set workspace version");

        } else if (cmdLine.readAll("get workspace version", "gwv")) {
            cmdLine.unexpectedArgument("config get workspace version");
            NutsBootConfig c = context.getWorkspace().getConfigManager().getWorkspaceBootConfig();
            context.out().printf("boot-version  : %s\n",StringUtils.trim(c.getBootAPIVersion()));
            context.out().printf("runtime-id    : %s\n",StringUtils.trim(c.getBootRuntime()));
        } else if (cmdLine.readAll("set workspace", "sw")) {
            boolean createIfNotFound = false;
            boolean save = true;
            String login = null;
            String password = null;
            String archetype = null;
            int wsCount = 0;
            boolean processed = false;
            while (wsCount == 0 || cmdLine.hasNext()) {
                if (cmdLine.readAllOnce("-c", "--create")) {
                    createIfNotFound = true;
                } else if (cmdLine.readAllOnce("-s", "--save")) {
                    save = true;
                } else if (cmdLine.readAllOnce("-s", "--nosave")) {
                    save = false;
                } else if (cmdLine.readAllOnce("-h", "--archetype")) {
                    archetype = cmdLine.readRequiredNonOption(new ArchitectureNonOption("Archetype", context.getWorkspace())).getStringOrError();
                } else if (cmdLine.readAllOnce("-u", "--login")) {
                    login = cmdLine.readRequiredNonOption(new DefaultNonOption("Username")).getStringOrError();
                } else if (cmdLine.readAllOnce("-x", "--password")) {
                    password = cmdLine.readRequiredNonOption(new DefaultNonOption("Password")).getStringOrError();
                } else {
                    String ws = cmdLine.readRequiredNonOption(new FolderNonOption("WorkspacePath")).getString();
                    wsCount++;
                    cmdLine.unexpectedArgument("config set workspace");
                    processed = true;
                    if (cmdLine.isExecMode()) {
                        NutsWorkspace workspace = context.getWorkspace().openWorkspace(
                                new NutsWorkspaceOptions()
                                        .setWorkspace(ws)
                                        .setArchetype(archetype)
                                        .setSaveIfCreated(save)
                                        .setCreateIfNotFound(createIfNotFound)
                        );
                        if (!StringUtils.isEmpty(login)) {
                            workspace.getSecurityManager().login(login, password);
                        }
                        context.consoleContext().setWorkspace(workspace);
                        ConfigCommand.trySave(context, workspace, null, autoSave, cmdLine);
                    }
                }
            }
            cmdLine.unexpectedArgument("config set workspace");
            if (cmdLine.isExecMode()) {
                if (!processed) {
                    throw new NutsCommandSyntaxError("incorrect command : create workspace");
                }
            }
            return true;
        } else if (cmdLine.readAll("save workspace", "save", "sw")) {
            cmdLine.unexpectedArgument("config save workspace");
            if (cmdLine.isExecMode()) {
                ConfigCommand.trySave(context, context.getWorkspace(), null, autoSave, cmdLine);
            }
            return true;
        }
        return false;
    }

    @Override
    public int getSupportLevel(Object criteria) {
        return DEFAULT_SUPPORT;
    }

}
