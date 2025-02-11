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
package net.thevpc.nuts.text;

import net.thevpc.nuts.NutsBlankable;
import net.thevpc.nuts.io.NutsPath;

import java.util.Objects;
import java.util.function.Function;

/**
 * @app.category Format
 */
public class NutsTextTransformConfig implements Cloneable, NutsBlankable {
    /**
     * when true all styles are removed, transform any node to PLAIN, or LIST only
     */
    private boolean filtered;
    /**
     * when true, every node is split over newlines. Newlines will remain as separate plain tokens
     */
    private boolean flatten;
    /**
     * when true, transform any node to PLAIN, STYLED or LIST
     */
    private boolean normalize;

    /**
     * when true any title will be prefixed with an incremental number according to {@code titleNumberSequence}
     */
    private boolean processTitleNumbers;

    /**
     * sequence to use when {@code processTitleNumbers==true}
     */
    private NutsTitleSequence titleNumberSequence;

    /**
     * when true, include commands are processed, resources are loaded to replace the given command
     */
    private boolean processIncludes;
    /**
     * current directory to load from included files
     */
    private NutsPath currentDir;

    /**
     * when true, replace all {@code "${varName}" } literal by evaluating  {@code varProvider.apply("${varName}") }.
     * default vars are available
     */
    private boolean processVars;
    private Function<String, String> varProvider;
    private String anchor;
    /**
     * when provided, ensure that to root level of the document is translated to {@code rootLevel}
     */
    private Integer rootLevel;

    /**
     * when provided, try to use this class loader for 'classpath:' resources
     */
    private ClassLoader importClassLoader;

    public boolean isProcessTitleNumbers() {
        return processTitleNumbers;
    }

    public NutsTextTransformConfig setProcessTitleNumbers(boolean processTitleNumbers) {
        this.processTitleNumbers = processTitleNumbers;
        return this;
    }

    public NutsTitleSequence getTitleNumberSequence() {
        return titleNumberSequence;
    }

    public NutsTextTransformConfig setTitleNumberSequence(NutsTitleSequence titleNumberSequence) {
        this.titleNumberSequence = titleNumberSequence;
        return this;
    }

    public boolean isFiltered() {
        return filtered;
    }

    public NutsTextTransformConfig setFiltered(boolean filtered) {
        this.filtered = filtered;
        return this;
    }

    public NutsTextTransformConfig copy() {
        return clone();
    }

    @Override
    protected NutsTextTransformConfig clone() {
        try {
            return (NutsTextTransformConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean isProcessIncludes() {
        return processIncludes;
    }

    public NutsTextTransformConfig setProcessIncludes(boolean processIncludes) {
        this.processIncludes = processIncludes;
        return this;
    }


    public Function<String, String> getVarProvider() {
        return varProvider;
    }

    public boolean isProcessVars() {
        return processVars;
    }

    public NutsTextTransformConfig setProcessVars(boolean processVars) {
        this.processVars = processVars;
        return this;
    }

    public NutsTextTransformConfig setVarProvider(Function<String, String> varProvider) {
        this.varProvider = varProvider;
        return this;
    }

    public String getAnchor() {
        return anchor;
    }

    public NutsTextTransformConfig setAnchor(String anchor) {
        this.anchor = anchor;
        return this;
    }

    public NutsTextTransformConfig setProcessAll(boolean enable) {
        setProcessTitleNumbers(enable);
        setProcessVars(enable);
        setProcessIncludes(enable);
        return this;
    }

    public Integer getRootLevel() {
        return rootLevel;
    }

    public NutsTextTransformConfig setRootLevel(Integer rootLevel) {
        this.rootLevel = rootLevel;
        return this;
    }

    public boolean isBlank() {
        return !filtered
                && !flatten
                && !normalize
                && !processTitleNumbers
                && !processIncludes
                && !processVars
                && titleNumberSequence==null
                && currentDir==null
                && varProvider==null
                && NutsBlankable.isBlank(anchor)
                && rootLevel==null
                && importClassLoader==null;
    }


    public NutsPath getCurrentDir() {
        return currentDir;
    }

    public NutsTextTransformConfig setCurrentDir(NutsPath currentDir) {
        this.currentDir = currentDir;
        return this;
    }


    public boolean isFlatten() {
        return flatten;
    }

    public NutsTextTransformConfig setFlatten(boolean flatten) {
        this.flatten = flatten;
        return this;
    }

    public boolean isNormalize() {
        return normalize;
    }

    public NutsTextTransformConfig setNormalize(boolean normalize) {
        this.normalize = normalize;
        return this;
    }

    public ClassLoader getImportClassLoader() {
        return importClassLoader;
    }

    public NutsTextTransformConfig setImportClassLoader(ClassLoader importClassLoader) {
        this.importClassLoader = importClassLoader;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutsTextTransformConfig that = (NutsTextTransformConfig) o;
        return filtered == that.filtered && flatten == that.flatten && normalize == that.normalize && processTitleNumbers == that.processTitleNumbers && processIncludes == that.processIncludes && processVars == that.processVars && Objects.equals(titleNumberSequence, that.titleNumberSequence) && Objects.equals(currentDir, that.currentDir) && Objects.equals(varProvider, that.varProvider) && Objects.equals(anchor, that.anchor) && Objects.equals(rootLevel, that.rootLevel) && Objects.equals(importClassLoader, that.importClassLoader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filtered, flatten, normalize, processTitleNumbers, titleNumberSequence, processIncludes, currentDir, processVars, varProvider, anchor, rootLevel, importClassLoader);
    }
}
