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
 * <p>
 * Copyright [2020] [thevpc] Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * <br> ====================================================================
 */
package net.thevpc.nuts;

import net.thevpc.nuts.boot.DefaultNutsWorkspaceOptionsBuilder;
import net.thevpc.nuts.boot.NutsBootWorkspace;
import net.thevpc.nuts.cmdline.NutsCommandLine;
import net.thevpc.nuts.reserved.NutsReservedBootLog;
import net.thevpc.nuts.util.NutsApiUtils;
import net.thevpc.nuts.util.NutsStringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Nuts Top Class. Nuts is a Package manager for Java Applications and this
 * class is it's main class for creating and opening nuts workspaces.
 *
 * @author thevpc
 * @app.category Base
 * @since 0.1.0
 */
public final class Nuts {

    /**
     * current Nuts version
     */
    public static NutsVersion version;

    /**
     * private constructor
     */
    private Nuts() {
    }

    /**
     * current nuts version, loaded from pom file
     *
     * @return current nuts version
     */
    public static NutsVersion getVersion() {
        if (version == null) {
            synchronized (Nuts.class) {
                if (version == null) {
                    String v = NutsApiUtils.resolveNutsVersionFromClassPath(new NutsReservedBootLog(null));
                    if (v == null) {
                        throw new NutsBootException(
                                NutsMessage.ofPlain(
                                        "unable to detect nuts version. Most likely you are missing valid compilation of nuts. pom.properties could not be resolved and hence, we are unable to resolve nuts version."
                                )
                        );
                    }
                    version = NutsVersion.of(v).get();
                }
            }
        }
        return version;
    }

    /**
     * main method. This Main will call
     * {@link Nuts#runWorkspace(java.lang.String...)} then
     * {@link System#exit(int)} at completion
     *
     * @param args main arguments
     */
    @SuppressWarnings("UseSpecificCatch")
    public static void main(String[] args) {
        try {
            runWorkspace(args);
            System.exit(0);
        } catch (Exception ex) {
            NutsSession session = NutsSessionAwareExceptionBase.resolveSession(ex).orNull();
            if (session != null) {
                System.exit(NutsApplicationExceptionHandler.of(session)
                        .processThrowable(args, ex, session));
            } else {
                System.exit(NutsApiUtils.processThrowable(ex, args));
            }
        }
    }

    /**
     * open a workspace using "nuts.boot.args" and "nut.args" system
     * properties. "nuts.boot.args" is to be passed by nuts parent process.
     * "nuts.args" is an optional property that can be 'exec' method. This
     * method is to be called by child processes of nuts in order to inherit
     * workspace configuration.
     *
     * @param overriddenNutsArgs nuts arguments to override inherited arguments
     * @param appArgs application arguments
     * @return NutsSession instance
     */
    public static NutsSession openInheritedWorkspace(String[] overriddenNutsArgs, String... appArgs) throws NutsUnsatisfiedRequirementsException {
        return openInheritedWorkspace(null, overriddenNutsArgs,appArgs);
    }

    /**
     * open a workspace using "nuts.boot.args" and "nut.args" system
     * properties. "nuts.boot.args" is to be passed by nuts parent process.
     * "nuts.args" is an optional property that can be 'exec' method. This
     * method is to be called by child processes of nuts in order to inherit
     * workspace configuration.
     *
     * @param term boot terminal or null for defaults
     * @param overriddenNutsArgs nuts arguments to override inherited arguments
     * @param appArgs arguments
     * @return NutsSession instance
     */
    public static NutsSession openInheritedWorkspace(NutsWorkspaceTerminalOptions term, String[] overriddenNutsArgs, String... appArgs) throws NutsUnsatisfiedRequirementsException {
        Instant startTime = Instant.now();
        List<String> nutsArgs = new ArrayList<>();
        nutsArgs.addAll(NutsCommandLine.parseDefault(NutsStringUtils.trim(System.getProperty("nuts.boot.args"))).get().toStringList());
        nutsArgs.addAll(NutsCommandLine.parseDefault(NutsStringUtils.trim(System.getProperty("nuts.args"))).get().toStringList());
        if (overriddenNutsArgs != null) {
            nutsArgs.addAll(Arrays.asList(overriddenNutsArgs));
        }
        NutsWorkspaceOptionsBuilder options = new DefaultNutsWorkspaceOptionsBuilder();
        options.setCommandLine(nutsArgs.toArray(new String[0]),null);
        if (options.getApplicationArguments().isNotPresent()) {
            options.setApplicationArguments(new ArrayList<>());
        }
        options.getApplicationArguments().get().addAll(Arrays.asList(appArgs));
        options.setApplicationArguments(Arrays.asList(appArgs));
        options.setInherited(true);
        options.setCreationTime(startTime);
        if (term != null) {
            options.setStdin(term.getIn());
            options.setStdout(term.getOut());
            options.setStderr(term.getErr());
        }
        return new NutsBootWorkspace(options).openWorkspace();
    }

    /**
     * open a workspace. Nuts Boot arguments are passed in <code>args</code>
     *
     * @param args nuts boot arguments
     * @return new NutsSession instance
     */
    public static NutsSession openWorkspace(String... args) throws NutsUnsatisfiedRequirementsException {
        return new NutsBootWorkspace(null, args).openWorkspace();
    }

    /**
     * open a workspace. Nuts Boot arguments are passed in <code>args</code>
     *
     * @param term boot terminal or null for null
     * @param args nuts boot arguments
     * @return new NutsSession instance
     */
    public static NutsSession openWorkspace(NutsWorkspaceTerminalOptions term, String... args) throws NutsUnsatisfiedRequirementsException {
        return new NutsBootWorkspace(term, args).openWorkspace();
    }

    /**
     * open default workspace (no boot options)
     *
     * @return new NutsSession instance
     */
    public static NutsSession openWorkspace() {
        return openWorkspace((NutsWorkspaceOptions) null);
    }

    /**
     * open a workspace using the given options
     *
     * @param options boot options
     * @return new NutsSession instance
     */
    public static NutsSession openWorkspace(NutsWorkspaceOptions options) {
        return new NutsBootWorkspace(options).openWorkspace();
    }

    /**
     * open then run Nuts application with the provided arguments. This Main
     * will <strong>NEVER</strong> call {@link System#exit(int)}.
     * Not that if --help or --version are detected in the command line arguments
     * the workspace will not be opened and a null session is returned after displaying
     * help/version information on the standard
     *
     * @param term boot terminal or null for defaults
     * @param args boot arguments
     * @return session
     */
    public static NutsSession runWorkspace(NutsWorkspaceTerminalOptions term, String... args) throws NutsExecutionException {
        return new NutsBootWorkspace(term, args).runWorkspace();
    }

    /**
     * open then run Nuts application with the provided arguments. This Main
     * will <strong>NEVER</strong> call {@link System#exit(int)}.
     * Not that if --help or --version are detected in the command line arguments
     * the workspace will not be opened and a null session is returned after displaying
     * help/version information on the standard
     *
     * @param args boot arguments
     * @return session
     */
    public static NutsSession runWorkspace(String... args) throws NutsExecutionException {
        return new NutsBootWorkspace(null, args).runWorkspace();
    }
}
