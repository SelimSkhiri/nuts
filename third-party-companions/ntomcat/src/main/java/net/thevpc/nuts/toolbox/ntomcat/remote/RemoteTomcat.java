package net.thevpc.nuts.toolbox.ntomcat.remote;

import net.thevpc.nuts.*;
import net.thevpc.nuts.cmdline.NutsArgument;
import net.thevpc.nuts.cmdline.NutsCommandLine;
import net.thevpc.nuts.io.NutsPath;
import net.thevpc.nuts.text.NutsTextStyle;
import net.thevpc.nuts.text.NutsTexts;
import net.thevpc.nuts.toolbox.ntomcat.NTomcatConfigVersions;
import net.thevpc.nuts.toolbox.ntomcat.remote.config.RemoteTomcatConfig;
import net.thevpc.nuts.toolbox.ntomcat.util.TomcatUtils;
import net.thevpc.nuts.util.NutsUnsafeFunction;

import java.util.ArrayList;
import java.util.List;

public class RemoteTomcat {

    public NutsApplicationContext context;
    public NutsCommandLine cmdLine;
    public NutsPath sharedConfigFolder;

    public RemoteTomcat(NutsApplicationContext applicationContext, NutsCommandLine cmdLine) {
        this.setContext(applicationContext);
        this.cmdLine = cmdLine;
        sharedConfigFolder = applicationContext.getVersionFolder(NutsStoreLocation.CONFIG, NTomcatConfigVersions.CURRENT);
    }

    public void runArgs() {
        NutsSession session = getContext().getSession();
        NutsArgument a;
        cmdLine.setCommandName("tomcat --remote");
        while (cmdLine.hasNext()) {
            if (cmdLine.isNextOption()) {
                context.configureLast(cmdLine);
            } else {
                if ((a = cmdLine.next("list").orNull()) != null) {
                    list(cmdLine);
                    return;
                } else if ((a = cmdLine.next("show").orNull()) != null) {
                    show(cmdLine);
                    return;
                } else if ((a = cmdLine.next("add", "set").orNull()) != null) {
                    add(cmdLine);
                    return;
                } else if ((a = cmdLine.next("remove").orNull()) != null) {
                    remove(cmdLine);
                    return;
                } else if ((a = cmdLine.next("start").orNull()) != null) {
                    restart(cmdLine, false);
                    return;
                } else if ((a = cmdLine.next("restart").orNull()) != null) {
                    restart(cmdLine, true);
                    return;
                } else if ((a = cmdLine.next("stop").orNull()) != null) {
                    stop(cmdLine);
                    return;
                } else if ((a = cmdLine.next("install").orNull()) != null) {
                    install(cmdLine);
                    return;
                } else if ((a = cmdLine.next("deploy").orNull()) != null) {
                    deploy(cmdLine);
                    return;
                } else if ((a = cmdLine.next("reset").orNull()) != null) {
                    reset(cmdLine);
                    return;
                } else {
                    cmdLine.setCommandName("tomcat --remote").throwUnexpectedArgument(session);
                }
            }
        }
        throw new NutsExecutionException(context.getSession(), NutsMessage.ofPlain("missing tomcat action. Type: nuts tomcat --help"), 1);
    }

    public void list(NutsCommandLine args) {
        NutsSession session = getContext().getSession();
        NutsArgument a;
        class Helper {

            boolean processed = false;

            void print(RemoteTomcatConfigService c) {
                processed = true;
                List<RemoteTomcatAppConfigService> apps = c.getApps();
                for (RemoteTomcatAppConfigService app : apps) {
                    context.getSession().out().printf("%s\n", app.getName());
                }
            }
        }
        Helper x = new Helper();
        while (args.hasNext()) {
            if ((a = args.nextString("--name").orNull()) != null) {
                x.print(loadOrCreateTomcatConfig(a.getStringValue().get(session)));
            } else if (args.peek().get(session).isNonOption()) {
                x.print(loadOrCreateTomcatConfig(args.nextNonOption().get(session).getStringValue().get(session)));
            } else {
                context.configureLast(args);
            }
        }
        if (!x.processed) {
            for (RemoteTomcatConfigService tomcatConfig : listConfig()) {
                getContext().getSession().out().println(tomcatConfig.getName());
            }
        }
    }

    private void add(NutsCommandLine args) {
        RemoteTomcatConfigService c = null;
        String appName = null;
        String instanceName = null;
        NutsArgument a;
        args.setCommandName("tomcat --remote add");
        NutsSession session = context.getSession();
        while (args.hasNext()) {
            if ((a = args.nextString("--name").orNull()) != null) {
                if (c == null) {
                    instanceName = a.getStringValue().get(session);
                    c = loadOrCreateTomcatConfig(instanceName);
                } else {
                    throw new NutsExecutionException(session, NutsMessage.ofPlain("instance already defined"), 2);
                }
            } else if ((a = args.nextString("--server").orNull()) != null) {
                if (c == null) {
                    c = loadOrCreateTomcatConfig(null);
                }
                c.getConfig().setServer(a.getStringValue().get(session));
            } else if ((a = args.nextString("--remote-temp-path").orNull()) != null) {
                if (c == null) {
                    c = loadOrCreateTomcatConfig(null);
                }
                c.getConfig().setRemoteTempPath(a.getStringValue().get(session));
            } else if ((a = args.nextString("--remote-instance").orNull()) != null) {
                String value = a.getStringValue().get(session);
                if (c == null) {
                    c = loadOrCreateTomcatConfig(null);
                }
                c.getConfig().setRemoteName(value);
            } else if ((a = args.nextString("--app").orNull()) != null) {
                appName = a.getStringValue().get(session);
                if (c == null) {
                    c = loadOrCreateTomcatConfig(null);
                }
                c.getAppOrCreate(appName);
            } else if ((a = args.nextString("--app.path").orNull()) != null) {
                String value = a.getStringValue().get(session);
                if (c == null) {
                    c = loadOrCreateTomcatConfig(null);
                }
                RemoteTomcatAppConfigService tomcatAppConfig = c.getAppOrError(appName);
                tomcatAppConfig.getConfig().setPath(value);
            } else if ((a = args.nextString("--app.version").orNull()) != null) {
                String value = a.getStringValue().get(session);
                if (c == null) {
                    c = loadOrCreateTomcatConfig(null);
                }
                RemoteTomcatAppConfigService tomcatAppConfig = c.getAppOrError(appName);
                tomcatAppConfig.getConfig().setVersionCommand(value);
            } else {
                context.configureLast(args);
            }
        }
        if (c == null) {
            c = loadOrCreateTomcatConfig(null);
        }
        boolean ok = false;
        NutsTexts text = NutsTexts.of(getContext().getSession());
        while (!ok) {
            try {
                ok = true;
                if (NutsBlankable.isBlank(c.getConfig().getServer())) {
                    ok = false;
                    c.getConfig().setServer(session.getTerminal()
                            .ask()
                            .forString(
                                    NutsMessage.ofCstyle("[instance=%s] would you enter %s value ?"
                                            , text.ofStyled(c.getName(), NutsTextStyle.primary1())
                                            , text.ofStyled("--server", NutsTextStyle.option())
                                    )
                            )
                            .setDefaultValue("ssh://login@myserver/instanceName")
                            .getValue()
                    );
                }
                if (NutsBlankable.isBlank(c.getConfig().getRemoteTempPath())) {
                    ok = false;
                    c.getConfig()
                            .setRemoteTempPath(session.getTerminal().ask()
                                    .resetLine()
                                    .forString(NutsMessage.ofCstyle("[instance=%s] would you enter %s value ?"
                                            , text.ofStyled(c.getName(), NutsTextStyle.primary1())
                                            , text.ofStyled("--remote-temp-path", NutsTextStyle.option())
                                    )).setDefaultValue("/tmp")
                                    .getValue()
                            );
                }
                for (RemoteTomcatAppConfigService aa : c.getApps()) {
                    if (NutsBlankable.isBlank(aa.getConfig().getPath())) {
                        ok = false;
                        aa.getConfig().setPath(session.getTerminal().ask()
                                .resetLine()
                                .forString(NutsMessage.ofCstyle("[instance=%s] [app=%s] would you enter %s value ?"
                                        , text.ofStyled(c.getName(), NutsTextStyle.primary1())
                                        , text.ofStyled(aa.getName(), NutsTextStyle.option())
                                        , text.ofStyled("--app.path", NutsTextStyle.option())
                                ))
                                .getValue());
                    }
                }
            } catch (NutsCancelException ex) {
                throw new NutsExecutionException(session, NutsMessage.ofPlain("cancelled"), 1);
            }
        }
        c.save();
    }

    public void remove(NutsCommandLine args) {
        RemoteTomcatServiceBase s = null;
        NutsArgument a;
        boolean processed = false;
        int lastExitCode = 0;
        args.setCommandName("tomcat --remote remove");
        while (args.hasNext()) {
            if ((s = readBaseServiceArg(args)) != null) {
                s.remove();
                if (!(s instanceof RemoteTomcatConfigService)) {
                    toRemoteTomcatConfigService(s).save();
                }
                processed = true;
                lastExitCode = 0;
            } else {
                context.configureLast(args);
            }
        }
        if (!processed) {
            throw new NutsExecutionException(context.getSession(), NutsMessage.ofPlain("invalid parameters"), 2);
        }
        if (lastExitCode != 0) {
            throw new NutsExecutionException(context.getSession(), NutsMessage.ofPlain("tomcat remove failed"), lastExitCode);
        }
    }

    private void install(NutsCommandLine args) {
        NutsSession session = getContext().getSession();
        String conf = null;
        String app = null;
        NutsArgument a;
        args.setCommandName("tomcat --remote install");
        while (args.hasNext()) {
            if ((a = args.nextString("--app").orNull()) != null) {
                loadApp(a.getStringValue().get(session)).install();
            } else {
                context.configureLast(args);
            }
        }
    }

    private void deploy(NutsCommandLine args) {
        NutsSession session = getContext().getSession();
        String app = null;
        String version = null;
        NutsArgument a;
        args.setCommandName("tomcat --remote deploy");
        while (args.hasNext()) {
            if ((a = args.nextString("--app").orNull()) != null) {
                app = a.getStringValue().get(session);
            } else if ((a = args.nextString("--version").orNull()) != null) {
                version = a.getStringValue().get(session);
            } else {
                context.configureLast(args);
            }
        }
        loadApp(app).deploy(version);
    }

    private void stop(NutsCommandLine args) {
        NutsSession session = getContext().getSession();
        String name = null;
        NutsArgument a;
        while (args.hasNext()) {
            if (args.peek().get(session).isNonOption()) {
                name = args.nextNonOption().flatMap(NutsValue::asString).get(session);
                RemoteTomcatConfigService c = loadTomcatConfig(name);
                c.shutdown();
            } else {
                context.configureLast(args);
            }
        }
    }

    public void restart(NutsCommandLine args, boolean shutdown) {
        NutsSession session = context.getSession();
        String instance = null;
        boolean deleteLog = false;
        boolean install = false;
        List<String> apps = new ArrayList<>();
        NutsArgument a;
        while (args.hasNext()) {
            if ((a = args.nextBoolean("--deleteLog").orNull()) != null) {
                deleteLog = a.getBooleanValue().get(session);
            } else if ((a = args.nextBoolean("--install").orNull()) != null) {
                install = a.getBooleanValue().get(session);
            } else if ((a = args.nextString("--name").orNull()) != null) {
                instance = a.getStringValue().get(session);
            } else if ((a = args.nextString("--deploy").orNull()) != null) {
                for (String s : a.getStringValue().get(session).split(",")) {
                    s = s.trim();
                    if (!NutsBlankable.isBlank(s)) {
                        apps.add(s);
                    }
                }
//            } else if ((a = args.nextNonOption(DefaultNonOption.NAME)) != null) {
//                instance = a.getString();
            } else if (args.peek().get(session).isNonOption()) {
                instance = args.nextNonOption().get(session).getStringValue().get(session);
            } else {
                context.configureLast(args);
            }
        }
        if (install) {
            for (String app : apps) {
                install(NutsCommandLine.of(
                        new String[]{
                                "--name",
                                instance,
                                "--app",
                                app
                        }
                ));
            }
        }
        RemoteTomcatConfigService c = loadTomcatConfig(instance);
        if (shutdown) {
            c.restart(apps.toArray(new String[0]), deleteLog);
        } else {
            c.start(apps.toArray(new String[0]), deleteLog);
        }
    }

    public void reset(NutsCommandLine args) {
        NutsArgument a;
        args.setCommandName("tomcat --remote reset");
        while (args.hasNext()) {
            context.configureLast(args);
        }
        for (RemoteTomcatConfigService tomcatConfig : listConfig()) {
            tomcatConfig.remove();
        }
    }

    public RemoteTomcatConfigService[] listConfig() {
        return
                sharedConfigFolder.list().filter(
                                pathname -> pathname.isRegularFile() && pathname.getName().endsWith(RemoteTomcatConfigService.REMOTE_CONFIG_EXT),
                                "isRegularFile() && matches(*" + RemoteTomcatConfigService.REMOTE_CONFIG_EXT + ")"
                        )
                        .mapUnsafe(
                                NutsUnsafeFunction.of(x -> loadTomcatConfig(x), "loadTomcatConfig")
                                , null)
                        .filterNonNull()
                        .toArray(RemoteTomcatConfigService[]::new);
    }

    public void show(NutsCommandLine args) {
        NutsSession session = context.getSession();
        NutsArgument a;
        RemoteTomcatServiceBase s;
        class Helper {

            boolean json = false;

            public void show(RemoteTomcatServiceBase aa) {
                NutsSession session = getContext().getSession();
                if (json) {
                    session.out().printf("%s :\n", NutsTexts.of(session).ofStyled(aa.getName(), NutsTextStyle.primary4()));
                    aa.println(session.out());
                } else {
                    session.out().printf("%s :\n", NutsTexts.of(session).ofStyled(aa.getName(), NutsTextStyle.primary4()));
                    aa.println(session.out());
                }
            }
        }
        Helper h = new Helper();
        args.setCommandName("tomcat --remote show");
        while (args.hasNext()) {
            if ((a = args.nextBoolean("--json").orNull()) != null) {
                h.json = a.getBooleanValue().get(session);
            } else if ((s = readBaseServiceArg(args)) != null) {
                h.show(s);
            } else {
                context.configureLast(args);
            }
        }
    }

    public RemoteTomcatConfigService loadTomcatConfig(String name) {
        RemoteTomcatConfigService t = new RemoteTomcatConfigService(name, this);
        t.loadConfig();
        return t;
    }

    public RemoteTomcatConfigService loadTomcatConfig(NutsPath name) {
        RemoteTomcatConfigService t = new RemoteTomcatConfigService(name, this);
        t.loadConfig();
        return t;
    }

    public RemoteTomcatConfigService createTomcatConfig(String name) {
        RemoteTomcatConfigService t = new RemoteTomcatConfigService(name, this);
        t.setConfig(new RemoteTomcatConfig());
        return t;
    }

    public RemoteTomcatConfigService loadOrCreateTomcatConfig(String name) {
        RemoteTomcatConfigService t = new RemoteTomcatConfigService(name, this);
        if (t.existsConfig()) {
            t.loadConfig();
        } else {
            t.setConfig(new RemoteTomcatConfig());
        }
        return t;
    }

    public RemoteTomcatServiceBase loadServiceBase(String name) {
        String[] strings = TomcatUtils.splitInstanceAppPreferInstance(name);
        if (strings[1].isEmpty()) {
            return loadOrCreateTomcatConfig(strings[0]);
        } else {
            RemoteTomcatConfigService u = loadOrCreateTomcatConfig(strings[0]);
            RemoteTomcatAppConfigService a = u.getAppOrNull(strings[1]);
            if (a == null) {
                throw new NutsExecutionException(context.getSession(), NutsMessage.ofCstyle("unknown name %s. it is no domain or app", name), 3);
            }
            return a;
        }
    }

    public RemoteTomcatServiceBase readBaseServiceArg(NutsCommandLine args) {
        NutsSession session = context.getSession();
        NutsArgument a;
        if ((a = args.nextString("--name").orNull()) != null) {
            return (loadOrCreateTomcatConfig(a.getStringValue().get(session)));
        } else if ((a = args.nextString("--app").orNull()) != null) {
            return (loadApp(a.getStringValue().get(session)));
        } else if (args.hasNext() && args.isNextOption()) {
            return null;
        } else {
            return (loadServiceBase(args.next().flatMap(NutsValue::asString).get(session)));
        }
    }

    public RemoteTomcatAppConfigService loadApp(String name) {
        String[] strings = TomcatUtils.splitInstanceAppPreferApp(name);
        return loadOrCreateTomcatConfig(strings[0]).getApp(strings[1]);
    }

    public NutsApplicationContext getContext() {
        return context;
    }

    public void setContext(NutsApplicationContext context) {
        this.context = context;
    }

    public RemoteTomcatConfigService toRemoteTomcatConfigService(RemoteTomcatServiceBase s) {
        if (s instanceof RemoteTomcatAppConfigService) {
            s = ((RemoteTomcatAppConfigService) s).getTomcat();
        }
        return ((RemoteTomcatConfigService) s);
    }

}
