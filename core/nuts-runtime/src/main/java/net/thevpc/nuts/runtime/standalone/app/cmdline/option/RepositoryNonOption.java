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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thevpc
 */
public class RepositoryNonOption extends DefaultNonOption {


    public RepositoryNonOption(String name) {
        super(name);
    }


    @Override
    public List<NutsArgumentCandidate> getCandidates(NutsCommandAutoComplete context) {
        List<NutsArgumentCandidate> all = new ArrayList<>();
        NutsRepository repository=context.get(NutsRepository.class);
        if(repository!=null){
            if (repository.config().isSupportedMirroring()) {
                for (NutsRepository repo : repository.config().setSession(context.getSession()).getMirrors()) {
                    all.add(new DefaultNutsArgumentCandidate(repo.getName()));
                }
            }
        }else{
            for (NutsRepository repo : context.getSession().repos().setSession(context.getSession()).getRepositories()) {
                all.add(new DefaultNutsArgumentCandidate(repo.getName()));
            }

        }
        return all;
    }

}
