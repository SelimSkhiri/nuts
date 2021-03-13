package net.thevpc.nuts.runtime.core.format.text.bloc;

import net.thevpc.nuts.NutsTextNode;
import net.thevpc.nuts.NutsTextNodeStyle;
import net.thevpc.nuts.NutsWorkspace;
import net.thevpc.nuts.runtime.bundles.collections.EvictingQueue;
import net.thevpc.nuts.runtime.bundles.parsers.StreamTokenizerExt;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import net.thevpc.nuts.NutsSupportLevelContext;
import net.thevpc.nuts.spi.NutsComponent;
import net.thevpc.nuts.NutsCodeFormat;
import net.thevpc.nuts.runtime.core.util.CoreStringUtils;
import net.thevpc.nuts.NutsTextManager;

public class XmlCodeFormatter implements NutsCodeFormat {

    private NutsWorkspace ws;
    NutsTextManager factory;

    public XmlCodeFormatter(NutsWorkspace ws) {
        this.ws = ws;
        factory = ws.formats().text();
    }

    @Override
    public int getSupportLevel(NutsSupportLevelContext<String> criteria) {
        String s = criteria.getConstraints();
        return "xml".equals(s) ? NutsComponent.DEFAULT_SUPPORT : NutsComponent.NO_SUPPORT;
    }

    public NutsTextNode tokenToNode(String text, String nodeType) {
        switch (CoreStringUtils.trim(nodeType).toLowerCase()) {
            case "name":
                return formatNodeName(text);
            case "attribute":
                return formatNodeName(text);
            case "string":
                return formatNodeString(text);
            case "<":
            case "<?":
            case "</":
            case ">":
            case "&":
            case "=":
            case "separator":
                return formatNodeSeparator(text);
        }
        return factory.plain(text);
    }

    public NutsTextNode formatNodeName(String text) {
        return factory.styled(factory.plain(text), NutsTextNodeStyle.keyword());
    }

    public NutsTextNode formatNodeString(String text) {
        return factory.styled(factory.plain(text), NutsTextNodeStyle.string());
    }

    public NutsTextNode formatNodeSeparator(String text) {
        return factory.styled(factory.plain(text), NutsTextNodeStyle.separator());
    }

    @Override
    public NutsTextNode textToNode(String text) {
        StreamTokenizerExt st = new StreamTokenizerExt(new StringReader(text));
        st.xmlComments(true);
        st.doNotParseNumbers();
        st.wordChars('0', '9');
        st.wordChars('.', '.');
        st.wordChars('-', '-');

        List<NutsTextNode> nodes = new ArrayList<>();
        int s;
        EvictingQueue<String> last = new EvictingQueue<>(3);
        while ((s = st.nextToken()) != StreamTokenizerExt.TT_EOF) {
            switch (s) {
                case StreamTokenizerExt.TT_SPACES: {
                    nodes.add(factory.plain(st.image));
                    break;
                }
                case StreamTokenizerExt.TT_COMMENTS: {
                    nodes.add(factory.styled(factory.plain(st.image), NutsTextNodeStyle.comments()));
                    break;
                }
                case StreamTokenizerExt.TT_INTEGER:
                case StreamTokenizerExt.TT_DOUBLE: {
                    nodes.add(factory.styled(factory.plain(st.image), NutsTextNodeStyle.number()));
                    break;
                }
                case StreamTokenizerExt.TT_WORD: {
                    if (last.size() > 0 && last.get(last.size() - 1).equals("<")) {
                        nodes.add(formatNodeName(st.image));
                    } else if (last.size() > 1 && last.get(last.size() - 2).equals("<") && last.get(last.size() - 1).equals("/")) {
                        nodes.add(formatNodeName(st.image));
                    } else if (last.size() > 1 && last.get(last.size() - 2).equals("<") && last.get(last.size() - 1).equals("?")) {
                        nodes.add(formatNodeName(st.image));
                    } else {
                        if (st.image.equals("true") || st.image.equals("false")) {
                            nodes.add(formatNodeName(st.image));
                        } else {
                            nodes.add(factory.plain(st.image));
                        }
                    }
                    break;
                }
                case '\'': {
                    nodes.add(formatNodeString(st.image));
                    break;
                }
                case '\"': {
                    nodes.add(formatNodeString(st.image));
                    break;
                }
                case '<':
                case '>':
                case '&':
                case '=': {
                    nodes.add(factory.styled(factory.plain(st.image), NutsTextNodeStyle.separator()));
                    break;
                }
                default: {
                    nodes.add(factory.styled(factory.plain(st.image), NutsTextNodeStyle.separator()));
                }
            }
            last.add(st.image == null ? "" : st.image);
        }
        return factory.list(nodes);
    }
}
