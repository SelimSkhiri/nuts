/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.thevpc.nuts.runtime.bundles.iter;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import net.thevpc.nuts.NutsSession;
import net.thevpc.nuts.runtime.standalone.util.CoreCollectionUtils;

/**
 * @author thevpc
 */
public class IteratorBuilder<T> {

    private final Iterator<T> it;

    private IteratorBuilder(Iterator<T> it) {
        if (it == null) {
            it = IteratorUtils.emptyIterator();
        }
        this.it = it;
    }

    public static <T> IteratorBuilder<T> ofCoalesce(List<Iterator<? extends T>> t) {
        return new IteratorBuilder<>(
                IteratorUtils.coalesce(t)
        );
    }

    public static <T> IteratorBuilder<T> ofList(List<Iterator<? extends T>> t) {
        return new IteratorBuilder<>(
                IteratorUtils.concat(t)
        );
    }

    public static <T> IteratorBuilder<T> of(Iterator<T> t) {
        return new IteratorBuilder<>(t);
    }

    public static <T> IteratorBuilder<T> ofLazyNamed(String name, Iterable<T> t) {
        return ofLazy(new NamedIterable<T>(name) {
            @Override
            public Iterator<T> iterator() {
                return t.iterator();
            }
        });
    }

    public static <T> IteratorBuilder<T> ofNamed(String name, Iterator<T> t) {
        return of(new NamedIterator<T>(t, name));
    }

    public static <T> IteratorBuilder<T> ofNodeAware(IterInfoNode info, Iterator<T> t) {
        return of(
                new IterInfoNodeAwareAdapter<>(t, info)
        );
    }

    public static <T> IteratorBuilder<T> ofLazy(Iterable<T> t) {
        return new IteratorBuilder<>(
                new LazyIterator(t)
        );
    }

//    public static IteratorBuilder<File> ofFileDFS(File file) {
//        return of(new FileDepthFirstIterator(file));
//    }

    public static <T> IteratorBuilder<T> ofArray(T... t) {
        return of(t == null ? IteratorUtils.emptyIterator() : Arrays.asList(t).iterator());
    }

    public static IteratorBuilder<File> ofFileList(File file) {
        return ofArray(file.listFiles());
    }

    public static IteratorBuilder<File> ofFileList(File file, boolean intcludeSelf) {
        if (intcludeSelf) {
            return ofArray(file).concat(ofArray(file.listFiles()));
        }
        return ofArray(file.listFiles());
    }

    public IteratorBuilder<T> filter(Predicate<T> t) {
        if (t == null) {
            return this;
        }
        return new IteratorBuilder<>(new FilteredIterator<>(it, t));
    }

    public IteratorBuilder<T> concat(IteratorBuilder<T> t) {
        return concat(t.it);
    }

    public IteratorBuilder<T> concat(Iterator<T> t) {
        if (t == null) {
            return this;
        }
        return new IteratorBuilder<>(IteratorUtils.concat(Arrays.asList(it, t)));
    }

    public <V> IteratorBuilder<V> map(Function<T, V> t) {
        return new IteratorBuilder<>(new ConvertedIterator<>(it, t));
    }

    public <V> IteratorBuilder<V> mapMulti(Function<T, List<V>> t) {
        return new IteratorBuilder<>(new ConvertedToListIterator<>(it, t));
    }

    public <V> IteratorBuilder<V> flatMap(Function<T, Iterator<V>> fun) {
        return new IteratorBuilder<>(IteratorUtils.flatMap(it, fun));
    }

    public <V> IteratorBuilder<V> convertMulti(Function<T, List<V>> t) {
        return new IteratorBuilder<>(new ConvertedToListIterator<>(it, t));
    }

    public <V> IteratorBuilder<T> sort(Comparator<T> t, boolean removeDuplicates) {
        return new IteratorBuilder<>(IteratorUtils.sort(it, t, removeDuplicates));
    }

    public <V> IteratorBuilder<T> distinct() {
        return distinct(null);
    }

    public <V> IteratorBuilder<T> distinct(Function<T, V> t) {
        if (t == null) {
            return new IteratorBuilder<>(IteratorUtils.distinct(it));
        } else {
            return new IteratorBuilder<>(IteratorUtils.distinct(it, t));
        }
    }

    public <V> IteratorBuilder<T> named(String n) {
        if (n != null) {
            return new IteratorBuilder<>(IteratorUtils.name(n, it));
        }
        return this;
    }

    public <V> IteratorBuilder<T> withInfo(IterInfoNode nfo) {
        if (nfo != null) {
            return new IteratorBuilder<>(new IterInfoNodeAwareAdapter<T>(it, nfo));
        }
        return this;
    }

    public <V> IteratorBuilder<T> withDefaultInfo(IterInfoNode nfo, NutsSession session) {
        if (nfo != null) {
            IterInfoNode a = IterInfoNode.resolveOrNull(it, session);
            if (a != null) {
                return this;
            }
            return withInfo(nfo);
        }
        return this;
    }

    public IteratorBuilder<T> safe(IteratorErrorHandlerType type) {
        return new IteratorBuilder<>(new ErrorHandlerIterator(type, it));
    }

    public IteratorBuilder<T> safeIgnore() {
        return safe(IteratorErrorHandlerType.IGNORE);
    }

    public IteratorBuilder<T> safePostpone() {
        return safe(IteratorErrorHandlerType.POSTPONE);
    }

    public IteratorBuilder<T> notNull() {
        return filter(IteratorUtils.NON_NULL);
    }

    public IteratorBuilder<String> notBlank() {
        return this.filter(IteratorUtils.NON_BLANK);
    }

    public Iterator<T> iterator() {
        return it;
    }

    public List<T> list() {
        return CoreCollectionUtils.toList(it);
    }

    public Iterator<T> build() {
        return it;
    }

    public List<T> toList() {
        return IteratorUtils.toList(it);
    }

}
