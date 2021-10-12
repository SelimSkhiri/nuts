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
package net.thevpc.nuts.runtime.core.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.thevpc.nuts.*;
import net.thevpc.nuts.runtime.core.filters.version.DefaultNutsVersionFilter;
import net.thevpc.nuts.runtime.core.util.CoreNumberUtils;
import net.thevpc.nuts.runtime.core.util.CoreStringUtils;

/**
 * Created by vpc on 1/15/17.
 */
public class DefaultNutsVersion /*extends DefaultNutsTokenFilter*/ implements NutsVersion {

    private static final long serialVersionUID = 1L;
//    public static final NutsVersion EMPTY = new DefaultNutsVersion("");
    private VersionParts parts;
    private transient NutsSession session;
    protected String expression;
//    public static NutsVersion valueOf(String value, NutsSession session) {
//        value = NutsUtilStrings.trim(value);
//        if (value.isEmpty()) {
//            return new DefaultNutsVersion("",session);
//        }
//        if(PATTERN.matcher(value).matches()) {
//            return new DefaultNutsVersion(value, session);
//        }
//        throw new NutsParseEnumException();
//    }

    public DefaultNutsVersion(String expression,NutsSession session) {
        this.expression=(NutsUtilStrings.trim(expression));
        this.session=session;
    }
    @Override
    public boolean isNull() {
        return expression == null;
    }

    @Override
    public boolean isBlank() {
        return expression == null || expression.trim().isEmpty();
    }


    @Override
    public NutsFormat formatter() {
        return session.version().formatter().setVersion(this);
    }

    @Override
    public String getValue() {
        return expression;
    }

    @Override
    public int compareTo(String other) {
        return compareVersions(expression, other);
    }

    @Override
    public int compareTo(NutsVersion other) {
        return compareTo(other == null ? null : other.getValue());
    }

    @Override
    public NutsVersionFilter filter() {
        return DefaultNutsVersionFilter.parse(expression, session);
    }

    @Override
    public NutsVersion compatNewer() {
        String v = toExplicitSingleValueOrNullString();
        if(v==null) {
            return this;
        }
        return new DefaultNutsVersion("["+expression+",[", session);
    }

    @Override
    public NutsVersion compatOlder() {
        String v = toExplicitSingleValueOrNullString();
        if(v==null) {
            return this;
        }
        return new DefaultNutsVersion("],"+v+"]", session);
    }

    private String toExplicitSingleValueOrNullString(){
        if(!isBlank() && !isFilter()){
            return expression;
        }
        return null;
    }

    private String toSingleValueOrNullString(){
        NutsVersionInterval[] nutsVersionIntervals = intervals();
        if(nutsVersionIntervals.length==1){
            if(nutsVersionIntervals[0].isFixedValue()){
                return nutsVersionIntervals[0].getLowerBound();
            }
        }
        return null;
    }

    @Override
    public NutsVersionInterval[] intervals() {
        NutsVersionFilter s = filter();
        if (s instanceof DefaultNutsVersionFilter) {
            return ((DefaultNutsVersionFilter) s).getIntervals();
        }
        return new NutsVersionInterval[0];
    }

    @Override
    public boolean isFilter() {
        for (char c : expression.toCharArray()) {
            switch (c){
                case '*':
                case ',':
                case '(':
                case ')':
                case '[':
                case ']':{
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isSingleValue() {
        NutsVersionInterval[] nutsVersionIntervals = intervals();
        return nutsVersionIntervals.length != 0 && nutsVersionIntervals.length <= 1 && nutsVersionIntervals[0].isFixedValue();
    }

    @Override
    public NutsVersion inc() {
        return inc(-1);
    }

    @Override
    public NutsVersion inc(int position) {
        return inc(position, 1);
    }

    public int size() {
        VersionParts parts = getParts();
        return parts.size();
    }

    @Override
    public int numberSize() {
        return getParts().getDigitCount();
    }

    private VersionParts getParts() {
        if(parts==null){
            parts=splitVersionParts2(getValue());
        }
        return parts;
    }

    public String get(int level) {
        VersionParts parts = getParts();
        int size = parts.size();
        if (level >= 0) {
            return parts.get(level).string;
        }else{
            int x=size+level;
            return parts.get(x).string;
        }
    }

    public BigInteger getNumber(int level) {
        VersionParts parts = getParts();
        int size = parts.getDigitCount();
        if (level >= 0) {
            return new BigInteger(parts.getDigit(level).string);
        }else{
            int x=size+level;
            return new BigInteger(parts.getDigit(x).string);
        }
    }

    @Override
    public int getInt(int index, int defaultValue) {
        return getNumber(index,BigInteger.valueOf(defaultValue)).intValue();
    }

    @Override
    public long getLong(int index, long defaultValue) {
        return getNumber(index,BigInteger.valueOf(defaultValue)).longValue();
    }


    public BigInteger getNumber(int level, BigInteger defaultValue) {
        VersionParts parts = getParts();
        int size = parts.getDigitCount();
        if (level >= 0) {
            if(level<size) {
                return new BigInteger(parts.getDigit(level).string);
            }
        }else{
            int x=size+level;
            if(x<size) {
                return new BigInteger(parts.getDigit(x).string);
            }
        }
        return defaultValue;
    }

    @Override
    public NutsVersion inc(int position, long amount) {
        return new DefaultNutsVersion(incVersion(getValue(), position, amount),session);
    }

    @Override
    public NutsVersion inc(int position, BigInteger amount) {
        return new DefaultNutsVersion(incVersion(getValue(), position, amount),session);
    }

    @Override
    public String toString() {
        return expression == null ? "" : String.valueOf(expression);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultNutsVersion version = (DefaultNutsVersion) o;

        return expression != null ? expression.equals(version.expression) : version.expression == null;

    }

    @Override
    public int hashCode() {
        return expression != null ? expression.hashCode() : 0;
    }

//    @Override
//    public boolean matches(String expression) {
//        if (NutsBlankable.isBlank(expression)) {
//            return true;
//        }
//        return DefaultNutsVersionFilter.parse(expression,session).acceptVersion(this, session);
//    }

    public static boolean versionMatches(String version, String pattern) {
        if (isBlankVersion(pattern)) {
            return true;
        }
        return pattern.equals(version);
    }

    public static String incVersion(String oldVersion, int level, long count) {
        return incVersion(oldVersion,level,BigInteger.valueOf(count));
    }
    public static String incVersion(String oldVersion, int level, BigInteger count) {
        if(count==null){
            count=BigInteger.ZERO;
        }
        VersionParts parts = splitVersionParts2(oldVersion);
        int digitCount = parts.getDigitCount();
        if (digitCount == 0) {
            parts.addDigit(BigInteger.ZERO, ".");
            digitCount = parts.getDigitCount();
        }
        if (level < 0) {
            level = digitCount+level;
            while(level<0){
                parts.addDigit(BigInteger.ZERO, ".");
                level++;
            }
            VersionPart digit = parts.getDigit(level);
            digit.string = String.valueOf(new BigInteger(digit.string).add(count));
            return parts.toString();
        }else {
            for (int i = digitCount; i < level; i++) {
                parts.addDigit(BigInteger.ZERO, ".");
            }
            VersionPart digit = parts.getDigit(level);
            digit.string = String.valueOf(new BigInteger(digit.string).add(count));
            return parts.toString();
        }
    }

    public static int compareVersions(String v1, String v2) {
        v1 = NutsUtilStrings.trim(v1);
        v2 = NutsUtilStrings.trim(v2);
        if (v1.equals(v2)) {
            return 0;
        }
        if (NutsConstants.Versions.LATEST.equals(v1)) {
            return 1;
        }
        if (NutsConstants.Versions.LATEST.equals(v2)) {
            return -1;
        }
        if (NutsConstants.Versions.RELEASE.equals(v1)) {
            return 1;
        }
        if (NutsConstants.Versions.RELEASE.equals(v2)) {
            return -1;
        }
        String[] v1arr = splitVersionParts(v1);
        String[] v2arr = splitVersionParts(v2);
        for (int i = 0; i < Math.max(v1arr.length, v2arr.length); i++) {
            if (i >= v1arr.length) {
                if (v2arr[i].equalsIgnoreCase("SNAPSHOT")) {
                    return 1;
                }
                return -1;
            }
            if (i >= v2arr.length) {
                if (v1arr[i].equalsIgnoreCase("SNAPSHOT")) {
                    return -1;
                }
                return 1;
            }
            int x = compareVersionItem(v1arr[i], v2arr[i]);
            if (x != 0) {
                return x;
            }
        }
        return 0;
    }

    private static class VersionPart {

        String string;
        boolean digit;

        public VersionPart(String string, boolean digit) {
            this.string = string;
            this.digit = digit;
        }

        @Override
        public String toString() {
            String name = digit ? "digit" : "sep";
            return name + "(" + string + ")";
        }
    }

    private static class VersionParts {

        List<VersionPart> all;

        public VersionParts(List<VersionPart> all) {
            this.all = all;
        }

        public VersionPart get(int index) {
            return all.get(index);
        }

        public int size() {
            return all.size();
        }

        public int getDigitCount() {
            int c = 0;
            for (VersionPart s : all) {
                if (s.digit) {
                    c++;
                }
            }
            return c;
        }

        public VersionPart getDigit(int index) {
            int c = 0;
            for (VersionPart s : all) {
                if (s.digit) {
                    if (c == index) {
                        return s;
                    }
                    c++;
                }
            }
            return null;
        }

        public void insertDigit(long val, String sep) {
            if (all.size() == 0) {
                all.add(new VersionPart(String.valueOf(val), true));
            } else if (all.get(0).digit) {
                all.add(0,new VersionPart(sep, false));
                all.add(0,new VersionPart(String.valueOf(val), true));
            } else {
                all.add(0,new VersionPart(String.valueOf(val), true));
            }
        }

        public void addDigit(BigInteger val, String sep) {
            if (all.size() == 0) {
                all.add(new VersionPart(String.valueOf(val), true));
            } else if (all.get(all.size() - 1).digit) {
                all.add(new VersionPart(sep, false));
                all.add(new VersionPart(String.valueOf(val), true));
            } else {
                all.add(new VersionPart(String.valueOf(val), true));
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (VersionPart versionPart : all) {
                sb.append(versionPart.string);
            }
            return sb.toString();
        }
    }

    private static VersionParts splitVersionParts2(String v1) {
        v1 = NutsUtilStrings.trim(v1);
        List<VersionPart> parts = new ArrayList<>();
        StringBuilder last = new StringBuilder();
        boolean digit = false;
        for (char c : v1.toCharArray()) {
            if (Character.isDigit(c)) {
                if (last.length() == 0 || digit) {
                    digit = true;
                    last.append(c);
                } else {
                    parts.add(new VersionPart(last.toString(), false));
                    CoreStringUtils.clear(last);
                    digit = true;
                    last.append(c);
                }
            } else {
                if (last.length() == 0) {
                    digit = false;
                    last.append(c);
                } else if (!digit) {
                    last.append(c);
                } else {
                    parts.add(new VersionPart(last.toString(), true));
                    CoreStringUtils.clear(last);
                    digit = false;
                    last.append(c);
                }
            }
        }
        if (last.length() > 0) {
            parts.add(new VersionPart(last.toString(), digit));
        }
        return new VersionParts(parts);
    }

    private static String[] splitVersionParts(String v1) {
        v1 = NutsUtilStrings.trim(v1);
        List<String> parts = new ArrayList<>();
        StringBuilder last = new StringBuilder();
        for (char c : v1.toCharArray()) {
            if (last.length() == 0) {
                last.append(c);
            } else if (Character.isDigit(last.charAt(0)) == Character.isDigit(c)) {
                last.append(c);
            } else {
                parts.add(last.toString());
                CoreStringUtils.clear(last);
            }
        }
        if (last.length() > 0) {
            parts.add(last.toString());
        }
        return parts.toArray(new String[0]);
    }

    private static int compareVersionItem(String v1, String v2) {
        BigInteger i1 = null;
        BigInteger i2 = null;

        if (v1.equals(v2)) {
            return 0;
        } else if ((i1 = CoreNumberUtils.convertToBigInteger(v1, null)) != null
                && (i2 = CoreNumberUtils.convertToBigInteger(v2, null)) != null) {
            return i1.compareTo(i2);
        } else if ("SNAPSHOT".equalsIgnoreCase(v1)) {
            return -1;
        } else if ("SNAPSHOT".equalsIgnoreCase(v2)) {
            return 1;
        } else {
            BigInteger a = CoreNumberUtils.getStartingBigInteger(v1);
            BigInteger b = CoreNumberUtils.getStartingBigInteger(v2);
            if (a != null && b != null && !a.equals(b)) {
                return a.compareTo(b);
            } else {
                return v1.compareTo(v2);
            }
        }
    }

    public static boolean isBlankVersion(String pattern) {
        if (NutsBlankable.isBlank(pattern)) {
            return true;
        }
        return NutsConstants.Versions.LATEST.equals(pattern) || NutsConstants.Versions.RELEASE.equals(pattern);
    }

    public static boolean isStaticVersionPattern(String pattern) {
        if (isBlankVersion(pattern)) {
            return false;
        }
        if (pattern.contains("[") || pattern.contains("]") || pattern.contains(",") || pattern.contains("*")) {
            return false;
        } else {
            return true;
        }
    }
}
