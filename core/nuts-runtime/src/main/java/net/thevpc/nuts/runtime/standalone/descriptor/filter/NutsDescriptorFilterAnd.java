package net.thevpc.nuts.runtime.standalone.descriptor.filter;

import net.thevpc.nuts.*;
import net.thevpc.nuts.runtime.standalone.util.filters.CoreFilterUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NutsDescriptorFilterAnd extends AbstractDescriptorFilter{

    private NutsDescriptorFilter[] all;

    public NutsDescriptorFilterAnd(NutsSession session, NutsDescriptorFilter... all) {
        super(session, NutsFilterOp.AND);
        List<NutsDescriptorFilter> valid = new ArrayList<>();
        if (all != null) {
            for (NutsDescriptorFilter filter : all) {
                if (filter != null) {
                    valid.add(filter);
                }
            }
        }
        this.all = valid.toArray(new NutsDescriptorFilter[0]);
    }

    @Override
    public boolean acceptDescriptor(NutsDescriptor id, NutsSession session) {
        if (all.length == 0) {
            return true;
        }
        for (NutsDescriptorFilter filter : all) {
            if (!filter.acceptDescriptor(id, session)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public NutsDescriptorFilter simplify() {
        return CoreFilterUtils.simplifyFilterAnd(getSession(),NutsDescriptorFilter.class,this,all);
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Arrays.deepHashCode(this.all);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NutsDescriptorFilterAnd other = (NutsDescriptorFilterAnd) obj;
        if (!Arrays.deepEquals(this.all, other.all)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.join(" and ", Arrays.asList(all).stream().map(x -> "(" + x.toString() + ")").collect(Collectors.toList()));
    }

    public List<NutsFilter> getSubFilters() {
        return Arrays.asList(all);
    }
}
