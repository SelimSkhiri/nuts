/**
 * ====================================================================
 * Nuts : Network Updatable Things Service
 * (universal package manager)
 * <p>
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 * <p>
 * Copyright (C) 2016-2017 Taha BEN SALAH
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * ====================================================================
 */
package net.vpc.app.nuts;

import java.io.Serializable;

/**
 * @author vpc
 * @since 0.5.4
 */
public class NutsSdkLocation implements Serializable {

    public static final long serialVersionUID = 1;
    private final NutsId id;
    private final String name;
    private final String packaging;
    private final String product;
    private final String path;
    private final String version;

    public NutsSdkLocation(NutsId id, String product, String name, String path, String version, String packaging) {
        this.id = id;
        this.product = product;
        this.name = name;
        this.path = path;
        this.version = version;
        this.packaging = packaging;
    }

    public NutsId getId() {
        return id;
    }

    /**
     * Oracle JDK or OpenJDK for Java
     *
     * @return product name
     */
    public String getProduct() {
        return product;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    /**
     * JRE or JDK for java
     *
     * @return packaging name
     */
    public String getPackaging() {
        return packaging;
    }
}
