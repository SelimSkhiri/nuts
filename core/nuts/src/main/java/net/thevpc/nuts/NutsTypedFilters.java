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
package net.thevpc.nuts;

import net.thevpc.nuts.spi.NutsComponent;
import net.thevpc.nuts.spi.NutsComponentScope;
import net.thevpc.nuts.spi.NutsComponentScopeType;

/**
 * Filters helper
 *
 * @author thevpc
 * @app.category Config
 * @since 0.8.0
 */
@NutsComponentScope(NutsComponentScopeType.SESSION)
public interface NutsTypedFilters<T extends NutsFilter> extends NutsComponent<Object> {
    T nonnull(NutsFilter filter);

    T always();

    T never();

    T all(NutsFilter... others);

    T any(NutsFilter... others);

    T not(NutsFilter other);

    T none(NutsFilter... others);

    T from(NutsFilter a);

    T as(NutsFilter a);

    T parse(String expression);
}
