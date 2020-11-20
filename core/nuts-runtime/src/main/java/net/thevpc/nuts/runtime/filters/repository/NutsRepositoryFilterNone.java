package net.thevpc.nuts.runtime.filters.repository;

import net.thevpc.nuts.*;
import net.thevpc.nuts.runtime.filters.AbstractNutsFilter;
import net.thevpc.nuts.runtime.util.CoreNutsUtils;
import net.thevpc.nuts.runtime.util.common.CoreStringUtils;
import net.thevpc.nuts.runtime.util.common.Simplifiable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NutsRepositoryFilterNone extends AbstractNutsFilter implements NutsRepositoryFilter, Simplifiable<NutsRepositoryFilter> {

    private NutsRepositoryFilter[] all;

    public NutsRepositoryFilterNone(NutsWorkspace ws, NutsRepositoryFilter... all) {
        super(ws, NutsFilterOp.NOT);
        List<NutsRepositoryFilter> valid = new ArrayList<>();
        if (all != null) {
            for (NutsRepositoryFilter filter : all) {
                if (filter != null) {
                    valid.add(filter);
                }
            }
        }
        this.all = valid.toArray(new NutsRepositoryFilter[0]);
    }

    @Override
    public boolean acceptRepository(NutsRepository id) {
        if (all.length == 0) {
            return true;
        }
        for (NutsRepositoryFilter filter : all) {
            if (filter.acceptRepository(id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public NutsRepositoryFilter simplify() {
        return CoreNutsUtils.simplifyFilterNone(getWorkspace(), NutsRepositoryFilter.class,this,all);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutsRepositoryFilterNone that = (NutsRepositoryFilterNone) o;
        return Arrays.equals(all, that.all);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(all);
    }

    @Override
    public String toString() {
        return "Not("+String.join(" Or ", Arrays.asList(all).stream().map(x -> "(" + x.toString() + ")").collect(Collectors.toList()))+")";
    }

    @Override
    public NutsFilterOp getFilterOp() {
        return NutsFilterOp.NOT;
    }
    public NutsFilter[] getSubFilters() {
        return all;
    }
}
