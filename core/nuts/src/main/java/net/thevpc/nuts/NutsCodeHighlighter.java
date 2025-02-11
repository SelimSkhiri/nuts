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

import net.thevpc.nuts.spi.NutsComponent;
import net.thevpc.nuts.spi.NutsSupportLevelContext;
import net.thevpc.nuts.text.NutsText;
import net.thevpc.nuts.text.NutsTexts;

/**
 * Code Format to help formatting (syntax coloring) to NTF some code
 *
 * @author thevpc
 * @since 0.8.3
 */
public interface NutsCodeHighlighter extends NutsComponent {

    String getId();

    /**
     * evaluate support level (who much this instance should be considered convenient, acceptable)
     * for the given arguments (provided in context).
     *
     * @param context evaluation context
     * @return support level value
     */
    int getSupportLevel(NutsSupportLevelContext context);

    NutsText stringToText(String text, NutsTexts txt, NutsSession session);

    NutsText tokenToText(String text, String tokenType, NutsTexts txt, NutsSession session);
}
