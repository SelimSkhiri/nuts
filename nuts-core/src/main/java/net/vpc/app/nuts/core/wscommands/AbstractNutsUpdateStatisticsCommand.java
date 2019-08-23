/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.nuts.core.wscommands;

import net.vpc.app.nuts.*;

import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 *
 * @author vpc
 */
public abstract class AbstractNutsUpdateStatisticsCommand extends NutsWorkspaceCommandBase<NutsUpdateStatisticsCommand> implements NutsUpdateStatisticsCommand {

    private LinkedHashSet<Path> paths = new LinkedHashSet<>();
    private LinkedHashSet<String> repositrories = new LinkedHashSet<>();

    public AbstractNutsUpdateStatisticsCommand(NutsWorkspace ws) {
        super(ws, "update-statistics");
    }

    @Override
    public NutsUpdateStatisticsCommand clearRepos() {
        repositrories.clear();
        return this;
    }

    @Override
    public NutsUpdateStatisticsCommand repo(String s) {
        return addRepo(s);
    }

    @Override
    public NutsUpdateStatisticsCommand addRepo(String s) {
        if (s != null) {
            repositrories.add(s);
        }
        return this;
    }

    @Override
    public NutsUpdateStatisticsCommand removeRepo(String s) {
        if (s != null) {
            repositrories.remove(s);
        }
        return this;
    }

    @Override
    public NutsUpdateStatisticsCommand addRepos(String... all) {
        if (all != null) {
            for (String string : all) {
                addRepo(string);
            }
        }
        return this;
    }

    @Override
    public NutsUpdateStatisticsCommand addRepos(Collection<String> all) {
        if (all != null) {
            for (String string : all) {
                addRepo(string);
            }
        }
        return this;
    }

    public String[] getRepositrories() {
        return repositrories.toArray(new String[0]);
    }

    @Override
    public NutsUpdateStatisticsCommand clearPaths() {
        paths.clear();
        return this;
    }

    @Override
    public NutsUpdateStatisticsCommand path(Path s) {
        return addPath(s);
    }

    @Override
    public NutsUpdateStatisticsCommand addPath(Path s) {
        if (s != null) {
            paths.add(s);
        }
        return this;
    }

    @Override
    public NutsUpdateStatisticsCommand removePath(Path s) {
        if (s != null) {
            paths.remove(s);
        }
        return this;
    }

    @Override
    public NutsUpdateStatisticsCommand addPaths(Path... all) {
        if (all != null) {
            for (Path string : all) {
                addPath(string);
            }
        }
        return this;
    }

    @Override
    public NutsUpdateStatisticsCommand addPaths(Collection<Path> all) {
        if (all != null) {
            for (Path string : all) {
                addPath(string);
            }
        }
        return this;
    }

    public Path[] getPaths() {
        return paths.toArray(new Path[0]);
    }

    @Override
    public boolean configureFirst(NutsCommandLine cmdLine) {
        NutsArgument a = cmdLine.peek();
        if (a == null) {
            return false;
        }
        switch (a.getStringKey()) {
            default: {
                if (super.configureFirst(cmdLine)) {
                    return true;
                }
            }
        }
        return false;
    }
}
