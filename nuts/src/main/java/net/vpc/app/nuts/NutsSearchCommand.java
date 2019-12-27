/**
 * ====================================================================
 * Nuts : Network Updatable Things Service
 * (universal package manager)
 * <p>
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 * <p>
 * Copyright (C) 2016-2017 Taha BEN SALAH
 * <p>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * ====================================================================
 */
package net.vpc.app.nuts;

import java.nio.file.Path;
import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

/**
 * Search command class helps searching multiple artifacts with all of their
 * files.
 *
 * @author vpc
 * @since 0.5.4
 */
public interface NutsSearchCommand extends NutsWorkspaceCommand {

    ////////////////////////////////////////////////////////
    // Setters
    ////////////////////////////////////////////////////////

    /**
     * reset ids to search for
     * @return {@code this} instance
     */
    NutsSearchCommand clearIds();

    /**
     * add id to search.
     *
     * @param id id to search
     * @return {@code this} instance
     */
    NutsSearchCommand addId(String id);

    /**
     * add id to search.
     *
     * @param id id to search
     * @return {@code this} instance
     */
    NutsSearchCommand addId(NutsId id);

    /**
     * add ids to search.
     *
     * @param ids id to search
     * @return {@code this} instance
     */
    NutsSearchCommand addIds(String... ids);

    /**
     * add ids to search.
     *
     * @param ids ids to search
     * @return {@code this} instance
     */
    NutsSearchCommand addIds(NutsId... ids);

    /**
     * remove id to search.
     *
     * @param id id to search
     * @return {@code this} instance
     */
    NutsSearchCommand removeId(String id);

    /**
     * remove id to search.
     *
     * @param id id to search
     * @return {@code this} instance
     */
    NutsSearchCommand removeId(NutsId id);

    /**
     * add id to search.
     *
     * @param id id to search
     * @return {@code this} instance
     */
    NutsSearchCommand id(String id);

    /**
     * set lib filter. lib (non app) only are retrieved.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand lib();

    /**
     * set lib filter. if true lib (non app) only are retrieved.
     *
     * @param enable lib filter
     * @return {@code this} instance
     */
    NutsSearchCommand lib(boolean enable);

    /**
     * set lib filter. if true lib (non app) only are retrieved.
     *
     * @param enable lib filter
     * @return {@code this} instance
     */
    NutsSearchCommand setLib(boolean enable);

    /**
     * set extensions filter. extensions only are retrieved.
     *
     * @return {@code this} instance
     * @since 0.5.7
     */
    NutsSearchCommand extensions();

    /**
     * set extensions filter. if true extensions only are retrieved.
     *
     * @param enable extensions filter
     * @return {@code this} instance
     * @since 0.5.7
     */
    NutsSearchCommand extensions(boolean enable);

    /**
     * set extensions filter. if true extensions only are retrieved.
     *
     * @param enable extensions filter
     * @return {@code this} instance
     * @since 0.5.7
     */
    NutsSearchCommand setExtension(boolean enable);


    /**
     * set companions filter. companions only are retrieved.
     *
     * @return {@code this} instance
     * @since 0.5.7
     */
    NutsSearchCommand companion();

    /**
     * set companions filter. if true companions only are retrieved.
     *
     * @param enable companions filter
     * @return {@code this} instance
     * @since 0.5.7
     */
    NutsSearchCommand companion(boolean enable);

    /**
     * set companions filter. if true companions only are retrieved.
     *
     * @param enable companions filter
     * @return {@code this} instance
     * @since 0.5.7
     */
    NutsSearchCommand setCompanion(boolean enable);

    /**
     * set app filter. non lib (app) only are retrieved.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand exec();

    /**
     * set app filter. if true non lib (app) only are retrieved.
     *
     * @param enable lib filter
     * @return {@code this} instance
     */
    NutsSearchCommand exec(boolean enable);

    /**
     * set app filter. if true non lib (app) only are retrieved.
     *
     * @param enable lib filter
     * @return {@code this} instance
     */
    NutsSearchCommand setExec(boolean enable);

    /**
     * add runtime id to search
     * @return {@code this} instance
     */
    NutsSearchCommand runtime();

    /**
     * add runtime id to search
     * @param enable when true include runtime id in search
     * @return {@code this} instance
     */
    NutsSearchCommand runtime(boolean enable);

    /**
     * add runtime id to search
     * @param enable when true include runtime id in search
     * @return {@code this} instance
     */
    NutsSearchCommand setRuntime(boolean enable);

    /**
     * set nuts app filter. nuts app (implementing NutsApplication) only are
     * retrieved.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand applications();

    /**
     * set nuts app filter. if true nuts app (implementing NutsApplication) only
     * are retrieved.
     *
     * @param enable ap filter
     * @return {@code this} instance
     */
    NutsSearchCommand applications(boolean enable);

    /**
     * set nuts app filter. if true nuts app (implementing NutsApplication) only
     * are retrieved.
     *
     * @param enable ap filter
     * @return {@code this} instance
     */
    NutsSearchCommand setApplication(boolean enable);

    /**
     * return true when runtime id is included in search
     * @return true when runtime id is included in search
     */
    boolean isRuntime();

    /**
     * companion filter
     *
     * @return companion filter
     */
    boolean isCompanion();

    /**
     * extension filter
     *
     * @return extension filter
     */
    boolean isExtension();

    /**
     * app filter
     *
     * @return app filter
     */
    boolean isExec();

    /**
     * nuts app filter
     *
     * @return nuts app filter
     */
    boolean isApplication();

    /**
     * lib filter
     *
     * @return lib filter
     */
    boolean isLib();

    /**
     * add id to search.
     *
     * @param id id to search
     * @return {@code this} instance
     */
    NutsSearchCommand id(NutsId id);

    /**
     * add ids to search.
     *
     * @param ids ids to search
     * @return {@code this} instance
     */
    NutsSearchCommand ids(String... ids);

    /**
     * add ids to search.
     *
     * @param ids ids to search
     * @return {@code this} instance
     */
    NutsSearchCommand ids(NutsId... ids);

    /**
     * add javascript filter.
     *
     * @param value javascript filter
     * @return {@code this} instance
     */
    NutsSearchCommand script(String value);

    /**
     * add javascript filter.
     *
     * @param value javascript filter
     * @return {@code this} instance
     */
    NutsSearchCommand addScript(String value);

    /**
     * remove javascript filter.
     *
     * @param value javascript filter
     * @return {@code this} instance
     */
    NutsSearchCommand removeScript(String value);

    /**
     * add javascript filter.
     *
     * @param value javascript filter
     * @return {@code this} instance
     */
    NutsSearchCommand scripts(Collection<String> value);

    /**
     * add javascript filter.
     *
     * @param value javascript filter
     * @return {@code this} instance
     */
    NutsSearchCommand addScripts(Collection<String> value);

    /**
     * add javascript filter.
     *
     * @param value javascript filter
     * @return {@code this} instance
     */
    NutsSearchCommand scripts(String... value);

    /**
     * add javascript filter.
     *
     * @param value javascript filter
     * @return {@code this} instance
     */
    NutsSearchCommand addScripts(String... value);

    /**
     * remove all javascript filters
     *
     * @return {@code this} instance
     */
    NutsSearchCommand clearScripts();

    /**
     * return javascript filters
     *
     * @return javascript filters
     */
    String[] getScripts();

    /**
     * reset searched for archs
     * @return {@code this} instance
     */
    NutsSearchCommand clearArchs();

    /**
     * define locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param values ids
     * @return {@code this} instance
     */
    NutsSearchCommand lockedIds(String... values);

    /**
     * define locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param values ids
     * @return {@code this} instance
     */
    NutsSearchCommand addLockedIds(String... values);

    /**
     * define locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param values ids
     * @return {@code this} instance
     */
    NutsSearchCommand lockedIds(NutsId... values);

    /**
     * define locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param values ids
     * @return {@code this} instance
     */
    NutsSearchCommand addLockedIds(NutsId... values);

    /**
     * reset locked ids
     * @return {@code this} instance
     */
    NutsSearchCommand clearLockedIds();

    /**
     * add arch to search
     * @param value arch to search for
     * @return {@code this} instance
     */
    NutsSearchCommand addArch(String value);

    /**
     * remove arch to search
     * @param value arch to remove
     * @return {@code this} instance
     */
    NutsSearchCommand removeArch(String value);

    /**
     * add archs to search
     * @param values arch to search for
     * @return {@code this} instance
     */
    NutsSearchCommand addArchs(Collection<String> values);

    /**
     * add archs to search
     * @param values arch to search for
     * @return {@code this} instance
     */
    NutsSearchCommand addArchs(String... values);

    /**
     * add arch to search
     * @param value arch to search for
     * @return {@code this} instance
     */
    NutsSearchCommand arch(String value);

    /**
     * add archs to search
     * @param values arch to search for
     * @return {@code this} instance
     */
    NutsSearchCommand archs(Collection<String> values);

    /**
     * add archs to search
     * @param values arch to search for
     * @return {@code this} instance
     */
    NutsSearchCommand archs(String... values);

    /**
     * add packaging to search
     * @param value packaging to search for
     * @return {@code this} instance
     */
    NutsSearchCommand packaging(String value);

    /**
     * add packagings to search
     * @param values packagings to search for
     * @return {@code this} instance
     */
    NutsSearchCommand packagings(Collection<String> values);

    /**
     * add packagings to search
     * @param values packagings to search for
     * @return {@code this} instance
     */
    NutsSearchCommand packagings(String... values);

    /**
     * reset packagings to search
     * @return {@code this} instance
     */
    NutsSearchCommand clearPackagings();

    /**
     * add packagings to search
     * @param values packagings to search for
     * @return {@code this} instance
     */
    NutsSearchCommand addPackagings(Collection<String> values);

    /**
     * add packagings to search
     * @param values packagings to search for
     * @return {@code this} instance
     */
    NutsSearchCommand addPackagings(String... values);

    /**
     * add packaging to search
     * @param value packaging to search for
     * @return {@code this} instance
     */
    NutsSearchCommand addPackaging(String value);

    /**
     * remove packaging from search
     * @param value packaging to remove
     * @return {@code this} instance
     */
    NutsSearchCommand removePackaging(String value);

    /**
     * reset repositories to search into
     * @return {@code this} instance
     */
    NutsSearchCommand clearRepositories();

    /**
     * add repositories to search into
     * @param values repositories to search into
     * @return {@code this} instance
     */
    NutsSearchCommand repositories(Collection<String> values);

    /**
     * add repositories to search into
     * @param values repositories to search into
     * @return {@code this} instance
     */
    NutsSearchCommand addRepositories(Collection<String> values);

    /**
     * add repositories to search into
     * @param values repositories to search into
     * @return {@code this} instance
     */
    NutsSearchCommand addRepositories(String... values);

    /**
     * add repository to search into
     * @param value repository to search into
     * @return {@code this} instance
     */
    NutsSearchCommand addRepository(String value);

    /**
     * add repository to search into
     * @param value repository to search into
     * @return {@code this} instance
     */
    NutsSearchCommand removeRepository(String value);

    /**
     * add repository to search into
     * @param value repository to search into
     * @return {@code this} instance
     */
    NutsSearchCommand repository(String value);

    /**
     * add repositories to search into
     * @param values repositories to search into
     * @return {@code this} instance
     */
    NutsSearchCommand repositories(String... values);

    /**
     * equivalent to {@code setSort(true)}
     *
     * @return sort mode
     */
    NutsSearchCommand sort();

    /**
     * sort result
     * @param sort enable sort
     * @return {@code this} instance
     */
    NutsSearchCommand sort(boolean sort);

    /**
     * sort result
     * @param sort enable sort
     * @return {@code this} instance
     */
    NutsSearchCommand setSorted(boolean sort);

    /**
     * search must return only latest versions for each artifact id
     *
     * @return {@code this} instance
     */
    NutsSearchCommand latest();

    /**
     * if true search must return only latest versions for each artifact id
     *
     * @param enable enable latest artifact id filter
     * @return {@code this} instance
     */
    NutsSearchCommand latest(boolean enable);

    /**
     * if true search must return only latest versions for each artifact id
     *
     * @param enable enable latest artifact id filter
     * @return {@code this} instance
     */
    NutsSearchCommand setLatest(boolean enable);

    /**
     * define dependency filter. applicable when using {@link #inlineDependencies()}
     * @param filter dependency filter
     * @return {@code this} instance
     */
    NutsSearchCommand dependencyFilter(NutsDependencyFilter filter);

    /**
     * add locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param id id to lock
     * @return {@code this} instance
     */
    NutsSearchCommand lockedId(NutsId id);

    /**
     * add locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param id id to lock
     * @return {@code this} instance
     */
    NutsSearchCommand addLockedId(NutsId id);

    /**
     * remove locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param id id to unlock
     * @return {@code this} instance
     */
    NutsSearchCommand removeLockedId(NutsId id);

    /**
     * add locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param id id to lock
     * @return {@code this} instance
     */
    NutsSearchCommand lockedId(String id);

    /**
     * remove locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param id id to unlock
     * @return {@code this} instance
     */
    NutsSearchCommand removeLockedId(String id);

    /**
     * add locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @param id id to lock
     * @return {@code this} instance
     */
    NutsSearchCommand addLockedId(String id);

    /**
     * return locked ids to prevent them to be updated or the force other ids to use them (the installed version).
     * @return locked ids
     */
    NutsId[] getLockedIds();

    /**
     * define dependency filter. applicable when using {@link #inlineDependencies()}
     * @param filter dependency filter
     * @return {@code this} instance
     */
    NutsSearchCommand setDependencyFilter(NutsDependencyFilter filter);

    /**
     * define dependency filter. applicable when using {@link #inlineDependencies()}
     * @param filter dependency filter
     * @return {@code this} instance
     */
    NutsSearchCommand dependencyFilter(String filter);

    /**
     * define dependency filter. applicable when using {@link #inlineDependencies()}
     * @param filter dependency filter
     * @return {@code this} instance
     */
    NutsSearchCommand setDependencyFilter(String filter);

    /**
     * define repository filter.
     * @param filter repository filter
     * @return {@code this} instance
     */
    NutsSearchCommand repositoryFilter(NutsRepositoryFilter filter);

    /**
     * define repository filter.
     * @param filter repository filter
     * @return {@code this} instance
     */
    NutsSearchCommand setRepositoryFilter(NutsRepositoryFilter filter);

    /**
     * define repository filter.
     * @param filter repository filter
     * @return {@code this} instance
     */
    NutsSearchCommand setRepository(String filter);

    /**
     * define descriptor filter.
     * @param filter descriptor filter
     * @return {@code this} instance
     */
    NutsSearchCommand descriptorFilter(NutsDescriptorFilter filter);

    /**
     * define descriptor filter.
     * @param filter descriptor filter
     * @return {@code this} instance
     */
    NutsSearchCommand setDescriptorFilter(NutsDescriptorFilter filter);

    /**
     * define descriptor filter.
     * @param filter descriptor filter
     * @return {@code this} instance
     */
    NutsSearchCommand descriptorFilter(String filter);

    /**
     * define descriptor filter.
     * @param filter descriptor filter
     * @return {@code this} instance
     */
    NutsSearchCommand setDescriptorFilter(String filter);

    /**
     * define id filter.
     * @param filter id filter
     * @return {@code this} instance
     */
    NutsSearchCommand idFilter(NutsIdFilter filter);

    /**
     * define id filter.
     * @param filter id filter
     * @return {@code this} instance
     */
    NutsSearchCommand setIdFilter(NutsIdFilter filter);

    /**
     * define id filter.
     * @param filter id filter
     * @return {@code this} instance
     */
    NutsSearchCommand idFilter(String filter);

    /**
     * define id filter.
     * @param filter id filter
     * @return {@code this} instance
     */
    NutsSearchCommand setIdFilter(String filter);

    /**
     * set armed (true) fail safe mode. null replaces NutsNotFoundException.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand failFast();

    /**
     * set armed (or disarmed) fail safe mode. if true, null replaces
     * NutsNotFoundException.
     *
     * @param enable if true, null replaces NutsNotFoundException.
     * @return {@code this} instance
     */
    NutsSearchCommand failFast(boolean enable);

    /**
     * set armed (or disarmed) fail safe mode. if true, null replaces
     * NutsNotFoundException.
     *
     * @param enable if true, null replaces NutsNotFoundException.
     * @return {@code this} instance
     */
    NutsSearchCommand setFailFast(boolean enable);

    /**
     * sort results. Comparator should handle types of the result.
     * @param comparator result comparator
     * @return {@code this}
     */
    NutsSearchCommand sort(Comparator comparator);

    /**
     * skip duplicates
     * @return {@code this}
     */
    NutsSearchCommand distinct();

    /**
     * skip duplicates
     * @param distinct skip duplicates
     * @return {@code this}
     */
    NutsSearchCommand distinct(boolean distinct);

    /**
     * skip duplicates
     * @param distinct skip duplicates
     * @return {@code this}
     */
    NutsSearchCommand setDistinct(boolean distinct);

    /**
     * copy content from given {@code other}
     * @param other other instance
     * @return {@code this}
     */
    NutsSearchCommand copyFrom(NutsSearchCommand other);

    /**
     * copy content from given {@code other}
     * @param other other instance
     * @return {@code this}
     */
    NutsSearchCommand copyFrom(NutsFetchCommand other);

    /**
     * create new instance copy of this
     * @return new instance
     */
    NutsSearchCommand copy();

    /**
     * include base package when searching for inlined dependencies
     * @param includeBasePackage include Base Package
     * @return {@code this} instance
     */
    NutsSearchCommand setBasePackage(boolean includeBasePackage);

    /**
     * include base package when searching for inlined dependencies
     * @param includeBasePackage include Base Package
     * @return {@code this} instance
     */
    NutsSearchCommand basePackage(boolean includeBasePackage);

    /**
     * include base package when searching for inlined dependencies
     * @return {@code this} instance
     */
    NutsSearchCommand basePackage();

    ////////////////////////////////////////////////////////
    // Getters
    ////////////////////////////////////////////////////////

    /**
     * return ids to search for
     * @return ids to search for
     */
    NutsId[] getIds();

    /**
     * return true if sort flag is armed.
     * @return true if sort flag is armed.
     */
    boolean isSorted();

    /**
     * return dependency filter
     * @return dependency filter
     */
    NutsDependencyFilter getDependencyFilter();

    /**
     * return repository filter
     * @return repository filter
     */
    NutsRepositoryFilter getRepositoryFilter();

    /**
     * return descriptor filter
     * @return descriptor filter
     */
    NutsDescriptorFilter getDescriptorFilter();

    /**
     * return id filter
     * @return id filter
     */
    NutsIdFilter getIdFilter();

    String[] getArch();

    String[] getPackaging();

    String[] getRepositories();

    /**
     * when true, NutsNotFoundException instances are ignored
     *
     * @return true if armed FailFast mode
     */
    boolean isFailFast();

    /**
     * result comparator
     * @return result comparator
     */
    Comparator getComparator();

    /**
     * true if duplicates are skipped
     * @return true if duplicates are skipped
     */
    boolean isDistinct();

    /**
     * target api version
     * @return target api version
     */
    String getTargetApiVersion();

    /**
     * set target api version
     * @param  targetApiVersion new value
     * @return target api version
     */
    NutsSearchCommand setTargetApiVersion(String targetApiVersion);

    /**
     * set target api version
     * @param  targetApiVersion new value
     * @return target api version
     */
    NutsSearchCommand targetApiVersion(String targetApiVersion);

    /**
     * true if base package flag is armed.
     * @return true if base package flag is armed.
     */
    boolean isBasePackage();

    /**
     * true if search must return only latest versions for each artifact id
     *
     * @return true if search must return only latest versions for each artifact id
     */
    boolean isLatest();

    /**
     * create fetch command initialized with this instance options.
     * @return fetch command
     */
    NutsFetchCommand toFetch();

    ////////////////////////////////////////////////////////
    // Result
    ////////////////////////////////////////////////////////

    /**
     * execute query and return result as ids
     * @return result as ids
     */
    NutsSearchResult<NutsId> getResultIds();

    /**
     * execute query and return result as definitions
     * @return result as definitions
     */
    NutsSearchResult<NutsDefinition> getResultDefinitions();

    /**
     * execute query and return result as class loader
     * @return result as class loader
     */
    ClassLoader getResultClassLoader();

    /**
     * execute query and return result as class loader
     * @param parent parent class loader
     * @return result as class loader
     */
    ClassLoader getResultClassLoader(ClassLoader parent);

    /**
     * execute query and return result as nuts path string
     * @return result as nuts path string
     */
    String getResultNutsPath();

    /**
     * execute query and return result as class path string
     * @return result as class path string
     */
    String getResultClassPath();

    ///////////////////////
    // SHARED
    ///////////////////////
    ////////////////////////////////////////////////////////
    // Setters
    ////////////////////////////////////////////////////////

    /**
     * set fetch strategy.
     *
     * @param fetchStrategy fetch strategy
     * @return {@code this} instance
     */
    NutsSearchCommand fetchStrategy(NutsFetchStrategy fetchStrategy);

    /**
     * set fetch strategy.
     *
     * @param fetchStrategy fetch strategy
     * @return {@code this} instance
     */
    NutsSearchCommand setFetchStrategy(NutsFetchStrategy fetchStrategy);

    /**
     * set or unset transitive mode
     *
     * @param enable if true, transitive mode is armed
     * @return {@code this} instance
     */
    NutsSearchCommand setTransitive(boolean enable);

    /**
     * set or unset transitive mode
     *
     * @param enable if true, transitive mode is armed
     * @return {@code this} instance
     */
    NutsSearchCommand transitive(boolean enable);

    /**
     * set transitive mode to true
     *
     * @return {@code this} instance
     */
    NutsSearchCommand transitive();

    /**
     * remote only
     *
     * @return {@code this} instance
     */
    NutsSearchCommand remote();

    /**
     * local only (installed or not)
     *
     * @return {@code this} instance
     */
    NutsSearchCommand offline();

    /**
     * local or remote. If local result found will not fetch remote.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand online();

//    /**
//     * only installed artifacts
//     *
//     * @return {@code this} instance
//     */
//    NutsSearchCommand installed();

    /**
     * all artifacts (local and remote). If local result found will any way
     * fetch remote.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand anyWhere();

    /**
     * remove all dependency scope filters.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand clearScopes();

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand scope(NutsDependencyScope scope);

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand addScope(NutsDependencyScope scope);

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand scopes(NutsDependencyScope... scope);

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand addScopes(NutsDependencyScope... scope);

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand removeScope(NutsDependencyScope scope);

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand scope(NutsDependencyScopePattern scope);

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand addScope(NutsDependencyScopePattern scope);

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand scopes(NutsDependencyScopePattern... scope);

    /**
     * add dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand addScopes(NutsDependencyScopePattern... scope);

    /**
     * remove dependency scope filter. Only relevant with {@link #dependencies(boolean)
     * } and {@link #dependenciesTree(boolean)}
     *
     * @param scope scope filter
     * @return {@code this} instance
     */
    NutsSearchCommand removeScope(NutsDependencyScopePattern scope);

    /**
     * default version only
     *
     * @return {@code this} instance
     * @since v0.5.5
     */
    NutsSearchCommand defaultVersions();

    /**
     * default version only filter
     *
     * @param enable if non null apply filter
     * @return {@code this} instance
     * @since v0.5.5
     */
    NutsSearchCommand defaultVersions(Boolean enable);

    /**
     * default version only filter
     *
     * @param enable if non null apply filter
     * @return {@code this} instance
     * @since v0.5.5
     */
    NutsSearchCommand setDefaultVersions(Boolean enable);

    /**
     * retrieve optional only
     *
     * @return {@code this} instance
     */
    NutsSearchCommand optional();

    /**
     * set option filter. if null filter is removed. if false only non optional
     * will be retrieved. if true, only optional will be retrieved.
     *
     * @param enable option filter
     * @return {@code this} instance
     */
    NutsSearchCommand optional(Boolean enable);

    /**
     * set option filter. if null filter is removed. if false only non optional
     * will be retrieved. if true, only optional will be retrieved.
     *
     * @param enable option filter
     * @return {@code this} instance
     */
    NutsSearchCommand setOptional(Boolean enable);

    /**
     * set index filter.if null index is removed. if false do not consider
     * index. if true, consider index.
     *
     * @param enable index filter.
     * @return {@code this} instance
     */
    NutsSearchCommand setIndexed(Boolean enable);

    /**
     * set index filter to true.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand indexed();

    /**
     * set index filter.if null index is removed. if false do not consider
     * index. if true, consider index.
     *
     * @param enable index filter.
     * @return {@code this} instance
     */
    NutsSearchCommand indexed(boolean enable);

    /**
     * enable inlined dependencies list retrieval
     *
     * @return {@code this} instance
     */
    NutsSearchCommand inlineDependencies();

    /**
     * enable/disable inlined dependencies list retrieval
     *
     * @param enable if true retrieval is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand inlineDependencies(boolean enable);

    /**
     * enable/disable inlined dependencies list retrieval
     *
     * @param enable if true retrieval is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand setInlineDependencies(boolean enable);

    /**
     * enable dependencies list retrieval
     *
     * @return {@code this} instance
     */
    NutsSearchCommand dependencies();

    /**
     * enable/disable dependencies list retrieval
     *
     * @param enable if true retrieval is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand dependencies(boolean enable);

    /**
     * enable/disable dependencies list retrieval
     *
     * @param enable if true retrieval is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand setDependencies(boolean enable);

    /**
     * enable dependencies tree retrieval
     *
     * @return {@code this} instance
     */
    NutsSearchCommand dependenciesTree();

    /**
     * enable/disable dependencies tree retrieval
     *
     * @param enable if true retrieval is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand dependenciesTree(boolean enable);

    /**
     * enable/disable dependencies tree retrieval
     *
     * @param enable if true retrieval is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand setDependenciesTree(boolean enable);

    /**
     * enable/disable effective descriptor evaluation
     *
     * @param enable if true evaluation is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand setEffective(boolean enable);

    /**
     * enable/disable effective descriptor evaluation
     *
     * @param enable if true evaluation is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand effective(boolean enable);

    /**
     * enable effective descriptor evaluation
     *
     * @return {@code this} instance
     */
    NutsSearchCommand effective();

    /**
     * enable retrieval from cache
     *
     * @return {@code this} instance
     */
    NutsSearchCommand cached();

    /**
     * enable/disable retrieval from cache
     *
     * @param enable if true cache is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand cached(boolean enable);

    /**
     * enable/disable retrieval from cache
     *
     * @param enable if true cache is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand setCached(boolean enable);

    /**
     * enable retrieval of content info
     *
     * @return {@code this} instance
     */
    NutsSearchCommand content();

    /**
     * enable/disable retrieval of content info
     *
     * @param enable if true retrieval is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand content(boolean enable);

    /**
     * enable/disable retrieval of content info
     *
     * @param enable if true retrieval is enabled.
     * @return {@code this} instance
     */
    NutsSearchCommand setContent(boolean enable);

    /**
     * set locating where to fetch the artifact. If the location is a folder, a
     * new name will be generated.
     *
     * @param fileOrFolder path to store to
     * @return {@code this} instance
     */
    NutsSearchCommand setLocation(Path fileOrFolder);

    /**
     * set locating where to fetch the artifact. If the location is a folder, a
     * new name will be generated.
     *
     * @param fileOrFolder path to store to
     * @return {@code this} instance
     */
    NutsSearchCommand location(Path fileOrFolder);

    /**
     * unset location to store to fetched id and fall back to default location.
     *
     * @return {@code this} instance
     */
    NutsSearchCommand setDefaultLocation();

    ////////////////////////////////////////////////////////
    // Getters
    ////////////////////////////////////////////////////////

    /**
     * get locating where to fetch the artifact. If the location is a folder, a
     * new name will be generated.
     *
     * @return location path
     */
    Path getLocation();

    /**
     * fetch strategy
     * @return fetch strategy
     */
    NutsFetchStrategy getFetchStrategy();

    /**
     * true if indexes are enabled
     * @return true if indexes are enabled
     */
    boolean isIndexed();

    /**
     * scope filter filter. applicable with {@link #inlineDependencies()}
     * @return optional filter
     */
    Set<NutsDependencyScope> getScope();

    /**
     * optional filter. When non null will filter
     * dependencies from {@link #inlineDependencies()}
     * @return optional filter
     */
    Boolean getOptional();

    /**
     * true if content is resolved
     * @return true if content is resolved
     */
    boolean isContent();

    /**
     * true if descriptor is resolved against its effective value
     * @return true if descriptor is resolved against its effective value
     */
    boolean isEffective();

    /**
     * true if dependencies are inlined
     * @return true if dependencies are inlined
     */
    boolean isInlineDependencies();

    /**
     * true if dependencies as list is activated
     * @return true if dependencies as list is activated
     */
    boolean isDependencies();

    /**
     * true if dependencies as tree is activated
     * @return true if dependencies as tree is activated
     */
    boolean isDependenciesTree();

    /**
     * true if transitive is enabled
     * @return true if transitive is enabled
     */
    boolean isTransitive();

    /**
     * true if cache is enabled
     * @return true if cache is enabled
     */
    boolean isCached();

    /**
     * search for default versions status.
     * <ul>
     * <li>return true of only default values are searched for</li>
     * <li>return false of only default values are searched for</li>
     * <li>return null of both default values and non default ones are searched for</li>
     * </ul>
     *
     * @return search for default versions status
     * @since v0.5.5
     */
    Boolean getDefaultVersions();

    //
    // NutsWorkspaceCommand overridden methods
    //    

    /**
     * update session
     *
     * @param session session
     * @return {@code this} instance
     */
    @Override
    NutsSearchCommand session(NutsSession session);

    /**
     * update session
     *
     * @param session session
     * @return {@code this} instance
     */
    @Override
    NutsSearchCommand setSession(NutsSession session);

    /**
     * configure the current command with the given arguments. This is an
     * override of the {@link NutsConfigurable#configure(boolean, java.lang.String...)
     * }
     * to help return a more specific return type;
     *
     * @param skipUnsupported when true, all unsupported options are skipped
     * @param args            argument to configure with
     * @return {@code this} instance
     */
    @Override
    NutsSearchCommand configure(boolean skipUnsupported, String... args);

    /**
     * execute the command and return this instance
     *
     * @return {@code this} instance
     */
    @Override
    NutsSearchCommand run();

    /**
     * return result as content paths
     *
     * @return result as content paths
     */
    NutsSearchResult<String> getResultPaths();

    /**
     * return result as content path names
     *
     * @return result as content path names
     */
    NutsSearchResult<String> getResultPathNames();

    /**
     * execute query and return install dates
     * @return query result
     */
    NutsSearchResult<Instant> getResultInstallDates();

    /**
     * execute query and return install users
     * @return query result
     */
    NutsSearchResult<String> getResultInstallUsers();

    /**
     * execute query and return install folders
     * @return query result
     */
    NutsSearchResult<Path> getResultInstallFolders();

    /**
     * execute query and return store location path
     * @param location location type to return
     * @return query result
     */
    NutsSearchResult<Path> getResultStoreLocations(NutsStoreLocation location);

    /**
     * execute query and return the selected columns.
     * Supported columns are :
     * <ul>
     *     <li>all</li>
     *     <li>long</li>
     *     <li>status</li>
     *     <li>install-date</li>
     *     <li>install-user</li>
     *     <li>install-folder</li>
     *     <li>repository</li>
     *     <li>repository-id</li>
     *     <li>id</li>
     *     <li>name</li>
     *     <li>arch</li>
     *     <li>packaging</li>
     *     <li>platform</li>
     *     <li>os</li>
     *     <li>osdist</li>
     *     <li>exec-entry</li>
     *     <li>file-name</li>
     *     <li>file</li>
     *     <li>var-location</li>
     *     <li>temp-folder</li>
     *     <li>config-folder</li>
     *     <li>lib-folder</li>
     *     <li>log-folder</li>
     *     <li>cache-folder</li>
     *     <li>apps-folder</li>
     * </ul>
     * @return query result
     */
    NutsSearchResult<String[]> getResultStrings(String[] columns);

    /**
     * return result as artifact names
     *
     * @return result as artifact names
     */
    NutsSearchResult<String> getResultNames();

    /**
     * return result as operating system names
     *
     * @return result as operating system names
     */
    NutsSearchResult<String> getResultOses();

    /**
     * return result as execution entries
     *
     * @return result as execution entries
     */
    NutsSearchResult<NutsExecutionEntry> getResultExecutionEntries();

    /**
     * return result as osdist names
     *
     * @return result as osdist names
     */
    NutsSearchResult<String> getResultOsdists();

    /**
     * return result as packagings
     *
     * @return result as packagings
     */
    NutsSearchResult<String> getResultPackagings();

    /**
     * return result as platforms
     *
     * @return result as platforms
     */
    NutsSearchResult<String> getResultPlatforms();

    /**
     * return result as archs
     *
     * @return result as archs
     */
    NutsSearchResult<String> getResultArchs();

    /**
     * enable print search result
     *
     * @return {@code this} instance
     */
    NutsSearchCommand printResult();

    /**
     * enable print search result
     *
     * @param enable lib filter
     * @return {@code this} instance
     */
    NutsSearchCommand printResult(boolean enable);

    /**
     * enable print search result
     *
     * @param enable lib filter
     * @return {@code this} instance
     */
    NutsSearchCommand setPrintResult(boolean enable);

    /**
     * true when print result
     *
     * @return lib filter
     */
    boolean isPrintResult();

    /**
     * return the defined installStatus
     * @return {@code this} instance
     */
    NutsInstallStatus getInstallStatus();

    /**
     * search for non packages with the given {@code installStatus}
     * @return {@code this} instance
     */
    NutsSearchCommand setInstallStatus(NutsInstallStatus installStatus);

    /**
     * search for non packages with the given {@code installStatus}
     * @return {@code this} instance
     */
    NutsSearchCommand installStatus(NutsInstallStatus installStatus);

    /**
     * search for non installed packages
     * @return {@code this} instance
     */
    NutsSearchCommand installed();

    /**
     * search for included (in other installations as dependency) packages
     * @return {@code this} instance
     */
    NutsSearchCommand included();

    /**
     * search for non installed or included (in other installations as dependency) packages
     * @return {@code this} instance
     */
    NutsSearchCommand installedOrIncluded();

    /**
     * search for non installed packages
     * @return {@code this} instance
     */
    NutsSearchCommand notInstalled();
}
