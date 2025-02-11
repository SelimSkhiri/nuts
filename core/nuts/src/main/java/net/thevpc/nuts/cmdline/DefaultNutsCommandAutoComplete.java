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
package net.thevpc.nuts.cmdline;

import net.thevpc.nuts.NutsSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thevpc
 */
public class DefaultNutsCommandAutoComplete extends NutsCommandAutoCompleteBase {

    private NutsSession session;
    private List<String> words = new ArrayList<>();
    private int currentWordIndex;
    private String line;

    @Override
    public NutsSession getSession() {
        return session;
    }

    public DefaultNutsCommandAutoComplete setSession(NutsSession session) {
        this.session = session;
        return this;
    }

    @Override
    public String getLine() {
        return line;
    }

    @Override
    public List<String> getWords() {
        return words;
    }

    public DefaultNutsCommandAutoComplete setWords(List<String> words) {
        this.words = words;
        return this;
    }

    @Override
    public int getCurrentWordIndex() {
        return currentWordIndex;
    }

    public DefaultNutsCommandAutoComplete setCurrentWordIndex(int currentWordIndex) {
        this.currentWordIndex = currentWordIndex;
        return this;
    }

    public DefaultNutsCommandAutoComplete setLine(String line) {
        this.line = line;
        return this;
    }


}
