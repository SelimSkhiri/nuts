/**
 * ====================================================================
 * Nuts : Network Updatable Things Service
 * (universal package manager)
 * <br>
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 *
 * <br>
 * <p>
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
package net.thevpc.nuts.util;

import net.thevpc.nuts.*;
import net.thevpc.nuts.elem.NutsElement;
import net.thevpc.nuts.elem.NutsElements;
import net.thevpc.nuts.spi.NutsStreams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Find Result items from find command
 *
 * @param <T> Result Type
 * @author thevpc
 * @app.category Base
 * @see NutsSearchCommand#getResultIds()
 * @since 0.5.4
 */
public interface NutsStream<T> extends NutsIterable<T> {
    static <T> NutsStream<T> of(T[] str, Function<NutsSession, NutsElement> name, NutsSession session) {
        return NutsStreams.of(session).createStream(str, name);
    }

    static <T> NutsStream<T> of(Iterable<T> str, Function<NutsSession, NutsElement> name, NutsSession session) {
        return NutsStreams.of(session).createStream(str, name);
    }

    static <T> NutsStream<T> of(Iterator<T> str, Function<NutsSession, NutsElement> name, NutsSession session) {
        return NutsStreams.of(session).createStream(str, name);
    }

    static <T> NutsStream<T> of(Stream<T> str, Function<NutsSession, NutsElement> name, NutsSession session) {
        return NutsStreams.of(session).createStream(str, name);
    }

    static <T> NutsStream<T> of(T[] str, NutsSession session) {
        return NutsStreams.of(session).createStream(str, e-> NutsElements.of(e).ofString("array"));
    }

    static <T> NutsStream<T> of(Iterable<T> str, NutsSession session) {
        return NutsStreams.of(session).createStream(str, e->NutsElements.of(e).ofString("iterable"));
    }

    static <T> NutsStream<T> of(Iterator<T> str, NutsSession session) {
        return NutsStreams.of(session).createStream(str, e->NutsElements.of(e).ofString("iterator"));
    }

    static <T> NutsStream<T> of(Stream<T> str, NutsSession session) {
        return NutsStreams.of(session).createStream(str, e->NutsElements.of(e).ofString("stream"));
    }

    static <T> NutsStream<T> of(NutsIterable<T> str, NutsSession session) {
        return NutsStreams.of(session).createStream(str);
    }

    static <T> NutsStream<T> of(NutsIterator<T> str, NutsSession session) {
        return NutsStreams.of(session).createStream(str);
    }

    static <T> NutsStream<T> ofEmpty(NutsSession session) {
        return NutsStreams.of(session).createEmptyStream();
    }

    static <T> NutsStream<T> ofSingleton(T element, NutsSession session) {
        return of(Arrays.asList(element), session);
    }

    /**
     * return result as a  java.util.List .
     * <p>
     * consumes the result and returns a list Calling this method twice will
     * result in unexpected behavior (may return an empty list as the result is
     * already consumed or throw an Exception)
     *
     * @return result as a  java.util.List
     */
    List<T> toList();

    Set<T> toSet();

    Set<T> toSortedSet();

    Set<T> toOrderedSet();

    /**
     * return the first value or null if none found.
     * <p>
     * Calling this method twice will result in unexpected behavior (may return
     * an incorrect value such as null as the result is already consumed or
     * throw an Exception)
     *
     * @return the first value or null if none found
     */
    T first();

    /**
     * return the last value or null if none found. consumes all of the stream
     * <p>
     * Calling this method twice will result in unexpected behavior (may return
     * an incorrect value such as null as the result is already consumed or
     * throw an Exception)
     *
     * @return the last value or null if none found
     */
    T last();

    /**
     * return the first value or NutsNotFoundException if not found.
     * <p>
     * Calling this method twice will result in unexpected behavior (may return
     * an incorrect value such as null as the result is already consumed or
     * throw an Exception)
     *
     * @return the first value or NutsNotFoundException if not found
     */
    T required() throws NutsNotFoundException;

    /**
     * return the first value while checking that there are no more elements.
     * <p>
     * Calling this method twice will result in unexpected behavior (may return
     * an incorrect value such as null as the result is already consumed or
     * throw an Exception)
     *
     * @return the first value while checking that there are no more elements to
     * consume. An IllegalArgumentException is thrown if there are no elements
     * to consume. An IllegalArgumentException is also thrown if the are more
     * than one element consumed
     */
    T singleton() throws NutsTooManyElementsException, NutsNotFoundException;

    /**
     * return result as a  java.util.stream.Stream .
     * <p>
     * Calling this method twice will result in unexpected behavior (may return
     * 0 as the result is already consumed or throw an Exception)
     *
     * @return result as a  java.util.stream.Stream
     */
    Stream<T> stream();


    /**
     * return elements count of this result.
     * <p>
     * consumes the result and returns the number of elements consumed. Calling
     * this method twice will result in unexpected behavior (may return 0 as the
     * result is already consumed or throw an Exception)
     *
     * @return elements count of this result.
     */
    long count();

    /**
     * return NutsStream a stream consisting of the results of applying the given function to the elements of this stream.
     *
     * @param <R>    to type
     * @param mapper mapper
     * @return NutsStream a stream consisting of the results of applying the given function to the elements of this stream.
     */
    <R> NutsStream<R> map(NutsFunction<? super T, ? extends R> mapper);

    <R> NutsStream<R> map(Function<? super T, ? extends R> mapper, String name);

    <R> NutsStream<R> map(Function<? super T, ? extends R> mapper, NutsElement name);

    <R> NutsStream<R> map(Function<? super T, ? extends R> mapper, Function<NutsSession, NutsElement> name);

    <R> NutsStream<R> mapUnsafe(NutsUnsafeFunction<? super T, ? extends R> mapper, NutsFunction<Exception, ? extends R> onError);

    NutsStream<T> sorted();

    NutsStream<T> sorted(NutsComparator<T> comp);

    NutsStream<T> distinct();

    <R> NutsStream<T> distinctBy(NutsFunction<T, R> d);

    NutsStream<T> nonNull();

    NutsStream<T> nonBlank();

    NutsStream<T> filter(NutsPredicate<? super T> predicate);

    NutsStream<T> filter(Predicate<? super T> predicate, String name);

    NutsStream<T> filter(Predicate<? super T> predicate, NutsElement name);

    NutsStream<T> filter(Predicate<? super T> predicate, Function<NutsSession, NutsElement> info);

    NutsStream<T> filterNonNull();

    NutsStream<T> filterNonBlank();

    NutsStream<T> coalesce(NutsIterator<? extends T> other);

    <A> A[] toArray(IntFunction<A[]> generator);

    <K, U> Map<K, U> toMap(Function<? super T, ? extends K> keyMapper,
                           Function<? super T, ? extends U> valueMapper);

    <K, U> Map<K, U> toOrderedMap(Function<? super T, ? extends K> keyMapper,
                                  Function<? super T, ? extends U> valueMapper);

    <K, U> Map<K, U> toSortedMap(Function<? super T, ? extends K> keyMapper,
                                 Function<? super T, ? extends U> valueMapper);

    <R> NutsStream<R> flatMapIter(NutsFunction<? super T, ? extends Iterator<? extends R>> mapper);

    <R> NutsStream<R> flatMapList(NutsFunction<? super T, ? extends List<? extends R>> mapper);

    <R> NutsStream<R> flatMapArray(NutsFunction<? super T, ? extends R[]> mapper);

    <R> NutsStream<R> flatMap(NutsFunction<? super T, ? extends Stream<? extends R>> mapper);

    <R> NutsStream<R> flatMapStream(NutsFunction<? super T, ? extends NutsStream<? extends R>> mapper);

    <K> Map<K, List<T>> groupBy(NutsFunction<? super T, ? extends K> classifier);

    <K> NutsStream<Map.Entry<K, List<T>>> groupedBy(NutsFunction<? super T, ? extends K> classifier);

    NutsOptional<T> findAny();

    NutsOptional<T> findFirst();

    DoubleStream flatMapToDouble(NutsFunction<? super T, ? extends DoubleStream> mapper);

    IntStream flatMapToInt(NutsFunction<? super T, ? extends IntStream> mapper);

    LongStream flatMapToLong(NutsFunction<? super T, ? extends LongStream> mapper);

    boolean allMatch(Predicate<? super T> predicate);

    boolean noneMatch(Predicate<? super T> predicate);

    NutsStream<T> limit(long maxSize);

    NutsIterator<T> iterator();

    <R> R collect(Supplier<R> supplier,
                  BiConsumer<R, ? super T> accumulator,
                  BiConsumer<R, R> combiner);

    <R, A> R collect(Collector<? super T, A, R> collector);

    NutsOptional<T> min(Comparator<? super T> comparator);

    NutsOptional<T> max(Comparator<? super T> comparator);

}
