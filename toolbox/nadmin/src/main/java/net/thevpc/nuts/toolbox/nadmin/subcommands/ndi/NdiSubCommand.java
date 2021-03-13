package net.thevpc.nuts.toolbox.nadmin.subcommands.ndi;

import net.thevpc.nuts.*;
import net.thevpc.nuts.toolbox.nadmin.subcommands.AbstractNAdminSubCommand;
import net.thevpc.nuts.toolbox.nadmin.subcommands.ndi.sys.LinuxNdi;
import net.thevpc.nuts.toolbox.nadmin.subcommands.ndi.sys.MacosNdi;
import net.thevpc.nuts.toolbox.nadmin.subcommands.ndi.sys.UnixNdi;
import net.thevpc.nuts.toolbox.nadmin.subcommands.ndi.sys.WindowsNdi;
import net.thevpc.nuts.toolbox.nadmin.subcommands.ndi.util.NdiUtils;

import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import net.thevpc.nuts.toolbox.nadmin.optional.oswindows.OptionalWindows;

public class NdiSubCommand extends AbstractNAdminSubCommand {

    public SystemNdi createNdi(NutsApplicationContext appContext) {
        SystemNdi ndi = null;
        switch (appContext.getWorkspace().env().getOsFamily()) {
            case LINUX: {
                ndi = new LinuxNdi(appContext);
                break;
            }
            case UNIX: {
                ndi = new UnixNdi(appContext);
                break;
            }
            case MACOS: {
                ndi = new MacosNdi(appContext);
                break;
            }
            case WINDOWS: {
                if (OptionalWindows.isAvailable()) {
                    ndi = new WindowsNdi(appContext);
                }
                break;
            }
        }
        return ndi;
    }

    public void runAddScript(NutsCommandLine commandLine, NutsApplicationContext context) {
        ArrayList<String> idsToInstall = new ArrayList<>();
        NutsWorkspace ws = context.getWorkspace();
        ArrayList<String> executorOptions = new ArrayList<>();
        NutsExecutionType execType = null;
        boolean fetch = false;
        boolean missingAnyArgument = true;
        NutsArgument a;
//        boolean forceAll = false;
        Boolean persistentConfig = null;
        boolean ignoreUnsupportedOs = false;

        String switchWorkspaceLocation = null;
        String linkName = null;
        boolean env = false;

        while (commandLine.hasNext()) {
            if ((a = commandLine.nextBoolean("-t", "--fetch")) != null) {
                fetch = a.getBooleanValue();
            } else if ((a = commandLine.nextBoolean("-x", "--external", "--spawn")) != null) {
                if (a.getBooleanValue()) {
                    execType = NutsExecutionType.SPAWN;
                }
            } else if ((a = commandLine.nextBoolean("-b", "--embedded")) != null) {
                if (a.getBooleanValue()) {
                    execType = NutsExecutionType.EMBEDDED;
                }
            } else if ((a = commandLine.nextBoolean("-e", "--env")) != null) {
                env = a.getBooleanValue();
            } else if ((a = commandLine.nextBoolean("--user-cmd")) != null) {
                if (a.getBooleanValue()) {
                    execType = NutsExecutionType.USER_CMD;
                }
            } else if ((a = commandLine.nextBoolean("--root-cmd")) != null) {
                if (a.getBooleanValue()) {
                    execType = NutsExecutionType.ROOT_CMD;
                }
            } else if ((a = commandLine.nextString("-X", "--exec-options")) != null) {
                executorOptions.add(a.getStringValue());
            } else if ((a = commandLine.nextString("-i", "--installed")) != null) {
                context.getSession().setConfirm(NutsConfirmationMode.YES);
                for (NutsId resultId : ws.search().setInstallStatus(
                        ws.filters().installStatus().byInstalled(true)
                ).getResultIds()) {
                    idsToInstall.add(resultId.getLongName());
                    missingAnyArgument = false;
                }
            } else if ((a = commandLine.nextString("-c", "--companions")) != null) {
                context.getSession().setConfirm(NutsConfirmationMode.YES);
                for (NutsId companion : ws.getCompanionIds()) {
                    idsToInstall.add(ws.search().addId(companion).setLatest(true).getResultIds().required().getLongName());
                    missingAnyArgument = false;
                }
            } else if ((a = commandLine.nextString("--switch")) != null) {
                Boolean booleanValue = a.getBooleanValue(null);
                if (booleanValue != null) {
                    persistentConfig = booleanValue;
                }
            } else if ((a = commandLine.nextString("--ignore-unsupported-os")) != null) {
                if (a.isEnabled()) {
                    ignoreUnsupportedOs = a.getBooleanValue();
                }
            } else if (commandLine.peek().getStringKey().equals("-w") || commandLine.peek().getStringKey().equals("--workspace")) {
                a = commandLine.nextString();
                if (a.isEnabled()) {
                    switchWorkspaceLocation = a.getStringValue();
                }
            } else if (commandLine.peek().getStringKey().equals("-n") || commandLine.peek().getStringKey().equals("--name")) {
                a = commandLine.nextString();
                if (a.isEnabled()) {
                    linkName = a.getStringValue();
                }
            } else if (commandLine.peek().isOption()) {
                context.configureLast(commandLine);
            } else {
                idsToInstall.add(commandLine.next().getString());
                missingAnyArgument = false;
            }
        }

        if (missingAnyArgument) {
            commandLine.required();
        }
        if (commandLine.isExecMode()) {
            SystemNdi ndi = createNdi(context);
            if (ndi == null) {
                if (ignoreUnsupportedOs) {
                    return;
                }
                throw new NutsExecutionException(ws, "platform not supported : " + ws.env().getOs(), 2);
            }
            if (!idsToInstall.isEmpty()) {
                printResults(context, ndi.createNutsScript(idsToInstall, switchWorkspaceLocation, linkName, persistentConfig, executorOptions, env, fetch, execType, context));
            }
        }
    }

    public void runRemoveScript(NutsCommandLine commandLine, NutsApplicationContext context) {
        ArrayList<String> idsToUninstall = new ArrayList<>();
        boolean forceAll = false;
        NutsWorkspace ws = context.getWorkspace();
        boolean missingAnyArgument = true;
        NutsArgument a;
        boolean ignoreUnsupportedOs = false;
        while (commandLine.hasNext()) {
            if ((a = commandLine.nextString("--ignore-unsupported-os")) != null) {
                if (a.isEnabled()) {
                    ignoreUnsupportedOs = a.getBooleanValue();
                }
            } else if (commandLine.peek().isOption()) {
                context.configureLast(commandLine);
            } else {
                idsToUninstall.add(commandLine.next().getString());
                missingAnyArgument = false;
            }
        }
        if (missingAnyArgument) {
            commandLine.required();
        }
        Path workspaceLocation = Paths.get(ws.locations().getWorkspaceLocation());
        if (commandLine.isExecMode()) {
            if (forceAll) {
                context.getSession().setConfirm(NutsConfirmationMode.YES);
            }
            SystemNdi ndi = createNdi(context);
            if (ndi == null) {
                if (ignoreUnsupportedOs) {
                    return;
                }
                throw new NutsExecutionException(ws, "platform not supported : " + ws.env().getOs(), 2);
            }
            boolean subTrace = context.getSession().isTrace();
            if (!context.getSession().isPlainTrace()) {
                subTrace = false;
            }
            if (!idsToUninstall.isEmpty()) {
                for (String id : idsToUninstall) {
                    try {
                        ndi.removeNutsScript(
                                id,
                                context.getSession().copy().setTrace(subTrace)
                        );
                    } catch (UncheckedIOException e) {
                        throw new NutsExecutionException(ws, "Unable to run script " + id + " : " + e.toString(), e);
                    }
                }
            }
        }
    }

    public void runSwitch(NutsCommandLine commandLine, NutsApplicationContext context) {
        NutsWorkspace ws = context.getWorkspace();
        String switchWorkspaceLocation = null;
        String switchWorkspaceApi = null;
        List<NdiScriptnfo> result = new ArrayList<NdiScriptnfo>();
        ArrayList<String> executorOptions = new ArrayList<>();
        NutsExecutionType execType = null;
        NutsArgument a;
        Runnable action = null;
        boolean ignoreUnsupportedOs = false;
        while (commandLine.hasNext()) {
            if ((a = commandLine.nextString("--ignore-unsupported-os")) != null) {
                if (a.isEnabled()) {
                    ignoreUnsupportedOs = a.getBooleanValue();
                }
            } else if (commandLine.peek().getStringKey().equals("-w") || commandLine.peek().getStringKey().equals("--workspace")) {
                switchWorkspaceLocation = commandLine.nextString().getStringValue();
            } else if (commandLine.peek().getStringKey().equals("-a") || commandLine.peek().getStringKey().equals("--api")) {
                switchWorkspaceApi = commandLine.nextString().getStringValue();
            } else if (commandLine.peek().isOption()) {
                commandLine.unexpectedArgument();
            } else if (switchWorkspaceLocation == null) {
                switchWorkspaceLocation = commandLine.next().getString();
            } else if (switchWorkspaceApi == null) {
                switchWorkspaceApi = commandLine.next().getString();
            } else if (commandLine.peek().isOption()) {
                context.configureLast(commandLine);
            } else {
                commandLine.unexpectedArgument();
            }
        }
        if (commandLine.isExecMode()) {
            SystemNdi ndi = createNdi(context);
            if (ndi == null) {
                if (ignoreUnsupportedOs) {
                    return;
                }
                throw new NutsExecutionException(ws, "platform not supported : " + ws.env().getOs(), 2);
            }
            if (switchWorkspaceLocation != null || switchWorkspaceApi != null) {
                ndi.switchWorkspace(switchWorkspaceLocation, switchWorkspaceApi);
            }
        }

    }

    private void printResults(NutsApplicationContext context, List<NdiScriptnfo> result) {
        NutsWorkspace ws = context.getWorkspace();
        if (context.getSession().isTrace()) {
            if (context.getSession().isPlainTrace()) {
                int namesSize = result.stream().mapToInt(x -> x.getName().length()).max().orElse(1);
                for (NdiScriptnfo ndiScriptnfo : result) {
                    context.getSession().out().printf("%s script %-" + namesSize + "s for "
                            + ws.id().formatter(ndiScriptnfo.getId().getLongNameId()).format()
                            + " at %s%n", ndiScriptnfo.isOverride()
                            ? ws.formats().text().styled("re-installing", NutsTextNodeStyle.success())
                            : ws.formats().text().styled("installing", NutsTextNodeStyle.success()),
                            ndiScriptnfo.getName(),
                            ws.formats().text().styled(NdiUtils.betterPath(ndiScriptnfo.getPath().toString()), NutsTextNodeStyle.path())
                    );
                }

            } else {
                context.getSession().formatObject(result).println();
            }
        }
    }

    @Override
    public boolean exec(NutsCommandLine cmdLine, Boolean autoSave, NutsApplicationContext context) {
        if (cmdLine.next("add script", "sca") != null) {
            runAddScript(cmdLine, context);
            return true;
        } else if (cmdLine.next("remove script", "scrm") != null) {
            runRemoveScript(cmdLine, context);
            return true;
        } else if (cmdLine.next("switch", "sw") != null) {
            runSwitch(cmdLine, context);
            return true;
        }
        return false;
    }
}
