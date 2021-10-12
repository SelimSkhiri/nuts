/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.nuts.runtime.bundles.iter;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.thevpc.nuts.NutsPredicates;
import net.thevpc.nuts.runtime.bundles.io.FileDepthFirstIterator;

/**
 * @author thevpc
 */
public class IteratorUtils {

    public static final Predicate NON_NULL = NutsPredicates.isNull().negate();
    public static final Predicate NON_BLANK = NutsPredicates.blank().negate();
    private static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator<>();

    public static FileDepthFirstIterator dsf(File file) {
        return new FileDepthFirstIterator(file);
    }

    public static <T> Iterator<T> safe(IteratorErrorHandlerType type, Iterator<T> t) {
        return new ErrorHandlerIterator(type, t);
    }

    public static <T> Iterator<T> safeIgnore(Iterator<T> t) {
        return new ErrorHandlerIterator(IteratorErrorHandlerType.IGNORE, t);
    }

    public static <T> Iterator<T> safePospone(Iterator<T> t) {
        return new ErrorHandlerIterator(IteratorErrorHandlerType.POSTPONE, t);
    }

    public static <T> boolean isNullOrEmpty(Iterator<T> t) {
        return t == null || t == EMPTY_ITERATOR;
    }

    public static <T> Iterator<T> emptyIterator() {
        return EMPTY_ITERATOR;
    }

    public static <T> Iterator<T> nonNull(Iterator<T> t) {
        if (t == null) {
            return emptyIterator();
        }
        return t;
    }

    public static <T> Iterator<T> concat(List<Iterator<? extends T>> all) {
        if (all == null || all.isEmpty()) {
            return IteratorUtils.emptyIterator();
        }
        QueueIterator<T> t = new QueueIterator<>();
        for (Iterator<? extends T> it : all) {
            if (!isNullOrEmpty(it)) {
                if (it instanceof QueueIterator) {
                    QueueIterator tt = (QueueIterator) it;
                    for (Iterator it1 : tt.getChildren()) {
                        t.add(it1);
                    }
                } else {
                    t.add(it);
                }
            }
        }
        int tsize = t.size();
        if (tsize == 0) {
            return IteratorUtils.emptyIterator();
        }
        if (tsize == 1) {
            return t.getChildren()[0];
        }
        return t;
    }

    public static <T> Iterator<T> coalesce2(List<Iterator<T>> all) {
        return coalesce((List) all);
    }

    public static <T> Iterator<T> coalesce(Iterator<? extends T> ... all) {
        return coalesce(Arrays.asList(all));
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> ... all) {
        return concat(Arrays.asList(all));
    }

    public static <T> Iterator<T> concatLists(List<Iterator<? extends T>> ... all) {
        List<Iterator<? extends T>> r=new ArrayList<>();
        if(all!=null) {
            for (List<Iterator<? extends T>> a : all) {
                if (a != null) {
                    for (Iterator<? extends T> b : a) {
                        if(b!=null){
                            r.add(b);
                        }
                    }
                }
            }
        }
        return concat(r);
    }

    public static <T> Iterator<T> coalesce(List<Iterator<? extends T>> all) {
        if (all == null || all.isEmpty()) {
            return IteratorUtils.emptyIterator();
        }
        CoalesceIterator<T> t = new CoalesceIterator<>();
        for (Iterator<? extends T> it : all) {
            if (!isNullOrEmpty(it)) {
                t.add(it);
            }
        }
        int tsize = t.size();
        if (tsize == 0) {
            return IteratorUtils.emptyIterator();
        }
        if (tsize == 1) {
            return t.getChildren()[0];
        }
        return t;
    }

    public static <T> Iterator<T> filter(Iterator<T> from, Predicate<? super T> filter) {
        if (from == null) {
            return emptyIterator();
        }
        if (filter == null) {
            return from;
        }
        return new FilteredIterator<>(from, filter);
    }

    public static <T> Iterator<T> name(String name, Iterator<T> from) {
        return new NamedIterator<>(from, name);
    }

    public static <T> Iterator<T> flatCollection(Iterator<Collection<T>> from) {
        return flatMap(from, (c->c.iterator()));
    }

    public static <T> Iterator<T> flatIterator(Iterator<Iterator<T>> from) {
        return flatMap(from, (c->c));
    }

    //? super T, ? extends Iterator<? extends R>
    public static <T, R> Iterator<R> flatMap(Iterator<T> from, Function<? super T, ? extends Iterator<? extends R>> fun) {
        if (from == null) {
            return emptyIterator();
        }
        return new FlatMapIterator<>(from, fun);
    }

    public static <T> Iterator<T> supplier(Supplier<Iterator<T>> from) {
        return new SupplierIterator<T>(from, null);
    }

    public static <T> Iterator<T> supplier(Supplier<Iterator<T>> from, String name) {
        return new SupplierIterator<T>(from, name);
    }

    public static <T> Iterator<T> onFinish(Iterator<T> from, Runnable r) {
        if (from == null) {
            return emptyIterator();
        }
        return new OnFinishIterator<>(from, r);
    }

    public static <F, T> Function<F,T> namedFunction(Function<F,T> converter, String name) {
        return new Function<F, T>() {
            @Override
            public T apply(F f) {
                return converter.apply(f);
            }

            @Override
            public String toString() {
                return name==null?(converter==null?"null":converter.toString()):name;
            }
        };
    }

    public static <F, T> Iterator<T> map(Iterator<F> from, Function<? super F, ? extends T> converter) {
        if (isNullOrEmpty(from)) {
            return emptyIterator();
        }
        return new ConvertedIterator<>(from, converter);
    }

    public static <F, T> Iterator<T> convertNonNull(Iterator<F> from, Function<F, T> converter, String name) {
        if (isNullOrEmpty(from)) {
            return emptyIterator();
        }
        return new ConvertedNonNullIterator<>(from, converter, name);
    }

    public static <T> List<T> toList(Iterator<T> it) {
        if (isNullOrEmpty(it)) {
            return Collections.emptyList();
        }
        List<T> a = new ArrayList<T>();
        while (it.hasNext()) {
            a.add(it.next());
        }
        return a;
    }

    public static <T> Set<T> toSet(Iterator<T> it) {
        if (isNullOrEmpty(it)) {
            return Collections.emptySet();
        }
        LinkedHashSet<T> a = new LinkedHashSet<T>();
        while (it.hasNext()) {
            a.add(it.next());
        }
        return a;
    }

    public static <T> Set<T> toTreeSet(Iterator<T> it, Comparator<T> c) {
        if (isNullOrEmpty(it)) {
            return Collections.emptySet();
        }
        TreeSet<T> a = new TreeSet<T>(c);
        while (it.hasNext()) {
            a.add(it.next());
        }
        return a;
    }

    public static <T> Iterator<T> sort(Iterator<T> it, Comparator<T> c, boolean removeDuplicates) {
        if (isNullOrEmpty(it)) {
            return emptyIterator();
        }
        return new SortIterator<>(it, c, removeDuplicates);
    }

    public static <T> Iterator<T> distinct(Iterator<T> it) {
        if (isNullOrEmpty(it)) {
            return emptyIterator();
        }
        Predicate<T> filter = new DistinctPredicate<>();
        return new FilteredIterator<>(it, filter);
    }

    public static <F, T> Iterator<F> distinct(Iterator<F> it, final Function<F, T> converter) {
        if (isNullOrEmpty(it)) {
            return emptyIterator();
        }
        if(converter==null){
            Predicate<F> filter = new DistinctPredicate<>();
            return new FilteredIterator<>(it, filter);
        }
        Predicate<F> filter = new DistinctWithConverterPredicate<>(converter);
        return new FilteredIterator<>(it, filter);
    }

    public static <T> CollectorIterator<T> collector(Iterator<T> it) {
        if (it == null) {
            return new CollectorIterator<>(null, emptyIterator());
        }
        return new CollectorIterator<>(null, it);
    }

    public static <T> Iterator<T> nullifyIfEmpty(Iterator<T> other) {
        if (other == null) {
            return null;
        }
        if (other instanceof PushBackIterator) {
            PushBackIterator<T> b = (PushBackIterator<T>) other;
            if (!b.isEmpty()) {
                return b;
            } else {
                return null;
            }
        }
        PushBackIterator<T> b = new PushBackIterator<>(other);
        if (!b.isEmpty()) {
            return b;
        } else {
            return null;
        }
    }

    private static class EmptyIterator<E> implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new IllegalStateException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
        }

        @Override
        public String toString() {
            return "EmptyIterator";
        }
    }

    private static class OnFinishIterator<T> implements Iterator<T> {

        private final Iterator<T> from;
        private final Runnable r;

        public OnFinishIterator(Iterator<T> from, Runnable r) {
            this.from = from;
            this.r = r;
        }

        @Override
        public boolean hasNext() {
            boolean n = from.hasNext();
            if (!n) {
                r.run();
            }
            return n;
        }

        @Override
        public T next() {
            return from.next();
        }
    }

    private static class SupplierIterator<T> implements Iterator<T> {

        private final Supplier<Iterator<T>> from;
        private Iterator<T> it;
        private String name;

        public SupplierIterator(Supplier<Iterator<T>> from, String name) {
            this.from = from;
            this.name = name;
        }

        @Override
        public boolean hasNext() {
            if (it == null) {
                it = from.get();
            }
            return it.hasNext();
        }

        @Override
        public T next() {
            return it.next();
        }

        @Override
        public String toString() {
            if (name == null) {
                return "supplier(" + from + ")";
            }
            return String.valueOf(name);
        }
    }

    private static class NamedIterator<T> implements Iterator<T> {

        private final Iterator<T> from;
        private final String name;

        public NamedIterator(Iterator<T> from, String name) {
            this.from = from;
            this.name = name;
        }

        @Override
        public boolean hasNext() {
            return from != null && from.hasNext();
        }

        @Override
        public T next() {
            return from.next();
        }

        @Override
        public String toString() {
            return String.valueOf(name);
        }
    }

    public static class CollectorIterator<T> implements Iterator<T> {

        private String name;
        private Iterator<T> base;
        private List<T> collected = new ArrayList<>();

        public CollectorIterator(String name, Iterator<T> base) {
            this.name = name;
            this.base = base;
        }

        @Override
        public boolean hasNext() {
            return base.hasNext();
        }

        @Override
        public T next() {
            T x = base.next();
            collected.add(x);
            return x;
        }

        public List<T> getCollected() {
            return collected;
        }

        @Override
        public String toString() {
            if (name == null) {
                return "collector(" + base + ")";
            }
            return String.valueOf(name);
        }
    }

    private static class FlatMapIterator<TT, RR> implements Iterator<RR> {

        private final Iterator<TT> from;
        private final Function<? super TT, ? extends Iterator<? extends RR>> fun;
        Iterator<? extends RR> n;

        public FlatMapIterator(Iterator<TT> from, Function<? super TT, ? extends Iterator<? extends RR>> fun) {
            this.from = from;
            this.fun = fun;
            n = null;
        }

        @Override
        public boolean hasNext() {
            while (true) {
                if (n == null) {
                    if (from.hasNext()) {
                        TT p = from.next();
                        if (p == null) {
                            n = Collections.emptyIterator();
                        } else {
                            n = fun.apply(p);
                            if (n == null) {
                                n = Collections.emptyIterator();
                            }
                        }
                    } else {
                        return false;
                    }
                }
                if (n.hasNext()) {
                    return true;
                } else {
                    n = null;
                }
            }
        }

        @Override
        public RR next() {
            return n.next();
        }

        @Override
        public String toString() {
            return "FlattenIterator(" + from + ")";
        }
    }

    private static class SortIterator<T> implements Iterator<T> {

        private final boolean removeDuplicates;
        private final Iterator<T> it;
        private final Comparator<T> c;
        Iterator<T> base;

        public SortIterator(Iterator<T> it, Comparator<T> c, boolean removeDuplicates) {
            this.removeDuplicates = removeDuplicates;
            this.it = it;
            this.c = c;
            base = null;
        }

        public Iterator<T> getBase() {
            if (base == null) {
                if (removeDuplicates) {
                    base = toTreeSet(it, c).iterator();
                } else {
                    List<T> a = toList(it);
                    a.sort(c);
                    base = a.iterator();
                }
            }
            return base;
        }

        @Override
        public boolean hasNext() {
            return getBase().hasNext();
        }

        @Override
        public T next() {
            return getBase().next();
        }

        @Override
        public String toString() {
            if(removeDuplicates){
                return "SortDistinct(" + it + ")";
            }else{
                return "SortDuplicates(" + it + ")";
            }
        }
    }

}
