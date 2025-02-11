package net.thevpc.nuts.runtime.standalone.text;

import net.thevpc.nuts.*;
import net.thevpc.nuts.io.NutsPrintStream;
import net.thevpc.nuts.runtime.standalone.text.parser.DefaultNutsTextCommand;
import net.thevpc.nuts.runtime.standalone.text.parser.DefaultNutsTextStyled;
import net.thevpc.nuts.runtime.standalone.text.parser.DefaultNutsTextTitle;
import net.thevpc.nuts.runtime.standalone.io.printstream.NutsPrintStreamHelper;
import net.thevpc.nuts.runtime.standalone.io.outputstream.OutputHelper;
import net.thevpc.nuts.runtime.standalone.io.outputstream.OutputStreamHelper;
import net.thevpc.nuts.runtime.standalone.util.CoreStringUtils;
import net.thevpc.nuts.spi.NutsSystemTerminalBase;
import net.thevpc.nuts.text.*;

import java.io.OutputStream;

public class NutsTextNodeWriterRenderer extends AbstractNutsTextNodeWriter {

    private byte[] buffer = new byte[1024];
    private int bufferSize = 0;
    private boolean enableBuffering = false;
    private byte[] later = null;
    private OutputHelper rawOutput;
    private RenderedRawStream renderedRawStream = new RenderedRawStream() {

        public OutputHelper baseOutput() {
            return rawOutput;
        }

        @Override
        public void writeRaw(byte[] buf, int off, int len) {
            NutsTextNodeWriterRenderer.this.writeRaw(new String(buf, off, len));
        }

        @Override
        public void writeLater(byte[] buf) {
            NutsTextNodeWriterRenderer.this.writeLater(buf);
        }
    };
    private NutsSession session;
    private NutsSystemTerminalBase term;
//    private NutsWorkspace ws;

    public NutsTextNodeWriterRenderer(NutsPrintStream rawOutput, NutsSession session) {
        this(new NutsPrintStreamHelper(rawOutput), session,rawOutput.getTerminal());
    }

    public NutsTextNodeWriterRenderer(OutputStream rawOutput, NutsSession session,NutsSystemTerminalBase term) {
        this(new OutputStreamHelper(rawOutput, session), session,term);
    }

    public NutsTextNodeWriterRenderer(OutputHelper rawOutput, NutsSession session,NutsSystemTerminalBase term) {
        this.rawOutput = rawOutput;
        this.session = session;
        this.term = term;
    }

    @Override
    public void writeNode(NutsText node) {
        writeNode(node, getWriteConfiguration());
    }

    @Override
    public final void writeRaw(byte[] buf, int off, int len) {
        rawOutput.write(buf, off, len);
    }

    @Override
    public void writeRaw(char[] buf, int off, int len) {
        writeRaw(new String(buffer,off,len));
    }

    @Override
    public final boolean flush() {
        if (bufferSize > 0) {
            rawOutput.write(buffer, 0, bufferSize);
            bufferSize = 0;
            return true;
        }
        rawOutput.flush();
        return false;
    }

    public void writeNode(NutsText node, NutsTextTransformConfig ctx) {
        writeNode(NutsTextStyles.of(), node, ctx);
    }

    private void writeNode(NutsTextStyles formats, NutsText node, NutsTextTransformConfig ctx) {
        if (formats == null) {
            formats = NutsTextStyles.of();
        }
        NutsTexts txt = NutsTexts.of(session);
        switch (node.getType()) {
            case PLAIN: {
                NutsTextPlain p = (NutsTextPlain) node;
                writeRaw(formats, p.getText(), ctx.isFiltered());
                break;
            }
            case LIST: {
                NutsTextList s = (NutsTextList) node;
                for (NutsText n : s) {
                    writeNode(formats, n, ctx);
                }
                break;
            }
            case STYLED: {
                DefaultNutsTextStyled s = (DefaultNutsTextStyled) node;
                NutsTextStyles styles = s.getStyles();
                NutsTextStyles format = txt.getTheme().toBasicStyles(styles, session);
                NutsTextStyles s2 = formats.append(format);
                writeNode(s2, s.getChild(), ctx);
                break;
            }
            case TITLE: {
                DefaultNutsTextTitle s = (DefaultNutsTextTitle) node;
                NutsTextStyles s2 = formats.append(txt.getTheme().toBasicStyles(
                        NutsTextStyles.of(NutsTextStyle.title(s.getLevel())), session
                ));
                if (ctx.isProcessTitleNumbers()) {
                    NutsTitleSequence seq = ctx.getTitleNumberSequence();
                    if (seq == null) {
                        seq = txt.ofNumbering();
                        ctx.setTitleNumberSequence(seq);
                    }
                    NutsTitleSequence a = seq.next(s.getLevel());
                    NutsText sWithTitle = txt.ofList(
                            txt.ofPlain(a.toString() + " "),
                            s.getChild()
                    );
                    writeNode(s2, sWithTitle, ctx);
                    writeRaw("\n");
                } else {
                    NutsText sWithTitle = txt.ofList(
                            txt.ofPlain(CoreStringUtils.fillString('#',s.getLevel())+") "),
                            s.getChild()
                    );
                    writeNode(s2, sWithTitle, ctx);
                    writeRaw("\n");
                }
                break;
            }
            case COMMAND: {
                DefaultNutsTextCommand s = (DefaultNutsTextCommand) node;
                if(term!=null){
                    if(!ctx.isFiltered()) {
                        term.run(s.getCommand(), session);
                    }
                }
                break;
            }
            case ANCHOR: {
                //ignore!!
                break;
            }
            case LINK: {
                //ignore!!
                NutsTextPlain child = txt.ofPlain(((NutsTextLink) node).getText());
                writeNode(formats,
                        txt.ofStyled(child,
                                NutsTextStyles.of(NutsTextStyle.underlined())
                        ),ctx);
                writeRaw(formats, "see: " + ((NutsTextLink) node).getText(), ctx.isFiltered());
                break;
            }
            case INCLUDE: {
                //ignore!!
                NutsTextPlain child = txt.ofPlain(((NutsTextInclude) node).getText());
                writeNode(formats,
                        txt.ofStyled(
                                txt.ofList(
                                        txt.ofPlain("include: "),
                                        child
                                )
                                ,
                                NutsTextStyles.of(NutsTextStyle.warn())
                        ),ctx);
                break;
            }
            case CODE: {
                NutsTextCode node1 = (NutsTextCode) node;
                if(ctx.isFiltered()){
                    writeRaw(formats, node1.getText(), true);
                }else {
                    NutsText cn = node1.highlight(session);
                    writeNode(formats, cn, ctx);
                }
                break;
            }
            default: {
                throw new UnsupportedOperationException("unsupported NutsTextNode type " + node.getClass().getSimpleName());
            }
        }
    }

    protected void writeRaw(NutsTextStyles format, String rawString, boolean filterFormat) {
        if (!filterFormat && format != null) {
            if (rawString.length() > 0) {
                if(format.isPlain()){
                    writeRaw(rawString);
                }else {
                    flush();
                    term.setStyles(format, session);
                    try{
                        writeRaw(rawString);
                    } finally {
                        term.setStyles(null, session);
                    }
                }
            }
        } else {
            if (rawString.length() > 0) {
                writeRaw(rawString);
            }
        }
    }

    public final void writeRaw(String rawString) {
        flushLater();
        byte[] b = rawString.getBytes();
        if (enableBuffering) {
            if (b.length + bufferSize < buffer.length) {
                System.arraycopy(b, 0, buffer, bufferSize, b.length);
                bufferSize += b.length;
            } else {
                flush();
                if (b.length >= buffer.length) {
                    rawOutput.write(b, 0, b.length);
                } else {
                    System.arraycopy(b, 0, buffer, bufferSize, b.length);
                    bufferSize += b.length;
                }
            }
        } else {
            rawOutput.write(b, 0, b.length);
        }
    }



    public final void writeLater(byte[] later) {
        this.later = later;
        rawOutput.flush();
    }

    public final void flushLater() {
        byte[] b = later;
        if (b != null) {
            later = null;
            if (enableBuffering) {
                if (b.length + bufferSize < buffer.length) {
                    System.arraycopy(b, 0, buffer, bufferSize, b.length);
                    bufferSize += b.length;
                } else {
                    flush();
                    if (b.length >= buffer.length) {
                        rawOutput.write(b, 0, b.length);
                    } else {
                        System.arraycopy(b, 0, buffer, bufferSize, b.length);
                        bufferSize += b.length;
                    }
                }
            } else {
                rawOutput.write(b, 0, b.length);
                rawOutput.flush();
            }
            //flush();
        }
    }

    @Override
    public String toString() {
        return "Printer(" + rawOutput + (this.later != null ? ";withLater" : "") + ")";
    }

}
