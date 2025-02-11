/**
 * ====================================================================
 *            Nuts : Network Updatable Things Service
 *                  (universal package manager)
 * <br>
 * is a new Open Source Package Manager to help install packages and libraries
 * for runtime execution. Nuts is the ultimate companion for maven (and other
 * build managers) as it helps installing all package dependencies at runtime.
 * Nuts is not tied to java and is a good choice to share shell scripts and
 * other 'things' . Its based on an extensible architecture to help supporting a
 * large range of sub managers / repositories.
 * <br>
 *
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
package net.thevpc.nuts.runtime.standalone.util.reflect;

import net.thevpc.nuts.NutsSession;
import net.thevpc.nuts.spi.NutsSupportLevelContext;
import net.thevpc.nuts.util.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thevpc
 */
public class DefaultNutsReflectRepository implements NutsReflectRepository {

    private final Map<Type, NutsReflectType> beans = new HashMap<>();
    private NutsReflectConfiguration configuration;

    public DefaultNutsReflectRepository(NutsSession session) {
        this(NutsReflectConfigurationBuilder.of(session).build());
    }

    public DefaultNutsReflectRepository(NutsReflectConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public DefaultNutsReflectRepository setConfiguration(NutsReflectConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    @Override
    public NutsReflectConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public NutsReflectType getType(Type clz) {
        NutsReflectType v = beans.get(clz);
        if (v == null) {
            v = create(clz);
            beans.put(clz, v);
        }
        return v;
    }

    private NutsReflectType create(Type clz) {
        Class raw = ReflectUtils.getRawClass(clz);
        NutsReflectPropertyAccessStrategy a = configuration.getAccessStrategy(raw);
        NutsReflectPropertyDefaultValueStrategy d = configuration.getDefaultValueStrategy(raw);
        return new ClassNutsReflectType(clz, a, d, this);
    }

    @Override
    public int getSupportLevel(NutsSupportLevelContext context) {
        return DEFAULT_SUPPORT;
    }
}
