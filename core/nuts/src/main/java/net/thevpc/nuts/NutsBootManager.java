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

import net.thevpc.nuts.boot.NutsClassLoaderNode;
import net.thevpc.nuts.boot.NutsWorkspaceBootOptions;
import net.thevpc.nuts.util.NutsUtils;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * @author thevpc
 */
public interface NutsBootManager {

    static NutsBootManager of(NutsSession session) {
        NutsUtils.requireSession(session);
        return session.boot();
    }

    NutsSession getSession();

    NutsBootManager setSession(NutsSession session);

    /**
     * return true when this is a first boot of the workspace (just installed!)
     * @return true when this is a first boot of the workspace (just installed!)
     */
    boolean isFirstBoot();

    NutsOptional<NutsValue> getCustomBootOption(String... names);

    NutsWorkspaceBootOptions getBootOptions();

    ClassLoader getBootClassLoader();

    List<URL> getBootClassWorldURLs();

    String getBootRepositories();

    Instant getCreationStartTime();

    Instant getCreationFinishTime();

    Duration getCreationDuration();


    NutsClassLoaderNode getBootRuntimeClassLoaderNode();

    List<NutsClassLoaderNode> getBootExtensionClassLoaderNode();

    NutsWorkspaceTerminalOptions getBootTerminal();
}
