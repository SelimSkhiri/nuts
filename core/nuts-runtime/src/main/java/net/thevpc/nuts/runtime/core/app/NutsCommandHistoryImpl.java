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
package net.thevpc.nuts.runtime.core.app;

import net.thevpc.nuts.*;
import net.thevpc.nuts.spi.NutsSupportLevelContext;

import java.io.*;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author vpc
 */
public class NutsCommandHistoryImpl implements NutsCommandHistory {

    private NutsPath path;
    private final NutsSession session;
    private final List<NutsCommandHistoryEntry> entries = new ArrayList<>();

    public NutsCommandHistoryImpl(NutsSession session) {
        this.session = session;
    }

    @Override
    public void load() {
        entries.clear();
        if (path == null) {
            throw new NutsIllegalArgumentException(session, NutsMessage.plain("missing path"));
        }
        if (path.exists()) {
            try (InputStream in = path.getInputStream()) {
                load(in);
            } catch (IOException ex) {
                throw new NutsIOException(session, ex);
            }
        }
    }

    @Override
    public void save() {
        if (path == null) {
            throw new NutsIllegalArgumentException(session, NutsMessage.plain("missing path"));
        }
        NutsPath p = path.getParent();
        if (p != null) {
            p.mkdir(true);
        }
        try (OutputStream out = path.getOutputStream()) {
            save(out);
        } catch (IOException ex) {
            throw new NutsIOException(session, ex);
        }
    }

    @Override
    public void load(InputStream in) {
        entries.clear();
        if (in != null) {
            try (BufferedReader out = new BufferedReader(new InputStreamReader(in))) {
                String line = null;
                Instant instant = null;
                int index = 0;
                while ((line = out.readLine()) != null) {
                    if (line.length() > 0) {
                        if (line.startsWith("#")) {
                            if (line.startsWith("#at:")) {
                                instant = Instant.parse(line.substring("#at:".length()).trim());
                            }
                        } else {
                            entries.add(new NutsCommandHistoryEntryImpl(index, line, instant));
                        }
                    }
                }
            } catch (IOException ex) {
                throw new NutsIOException(session, ex);
            }
        }
    }

    @Override
    public void save(OutputStream outs) {
        try (PrintStream out = new PrintStream(outs)) {
            for (NutsCommandHistoryEntry entry : entries) {
                out.println("#at:" + entry.getTime().toString());
                out.println(entry.getLine().replace("\n", "\\n").replace("\r", "\\r"));
            }
        }
    }

    @Override
    public NutsCommandHistory setPath(Path path) {
        this.path = path == null ? null : NutsPath.of(path, session);
        return this;
    }

    @Override
    public NutsCommandHistory setPath(File path) {
        this.path = path == null ? null : NutsPath.of(path, session);
        return this;
    }

    @Override
    public NutsCommandHistory setPath(NutsPath path) {
        this.path = path;
        return this;
    }

    @Override
    public NutsPath getPath() {
        return path;
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public void purge() {
        entries.clear();
        if (path.exists()) {
            path.delete();
        }
    }

    @Override
    public NutsCommandHistoryEntry getEntry(int index) {
        return entries.get(index);
    }

    @Override
    public ListIterator<NutsCommandHistoryEntry> iterator(int index) {
        return entries.listIterator(index);
    }

    @Override
    public void add(Instant time, String line) {
        entries.add(new NutsCommandHistoryEntryImpl(entries.size(), line, time));
    }

    @Override
    public int getSupportLevel(NutsSupportLevelContext context) {
        return DEFAULT_SUPPORT;
    }
}
