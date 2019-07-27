/**
 * ====================================================================
 * Nuts : Network Updatable Things Service
 * (universal package manager)
 * <p>
 * is a new Open Source Package Manager to help install packages and libraries
 * for runtime execution. Nuts is the ultimate companion for maven (and other
 * build managers) as it helps installing all package dependencies at runtime.
 * Nuts is not tied to java and is a good choice to share shell scripts and
 * other 'things' . Its based on an extensible architecture to help supporting a
 * large range of sub managers / repositories.
 * <p>
 * Copyright (C) 2016-2017 Taha BEN SALAH
 * <p>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * ====================================================================
 */
package net.vpc.app.nuts.core;

import net.vpc.app.nuts.NutsDependency;
import net.vpc.app.nuts.NutsDependencyBuilder;
import net.vpc.app.nuts.NutsId;
import net.vpc.app.nuts.NutsVersion;

import java.util.Arrays;
import net.vpc.app.nuts.core.util.NutsDependencyScopes;
import net.vpc.app.nuts.core.util.common.CoreStringUtils;

/**
 * Created by vpc on 1/5/17.
 */
public class DefaultNutsDependencyBuilder implements NutsDependencyBuilder {

    private String namespace;
    private String group;
    private String name;
    private NutsVersion version;
    private String scope;
    private String optional;
    private String classifier;
    private NutsId[] exclusions;

    public DefaultNutsDependencyBuilder() {
    }

    @Override
    public NutsDependencyBuilder set(NutsDependencyBuilder value) {
        if (value != null) {
            setNamespace(value.getNamespace());
            setGroupId(value.getGroupId());
            setArtifactId(value.getArtifactId());
            setVersion(value.getVersion());
            setScope(value.getScope());
            setOptional(value.getOptional());
            setExclusions(value.getExclusions());
            setClassifier(value.getClassifier());
        }else{
            clear();
        }
        return this;
    }

    @Override
    public NutsDependencyBuilder set(NutsDependency value) {
        if (value != null) {
            setNamespace(value.getNamespace());
            setGroupId(value.getGroupId());
            setArtifactId(value.getArtifactId());
            setVersion(value.getVersion());
            setScope(value.getScope());
            setOptional(value.getOptional());
            setExclusions(value.getExclusions());
            setClassifier(value.getClassifier());
        }else{
            clear();
        }
        return this;
    }

    @Override
    public NutsDependencyBuilder clear() {
        setNamespace(null);
        setGroupId(null);
        setArtifactId(null);
        setVersion((NutsVersion)null);
        setScope(null);
        setOptional(null);
        setExclusions(null);
        setClassifier(null);
        return this;
    }

    @Override
    public NutsDependencyBuilder setNamespace(String namespace) {
        this.namespace = CoreStringUtils.trimToNull(namespace);
        return this;
    }

    @Override
    public NutsDependencyBuilder setGroupId(String groupId) {
        this.group = CoreStringUtils.trimToNull(groupId);
        return this;
    }

    @Override
    public NutsDependencyBuilder setArtifactId(String artifactId) {
        this.name = CoreStringUtils.trimToNull(artifactId);
        return this;
    }

    @Override
    public NutsDependencyBuilder setVersion(NutsVersion version) {
        this.version = version == null ? DefaultNutsVersion.EMPTY : version;
        return this;
    }

    @Override
    public NutsDependencyBuilder setVersion(String classifier) {
        this.version = DefaultNutsVersion.valueOf(classifier);
        return this;
    }

    @Override
    public NutsDependencyBuilder setId(NutsId id) {
        if (id == null) {
            setNamespace(null);
            setGroupId(null);
            setArtifactId(null);
            setVersion((String) null);
        } else {
            setNamespace(id.getNamespace());
            setGroupId(id.getGroupId());
            setArtifactId(id.getArtifactId());
            setVersion(id.getVersion());
        }
        return this;
    }

    @Override
    public NutsDependencyBuilder setClassifier(String classifier) {
        this.classifier = CoreStringUtils.trimToNull(classifier);
        return this;
    }

    @Override
    public NutsDependencyBuilder setScope(String scope) {
        this.scope = NutsDependencyScopes.normalizeScope(scope);
        return this;
    }

    @Override
    public NutsDependencyBuilder setOptional(String optional) {
        this.optional = CoreStringUtils.isBlank(optional) ? "false" : CoreStringUtils.trim(optional);
        return this;
    }

    @Override
    public NutsDependencyBuilder setExclusions(NutsId[] exclusions) {
        if (exclusions != null) {
            exclusions = Arrays.copyOf(exclusions, exclusions.length);
        }
        this.exclusions = exclusions;
        return this;
    }

    @Override
    public boolean isOptional() {
        return Boolean.parseBoolean(optional);
    }

    @Override
    public String getOptional() {
        return optional;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public String getClassifier() {
        return classifier;
    }

    @Override
    public NutsId getId() {
        return new DefaultNutsId(
                getNamespace(),
                getGroupId(),
                getArtifactId(),
                getVersion().getValue(),
                ""
        );
    }

    @Override
    public NutsDependency build() {
        return new DefaultNutsDependency(
                getNamespace(), getGroupId(), getArtifactId(), getClassifier(),
                getVersion(),
                getScope(),
                getOptional(),
                getExclusions()
        );
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public String getGroupId() {
        return group;
    }

    @Override
    public String getArtifactId() {
        return name;
    }

    @Override
    public String getFullName() {
        if (CoreStringUtils.isBlank(group)) {
            return CoreStringUtils.trim(name);
        }
        return CoreStringUtils.trim(group) + ":" + CoreStringUtils.trim(name);
    }

    @Override
    public NutsVersion getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return build().toString();
    }

    @Override
    public NutsId[] getExclusions() {
        return exclusions == null ? new NutsId[0] : Arrays.copyOf(exclusions, exclusions.length);
    }

}
