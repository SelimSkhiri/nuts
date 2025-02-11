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
package net.thevpc.nuts.runtime.standalone.util.collections;

import net.thevpc.nuts.NutsBlankable;
import net.thevpc.nuts.NutsIdLocation;
import net.thevpc.nuts.util.NutsStringUtils;

import java.util.*;

/**
 * @author thevpc
 */
public class CoreCollectionUtils {
    public static <T> List<T> unmodifiableList(Collection<T> other) {
        return other == null ? Collections.emptyList() : Collections.unmodifiableList(nonNullList(other));
    }

    public static <T, V> Map<T, V> nonNullMap(Map<T, V> other) {
        if (other == null) {
            return new LinkedHashMap<>();
        }
        return new LinkedHashMap<>(other);
    }


    public static <T> List<T> nonNullList(Collection<T> other) {
        if (other == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(other);
    }

    public static <T> List<T> toList(Iterator<T> it) {
        List<T> all = new ArrayList<>();
        while (it.hasNext()) {
            all.add(it.next());
        }
        return all;
    }

    public static Set<String> toTrimmedNonEmptySet(String[] values0) {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        if (values0 != null) {
            for (String a : values0) {
                a = NutsStringUtils.trim(a);
                if (!NutsBlankable.isBlank(a)) {
                    set.add(a);
                }
            }
        }
        return set;
    }

    public static ArrayList<String> toDistinctTrimmedNonEmptyList(String[] values0) {
        return new ArrayList<>(toTrimmedNonEmptySet(values0));
    }

    public static Set<NutsIdLocation> toSet(NutsIdLocation[] classifierMappings) {
        LinkedHashSet<NutsIdLocation> set = new LinkedHashSet<>();
        if (classifierMappings != null) {
            for (NutsIdLocation a : classifierMappings) {
                if (a != null) {
                    set.add(a);
                }
            }
        }
        return set;
    }

    public static <K, V> Map<K, V> fill(Map<K, V> m, K k1, V v1) {
        m.put(k1, v1);
        return m;
    }

    public static <K, V> Map<K, V> fill(Map<K, V> m, K k1, V v1, K k2, V v2) {
        m.put(k1, v1);
        m.put(k2, v2);
        return m;
    }

    public static <K, V> Map<K, V> fill(Map<K, V> m, K k1, V v1, K k2, V v2, K k3, V v3) {
        m.put(k1, v1);
        m.put(k2, v2);
        m.put(k3, v3);
        return m;
    }

    public static <T, V> Map<T, V> unmodifiableMap(Map<T, V> other) {
        return other == null ? Collections.emptyMap() : Collections.unmodifiableMap(nonNullMap(other));
    }

    public static <T> List<T> nonNullListFromArray(T[] other) {
        return nonNullList(Arrays.asList(other));
    }
}
