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
package net.thevpc.nuts.runtime.core.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.thevpc.nuts.NutsIdLocation;

/**
 *
 * @author vpc
 */
public class CoreArrayUtils {

    public static String[] concatArrays(String[]... arrays) {
        return concatArrays(String.class, arrays);
    }

    public static <T> T[] concatArrays(Class<T> cls, T[]... arrays) {
        List<T> all = new ArrayList<>();
        if (arrays != null) {
            for (T[] v : arrays) {
                if (v != null) {
                    all.addAll(Arrays.asList(v));
                }
            }
        }
        return all.toArray((T[]) Array.newInstance(cls, all.size()));
    }

    public static String[] toDistinctTrimmedNonEmptyArray(String[] values0, String[]... values) {
        Set<String> set = CoreCollectionUtils.toTrimmedNonEmptySet(values0);
        if (values != null) {
            for (String[] value : values) {
                set.addAll(CoreCollectionUtils.toTrimmedNonEmptySet(value));
            }
        }
        return set.toArray(new String[0]);
    }

    public static NutsIdLocation[] toArraySet(NutsIdLocation[] classifierMappings) {
        Set<NutsIdLocation> set = CoreCollectionUtils.toSet(classifierMappings);
        return set.toArray(new NutsIdLocation[0]);
    }
    
}
