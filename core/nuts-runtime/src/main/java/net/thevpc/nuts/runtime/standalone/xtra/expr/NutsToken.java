package net.thevpc.nuts.runtime.standalone.xtra.expr;

import net.thevpc.nuts.util.NutsStringUtils;

public class NutsToken {

    /**
     * A constant indicating that the end of the stream has been read.
     */
    public static final int TT_EOF = -1;

    /**
     * A constant indicating that the end of the line has been read.
     */
    public static final int TT_EOL = '\n';

    public static final int TT_INT = -4;
    public static final int TT_LONG = -5;
    public static final int TT_BIG_INT = -6;
    public static final int TT_FLOAT = -7;
    public static final int TT_DOUBLE = -8;
    public static final int TT_BIG_DECIMAL = -9;

    /**
     * A constant indicating that a word token has been read.
     */
    public static final int TT_WORD = -3;

    /* A constant indicating that no token has been read, used for
     * initializing ttype.  FIXME This could be made public and
     * made available as the part of the API in a future release.
     */
    public static final int TT_NOTHING = -30;
    public static final int TT_STRING_LITERAL = -10;
    public static final int TT_SPACE = -11;
    public static final int TT_AND = -40;
    public static final int TT_OR = -41;
    public static final int TT_LEFT_SHIFT = -42;
    public static final int TT_RIGHT_SHIFT = -43;
    public static final int TT_LEFT_SHIFT_UNSIGNED = -44;
    public static final int TT_RIGHT_SHIFT_UNSIGNED = -45;
    public static final int TT_LTE = -46;
    public static final int TT_GTE = -47;
    public static final int TT_LTGT = -48;
    public static final int TT_EQ = -49;
    public static final int TT_NEQ = -50;
    public static final int TT_NEQ2 = -51;
    public static final int TT_RIGHT_ARROW = -52;

    public int ttype;
    public int lineno;
    public String sval;
    public Number nval;

    public NutsToken(int ttype, String sval, Number nval, int lineno) {
        this.ttype = ttype;
        this.sval = sval;
        this.nval = nval;
        this.lineno = lineno;
    }

    @Override
    public String toString() {
        if(ttype>=32){
            return "NutsToken{" +
                    "ttype='" + (char)ttype +"'"+
                    ", lineno=" + lineno +
                    ", sval=" + (sval==null?"null": NutsStringUtils.formatStringLiteral(sval, NutsStringUtils.QuoteType.SIMPLE)) +
                    ", nval=" + nval +
                    '}';
        }
        return "NutsToken{" +
                "ttype=" + ttype +
                ", lineno=" + lineno +
                ", sval=" + (sval==null?"null": NutsStringUtils.formatStringLiteral(sval, NutsStringUtils.QuoteType.SIMPLE)) +
                ", nval=" + nval +
                '}';
    }
}
