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

import net.thevpc.nuts.elem.NutsElementNotFoundException;
import net.thevpc.nuts.io.NutsPath;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * Definition is an <strong>immutable</strong> object that contains all information about a artifact identified by it's Id.
 *
 * @author thevpc
 * @app.category Base
 * @since 0.5.4
 */
public interface NutsDefinition extends Serializable, Comparable<NutsDefinition> {

    /**
     * artifact id
     *
     * @return artifact id
     */
    NutsId getId();

    /**
     * return artifact descriptor
     *
     * @return artifact descriptor
     */
    NutsDescriptor getDescriptor();

    /**
     * return artifact content file info (including path).
     * this is an <strong>optional</strong> property. It must be requested (see {@link NutsSearchCommand#setContent(boolean)}) to be available.
     *
     * @return artifact content file info
     * @throws NutsElementNotFoundException if the property is not requested
     */
    NutsOptional<NutsPath> getContent();

    /**
     * return artifact install information.
     *
     * @return artifact install information
     * @throws NutsElementNotFoundException if the property is not requested
     */
    NutsOptional<NutsInstallInformation> getInstallInformation();

    /**
     * return artifact effective descriptor.
     * this is an <strong>optional</strong> property.
     * It must be requested (see {@link NutsSearchCommand#setEffective(boolean)} to be available).
     *
     * @return artifact effective descriptor
     * @throws NutsElementNotFoundException if the property is not requested
     */
    NutsOptional<NutsDescriptor> getEffectiveDescriptor();

    /**
     * return all or some of the transitive dependencies of the current Nuts as List
     * result of the search command
     * this is an <strong>optional</strong> property.
     * It must be requested (see {@link NutsSearchCommand#setDependencies(boolean)} to be available.
     *
     * @return all or some of the transitive dependencies of the current Nuts as List
     * result of the search command.
     * @throws NutsElementNotFoundException if the property is not requested
     */
    NutsOptional<NutsDependencies> getDependencies();

    /**
     * return target api id (included in dependency) for the current id.
     * This is relevant for runtime, extension and companion ids.
     * For other regular ids, this returns null.
     *
     * @return target (included in dependency) api id for the current id
     */
    NutsId getApiId();

    /**
     * Compares this object with the specified definition for order.
     * This is equivalent to comparing subsequent ids.
     *
     * @param other other definition to compare with
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    int compareTo(NutsDefinition other);

    /**
     * id of the repository providing this id.
     *
     * @return id of the repository providing this id.
     */
    String getRepositoryUuid();

    /**
     * name of the repository providing this id.
     *
     * @return name of the repository providing this id.
     */
    String getRepositoryName();

}
