/**
 * ====================================================================
 * Nuts : Network Updatable Things Service
 * (universal package manager)
 * <br>
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 *
 * <br>
 * <p>
 * Copyright [2020] [thevpc]
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * <br>
 * ====================================================================
 */
package net.thevpc.nuts.io;

import net.thevpc.nuts.NutsSession;
import net.thevpc.nuts.spi.NutsComponent;
import net.thevpc.nuts.spi.NutsSystemTerminalBase;
import net.thevpc.nuts.util.NutsUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Path;

public interface NutsIO extends NutsComponent {
    static NutsIO of(NutsSession session) {
        NutsUtils.requireSession(session);
        return session.extensions().createSupported(NutsIO.class, true, session);
    }

    static InputStream ofNullInputStream(NutsSession session) {
        return of(session).ofNullInputStream();
    }

    InputStream ofNullInputStream();

    boolean isStdin(InputStream in);

    InputStream stdin();

    NutsPrintStream createNullPrintStream();

    NutsMemoryPrintStream createInMemoryPrintStream();

    /**
     * create print stream that supports the given {@code mode}. If the given
     * {@code out} is a PrintStream that supports {@code mode}, it should be
     * returned without modification.
     *
     * @param out      stream to wrap
     * @param mode     mode to support
     * @param terminal terminal
     * @return {@code mode} supporting PrintStream
     */
    NutsPrintStream createPrintStream(OutputStream out, NutsTerminalMode mode, NutsSystemTerminalBase terminal);

    NutsPrintStream createPrintStream(OutputStream out);

    NutsPrintStream createPrintStream(Writer out, NutsTerminalMode mode, NutsSystemTerminalBase terminal);

    NutsPrintStream createPrintStream(Writer out);

    boolean isStdout(NutsPrintStream out);

    boolean isStderr(NutsPrintStream out);

    NutsPrintStream stdout();

    NutsPrintStream stderr();

    NutsInputSource createMultiRead(NutsInputSource source);

    NutsInputSource createInputSource(InputStream inputStream);
    NutsInputSource createInputSource(InputStream inputStream, NutsInputSourceMetadata metadata);
    NutsInputSource createInputSource(byte[] inputStream);

    NutsInputSource createInputSource(byte[] inputStream, NutsInputSourceMetadata metadata);

    NutsOutputTarget createOutputTarget(OutputStream inputStream);

    NutsOutputTarget createOutputTarget(OutputStream inputStream, NutsOutputTargetMetadata metadata);


}
