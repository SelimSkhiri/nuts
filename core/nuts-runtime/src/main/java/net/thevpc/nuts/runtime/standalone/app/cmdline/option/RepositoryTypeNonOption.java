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
package net.thevpc.nuts.runtime.standalone.app.cmdline.option;

import net.thevpc.nuts.*;
import net.thevpc.nuts.cmdline.DefaultNutsArgumentCandidate;
import net.thevpc.nuts.cmdline.NutsArgumentCandidate;
import net.thevpc.nuts.cmdline.NutsCommandAutoComplete;
import net.thevpc.nuts.runtime.standalone.repository.util.NutsRepositoryUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author thevpc
 */
public class RepositoryTypeNonOption extends DefaultNonOption {

    public RepositoryTypeNonOption(String name) {
        super(name);
    }

    @Override
    public List<NutsArgumentCandidate> getCandidates(NutsCommandAutoComplete context) {
        TreeSet<String> allValid = new TreeSet<>();
        allValid.add(NutsConstants.RepoTypes.NUTS);
        allValid.add(NutsConstants.RepoTypes.MAVEN);
        for (NutsAddRepositoryOptions repo : context.getSession().config()
                .setSession(context.getSession())
                .getDefaultRepositories()) {
            if(repo.getConfig()!=null) {
                String t = NutsRepositoryUtils.getRepoType(repo.getConfig());
                if(!NutsBlankable.isBlank(t)){
                    allValid.add(t.trim());
                }
            }
        }
        List<NutsArgumentCandidate> all = new ArrayList<>();
        for (String v : allValid) {
            all.add(new DefaultNutsArgumentCandidate(v));
        }
        return all;
    }
}
