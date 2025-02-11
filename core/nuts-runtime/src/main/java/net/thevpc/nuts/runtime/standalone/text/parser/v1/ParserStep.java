package net.thevpc.nuts.runtime.standalone.text.parser.v1;

import net.thevpc.nuts.text.NutsText;

public abstract class ParserStep {

    public abstract void consume(char c, DefaultNutsTextNodeParser.State p, boolean wasNewLine);

    public abstract void appendChild(ParserStep tt);

    public abstract NutsText toText();

    public abstract void end(DefaultNutsTextNodeParser.State p);

    public abstract boolean isComplete();

}
