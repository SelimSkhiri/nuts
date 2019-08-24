package net.vpc.app.nuts.toolbox.ndi;

import java.io.*;

import net.vpc.app.nuts.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AnyNixNdi extends BaseSystemNdi {

    private static final Logger LOG = Logger.getLogger(AnyNixNdi.class.getName());

    public AnyNixNdi(NutsApplicationContext appContext) {
        super(appContext);
    }

    public String createNutsScriptCommand(NutsId fnutsId, NdiScriptOptions options) {
        StringBuilder command = new StringBuilder();
        command.append(getExecFileName("nuts")).append(" ");
        if (options.getExecType() != null) {
            command.append("--").append(options.getExecType().id());
        }
        command.append(" \"").append(fnutsId).append("\"");
        command.append(" \"$@\"");
        return command.toString();
    }


    public String toCommentLine(String line) {
        return "# " + line;
    }

    @Override
    protected String getCallScriptCommand(String path) {
        return "source \"" + path + "\"";
    }

    @Override
    public void configurePath(NutsSession session) throws IOException {
        Path ndiAppsFolder = context.getAppsFolder();
        final NutsWorkspace ws = context.getWorkspace();
        NutsWorkspaceConfigManager wsconfig = ws.config();
        Path apiAppsFolder = wsconfig.getStoreLocation(wsconfig.getApiId(), NutsStoreLocation.APPS);
        Path bashrcFile = Paths.get(System.getProperty("user.home")).resolve(".bashrc");
        Path apiConfigFile = apiAppsFolder.resolve(getExecFileName(".nuts-bashrc"));
        Path ndiConfigFile = ndiAppsFolder.resolve(getExecFileName(".ndi-bashrc"));
        List<String> updatedNames = new ArrayList<>();
        boolean force = session.isYes();
        removeFileLine(bashrcFile, "net.vpc.app.nuts.toolbox.ndi configuration", force);
        if (addFileLine(bashrcFile, "net.vpc.app.nuts configuration",
                getCallScriptCommand(apiConfigFile.toString()),
                force)) {
            updatedNames.add("~/.bashrc");
        }

        if (addFileLine(apiConfigFile, "net.vpc.app.nuts.toolbox.ndi configuration",
                getCallScriptCommand(ndiConfigFile.toString()),
                force)) {
            updatedNames.add(".nuts-bashrc");

        }

        String goodNdiRc = "# This File is generated by nuts ndi companion tool.\n" +
                "# Do not edit it manually. All changes will be lost when ndi runs again\n" +
                "# This file aims to prepare bash environment against current nuts\n" +
                "# workspace installation.\n" +
                "#\n" +
                "NUTS_VERSION='" + wsconfig.getApiVersion() + "'\n" +
                "NUTS_JAR='" + ws.search()
                .session(context.getSession().copy().trace(false))
                .id(wsconfig.getApiId()).getResultPaths().required() +
                "'\n" +
                "NUTS_WORKSPACE='" + wsconfig.getWorkspaceLocation().toString() + "'\n" +
                "[[ \":$PATH:\" != *\":" + ndiAppsFolder + ":\"* ]] && PATH=\"" + ndiAppsFolder + ":${PATH}\"\n" +
                "export PATH NUTS_VERSION NUTS_JAR NUTS_WORKSPACE \n";
        if (saveFile(ndiConfigFile, goodNdiRc, force)) {
            updatedNames.add(".ndi-bashrc");
        }

        if (!updatedNames.isEmpty() && session.isTrace()) {
            if (!updatedNames.isEmpty()) {
                if (context.getSession().isPlainTrace()) {
                    context.session().out().printf((context.getSession().isPlainTrace() ? "force " : "") + "updating ==%s== to point to workspace ==%s==%n",
                            String.join(", ", updatedNames)
                            , wsconfig.getWorkspaceLocation());
                }
                context.session().terminal().ask()
                        .forBoolean(
                                "@@ATTENTION@@ You may need to re-run terminal or issue \\\"==%s==\\\" in your current terminal for new environment to take effect.%n"
                                        + "Please type 'ok' if you agree, 'why' if you need more explanation or 'cancel' to cancel updates.",
                                ". ~/.bashrc"
                        )
                        .hintMessage("")
                        .session(context.getSession())
                        .parser(new NutsQuestionParser<Boolean>() {
                            @Override
                            public Boolean parse(Object response, Boolean defaultValue, NutsQuestion<Boolean> question) {
                                if (response instanceof Boolean) {
                                    return (Boolean) response;
                                }
                                if (response == null || ((response instanceof String) && response.toString().length() == 0)) {
                                    response = defaultValue;
                                }
                                if (response == null) {
                                    throw new NutsValidationException(ws, "Sorry... but you need to type 'ok', 'why' or 'cancel'");
                                }
                                String r = response.toString();
                                if ("ok".equalsIgnoreCase(r)) {
                                    return true;
                                }
                                if ("why".equalsIgnoreCase(r)) {
                                    PrintStream out = context.session().out();
                                    out.printf("\\\"==%s==\\\" is a special file in your home that is invoked upon each interactive terminal launch.%n", ".bashrc");
                                    out.print("It helps configuring environment variables. ==Nuts== make usage of such facility to update your **PATH** env variable\n");
                                    out.print("to point to current ==Nuts== workspace, so that when you call a ==Nuts== command it will be resolved correctly...\n");
                                    out.printf("However updating \\\"==%s==\\\" does not affect the running process/terminal. So you have basically two choices :%n", ".bashrc");
                                    out.print(" - Either to restart the process/terminal (konsole, term, xterm, sh, bash, ...)%n");
                                    out.printf(" - Or to run by your self the \\\"==%s==\\\" script (don\\'t forget the leading dot)%n", ". ~/.bashrc");
                                    throw new NutsValidationException(ws, "Try again...'");
                                } else if ("cancel".equalsIgnoreCase(r) || "cancel!".equalsIgnoreCase(r)) {
                                    throw new NutsUserCancelException(ws);
                                } else {
                                    throw new NutsValidationException(ws, "Sorry... but you need to type 'ok', 'why' or 'cancel'");
                                }
                            }
                        })
                        .getValue();
            }
        }
    }

    public String getExecFileName(String name) {
        return name;
    }

    protected String getTemplateBodyName() {
        return "linux_template_body.text";
    }

    protected String getTemplateNutsName() {
        return "linux_template_nuts.text";
    }

}
