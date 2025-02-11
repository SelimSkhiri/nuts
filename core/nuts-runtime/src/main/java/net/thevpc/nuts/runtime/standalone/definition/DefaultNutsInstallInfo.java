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
package net.thevpc.nuts.runtime.standalone.definition;

import net.thevpc.nuts.NutsId;
import net.thevpc.nuts.NutsInstallInformation;
import net.thevpc.nuts.NutsInstallStatus;
import net.thevpc.nuts.io.NutsPath;

import java.time.Instant;
import java.util.Objects;

/**
 * @author thevpc
 * @since 0.5.5
 */
public class DefaultNutsInstallInfo implements NutsInstallInformation {

    private NutsId id;
    private NutsInstallStatus installStatus;
    private boolean wasInstalled;
    private boolean wasRequired;
    private Instant lasModifiedDate;
    private Instant createdDate;
    private String installUser;
    private NutsPath installFolder;
    private String sourceRepositoryName;
    private String sourceRepositoryUUID;
    private boolean justInstalled;
    private boolean justRequired;
    public DefaultNutsInstallInfo(NutsId id, NutsInstallStatus installStatus, NutsPath installFolder, Instant createdDate, Instant lasModifiedDate, String installUser, String sourceRepositoryName, String sourceRepositoryUUID,boolean justInstalled,boolean justRequired) {
        this.id = id;
        this.installStatus = installStatus;
        this.installFolder = installFolder;
        this.createdDate = createdDate;
        this.lasModifiedDate = lasModifiedDate;
        this.installUser = installUser;
        this.sourceRepositoryName = sourceRepositoryName;
        this.sourceRepositoryUUID = sourceRepositoryUUID;
        this.justInstalled=justInstalled;
        this.justRequired=justRequired;
    }

    public DefaultNutsInstallInfo(NutsInstallInformation other) {
        this.id = other.getId();
        this.installStatus = other.getInstallStatus();
        this.installFolder = other.getInstallFolder();
        this.createdDate = other.getCreatedInstant();
        this.lasModifiedDate = other.getLastModifiedInstant();
        this.installUser = other.getInstallUser();
        this.sourceRepositoryName = other.getSourceRepositoryName();
        this.sourceRepositoryUUID = other.getSourceRepositoryUUID();
        this.justInstalled = other.isJustInstalled();
        this.justRequired = other.isJustRequired();
    }

    public static DefaultNutsInstallInfo notInstalled(NutsId id) {
        return new DefaultNutsInstallInfo(null,
                NutsInstallStatus.NONE,
                null,
                null,
                null,
                null,
                null, null,false,false
        );
    }

    @Override
    public NutsId getId() {
        return id;
    }

    @Override
    public Instant getCreatedInstant() {
        return createdDate;
    }

    @Override
    public Instant getLastModifiedInstant() {
        return lasModifiedDate;
    }

    @Override
    public boolean isDefaultVersion() {
        return getInstallStatus().isDefaultVersion();
    }

    @Override
    public NutsPath getInstallFolder() {
        return installFolder;
    }

    @Override
    public boolean isWasInstalled() {
        return wasInstalled;
    }

    public DefaultNutsInstallInfo setWasInstalled(boolean wasInstalled) {
        this.wasInstalled = wasInstalled;
        return this;
    }

    @Override
    public boolean isWasRequired() {
        return wasRequired;
    }

    @Override
    public String getInstallUser() {
        return installUser;
    }

    public NutsInstallStatus getInstallStatus() {
        return installStatus;
    }

    @Override
    public boolean isInstalledOrRequired() {
        return installStatus.isRequired()
                || installStatus.isInstalled();
    }

    @Override
    public String getSourceRepositoryName() {
        return sourceRepositoryName;
    }

    @Override
    public String getSourceRepositoryUUID() {
        return sourceRepositoryUUID;
    }

    public DefaultNutsInstallInfo setSourceRepositoryUUID(String sourceRepositoryUUID) {
        this.sourceRepositoryUUID = sourceRepositoryUUID;
        return this;
    }

    public DefaultNutsInstallInfo setSourceRepositoryName(String sourceRepositoryName) {
        this.sourceRepositoryName = sourceRepositoryName;
        return this;
    }

    public DefaultNutsInstallInfo setInstallStatus(NutsInstallStatus installStatus) {
        this.installStatus = installStatus;
        return this;
    }

    public DefaultNutsInstallInfo setInstallUser(String installUser) {
        this.installUser = installUser;
        return this;
    }

    public DefaultNutsInstallInfo setWasRequired(boolean wasRequired) {
        this.wasRequired = wasRequired;
        return this;
    }

    public DefaultNutsInstallInfo setInstallFolder(NutsPath installFolder) {
        this.installFolder = installFolder;
        return this;
    }

    public DefaultNutsInstallInfo setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public DefaultNutsInstallInfo setId(NutsId id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean isJustReInstalled() {
        return isWasInstalled() && isJustInstalled();
    }

    @Override
    public boolean isJustInstalled() {
        return justInstalled;
    }

    @Override
    public boolean isJustReRequired() {
        return isWasRequired() && isJustRequired();
    }

    @Override
    public boolean isJustRequired() {
        return justRequired;
    }

    public DefaultNutsInstallInfo setLasModifiedDate(Instant lasModifiedDate) {
        this.lasModifiedDate = lasModifiedDate;
        return this;
    }

    public DefaultNutsInstallInfo setJustInstalled(boolean justInstalled) {
        this.justInstalled = justInstalled;
        return this;
    }

    public DefaultNutsInstallInfo setJustRequired(boolean justRequired) {
        this.justRequired = justRequired;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultNutsInstallInfo that = (DefaultNutsInstallInfo) o;
        return wasInstalled == that.wasInstalled && wasRequired == that.wasRequired && justInstalled == that.justInstalled && justRequired == that.justRequired && Objects.equals(id, that.id) && Objects.equals(installStatus, that.installStatus) && Objects.equals(lasModifiedDate, that.lasModifiedDate) && Objects.equals(createdDate, that.createdDate) && Objects.equals(installUser, that.installUser) && Objects.equals(installFolder, that.installFolder) && Objects.equals(sourceRepositoryName, that.sourceRepositoryName) && Objects.equals(sourceRepositoryUUID, that.sourceRepositoryUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, installStatus, wasInstalled, wasRequired, lasModifiedDate, createdDate, installUser, installFolder, sourceRepositoryName, sourceRepositoryUUID, justInstalled, justRequired);
    }
}
