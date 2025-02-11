/**
 * ====================================================================
 *            Nuts : Network Updatable Things Service
 *                  (universal package manager)
 * <br>
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 * <br>
 *
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
package net.thevpc.nuts.runtime.standalone.util.filters;

import java.util.Objects;
import net.thevpc.nuts.NutsId;
import net.thevpc.nuts.NutsIdFilter;
import net.thevpc.nuts.util.NutsPredicates;
import net.thevpc.nuts.NutsSession;

/**
 *
 * @author thevpc
 */
public class NutsIdFilterToPredicate extends NutsPredicates.BasePredicate<NutsId> {
    
    private final NutsIdFilter ff;
    private final NutsSession session;

    public NutsIdFilterToPredicate(NutsIdFilter ff, NutsSession session) {
        this.ff = ff;
        this.session = session;
    }

    @Override
    public boolean test(NutsId x) {
        return ff.acceptId(x, session);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutsIdFilterToPredicate that = (NutsIdFilterToPredicate) o;
        return Objects.equals(ff, that.ff) && Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ff, session);
    }

    @Override
    public String toString() {
        return ff.toString();
    }
}
