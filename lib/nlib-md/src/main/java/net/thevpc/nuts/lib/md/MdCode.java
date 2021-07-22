/**
 * ====================================================================
 *            thevpc-common-md : Simple Markdown Manipulation Library
 * <br>
 *
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
 *
 * @author thevpc
 */
public class MdCode extends MdAbstractElement {

    private String language;
    private String value;
    private boolean inline;

    public MdCode(String code, String value, boolean inline) {
        this.language = code==null?"":code;
        this.value = value;
        this.inline = inline;
    }

    public boolean isInline() {
        return inline;
    }

    public String getLanguage() {
        return language;
    }

    public String getValue() {
        return value;
    }

    @Override
    public MdElementType type() {
        return MdElementType.CODE;
    }

    @Override
    public String toString() {
        if(inline){
            String lang=language+((language.length()>0)?" ":"");
            return "```" + lang + value + "```";
        }
        return "```" + language + "\n"
                + value + "\n"
                + "```";
    }
    @Override
    public boolean isEndWithNewline() {
        return !isInline();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdCode mdCode = (MdCode) o;
        return inline == mdCode.inline && Objects.equals(language, mdCode.language) && Objects.equals(value, mdCode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, value, inline);
    }
}
