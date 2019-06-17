/**
 * ====================================================================
 *            Nuts : Network Updatable Things Service
 *                  (universal package manager)
 *
 * is a new Open Source Package Manager to help install packages
 * and libraries for runtime execution. Nuts is the ultimate companion for
 * maven (and other build managers) as it helps installing all package
 * dependencies at runtime. Nuts is not tied to java and is a good choice
 * to share shell scripts and other 'things' . Its based on an extensible
 * architecture to help supporting a large range of sub managers / repositories.
 *
 * Copyright (C) 2016-2019 Taha BEN SALAH
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * ====================================================================
 */
package net.vpc.app.nuts.core.format;

import net.vpc.app.nuts.NutsOutputFormat;
import net.vpc.app.nuts.NutsUnsupportedArgumentException;
import net.vpc.app.nuts.NutsWorkspace;
import net.vpc.app.nuts.core.format.json.DefaultSearchFormatJson;
import net.vpc.app.nuts.core.format.plain.DefaultSearchFormatPlain;
import net.vpc.app.nuts.core.format.props.DefaultSearchFormatProps;
import net.vpc.app.nuts.core.format.table.DefaultSearchFormatTable;
import net.vpc.app.nuts.core.format.tree.DefaultSearchFormatTree;
import net.vpc.app.nuts.core.format.xml.DefaultSearchFormatXml;
import net.vpc.app.nuts.NutsIterableFormat;

/**
 *
 * @author vpc
 */
public class DefaultNutsIncrementalOutputFormat extends NutsIncrementalOutputFormatBase {

    private NutsOutputFormat lastNutsOutputFormat;

    public DefaultNutsIncrementalOutputFormat(NutsWorkspace ws) {
        super(ws);
    }

    @Override
    public NutsIterableFormat getFormat() {
        NutsOutputFormat outputFormat = getOutputFormat();
        NutsIterableFormat old = super.getFormat();
        if (old == null || old.getOutputFormat() != outputFormat) {
            switch (outputFormat) {
                case JSON: {
                    return prepare(new DefaultSearchFormatJson(getWorkspace(), getValidSession(), getValidOut()));
                }
                case XML: {
                    return prepare(new DefaultSearchFormatXml(getWorkspace(), getValidSession(), getValidOut()));
                }
                case PLAIN: {
                    return prepare(new DefaultSearchFormatPlain(getWorkspace(), getValidSession(), getValidOut()));
                }
                case PROPS: {
                    return prepare(new DefaultSearchFormatProps(getWorkspace(), getValidSession(), getValidOut()));
                }
                case TABLE: {
                    return prepare(new DefaultSearchFormatTable(getWorkspace(), getValidSession(), getValidOut()));
                }
                case TREE: {
                    return prepare(new DefaultSearchFormatTree(getWorkspace(), getValidSession(), getValidOut()));
                }
                default: {
                    throw new NutsUnsupportedArgumentException(getWorkspace(), "Unsupproted " + outputFormat);
                }
            }
        }
        return old;
    }
}
