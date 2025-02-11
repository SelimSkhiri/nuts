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
package net.thevpc.nuts.spi;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Created by vpc on 1/8/17.
 *
 * @app.category SPI Base
 * @since 0.5.4
 */
public class NutsTransportParamTextFilePart extends NutsTransportParamPart {

    private final String name;
    private final String fileName;
    private final Path value;

    public NutsTransportParamTextFilePart(String name, String fileName, Path value) {
        this.name = name;
        this.fileName = fileName;
        this.value = value;

    }

    public String getFileName() {
        return fileName;
    }

    public String getName() {
        return name;
    }

    public Path getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fileName, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutsTransportParamTextFilePart that = (NutsTransportParamTextFilePart) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(value, that.value);
    }

    @Override
    public String toString() {
        return "NutsTransportParamTextFilePart{" +
                "name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", value=" + value +
                '}';
    }
}
