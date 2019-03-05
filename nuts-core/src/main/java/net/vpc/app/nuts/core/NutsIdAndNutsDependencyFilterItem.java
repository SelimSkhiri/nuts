package net.vpc.app.nuts.core;

import net.vpc.app.nuts.*;

public class NutsIdAndNutsDependencyFilterItem {

    public NutsIdGraph.NutsIdNode id;
    public NutsDescriptor descriptor;
    public NutsDescriptor effDescriptor;

    public NutsIdAndNutsDependencyFilterItem(NutsIdGraph.NutsIdNode id) {
        this.id = id;
    }

    public NutsDescriptor getEffDescriptor(NutsWorkspace ws, NutsSession session) {
        if (effDescriptor == null) {
            effDescriptor = ws.fetch(id.id).setIncludeInstallInformation(true).setSession(session).setIncludeEffective(true).fetchDescriptor();
        }
        return effDescriptor;
    }

    public NutsDescriptor getDescriptor(NutsWorkspace ws, NutsSession session) {
        if (descriptor == null) {
            descriptor = ws.fetch(id.id).setIncludeInstallInformation(true).setSession(session).fetchDescriptor();
        }
        return descriptor;
    }
}
