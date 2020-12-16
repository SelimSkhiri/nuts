package net.thevpc.nuts.runtime.remote;

import net.thevpc.nuts.*;
import net.thevpc.nuts.runtime.standalone.util.NutsCollectionResult;
import net.thevpc.nuts.runtime.standalone.wscommands.AbstractNutsSearchCommand;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RemoteNutsSearchCommand extends AbstractNutsSearchCommand {
    public RemoteNutsSearchCommand(NutsWorkspace ws) {
        super(ws);
    }

    @Override
    protected RemoteNutsWorkspace getWorkspace() {
        return (RemoteNutsWorkspace) super.getWorkspace();
    }

    @Override
    public NutsSearchCommand copy() {
        RemoteNutsSearchCommand b = new RemoteNutsSearchCommand(ws);
        b.copyFrom(this);
        return b;
    }

    @Override
    protected NutsCollectionResult<NutsId> getResultIdsBase(boolean print, boolean sort) {
        return buildNutsCollectionSearchResult(getResultIdsBaseIterator(sort), print);
    }

    protected Iterator<NutsId> getResultIdsBaseIterator(boolean sort) {
        RemoteNutsWorkspace ws = getWorkspace();
        NutsElementBuilder e = ws.formats().element().builder();
        NutsObjectElementBuilder eb = e.forObject()
                .set("execType", getExecType())
                .set("defaultVersions", getDefaultVersions())
                .set("targetApiVersion", getTargetApiVersion())
                .set("optional", getOptional())
                .set("arch", e.forArray().addAll(getArch()).build())
                .set("packaging", e.forArray().addAll(getPackaging()).build())
                .set("repositories", e.forArray().addAll(getRepositories()).build())
                .set("ids", e.forArray().addAll(Arrays.stream(getIds())
                        .map(Object::toString).toArray(String[]::new)).build());
        if (getIdFilter() != null) {
            eb.set("idFilter", ws.formats().element().toElement(getIdFilter()));
        }
        if (getDescriptorFilter() != null) {
            eb.set("descriptorFilter", ws.formats().element().toElement(getDescriptorFilter()));
        }
        if (getInstallStatus() != null && getInstallStatus().length>0) {
            eb.set("installStatus", e.forArray()
                    .addAll(
                            Arrays.stream(getInstallStatus())
                                    .map(x->e.forArray().addAll(
                                            x.stream().map(NutsInstallStatus::id).toArray(String[]::new)
                                    ).build()).toArray(NutsArrayElement[]::new)
                    ).build());
        }

        return getWorkspace().remoteCall(
                getWorkspace().createCall(
                        "workspace.search",
                        eb.build()
                )
                , List.class
        ).iterator();
    }
}
