/**
 * ====================================================================
 * thevpc-common-md : Simple Markdown Manipulation Library
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
package net.thevpc.nuts.lib.md;

import java.util.Objects;

/**
 * @author thevpc
 */
public class MdNumberedList extends MdParent {
    private MdElementType id;
    public MdNumberedList(MdNumberedItem[] content) {
        super(content);
        id=new MdElementType(MdElementTypeGroup.UNNUMBERED_LIST,getChild(0).type().depth());
    }

    public MdNumberedList(int depth,MdNumberedItem[] content) {
        super(content);
        id=new MdElementType(MdElementTypeGroup.UNNUMBERED_LIST,depth);
    }

    public MdElementType type() {
        return id;
    }

    public boolean isInline() {
        return false;
    }

    @Override
    public MdNumberedItem[] getChildren() {
        return (MdNumberedItem[]) super.getChildren();
    }

    @Override
    public MdNumberedItem getChild(int i) {
        return (MdNumberedItem) super.getChild(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (i > 0) {
                if (!isInline()) {
                    sb.append("\n");
                }
            }
            sb.append(getChild(i));
        }
        return sb.toString();
    }

    @Override
    public boolean isEndWithNewline() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MdNumberedList that = (MdNumberedList) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
