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
 *
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
package net.thevpc.nuts.runtime.standalone.workspace.cmd;

import net.thevpc.nuts.NutsDefinition;
import net.thevpc.nuts.NutsId;
import net.thevpc.nuts.NutsUpdateResult;

import java.util.Collections;
import java.util.List;

/**
 * Created by vpc on 6/23/17.
 */
public final class DefaultNutsUpdateResult implements NutsUpdateResult {

    private NutsId id;
    private NutsDefinition installed;
    private NutsDefinition available;
    private List<NutsId> dependencies;
    private boolean runtime;
    private boolean updateApplied;
    private boolean updateForced;
    private boolean updateVersionAvailable;
    private boolean updateStatusAvailable;

    public DefaultNutsUpdateResult() {
    }

    public DefaultNutsUpdateResult(NutsId id, NutsDefinition installed, NutsDefinition available, List<NutsId> dependencies, boolean runtime) {
        this.id = id;
        this.installed = installed;
        this.available = available;
        this.runtime = runtime;
        this.dependencies = dependencies == null ? Collections.emptyList() : dependencies;
    }


    public boolean isInstalled() {
        return installed !=null;
    }

    public boolean isRuntime() {
        return runtime;
    }

    @Override
    public NutsId getId() {
        return id;
    }

    @Override
    public NutsDefinition getInstalled() {
        return installed;
    }

    @Override
    public NutsDefinition getAvailable() {
        return available;
    }

    @Override
    public List<NutsId> getDependencies() {
        return dependencies;
    }

    @Override
    public boolean isUpdateApplied() {
        return updateApplied;
    }

    public void setUpdateApplied(boolean updateApplied) {
        this.updateApplied = updateApplied;
    }

    @Override
    public boolean isUpdateForced() {
        return updateForced;
    }

    public void setUpdateForced(boolean updateForced) {
        this.updateForced = updateForced;
    }

    @Override
    public boolean isUpdatable() {
        return isUpdateVersionAvailable() || isUpdateStatusAvailable() || isUpdateForced();
    }

    @Override
    public boolean isUpdateVersionAvailable() {
        return updateVersionAvailable;
    }

    public void setUpdateVersionAvailable(boolean updateVersion) {
        this.updateVersionAvailable = updateVersion;
    }

    @Override
    public boolean isUpdateStatusAvailable() {
        return updateStatusAvailable;
    }

    public void setUpdateStatusAvailable(boolean updateStatus) {
        this.updateStatusAvailable = updateStatus;
    }

    public void setInstalled(NutsDefinition installed) {
        this.installed = installed;
    }

    public void setAvailable(NutsDefinition available) {
        this.available = available;
    }

    public void setId(NutsId id) {
        this.id = id;
    }

}
