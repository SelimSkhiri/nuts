package net.vpc.app.nuts.core.util.common;

import java.util.Collection;

/**
 * Created by vpc on 8/15/14.
 */
public interface CollectionFilter<A> {

    boolean accept(A a, int baseIndex, Collection<A> list);
}
