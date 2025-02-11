package net.thevpc.nuts.runtime.standalone.text.highlighter;

import net.thevpc.nuts.*;
import net.thevpc.nuts.io.NutsIOException;
import net.thevpc.nuts.runtime.standalone.xtra.expr.StringReaderExt;
import net.thevpc.nuts.runtime.standalone.text.parser.DefaultNutsTextPlain;
import net.thevpc.nuts.runtime.standalone.text.parser.DefaultNutsTextStyled;
import net.thevpc.nuts.spi.NutsComponent;
import net.thevpc.nuts.spi.NutsSupportLevelContext;
import net.thevpc.nuts.text.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinCmdBlocTextHighlighter implements NutsCodeHighlighter {

    private final NutsWorkspace ws;

    public WinCmdBlocTextHighlighter(NutsWorkspace ws) {
        this.ws = ws;
    }

    private static NutsText[] parseCommandLine_readAntiSlash(StringReaderExt ar, NutsSession session) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(ar.nextChar());
        if (ar.hasNext()) {
            sb2.append(ar.nextChar());
        }
        NutsTexts txt = NutsTexts.of(session);
        return new NutsText[]{txt.ofStyled(sb2.toString(), NutsTextStyle.separator())};
    }

    private static boolean isWord(NutsText n) {
        if (n instanceof DefaultNutsTextPlain) {
            return Character.isAlphabetic(((DefaultNutsTextPlain) n).getText().charAt(0));
        }
        return false;
    }

    private static boolean isSeparator(NutsText n) {
        if (n instanceof DefaultNutsTextStyled) {
            NutsText v = ((DefaultNutsTextStyled) n).getChild();
            if (v instanceof DefaultNutsTextPlain) {
                String t = ((DefaultNutsTextPlain) v).getText();
                switch (t.charAt(0)) {
                    case ';':
                    case '&':
                    case '|':
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean isWhites(NutsText n) {
        if (n instanceof DefaultNutsTextPlain) {
            return Character.isWhitespace(((DefaultNutsTextPlain) n).getText().charAt(0));
        }
        return false;
    }

    private static int indexOfFirstWord(List<NutsText> all, int from) {
        for (int i = from; i < all.size(); i++) {
            NutsText n = all.get(i);
            if (isWord(n)) {
                if (i == all.size() - 1 || isWhites(all.get(i + 1)) || isSeparator(all.get(i + 1))) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean isSynopsisOption(String s2) {
        return ((s2.startsWith("--") && isSynopsisWord(s2.substring(2)))
                || (s2.startsWith("++") && isSynopsisWord(s2.substring(2)))
                || (s2.startsWith("-") && isSynopsisWord(s2.substring(1)))
                || (s2.startsWith("+") && isSynopsisWord(s2.substring(1)))
                || (s2.startsWith("--!") && isSynopsisWord(s2.substring(3)))
                || (s2.startsWith("++!") && isSynopsisWord(s2.substring(3)))
                || (s2.startsWith("-!") && isSynopsisWord(s2.substring(2)))
                || (s2.startsWith("+!") && isSynopsisWord(s2.substring(2)))
                || (s2.startsWith("--~") && isSynopsisWord(s2.substring(3)))
                || (s2.startsWith("++~") && isSynopsisWord(s2.substring(3)))
                || (s2.startsWith("-~") && isSynopsisWord(s2.substring(2)))
                || (s2.startsWith("+~") && isSynopsisWord(s2.substring(2))));
    }

    private static boolean isOption(String s2) {
        return ((s2.startsWith("-"))
                || (s2.startsWith("+")));
    }

    private static boolean isSynopsisWord(String s) {
        if (s.length() > 0) {
            if (!Character.isAlphabetic(s.charAt(0))) {
                return false;
            }
            if (!Character.isAlphabetic(s.charAt(0))) {
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                if (Character.isAlphabetic(s.charAt(i))) {
                    //ok
                } else if (s.charAt(i) == '-') {
                    if (s.charAt(i - 1) == '-') {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public String getId() {
        return "cmd";
    }

    @Override
    public int getSupportLevel(NutsSupportLevelContext context) {
        String s = context.getConstraints();
        if (s == null) {
            return DEFAULT_SUPPORT;
        }
        switch (s) {
            case "bat":
            case "win-cmd":
            case "wincmd":
            case "cmd":
            case "powsershell":
            case "powser-shell":
            case "text/x-msdos-batch": {
                return NutsComponent.DEFAULT_SUPPORT;
            }
            case "system": {
                switch (NutsShellFamily.getCurrent()) {
                    case WIN_CMD:
                    case WIN_POWER_SHELL: {
                        return NutsComponent.DEFAULT_SUPPORT + 10;
                    }
                }
                return NutsComponent.DEFAULT_SUPPORT;
            }
        }
        return NutsComponent.NO_SUPPORT;
    }

    @Override
    public NutsText stringToText(String text, NutsTexts txt, NutsSession session) {
        txt.setSession(session);
        List<NutsText> all = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new StringReader(text));
        String line = null;
        boolean first = true;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) {
                    break;
                }
            } catch (IOException ex) {
                throw new NutsIOException(session, ex);
            }
            if (first) {
                first = false;
            } else {
                all.add(txt.ofPlain("\n"));
            }
            all.add(commandToNode(line, txt, session));
        }
        return txt.ofList(all).simplify();
    }

    @Override
    public NutsText tokenToText(String text, String nodeType, NutsTexts txt, NutsSession session) {
        return txt.ofPlain(text);
    }

    private NutsText[] parseCommandLine_readSimpleQuotes(StringReaderExt ar, NutsTexts txt, NutsSession session) {
        StringBuilder sb = new StringBuilder();
        sb.append(ar.nextChar()); //quote!
        List<NutsText> ret = new ArrayList<>();
        while (ar.hasNext()) {
            char c = ar.peekChar();
            /*if (c == '\\') {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(ar.nextChar());
                if (sb.length() > 0) {
                    ret.add(txt.ofStyled(sb.toString(), NutsTextStyle.string(2)));
                    sb.setLength(0);
                }
                if (ar.hasNext()) {
                    sb2.append(ar.nextChar());
                }
                ret.add(txt.ofStyled(sb2.toString(), NutsTextStyle.separator()));
                break;
            } else */
            if (c == '\'') {
                sb.append(ar.nextChar());
                break;
            } else {
                sb.append(ar.nextChar());
            }
        }
        if (sb.length() > 0) {
            ret.add(txt.ofStyled(sb.toString(), NutsTextStyle.string(2)));
            sb.setLength(0);
        }
        return ret.toArray(new NutsText[0]);
    }

    private NutsText[] parseCommandLine_readWord(StringReaderExt ar, NutsTexts txt, NutsSession session) {
        StringBuilder sb = new StringBuilder();
        List<NutsText> ret = new ArrayList<>();
        boolean inLoop = true;
        boolean endsWithSep = false;
        while (inLoop && ar.hasNext()) {
            char c = ar.peekChar();
            switch (c) {
//                case '\\': {
//                    if (sb.length() > 0) {
//                        ret.add(txt.ofPlain(sb.toString()));
//                        sb.setLength(0);
//                    }
//                    ret.addAll(Arrays.asList(parseCommandLine_readAntiSlash(ar, session)));
//                    break;
//                }
                case ';': {
                    endsWithSep = true;
                    inLoop = false;
                    break;
                }
                case ':': {
                    endsWithSep = true;
                    inLoop = false;
                    break;
                }
                case '$':
                case '`':
                case '\"':
                case '\'':
                case '(':
                case ')':
                case '[':
                case ']':
                case '{':
                case '}':
                case '<':
                case '>':
                case '&':
                case '|':
                case '*':
                case '?':
                case '#':
                case '=':
                case '~':
                case '!': {
                    inLoop = false;
                    break;
                }
                default: {
                    if (c <= 32) {
                        endsWithSep = true;
                        inLoop = false;
                    } else {
                        sb.append(ar.nextChar());
                    }
                }
            }
        }
        if (sb.length() > 0) {
            ret.add(txt.ofPlain(sb.toString()));
            sb.setLength(0);
        }
        if (ret.isEmpty()) {
            throw new IllegalArgumentException("was not expecting " + ar.peekChar() + " as part of word");
        }
        if (ret.get(0).getType() == NutsTextType.PLAIN && isOption(((NutsTextPlain) ret.get(0)).getText())) {
            ret.set(0, txt.ofStyled(ret.get(0), NutsTextStyle.option()));
        }
        return ret.toArray(new NutsText[0]);
    }

    private NutsText[] parseCommandLine_readDollar(StringReaderExt ar, NutsTexts txt, NutsSession session) {
        if (ar.peekChars("$((")) {
            return parseCommandLine_readDollarPar2(ar, txt, session);
        }
        StringBuilder sb2 = new StringBuilder();
        if (ar.hasNext(1)) {
            switch (ar.peekChar(1)) {
                case '(': {
                    return parseCommandLine_readDollarPar2(ar, txt, session);
                }
                case '{': {
                    return parseCommandLine_readDollarCurlyBrackets(ar, txt, session);
                }
                case '*':
                case '?':
                case '@':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    sb2.append(ar.nextChar());
                    sb2.append(ar.nextChar());
                    return new NutsText[]{txt.ofStyled(sb2.toString(), NutsTextStyle.separator())};
                }
            }
        }
        ar.nextChar();
        while (ar.hasNext()) {
            char c = ar.peekChar();
            if (Character.isAlphabetic(c) || Character.isDigit(c) || c == '_') {
                sb2.append(ar.nextChar());
            } else {
                break;
            }
        }
        if (sb2.length() > 0) {
            return new NutsText[]{
                    txt.ofStyled("$", NutsTextStyle.separator()),
                    txt.ofStyled(sb2.toString(), NutsTextStyle.keyword(4)),};
        }
        return new NutsText[]{
                txt.ofStyled("$", NutsTextStyle.separator()),};
    }

    private NutsText[] parseCommandLine_readDoubleQuotes(StringReaderExt ar, NutsTexts txt, NutsSession session) {
        List<NutsText> ret = new ArrayList<>();
        txt.setSession(session);
        StringBuilder sb = new StringBuilder();

        ret.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.string()));
        while (ar.hasNext()) {
            char c = ar.peekChar();
            /*if (c == '\\') {
                if (sb.length() > 0) {
                    ret.add(txt.ofStyled(sb.toString(), NutsTextStyle.string()));
                    sb.setLength(0);
                }
                ret.addAll(Arrays.asList(parseCommandLine_readAntiSlash(ar, session)));
            } else */
            if (c == '$') {
                if (sb.length() > 0) {
                    ret.add(txt.ofStyled(sb.toString(), NutsTextStyle.string()));
                    sb.setLength(0);
                }
                ret.addAll(Arrays.asList(parseCommandLine_readDollar(ar, txt, session)));
            } else if (c == '\"') {
                if (sb.length() > 0) {
                    ret.add(txt.ofStyled(sb.toString(), NutsTextStyle.string()));
                    sb.setLength(0);
                }
                ret.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.string()));
                break;
            } else {
                sb.append(ar.nextChar());
            }
        }
        if (sb.length() > 0) {
            ret.add(txt.ofStyled(sb.toString(), NutsTextStyle.string()));
            sb.setLength(0);
        }
        return ret.toArray(new NutsText[0]);
    }

    private NutsText[] parseCommandLine_readAntiQuotes(StringReaderExt ar, NutsTexts txt, NutsSession session) {
        List<NutsText> all = new ArrayList<>();
        txt.setSession(session);
        all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
        boolean inLoop = true;
        boolean wasSpace = true;
        while (inLoop && ar.hasNext()) {
            char c = ar.peekChar();
            switch (c) {
                case '`': {
                    wasSpace = false;
                    all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                    inLoop = false;
                    break;
                }
                default: {
                    wasSpace = parseCommandLineStep(ar, all, 1, wasSpace, txt, session);
                }
            }
        }
        return all.toArray(new NutsText[0]);
    }

    private NutsText[] parseCommandLine_readDollarPar(NutsWorkspace ws, StringReaderExt ar, NutsTexts txt, NutsSession session) {
        List<NutsText> all = new ArrayList<>();
        txt.setSession(session);
        all.add(txt.ofStyled(String.valueOf(ar.nextChar()) + ar.nextChar(), NutsTextStyle.separator()));
        boolean inLoop = true;
        boolean wasSpace = false;
        while (inLoop && ar.hasNext()) {
            char c = ar.peekChar();
            switch (c) {
                case ')': {
                    all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                    inLoop = false;
                    break;
                }
                default: {
                    wasSpace = parseCommandLineStep(ar, all, 2, wasSpace, txt, session);
                }
            }
        }
        return all.toArray(new NutsText[0]);
    }

    private NutsText[] parseCommandLine_readDollarPar2(StringReaderExt ar, NutsTexts txt, NutsSession session) {
        List<NutsText> all = new ArrayList<>();
        txt.setSession(session);
        all.add(txt.ofStyled(String.valueOf(ar.nextChar()) + ar.nextChar() + ar.nextChar(), NutsTextStyle.separator()));
        boolean inLoop = true;
        boolean wasSpace = true;
        while (inLoop && ar.hasNext()) {
            char c = ar.peekChar();
            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '%': {
                    wasSpace = false;
                    all.add(txt.ofStyled(String.valueOf(ar.nextChars(2)), NutsTextStyle.operator()));
                    break;
                }
                case ')': {
                    if (ar.peekChars(2).equals("))")) {
                        wasSpace = false;
                        all.add(txt.ofStyled(String.valueOf(ar.nextChars(2)), NutsTextStyle.separator()));
                        inLoop = false;
                    } else {
                        wasSpace = parseCommandLineStep(ar, all, 2, wasSpace, txt, session);
                    }
                    break;
                }
                default: {
                    wasSpace = parseCommandLineStep(ar, all, 2, wasSpace, txt, session);
                }
            }
        }
        return all.toArray(new NutsText[0]);
    }

    private NutsText[] parseCommandLine_readDollarCurlyBrackets(StringReaderExt ar, NutsTexts txt, NutsSession session) {
        List<NutsText> all = new ArrayList<>();
        txt.setSession(session);
        all.add(txt.ofStyled(String.valueOf(ar.nextChar()) + ar.nextChar(), NutsTextStyle.separator()));
        boolean inLoop = true;
        int startIndex = 0;
        boolean expectedName = true;
        boolean wasSpace = true;
        while (inLoop && ar.hasNext()) {
            char c = ar.peekChar();
            switch (c) {
                case '}': {
                    all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                    inLoop = false;
                    break;
                }
                default: {
                    startIndex = all.size();
                    wasSpace = parseCommandLineStep(ar, all, -1, wasSpace, txt, session);
                    if (expectedName) {
                        expectedName = false;
                        if (all.size() > startIndex) {
                            if (isWord(all.get(startIndex))) {
                                all.set(startIndex, txt.ofStyled(all.get(startIndex), NutsTextStyle.keyword(4)));
                                wasSpace = false;
                            }
                        }
                    }
                }
            }
        }
        return all.toArray(new NutsText[0]);
    }

    private NutsText[] parseCommandLine_readPar2(StringReaderExt ar, NutsTexts txt, NutsSession session) {
        List<NutsText> all = new ArrayList<>();
        txt.setSession(session);
        all.add(txt.ofStyled(String.valueOf(ar.nextChar()) + ar.nextChar(), NutsTextStyle.separator()));
        boolean inLoop = true;
        boolean wasSpace = true;
        while (inLoop && ar.hasNext()) {
            char c = ar.peekChar();
            switch (c) {
                case ')': {
                    if (ar.peekChars(2).equals("))")) {
                        all.add(txt.ofStyled(String.valueOf(ar.nextChars(2)), NutsTextStyle.separator()));
                        inLoop = false;
                    } else {
                        wasSpace = parseCommandLineStep(ar, all, 2, wasSpace, txt, session);
                    }
                    break;
                }
                default: {
                    wasSpace = parseCommandLineStep(ar, all, 2, wasSpace, txt, session);
                }
            }
        }
        return all.toArray(new NutsText[0]);
    }

    /**
     * return is space
     *
     * @param ar         ar
     * @param all        all
     * @param startIndex startIndex
     * @param wasSpace   wasSpace
     * @return is space
     */
    private boolean parseCommandLineStep(StringReaderExt ar, List<NutsText> all, int startIndex, boolean wasSpace, NutsTexts txt, NutsSession session) {
        char c = ar.peekChar();
        if (c <= 32) {
            all.addAll(Arrays.asList(StringReaderExtUtils.readSpaces(session, ar)));
            return true;
        }
        switch (c) {
            case '\'': {
                all.addAll(Arrays.asList(parseCommandLine_readSimpleQuotes(ar, txt, session)));
                break;
            }
            case '`': {
                all.addAll(Arrays.asList(parseCommandLine_readAntiQuotes(ar, txt, session)));
                break;
            }
            case '"': {
                all.addAll(Arrays.asList(parseCommandLine_readDoubleQuotes(ar, txt, session)));
                break;
            }
            case '$': {
                all.addAll(Arrays.asList(parseCommandLine_readDollar(ar, txt, session)));
                break;
            }
            case ';': {
                all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                break;
            }
            case ':': {
                all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator(2)));
                break;
            }
            case '|': {
                if (ar.peekChars(2).equals("||")) {
                    all.add(txt.ofStyled(ar.nextChars(2), NutsTextStyle.separator()));
                } else {
                    all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                }
                break;
            }
            case '&': {
                if (ar.peekChars(2).equals("&&")) {
                    all.add(txt.ofStyled(ar.nextChars(2), NutsTextStyle.separator()));
                } else if (ar.peekChars(3).equals("&>>")) {
                    all.add(txt.ofStyled(ar.nextChars(3), NutsTextStyle.separator()));
                } else if (ar.peekChars(2).equals("&>")) {
                    all.add(txt.ofStyled(ar.nextChars(2), NutsTextStyle.separator()));
                } else {
                    all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                }
                break;
            }
            case '>': {
                if (ar.peekChars(2).equals(">>")) {
                    all.add(txt.ofStyled(ar.nextChars(2), NutsTextStyle.separator()));
                } else if (ar.peekChars(2).equals(">&")) {
                    all.add(txt.ofStyled(ar.nextChars(2), NutsTextStyle.separator()));
                } else {
                    all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                }
                break;
            }
            case '<': {
                if (ar.peekChars(2).equals("<<")) {
                    all.add(txt.ofStyled(ar.nextChars(2), NutsTextStyle.separator()));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(ar.peekChar(0));
                    boolean ok = true;
                    int i = 1;
                    while (ok && ar.isAvailable(i)) {
                        char c1 = ar.peekChar(i);
                        if (c1 == '>') {
                            sb.append(c1);
                            break;
                        } else if (c1 == '-' || c1 == '+' || Character.isAlphabetic(c1)) {
                            sb.append(c1);
                        } else {
                            ok = false;
                        }
                        i++;
                    }
                    if (sb.charAt(sb.length() - 1) != '>') {
                        ok = false;
                    }
                    if (ok) {
                        String s = ar.nextChars(sb.length());
                        String s0 = s.substring(1, s.length() - 1);
                        if (isSynopsisOption(s0)) {
                            all.add(txt.ofStyled("<", NutsTextStyle.input()));
                            all.add(txt.ofStyled(s0, NutsTextStyle.option()));
                            all.add(txt.ofStyled(">", NutsTextStyle.input()));
                        } else if (isSynopsisWord(s0)) {
                            all.add(txt.ofStyled("<", NutsTextStyle.input()));
                            all.add(txt.ofStyled(s0, NutsTextStyle.input()));
                            all.add(txt.ofStyled(">", NutsTextStyle.input()));
                        } else {
                            all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                        }
                    } else {
                        all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                    }
                }
                break;
            }
            case '(': {
                if (ar.peekChars("((")) {
                    all.addAll(Arrays.asList(parseCommandLine_readPar2(ar, txt, session)));
                } else {
                    all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                }
            }
            case ')':
            case '{':
            case '}':
            case '~':
            case '!': {
                all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                break;
            }
            case '*':
            case '?':
            case '[':
            case ']':
            case '=': {
                all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                break;
            }
            case '#': {
                if (wasSpace) {
                    StringBuilder sb = new StringBuilder();
                    while (ar.hasNext()) {
                        c = ar.peekChar();
                        if (c == '\n') {
                            break;
                        } else if (c == '\r') {
                            break;
                        } else {
                            sb.append(ar.nextChar());
                        }
                    }
                    all.add(txt.ofStyled(sb.toString(), NutsTextStyle.comments()));
                } else {
                    all.add(txt.ofStyled(String.valueOf(ar.nextChar()), NutsTextStyle.separator()));
                }
                break;
            }
            default: {
                if (startIndex >= 0) {
                    boolean first = all.size() == startIndex;
                    all.addAll(Arrays.asList(parseCommandLine_readWord(ar, txt, session)));
                    if (first) {
                        int i = indexOfFirstWord(all, startIndex);
                        if (i >= 0) {
                            all.set(i, txt.ofStyled(all.get(i), NutsTextStyle.keyword()));
                        }
                    }
                } else {
                    all.addAll(Arrays.asList(parseCommandLine_readWord(ar, txt, session)));
                }
            }
        }
        return false;
    }

    private NutsText[] parseCommandLine(String commandLineString, NutsTexts txt, NutsSession session) {
        StringReaderExt ar = new StringReaderExt(commandLineString);
        List<NutsText> all = new ArrayList<>();
        boolean wasSpace = true;
        while (ar.hasNext()) {
            wasSpace = parseCommandLineStep(ar, all, 0, wasSpace, txt, session);
        }
        return all.toArray(new NutsText[0]);
    }

    public NutsText next(StringReaderExt reader, boolean exitOnClosedCurlBrace, boolean exitOnClosedPar, boolean exitOnDblQuote, boolean exitOnAntiQuote, NutsTexts txt, NutsSession session) {
        boolean lineStart = true;
        List<NutsText> all = new ArrayList<>();
        boolean exit = false;
        while (!exit && reader.hasNext()) {
            switch (reader.peekChar()) {
                case '}': {
                    lineStart = false;
                    if (exitOnClosedCurlBrace) {
                        exit = true;
                    } else {
                        all.add(txt.ofStyled(
                                reader.nextChars(1), NutsTextStyle.separator()
                        ));
                    }
                    break;
                }
                case ')': {
                    lineStart = false;
                    if (exitOnClosedPar) {
                        exit = true;
                    } else {
                        all.add(txt.ofStyled(
                                reader.nextChars(1), NutsTextStyle.separator()
                        ));
                    }
                    break;
                }
                case '>': {
                    lineStart = false;
                    if (reader.isAvailable(2) && reader.peekChar() == '>') {
                        all.add(txt.ofStyled(
                                reader.nextChars(2), NutsTextStyle.separator()
                        ));
                    } else {
                        all.add(txt.ofStyled(
                                reader.nextChars(1), NutsTextStyle.separator()
                        ));
                    }
                    break;
                }
                case '&': {
                    lineStart = false;
                    if (reader.isAvailable(2) && reader.peekChar() == '&') {
                        all.add(txt.ofStyled(
                                reader.nextChars(2), NutsTextStyle.separator()
                        ));
                    } else if (reader.isAvailable(2) && reader.peekChar() == '>') {
                        all.add(txt.ofStyled(
                                reader.nextChars(2), NutsTextStyle.separator()
                        ));
                    } else if (reader.isAvailable(2) && reader.peekChar() == '<') {
                        all.add(txt.ofStyled(
                                reader.nextChars(2), NutsTextStyle.separator()
                        ));
                    } else {
                        all.add(txt.ofStyled(
                                reader.nextChars(1), NutsTextStyle.separator()
                        ));
                    }
                    break;
                }
                case '|': {
                    lineStart = false;
                    if (reader.isAvailable(2) && reader.peekChar() == '|') {
                        all.add(txt.ofStyled(
                                reader.nextChars(2), NutsTextStyle.separator()
                        ));
                    } else {
                        all.add(txt.ofStyled(
                                reader.nextChars(1), NutsTextStyle.separator()
                        ));
                    }
                    break;
                }
                case ';': {
                    all.add(txt.ofStyled(
                            reader.nextChars(1), NutsTextStyle.separator()
                    ));
                    lineStart = true;
                    break;
                }
                case '\n': {
                    if (reader.isAvailable(2) && reader.peekChar() == '\r') {
                        all.add(txt.ofStyled(
                                reader.nextChars(2), NutsTextStyle.separator()
                        ));
                    } else {
                        all.add(txt.ofStyled(
                                reader.nextChars(1), NutsTextStyle.separator()
                        ));
                    }
                    lineStart = true;
                    break;
                }
                case '<': {
                    lineStart = false;
                    StringBuilder sb = new StringBuilder();
                    if (reader.isAvailable(3)) {
                        int index = 0;
                        sb.append(reader.peekChar(index));
                        index++;
                        boolean ok = false;
                        while (reader.isAvailable(index)) {
                            char c = reader.peekChar(index);
                            if (c == '>') {
                                sb.append(c);
                                ok = true;
                                break;
                            } else if (Character.isAlphabetic(c) || c == '-') {
                                sb.append(c);
                            } else {
                                break;
                            }
                            index++;
                        }
                        if (ok) {
                            reader.nextChars(sb.length());
                            all.add(txt.ofStyled(
                                    sb.toString(), NutsTextStyle.input()
                            ));
                            break;
                        } else {
                            all.add(txt.ofStyled(
                                    reader.nextChars(1), NutsTextStyle.separator()
                            ));
                        }
                    } else if (reader.isAvailable(2) && reader.peekChar() == '<') {
                        all.add(txt.ofStyled(
                                reader.nextChars(2), NutsTextStyle.separator()
                        ));
                    } else {
                        all.add(txt.ofStyled(
                                reader.nextChars(1), NutsTextStyle.separator()
                        ));
                    }
                    break;
                }
                case '\\': {
                    lineStart = false;
                    all.add(txt.ofStyled(
                            reader.nextChars(2), NutsTextStyle.separator(2)
                    ));
                    break;
                }
                case '\"': {
                    lineStart = false;
                    all.add(nextDoubleQuotes(reader, session));
                    break;
                }
                case '`': {
                    lineStart = false;
                    if (exitOnAntiQuote) {
                        exit = true;
                    } else {
                        List<NutsText> a = new ArrayList<>();
                        a.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.string()));
                        a.add(next(reader, false, false, false, true, txt, session));
                        if (reader.hasNext() && reader.peekChar() == '`') {
                            a.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.string()));
                        } else {
                            exit = true;
                        }
                        all.add(txt.ofList(a).simplify());
                    }
                    break;
                }
                case '\'': {
                    lineStart = false;
                    StringBuilder sb = new StringBuilder();
                    sb.append(reader.nextChar());
                    boolean end = false;
                    while (!end && reader.hasNext()) {
                        switch (reader.peekChar()) {
                            case '\\': {
                                sb.append(reader.nextChars(2));
                                break;
                            }
                            case '\'': {
                                sb.append(reader.nextChar());
                                end = true;
                                break;
                            }
                            default: {
                                sb.append(reader.nextChar());
                                break;
                            }
                        }
                    }
                    all.add(txt.ofStyled(sb.toString(), NutsTextStyle.string()));
                    break;
                }
                case '$': {
                    lineStart = false;
                    if (reader.isAvailable(2)) {
                        char c = reader.peekChar(1);
                        switch (c) {
                            case '(': {
                                break;
                            }
                            case '{': {
                                break;
                            }
                            case '$':
                            case '*':
                            case '@':
                            case '-':
                            case '?':
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9': {
                                all.add(txt.ofStyled(reader.nextChars(2), NutsTextStyle.string()));
                                break;
                            }
                            default: {
                                if (Character.isAlphabetic(reader.peekChar(1))) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(reader.nextChar());
                                    while (reader.hasNext() && (Character.isAlphabetic(reader.peekChar()) || reader.peekChar() == '_')) {
                                        sb.append(reader.nextChar());
                                    }
                                    all.add(txt.ofStyled(sb.toString(), NutsTextStyle.variable()));
                                } else {
                                    all.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.separator()));
                                }
                            }
                        }
                    } else {
                        all.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.string()));
                    }
                    break;
                }
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 33: {
                    StringBuilder whites = new StringBuilder();
                    while (reader.hasNext() && Character.isWhitespace(reader.peekChar())) {
                        whites.append(reader.nextChar());
                    }
                    all.add(txt.ofPlain(whites.toString()));
                    break;
                }
                default: {
                    StringBuilder sb = new StringBuilder();
                    sb.append(reader.nextChar());
                    while (reader.hasNext()) {
                        char c2 = reader.peekChar();
                        boolean accept = true;
                        switch (c2) {
                            case '$':
                            case '<':
                            case '>':
                            case '&':
                            case '|':
                            case '{':
                            case '}':
                            case '(':
                            case ')':
                            case '[':
                            case ']':
                            case '*':
                            case '+':
                            case '?':
                            case '\\': {
                                accept = false;
                                break;
                            }
                            default: {
                                if (c2 <= 32) {
                                    accept = false;
                                } else {
                                    sb.append(reader.nextChar());
                                }
                            }
                        }
                        if (!accept) {
                            break;
                        }
                    }
                    if (lineStart && !reader.hasNext() || Character.isWhitespace(reader.peekChar())) {
                        //command name
                        NutsTextStyle keyword1 = NutsTextStyle.keyword(2);
                        switch (sb.toString()) {
                            case "if":
                            case "while":
                            case "do":
                            case "fi":
                            case "elif":
                            case "then":
                            case "else": {
                                keyword1 = NutsTextStyle.keyword();
                                break;
                            }
                            case "cp":
                            case "ls":
                            case "ll":
                            case "rm":
                            case "pwd":
                            case "echo": {
                                keyword1 = NutsTextStyle.keyword(3);
                                break;
                            }
                        }
                        all.add(txt.ofStyled(sb.toString(), keyword1));
                    } else {
                        all.add(txt.ofPlain(sb.toString()));
                    }
                    lineStart = false;
                    break;
                }
            }
        }
        return txt.ofList(all).simplify();
    }

    private NutsText nextDollar(StringReaderExt reader, NutsTexts txt, NutsSession session) {
        if (reader.isAvailable(2)) {
            char c = reader.peekChar(1);
            switch (c) {
                case '(': {
                    List<NutsText> a = new ArrayList<>();
                    a.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.separator()));
                    a.add(next(reader, false, true, false, false, txt, session));
                    if (reader.hasNext() && reader.peekChar() == ')') {
                        a.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.separator()));
                    }
                    return txt.ofList(a).simplify();
                }
                case '{': {
                    List<NutsText> a = new ArrayList<>();
                    a.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.separator()));
                    a.add(next(reader, true, false, false, false, txt, session));
                    if (reader.hasNext() && reader.peekChar() == ')') {
                        a.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.separator()));
                    }
                    return txt.ofList(a).simplify();
                }
                case '$':
                case '*':
                case '@':
                case '-':
                case '?':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    return txt.ofStyled(reader.nextChars(2), NutsTextStyle.string());
                }
                default: {
                    if (Character.isAlphabetic(reader.peekChar(1))) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(reader.nextChar());
                        while (reader.hasNext() && (Character.isAlphabetic(reader.peekChar()) || reader.peekChar() == '_')) {
                            sb.append(reader.nextChar());
                        }
                        return txt.ofStyled(sb.toString(), NutsTextStyle.variable());
                    } else {
                        return txt.ofStyled(reader.nextChars(1), NutsTextStyle.separator());
                    }
                }
            }
        } else {
            return txt.ofStyled(reader.nextChars(1), NutsTextStyle.string());
        }
    }

    public NutsText nextDoubleQuotes(StringReaderExt reader, NutsSession session) {
        List<NutsText> all = new ArrayList<>();
        NutsTexts txt = NutsTexts.of(session);
        boolean exit = false;
        StringBuilder sb = new StringBuilder();
        sb.append(reader.nextChar());
        while (!exit && reader.hasNext()) {
            switch (reader.peekChar()) {
                case '\\': {
                    sb.append(reader.nextChars(2));
                    break;
                }
                case '\"': {
                    sb.append(reader.nextChars(1));
                    exit = true;
                    break;
                }
                case '$': {
                    if (sb.length() > 0) {
                        all.add(txt.ofStyled(sb.toString(), NutsTextStyle.string()));
                        sb.setLength(0);
                    }
                    all.add(nextDollar(reader, txt, session));
                }
                case '`': {
                    if (sb.length() > 0) {
                        all.add(txt.ofStyled(sb.toString(), NutsTextStyle.string()));
                        sb.setLength(0);
                    }
                    List<NutsText> a = new ArrayList<>();
                    a.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.string()));
                    a.add(next(reader, false, false, false, true, txt, session));
                    if (reader.hasNext() && reader.peekChar() == '`') {
                        a.add(txt.ofStyled(reader.nextChars(1), NutsTextStyle.string()));
                    } else {
                        exit = true;
                    }
                    all.add(txt.ofList(a).simplify());
                    break;
                }
                default: {
                    sb.append(reader.nextChars(1));
                }
            }
        }
        if (sb.length() > 0) {
            all.add(txt.ofStyled(sb.toString(), NutsTextStyle.string()));
            sb.setLength(0);
        }
        return txt.ofList(all).simplify();
    }

    public NutsText commandToNode(String text, NutsTexts txt, NutsSession session) {
        txt.setSession(session);
        return txt.ofList(parseCommandLine(text, txt, session));
    }

}
