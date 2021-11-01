package net.thevpc.nuts.runtime.core.eval;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Iterator;

class TokenIterator implements Iterator<NutsToken> {
    private final StreamTokenizer st;
    private NutsToken previous;
    private boolean returnSpace=false;
    private boolean returnComment=false;
    private boolean doReplay;

    public TokenIterator(Reader r) {
        this.st=new StreamTokenizer(r);
    }

    public void pushBack() {
        doReplay = true;
    }

    public NutsToken peek() {
        if (doReplay) {
            return previous;
        }
        if (hasNext()) {
            NutsToken n = next();
            doReplay = true;
            return n;
        }
        return null;
    }

    public NutsToken read() {
        if (hasNext()) {
            return next();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (doReplay) {
            return true;
        }
        while (true) {
            int nt = StreamTokenizer.TT_EOF;
            try {
                nt = st.nextToken();
            } catch (IOException e) {
                return false;
            }
            switch (nt) {
                case StreamTokenizer.TT_EOF: {
                    previous = null;
                    return false;
                }
                case ' ':
                case '\t':
                case StreamTokenizer.TT_EOL: {
                    if(returnSpace) {
                        previous = new NutsToken(NutsToken.TT_SPACE, st.sval, 0, st.lineno());
                        return true;
                    }
                    break;
                }
                default: {
                    switch (st.ttype) {
                        case ' ':
                        case '\n':
                        case '\r':
                        case '\t': {
                            if(returnSpace) {
                                previous = new NutsToken(NutsToken.TT_SPACE, st.sval, 0, st.lineno());
                                return true;
                            }
                            break;
                        }
                        case '\"':
                        case '\'': {
                            String sval = st.sval;
                            previous = new NutsToken(NutsToken.TT_STRING_LITERAL, sval, 0, st.lineno());
                            return true;
                        }
                        case NutsToken.TT_NUMBER: {
                            previous = new NutsToken(NutsToken.TT_NUMBER, st.sval, st.nval, st.lineno());
                            return true;
                        }
                        default:{
                            String s = st.sval;
                            if(st.ttype>=32){
                                s=String.valueOf((char)st.ttype);
                            }else{
                                s=null;
                            }
                            previous = new NutsToken(st.ttype, s, 0, st.lineno());
                            return true;
                        }
                    }
                }
            }
        }
    }

    @Override
    public NutsToken next() {
        if (doReplay) {
            doReplay = false;
        }
        return previous;
    }
}
