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
package net.thevpc.nuts.util;

import net.thevpc.nuts.NutsMessage;
import net.thevpc.nuts.NutsOptional;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * implementations of any class T must implement 3 static methods :
 *
 * <ul>
 * <li> {@code static T parseLenient(String value) } </li>
 *
 * <li> {@code static T parseLenient(String value, T emptyValue) } </li>
 *
 * <li> {@code static T parseLenient(String value, T emptyValue, T errorValue) } </li>
 * </ul>
 *
 * @since 0.8.1
 */
public interface NutsEnum {
    /**
     * parse the given value and return a valid value or a default value (mostly null, but can be other)
     *
     * @param type    enum type
     * @param value   string value to parse
     * @param <T>     enum Type
     * @return valid instance by calling {@code T.parse(value)}
     * @since 0.8.3
     */
    @SuppressWarnings("unchecked")
    static <T extends NutsEnum> NutsOptional<T> parse(Class<T> type, String value) {
        Method m;
        try {
            m = type.getMethod("parse", String.class);
        } catch (Exception ex) {
            NutsMessage msg = NutsMessage.ofCstyle("NutsEnum %s must implement a public static method parse(String,NutsSession)", type.getName());
            return NutsOptional.ofError(session -> msg,ex);
        }
        if (!Modifier.isStatic(m.getModifiers()) || !Modifier.isPublic(m.getModifiers()) || !m.getReturnType().equals(NutsOptional.class)) {
            NutsMessage msg = NutsMessage.ofCstyle("NutsEnum %s must implement a public static method parse(String,NutsSession)", type.getName());
            return NutsOptional.ofError(session -> msg);
        }
        NutsOptional<T> r;
        try {
            r = (NutsOptional<T>) m.invoke(null, value);
        } catch (Exception ex) {
            NutsMessage msg = NutsMessage.ofCstyle("failed executing %s.parse(String) ", type.getName());
            return NutsOptional.ofError(session -> msg,ex);
        }
        return r;
    }

    String id();
}
