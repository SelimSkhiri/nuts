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
package net.vpc.app.nuts.main.archetypes;

import net.vpc.app.nuts.*;
import net.vpc.app.nuts.main.config.DefaultNutsWorkspaceConfigManager;
import net.vpc.app.nuts.runtime.util.CoreNutsUtils;
import net.vpc.app.nuts.runtime.util.common.CoreCommonUtils;

import java.util.LinkedHashSet;

/**
 * Created by vpc on 1/23/17.
 */
@NutsSingleton
public class MinimalNutsWorkspaceArchetypeComponent implements NutsWorkspaceArchetypeComponent {

    @Override
    public String getName() {
        return "minimal";
    }

    @Override
    public int getSupportLevel(NutsSupportLevelContext<String> criteria) {
        return DEFAULT_SUPPORT + 1;
    }

    @Override
    public void initialize(NutsSession session) {
        NutsWorkspace ws = session.getWorkspace();


        DefaultNutsWorkspaceConfigManager rm = (DefaultNutsWorkspaceConfigManager) ws.config();
        LinkedHashSet<String> br = new LinkedHashSet<>(rm.resolveBootRepositories());
        for (String s : br) {
            rm.addRepository(s,session);
        }
        if (br.isEmpty()) {
            rm.addRepository(NutsConstants.Names.DEFAULT_REPOSITORY_NAME,session);
        }


        //simple rights for minimal utilization
        NutsUpdateUserCommand uu = ws.security().updateUser(NutsConstants.Users.ANONYMOUS);
//        for (String right : NutsConstants.Rights.RIGHTS) {
//            if (!NutsConstants.Rights.ADMIN.equals(right)) {
//                uu.addRights(right);
//            }
//        }
        uu.run();
    }
}
