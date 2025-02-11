package net.thevpc.nuts.runtime.standalone.definition.installstatus.filter;

import net.thevpc.nuts.*;
import net.thevpc.nuts.runtime.standalone.util.filters.CoreFilterUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NutsInstallStatusFilterOr extends AbstractInstallStatusFilter {

    private NutsInstallStatusFilter[] all;

    public NutsInstallStatusFilterOr(NutsSession session, NutsInstallStatusFilter... all) {
        super(session, NutsFilterOp.OR);
        List<NutsInstallStatusFilter> valid = new ArrayList<>();
        if (all != null) {
            for (NutsInstallStatusFilter filter : all) {
                if (filter != null) {
                    valid.add(filter);
                }
            }
        }
        this.all = valid.toArray(new NutsInstallStatusFilter[0]);
    }

    @Override
    public boolean acceptInstallStatus(NutsInstallStatus id, NutsSession session) {
        if (all.length == 0) {
            return true;
        }
        for (NutsInstallStatusFilter filter : all) {
            if (filter.acceptInstallStatus(id, session)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public NutsInstallStatusFilter simplify() {
        return CoreFilterUtils.simplifyFilterOr(getSession(),NutsInstallStatusFilter.class,this,all);
    }

    @Override
    public String toString() {
        return String.join(" Or ", Arrays.asList(all).stream().map(x -> "(" + x.toString() + ")").collect(Collectors.toList()));
    }

    public List<NutsFilter> getSubFilters() {
        return Arrays.asList(all);
    }
}
