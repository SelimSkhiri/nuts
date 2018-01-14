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

public interface NutsSecurityEntityConfig {
    long getInstanceSerialVersionUID();

    void setInstanceSerialVersionUID(long instanceSerialVersionUID);

    String getMappedUser();

    void setMappedUser(String mappedUser);

    String getUser();

    void setUser(String user);

    String getCredentials();

    void setCredentials(String credentials);

    String[] getRights();

    void setRights(String[] rights);

    void addGroup(String grp);

    void removeGroup(String grp);

    void addRight(String right);

    void removeRight(String right);

    String[] getGroups();

    void setGroups(String[] groups);

    boolean containsRight(String right);
}
