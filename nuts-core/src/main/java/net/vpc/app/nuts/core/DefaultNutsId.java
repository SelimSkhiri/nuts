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

import net.vpc.app.nuts.*;
import net.vpc.app.nuts.core.util.CoreNutsUtils;
import net.vpc.app.nuts.core.util.common.CoreStringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * Created by vpc on 1/5/17.
 */
public class DefaultNutsId implements NutsId {
    public static final long serialVersionUID = 1L;
    private final String namespace;
    private final String groupId;
    private final String artifactId;
    private final NutsVersion version;
    private final String query;

    public DefaultNutsId(String namespace, String groupId, String artifactId, String version, Map<String, String> query) {
        this(namespace, groupId, artifactId, DefaultNutsVersion.valueOf(version), query);
    }

    protected DefaultNutsId(String namespace, String groupId, String artifactId, NutsVersion version, Map<String, String> query) {
        this.namespace = CoreStringUtils.trimToNull(namespace);
        this.groupId = CoreStringUtils.trimToNull(groupId);
        this.artifactId = CoreStringUtils.trimToNull(artifactId);
        this.version = version == null ? DefaultNutsVersion.EMPTY : version;
        this.query = formatPropertiesQuery(query);
    }

    public static String formatPropertiesQuery(Map<String, String> query) {
        StringBuilder sb = new StringBuilder();
        if (query != null) {
            Set<String> sortedKeys = new TreeSet<>(query.keySet());
            for (String k : sortedKeys) {
                String v = query.get(k);
//                switch (k) {
//                    case NutsConstants.IdProperties.ALTERNATIVE: {
//                        if (CoreNutsUtils.isDefaultAlternative(v)) {
//                            v = null;
//                        }
//                        break;
//                    }
//                }
                if (v != null && v.length() > 0) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append(k).append("=").append(v);
                }
            }
        }
        return CoreStringUtils.trimToNull(sb.toString());
    }

    protected DefaultNutsId(String namespace, String groupId, String artifactId, NutsVersion version, String query) {
        this.namespace = CoreStringUtils.trimToNull(namespace);
        this.groupId = CoreStringUtils.trimToNull(groupId);
        this.artifactId = CoreStringUtils.trimToNull(artifactId);
        this.version = version == null ? DefaultNutsVersion.EMPTY : version;
        this.query = CoreStringUtils.trimToNull(query);
    }

    public DefaultNutsId(String groupId, String artifactId, String version) {
        this(null, groupId, artifactId, version, (String) null);
    }

    public DefaultNutsId(String namespace, String groupId, String artifactId, String version, String query) {
        this.namespace = CoreStringUtils.trimToNull(namespace);
        this.groupId = CoreStringUtils.trimToNull(groupId);
        this.artifactId = CoreStringUtils.trimToNull(artifactId);
        this.version = DefaultNutsVersion.valueOf(version);
        this.query = CoreStringUtils.trimToNull(query);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return toString().isEmpty();
    }

    @Override
    public boolean matches(String pattern) {
        if (pattern == null) {
            return true;
        }
        return toString().matches(pattern);
    }

    @Override
    public boolean contains(String substring) {
        return toString().contains(substring);
    }

    @Override
    public NutsTokenFilter groupIdToken() {
        return new DefaultNutsTokenFilter(getGroupId());
    }

    @Override
    public NutsTokenFilter propertiesToken() {
        return new DefaultNutsTokenFilter(getPropertiesQuery());
    }

    @Override
    public NutsTokenFilter versionToken() {
        return new DefaultNutsTokenFilter(getVersion().getValue());
    }

    @Override
    public NutsTokenFilter artifactIdToken() {
        return new DefaultNutsTokenFilter(getArtifactId());
    }

    @Override
    public NutsTokenFilter namespaceToken() {
        return new DefaultNutsTokenFilter(getNamespace());
    }

    @Override
    public NutsTokenFilter anyToken() {
        NutsTokenFilter[] oo = {groupIdToken(), propertiesToken(), versionToken(), artifactIdToken(), namespaceToken()};
        return new NutsTokenFilter() {
            @Override
            public boolean isNull() {
                for (NutsTokenFilter t : oo) {
                    if (t.isNull()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean isBlank() {
                for (NutsTokenFilter t : oo) {
                    if (t.isBlank()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean like(String pattern) {
                for (NutsTokenFilter t : oo) {
                    if (t.like(pattern)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean matches(String pattern) {
                for (NutsTokenFilter t : oo) {
                    if (t.matches(pattern)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean contains(String pattern) {
                for (NutsTokenFilter t : oo) {
                    if (t.contains(pattern)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    @Override
    public boolean equalsSimpleName(NutsId other) {
        if (other == null) {
            return false;
        }
        return CoreStringUtils.trim(artifactId).equals(CoreStringUtils.trim(other.getArtifactId()))
                && CoreStringUtils.trim(groupId).equals(CoreStringUtils.trim(other.getGroupId()));
    }

    @Override
    public boolean like(String pattern) {
        if (pattern == null) {
            return true;
        }
        return toString().matches(CoreStringUtils.simpexpToRegexp(pattern));
    }

    @Override
    public DefaultNutsId setGroupId(String newGroup) {
        if (CoreStringUtils.trim(groupId).equals(CoreStringUtils.trim(newGroup))) {
            return this;
        }
        return new DefaultNutsId(
                namespace,
                newGroup,
                artifactId,
                version,
                query
        );
    }

    @Override
    public NutsId setNamespace(String newNamespace) {
        if (CoreStringUtils.trim(namespace).equals(CoreStringUtils.trim(newNamespace))) {
            return this;
        }
        return new DefaultNutsId(
                newNamespace,
                groupId,
                artifactId,
                version,
                query
        );
    }

    @Override
    public NutsId setVersion(NutsVersion newVersion) {
        if (newVersion == null) {
            newVersion = DefaultNutsVersion.EMPTY;
        }
        if (newVersion.equals(version)) {
            return this;
        }
        return new DefaultNutsId(
                namespace,
                groupId,
                artifactId,
                newVersion,
                query
        );
    }

    @Override
    public NutsId setVersion(String newVersion) {
        NutsVersion nv = DefaultNutsVersion.valueOf(newVersion);
        if (nv.equals(version)) {
            return this;
        }
        return new DefaultNutsId(
                namespace,
                groupId,
                artifactId,
                newVersion,
                query
        );
    }

    @Override
    public NutsId setArtifactId(String newName) {
        if (CoreStringUtils.trim(artifactId).equals(CoreStringUtils.trim(newName))) {
            return this;
        }
        return new DefaultNutsId(
                namespace,
                groupId,
                newName,
                version,
                query
        );
    }

    @Override
    public String getFace() {
        String s = getProperties().get(NutsConstants.IdProperties.FACE);
        return CoreStringUtils.trimToNull(s);
    }

    @Override
    public String getScope() {
        String s = getProperties().get(NutsConstants.IdProperties.SCOPE);
        return CoreStringUtils.trimToNull(s);
    }

//    @Override
//    public String getAlternative() {
//        String s = getProperties().get(NutsConstants.IdProperties.ALTERNATIVE);
//        return CoreStringUtils.trimToNull(s);
//    }

    @Override
    public String getClassifier() {
        String s = getProperties().get(NutsConstants.IdProperties.CLASSIFIER);
        return CoreStringUtils.trimToNull(s);
    }

    @Override
    public NutsId setFace(String value) {
        return setProperty(NutsConstants.IdProperties.FACE, CoreStringUtils.trimToNull(value))
                .setProperties(CoreNutsUtils.QUERY_EMPTY_ENV, true);
    }

    @Override
    public NutsId setScope(String value) {
        return setProperty(NutsConstants.IdProperties.SCOPE, CoreStringUtils.trimToNull(value))
                .setProperties(CoreNutsUtils.QUERY_EMPTY_ENV, true);
    }

    @Override
    public NutsId setOptional(String value) {
        return setProperty(NutsConstants.IdProperties.OPTIONAL, CoreStringUtils.trimToNull(value))
                .setProperties(CoreNutsUtils.QUERY_EMPTY_ENV, true);
    }

//    @Override
//    public NutsId setAlternative(String value) {
//        return setProperty(NutsConstants.IdProperties.ALTERNATIVE, CoreNutsUtils.trimToNullAlternative(value))
//                .setProperties(CoreNutsUtils.QUERY_EMPTY_ENV, true);
//    }

    @Override
    public NutsId setArch(String value) {
        return setProperty(NutsConstants.IdProperties.ARCH, CoreStringUtils.trimToNull(value))
                .setProperties(CoreNutsUtils.QUERY_EMPTY_ENV, true);
    }

    @Override
    public NutsId setPackaging(String value) {
        return setProperty(NutsConstants.IdProperties.PACKAGING, CoreStringUtils.trimToNull(value));
    }

    @Override
    public NutsId setPlatform(String value) {
        return setProperty(NutsConstants.IdProperties.PLATFORM, CoreStringUtils.trimToNull(value));
    }

    @Override
    public NutsId setOsdist(String value) {
        return setProperty(NutsConstants.IdProperties.OSDIST, CoreStringUtils.trimToNull(value));
    }

    @Override
    public NutsId setOs(String value) {
        return setProperty(NutsConstants.IdProperties.OS, CoreStringUtils.trimToNull(value));
    }

    @Override
    public String getOs() {
        String s = getProperties().get(NutsConstants.IdProperties.OS);
        return CoreStringUtils.trimToNull(s);
    }

    @Override
    public String getOsdist() {
        String s = getProperties().get(NutsConstants.IdProperties.OSDIST);
        return CoreStringUtils.trimToNull(s);
    }

    @Override
    public String getPlatform() {
        String s = getProperties().get(NutsConstants.IdProperties.PLATFORM);
        return CoreStringUtils.trimToNull(s);
    }

    @Override
    public String getArch() {
        String s = getProperties().get(NutsConstants.IdProperties.ARCH);
        return CoreStringUtils.trimToNull(s);
    }

    @Override
    public NutsId setFaceComponent() {
        return setFace(NutsConstants.QueryFaces.COMPONENT);
    }

    @Override
    public NutsId setFaceDescriptor() {
        return setFace(NutsConstants.QueryFaces.DESCRIPTOR);
    }

    @Override
    public NutsId setProperty(String property, String value) {
        if (value == null || value.length() == 0) {
            if (query != null && !query.isEmpty()) {
                Map<String, String> m = getProperties();
                m.remove(property);
                return setProperties(m);
            }
            return this;
        } else {
            Map<String, String> m = getProperties();
            m.put(property, value);
            return setProperties(m);
        }
    }

    @Override
    public NutsId setProperties(Map<String, String> queryMap, boolean merge) {
        if (merge) {
            Map<String, String> m = getProperties();
            if (queryMap != null) {
                for (Map.Entry<String, String> e : queryMap.entrySet()) {
                    String property = e.getKey();
                    String value = e.getValue();
                    if (value == null || value.isEmpty()) {
                        m.remove(property);
                    } else {
                        m.put(property, value);
                    }
                }
            }
            return setProperties(m);
        } else {
            String m = DefaultNutsId.formatPropertiesQuery(queryMap);
            if (m == null) {
                m = "";
            }
            if (m.equals(query == null ? "" : query)) {
                return this;
            }
            return new DefaultNutsId(
                    namespace,
                    groupId,
                    artifactId,
                    version,
                    m
            );
        }
    }

    @Override
    public NutsId setProperties(Map<String, String> queryMap) {
        return setProperties(queryMap, false);
    }

    @Override
    public NutsId unsetProperties() {
        return setProperties("");
    }

    @Override
    public NutsId setProperties(String properties) {
        if (CoreStringUtils.trim(this.query).equals(properties)) {
            return this;
        }
        return new DefaultNutsId(
                namespace,
                groupId,
                artifactId,
                version,
                properties
        );
    }

    @Override
    public String getPropertiesQuery() {
        return query;
    }

    @Override
    public Map<String, String> getProperties() {
        String q = getPropertiesQuery();
        if (q == null || q.equals("")) {
            return new LinkedHashMap<>();
        }
        return CoreStringUtils.parseMap(q, "&");
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public String getGroupId() {
        return groupId;
    }

    @Override
    public NutsId getShortNameId() {
        return new DefaultNutsId(null, groupId, artifactId, (NutsVersion) null, "");
    }

    @Override
    public NutsId getLongNameId() {
        return new DefaultNutsId(null, groupId, artifactId, version, "");
    }

    @Override
    public String getShortName() {
        if (CoreStringUtils.isBlank(groupId)) {
            return CoreStringUtils.trim(artifactId);
        }
        return CoreStringUtils.trim(groupId) + ":" + CoreStringUtils.trim(artifactId);
    }

    @Override
    public String getLongName() {
        String s = getShortName();
        NutsVersion v = getVersion();
        if (v.isBlank()) {
            return s;
        }
        return s + "#" + v;
    }

    @Override
    public String getFullName() {
        return toString();
    }

    @Override
    public String getArtifactId() {
        return artifactId;
    }

    @Override
    public NutsVersion getVersion() {
        return version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!CoreStringUtils.isBlank(namespace)) {
            sb.append(namespace).append("://");
        }
        if (!CoreStringUtils.isBlank(groupId)) {
            sb.append(groupId).append(":");
        }
        sb.append(artifactId);
        if (!version.isBlank()) {
            sb.append("#").append(version);
        }
        if (!CoreStringUtils.isBlank(query)) {
            sb.append("?");
            sb.append(query);
        }
        return sb.toString();
    }

    @Override
    public boolean isOptional() {
        return Boolean.parseBoolean(getOptional());
    }

    @Override
    public String getOptional() {
        String s = getProperties().get(NutsConstants.IdProperties.OPTIONAL);
        return CoreStringUtils.trimToNull(s);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultNutsId nutsId = (DefaultNutsId) o;

        if (namespace != null ? !namespace.equals(nutsId.namespace) : nutsId.namespace != null) {
            return false;
        }
        if (groupId != null ? !groupId.equals(nutsId.groupId) : nutsId.groupId != null) {
            return false;
        }
        if (artifactId != null ? !artifactId.equals(nutsId.artifactId) : nutsId.artifactId != null) {
            return false;
        }
        if (version != null ? !version.equals(nutsId.version) : nutsId.version != null) {
            return false;
        }
        return query != null ? query.equals(nutsId.query) : nutsId.query == null;

    }

    @Override
    public int hashCode() {
        int result = namespace != null ? namespace.hashCode() : 0;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (artifactId != null ? artifactId.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (query != null ? query.hashCode() : 0);
        return result;
    }

    @Override
    public NutsId apply(Function<String, String> properties) {
        return new DefaultNutsId(
                CoreNutsUtils.applyStringProperties(this.getNamespace(), properties),
                CoreNutsUtils.applyStringProperties(this.getGroupId(), properties),
                CoreNutsUtils.applyStringProperties(this.getArtifactId(), properties),
                CoreNutsUtils.applyStringProperties(this.getVersion().getValue(), properties),
                CoreNutsUtils.applyMapProperties(this.getProperties(), properties)
        );
    }

    @Override
    public NutsIdBuilder builder() {
        return new DefaultNutsIdBuilder(this);
    }

    @Override
    public NutsIdFilter toFilter() {
        return new NutsPatternIdFilter(this);
    }

    @Override
    public int compareTo(NutsId o2) {
        int x = this.getShortName().compareTo(o2.getShortName());
        if (x != 0) {
            return x;
        }
        //latests versions first
        x = this.getVersion().compareTo(o2.getVersion());
        return -x;
    }
}
