package net.thevpc.nuts.toolbox.nsh.jshell.parser;

import net.thevpc.nuts.*;
import net.thevpc.nuts.io.NutsPath;
import net.thevpc.nuts.toolbox.nsh.jshell.*;
import net.thevpc.nuts.util.NutsGlob;

import java.util.*;
import java.util.stream.Collectors;

public class Yaccer {

    private final Lexer lexer;
    private final LinkedList<JShellNode> buffer = new LinkedList<>();

    public Yaccer(Lexer lexer) {
        this.lexer = lexer;
    }


    public Iterable<JShellNode> nodes() {
        return () -> new Iterator<JShellNode>() {
            JShellNode n = null;

            @Override
            public boolean hasNext() {
                n = readNode();
                return n != null;
            }

            @Override
            public JShellNode next() {
                return n;
            }
        };
    }

    public JShellNode readNodeL0() {
        if (!buffer.isEmpty()) {
            return buffer.removeFirst();
        }
        Token u = getLexer().peekToken();
        if (u == null) {
            return null;
        }
        switch (u.type) {
            case "WHITE": {
                getLexer().skipWhites();
                return new WhiteNode(u);
            }
            case "NEWLINE": {
                u = getLexer().nextToken();
                return new NewlineNode(u);
            }
            case "#": {
                return readComments();
            }
            case "WORD":
            case "$WORD":
            case "\"":
            case "'":
            case "`":
            case "$(":
            case "$((":
            case "${":
            case "{":
            case "=":
            case ":":
            case "&&":
            case "&":
            case "|":
            case "||":
            case "<":
            case "<<":
            case ">":
            case ">>":
            case "&>":
            case "&>>":
            case "&<":
            case "&<<":
            case "*":
            case "?": {
                return new TokenNode(getLexer().nextToken());
            }
            case "(": {
                return readScriptPar();
            }
            default: {
                return new TokenNode(getLexer().nextToken());
            }
        }
    }

    private Lexer getLexer() {
        return lexer;
    }

    private JShellCommandNode readScriptL1() {
        Token u = getLexer().peekToken();
        if (u == null) {
            return null;
        }
        while (true) {
            Token not = getLexer().peekToken();
            if (not != null && (not.isNewline() || not.isEndCommand())) {
                getLexer().nextToken();
            } else {
                u=not;
                break;
            }
        }
        if (u == null) {
            return null;
        }
        if (u.type.equals("!")) {
            Token not = getLexer().nextToken();
            JShellCommandNode next = readScriptL1();
            return new UnOpPrefix(not, next);
        }
        if (u.type.equals("(")) {
            return readScriptPar();
        }
        if (u.isSharp()) {
            Comments c = readComments();
            JShellCommandNode next = readScriptL1();
            return new CommentedNode(next, c);
        }
        JShellCommandNode a = readScriptLine();
        if (a == null) {
            return a;
        }
        u = getLexer().peekToken();
        if (u == null) {
            return a;
        }
        switch (u.type) {
            case "&&":
            case "||": {
                Token op = getLexer().nextToken();
                JShellNode b = readScriptLine();
                if (b == null) {
                    return new UnOpSuffix(a, op);
                }
                return new BinOp(a, op, b);
            }
        }
        return a;
    }

    public JShellCommandNode readScriptL2() {
        JShellCommandNode a = readScriptL1();
        if (a == null) {
            return null;
        }
        while (true) {
            Token u = getLexer().peekToken();
            if (u == null) {
                return a;
            }
            switch (u.type) {
                case "|": {
                    Token op = getLexer().nextToken();
                    JShellCommandNode b = readScriptL1();
                    if (b == null) {
                        return new UnOpSuffix(a, op);
                    } else {
                        a = new BinOp(a, op, b);
                    }
                    break;
                }
                default: {
                    return a;
                }
            }
        }
    }

    public JShellCommandNode readScriptL3() {
        JShellCommandNode a = readScriptL2();
        if (a == null) {
            return null;
        }
        while (true) {
            Token u = getLexer().peekToken();
            if (u == null) {
                return a;
            }
            switch (u.type) {
                case "&": {
                    Token op = getLexer().nextToken();
                    JShellCommandNode b = readScriptL2();
                    if (b == null) {
                        return new UnOpSuffix(a, op);
                    } else {
                        a = new BinOp(a, op, b);
                    }
                    break;
                }
                default: {
                    return a;
                }
            }
        }
    }

    public JShellCommandNode readScriptL4() {
        JShellCommandNode a = readScriptL3();
        if (a == null) {
            return null;
        }
        while (true) {
            Token u = getLexer().peekToken();
            if (u == null) {
                return a;
            }
            switch (u.type) {
                case ">":
                case ">>":
                case "<":
                case "<<":
                case "&>":
                case "&2>":
                case "&>>":
                case "&2>>": {
                    Token op = getLexer().nextToken();
                    JShellNode b = readScriptL3();
                    if (b == null) {
                        return new UnOpSuffix(a, op);
                    } else {
                        a = new BinOp(a, op, b);
                    }
                    break;
                }
                default: {
                    return a;
                }
            }
        }
    }

    public JShellCommandNode readScriptL5() {
        JShellCommandNode a = null;
        Token sep = null;
        while (true) {
            Token u = getLexer().peekToken();
            if (u == null) {
                return a;
            }
            switch (u.type) {
                case ";":
                case "NEWLINE": {
                    sep = getLexer().nextToken();
                    break;
                }
                default: {
                    JShellCommandNode b = readScriptL4();
                    if (b == null) {
                        return a;
                    }
                    if (a == null) {
                        a = b;
                    } else {
                        if (sep == null) {
                            sep = new Token("NEWLINE", "\n", "\n");
                        }
                        a = new BinOpCommand(a, sep, b);
                    }
                    sep = null;
                    break;
                }
            }
        }
    }

    public JShellNode readNodeL1() {
        JShellNode a = readNodeL0();
        if (a == null) {
            return a;
        }
        Token u = getLexer().peekToken();
        if (u == null) {
            return a;
        }
        switch (u.type) {
            case "&&":
            case "&": {
                Token op = getLexer().nextToken();
                JShellNode b = readNodeL0();
                if (b == null) {
                    return new UnOpSuffix(a, op);
                }
                return new BinOp(a, op, b);
            }
        }
        return a;
    }

    public JShellNode readNodeL2() {
        JShellNode a = readNodeL1();
        if (a == null) {
            return a;
        }
        Token u = getLexer().peekToken();
        if (u == null) {
            return a;
        }
        switch (u.type) {
            case "||":
            case "|": {
                Token op = getLexer().nextToken();
                JShellNode b = readNodeL1();
                if (b == null) {
                    return new UnOpSuffix(a, op);
                }
                return new BinOp(a, op, b);
            }
        }
        return a;
    }

    public JShellNode readNodeL3() {
        JShellNode a = readNodeL1();
        if (a == null) {
            return a;
        }
        Token u = getLexer().peekToken();
        if (u == null) {
            return a;
        }
        switch (u.type) {
            case ";": {
                Token op = getLexer().nextToken();
                JShellNode b = readNodeL1();
                if (b == null) {
                    return new UnOpSuffix(a, op);
                }
                return new BinOp(a, op, b);
            }
        }
        return a;
    }

    public JShellNode readNodeL4() {
        JShellNode a = readNodeL3();
        if (a == null) {
            return a;
        }
        Token u = getLexer().peekToken();
        if (u == null) {
            return a;
        }
        switch (u.type) {
            case ">":
            case "&>":
            case "<":
            case "&<": {
                Token op = getLexer().nextToken();
                JShellNode b = readNodeL3();
                if (b == null) {
                    return new UnOpSuffix(a, op);
                }
                return new BinOp(a, op, b);
            }
        }
        return a;
    }

    public JShellNode readNodeL5() {
        JShellNode a = readNodeL4();
        if (a == null) {
            return null;
        }

        Token u = getLexer().peekToken();
        if (u == null) {
            return a;
        }
        switch (u.type) {
            case ";": {
                Token op = getLexer().nextToken();
                JShellNode b = readNodeL4();
                if (b == null) {
                    return new UnOpSuffix(a, op);
                }
                return new BinOp(a, op, b);
            }
        }
        return a;
    }


    public JShellNode readNode() {
//        return readNodeL5();
        return readNodeL0();
    }

    public JShellCommandNode readScriptPar() {
        Token u = getLexer().peekToken();
        if (u == null) {
            return null;
        }
        if (u.type.equals("(")) {
            getLexer().nextToken();
            JShellNode n = readScript();
            u = getLexer().peekToken();
            if (u == null || u.type.equals(")")) {
                if (u != null) {
                    getLexer().nextToken();
                }
                return new Par(n);
            }
            return new Par(n);
        }
        return null;
    }

    public JShellCommandNode readCommandL1() {
        getLexer().skipWhites();
        Token t = getLexer().peekToken();
        if (t == null) {
            return null;
        }
        JShellCommandNode line = readScriptLine();
        if (line == null) {
            return null;
        }
        boolean loop = true;
        while (loop) {
            loop = false;
            t = getLexer().peekToken();
            if (t != null) {
                switch (t.type) {
                    case "<":
                    case ">":
                    case "<<":
                    case ">>":
                    case "&<":
                    case "&>":
                    case "&<<":
                    case "&>>": {
                        getLexer().nextToken();
                        JShellCommandNode next = readScriptLine();
                        if (next == null) {
                            line = new SuffixOpCommand(line, t);
                        } else {
                            line = new BinOpCommand(line, t, next);
                            loop = true;
                        }
                        break;
                    }
                }
            }
        }
        return line;
    }

    public JShellCommandNode readCommandL2() {
        JShellCommandNode line = readCommandL1();
        if (line == null) {
            return null;
        }
        boolean loop = true;
        while (loop) {
            loop = false;
            Token t = getLexer().peekToken();
            if (t != null) {
                switch (t.type) {
                    case "|": {
                        getLexer().nextToken();
                        JShellCommandNode next = readCommandL1();
                        if (next == null) {
                            line = new SuffixOpCommand(line, t);
                        } else {
                            line = new BinOpCommand(line, t, next);
                            loop = true;
                        }
                    }
                }
            }
        }
        return line;
    }

    public JShellCommandNode readCommandL3() {
        JShellCommandNode line = readCommandL2();
        if (line == null) {
            return null;
        }
        boolean loop = true;
        while (loop) {
            loop = false;
            Token t = getLexer().peekToken();
            if (t != null) {
                switch (t.type) {
                    case "&&":
                    case "||": {
                        getLexer().nextToken();
                        JShellCommandNode next = readCommandL2();
                        if (next == null) {
                            line = new SuffixOpCommand(line, t);
                        } else {
                            line = new BinOpCommand(line, t, next);
                            loop = true;
                        }
                    }
                }
            }
        }
        return line;
    }

    private String getArgumentsLineFirstArgToken(JShellCommandNode line) {
        if (line != null) {
            Argument arg1 = ((ArgumentsLine) line).args.get(0);
            if (arg1.nodes.size() == 1 && arg1.nodes.get(0) instanceof TokenNode) {
                Token token = ((TokenNode) arg1.nodes.get(0)).token;
                if (token.isWord()) {
                    return token.value.toString();
                }
            }
        }
        return "";
    }

    public ArgumentsLine readScriptLine() {
        List<Argument> a = new ArrayList<>();
        while (true) {
            Token t = getLexer().peekToken();
            if (t == null) {
                break;
            }
            boolean exit = false;
            switch (t.type) {
                case "NEWLINE":
                case ";":
                case "&":
                case "&&":
                case "<<":
                case ">>":
                case "&<":
                case "&>":
                case "&<<":
                case "&>>":
                case "|":
                case "||": {
                    exit = true;
                    break;
                }
            }
            if (exit) {
                break;
            }
            if (t.isWhite()) {
                getLexer().nextToken();
                //ignore
            } else {
                Argument aa = readArgument();
                if (aa != null) {
                    a.add(aa);
                } else {
                    throw new IllegalArgumentException("Unexpected " + aa);
                }
            }
        }
        if (a.isEmpty()) {
            return null;
        }
        return new ArgumentsLine(a);
    }

    public JShellCommandNode readScript() {
        return readScriptL5();
    }

    public Comments readComments() {
        List<Token> ok = new ArrayList<>();
        while (true) {
            Token t = getLexer().peekToken();
            if (t == null) {
                break;
            }
            if (t.isSharp()) {
                getLexer().nextToken();
                ok.add(t);
            } else {
                break;
            }
        }
        if (ok.isEmpty()) {
            return null;
        }
        return new Comments(ok);
    }

    public Argument readArgument() {
        List<JShellNode> a = new ArrayList<>();
        while (true) {
            Token t = getLexer().peekToken();
            if (t == null || t.isNewline() || t.isSemiColon()) {
                break;
            }
            if (t.isSharp()) {
                if (!a.isEmpty()) {
                    break;
                }
            }
            JShellNode n = readNode();
            if (n == null) {
                break;
            }
            if (n instanceof WhiteNode) {
                break;
            }
            a.add(n);
        }
        if (a.isEmpty()) {
            return null;
        }
        return new Argument(a);
//        List<NutsToken> ok = new ArrayList<>();
//        boolean loop = true;
//        while (loop) {
//            NutsToken t = jShellParser2.lexer().peedToken();
//            if (t == null) {
//                break;
//            }
//            switch (t.type) {
//                case "WORD":
//                case "$WORD":
//                case "$(":
//                case "$((":
//                case "${":
//                case "(":
//                case "{":
//                case "=":
//                case ":":
//                case "\"":
//                case "'":
//                case "`": {
//                    jShellParser2.lexer().nextToken();
//                    ok.add(t);
//                    break;
//                }
//                default: {
//                    loop = false;
//                    break;
//                }
//            }
//        }
//        if (ok.isEmpty()) {
//            return null;
//        }
//        return new Argument(ok);
    }

    public static String evalTokenString(Token token, JShellContext context) {
        NutsGlob g = NutsGlob.of(context.getSession());
        switch (token.type) {
            case "WORD": {
                return token.value.toString();
            }
            case "$WORD": {
                String s = (String) token.value;
                switch (s) {
                    case "0": {
                        return g.escape(context.getServiceName());
                    }
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9": {
                        return g.escape(context.getArg(Integer.parseInt(s) - 1));
                    }
                    case "?": {
                        return g.escape(String.valueOf(context.getArgsCount()));
                    }
                    default: {
                        String y = context.vars().get(s,"");
                        return g.escape(y);
                    }
                }
            }
            case "`":
            case "$(": {
                List<Token> subTokens = new ArrayList<>((Collection<? extends Token>) token.value);
                if (subTokens.isEmpty()) {
                    return "";
                }
                Yaccer yy2 = new Yaccer(new PreloadedLexer(subTokens));
                JShellCommandNode subCommand = yy2.readScript();
                if (subCommand == null) {
                    //all are comments perhaps!
                    return "";
                }
                return g.escape(context.getShell().getEvaluator().evalCommandAndReturnString(subCommand, context));
            }
            case "\"": {
                List<Token> s = (List<Token>) token.value;
                StringBuilder sb = new StringBuilder();
                for (Token token2 : s) {
                    sb.append(evalTokenString(token2, context));
                }
                return sb.toString();
            }
            case "'": {
                if (token.value instanceof String) {
                    return g.escape((String) token.value);
                }
                StringBuilder sb = new StringBuilder();
                for (Token t : ((List<Token>) token.value)) {
                    sb.append(g.escape(evalTokenString(t, context)));
                }
                return sb.toString();
            }
            case "STR": {
                return (String) token.value;
            }
            case "${": {
                StringBuilder sb = new StringBuilder();
                //TODO fix me, should implement ${...} expressions
                List<Token> values = (List<Token>) token.value;
                if(values.isEmpty()){
                    throw new IllegalArgumentException("bad substitution");
                }
                String varVal="";
                Token t = values.get(0);
                if(t.isWord()) {
                    String y = context.vars().get(evalTokenString(t,context),null);
                    if (y != null) {
                        varVal = y;
                    }
                }else{
                    throw new IllegalArgumentException("bad substitution");
                }
                sb.append(g.escape(varVal));
                return sb.toString();
            }
            default: {
                return (String) token.value;
            }
        }
    }


    public static String evalNodeString(JShellNode node, JShellContext context) {
        if (node instanceof Comments) {
            return "";
        } else if (node instanceof TokenNode) {
            return ((TokenNode) node).evalString(context);
        }
        throw new RuntimeException("Error");
    }

    public class WhiteNode implements JShellNode {
        Token token;

        public WhiteNode(Token token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return String.valueOf(token.getImage());
        }
    }

    public class NewlineNode implements JShellNode {
        Token token;

        public NewlineNode(Token token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return String.valueOf(token.getImage());
        }
    }

    public static class TokenNode implements JShellNode {
        Token token;

        public TokenNode(Token token) {
            this.token = token;
        }

        public String evalString(JShellContext context) {
            return evalTokenString(token, context);
        }

        @Override
        public String toString() {
            return String.valueOf(token.getImage());
        }
    }

    public static class BinOpCommand implements JShellCommandNode {
        JShellCommandNode left;
        Token op;
        JShellCommandNode right;

        public BinOpCommand(JShellCommandNode left, Token op, JShellCommandNode right) {
            this.left = left;
            this.op = op;
            this.right = right;
        }

        @Override
        public int eval(final JShellContext context) {
            String cmd = op.isNewline() ? ";" : String.valueOf(op.value);
            return context.getShell().getEvaluator().evalBinaryOperation(cmd, left, right, context);
        }
        private List<JShellCommandNode> expandCommands(JShellCommandNode c){
            if(c instanceof BinOpCommand){
                BinOpCommand b = (BinOpCommand) c;
                if(b.op.isSemiColon()){
                    List<JShellCommandNode> a=new ArrayList<>();
                    a.addAll(expandCommands(b.left));
                    a.addAll(expandCommands(b.right));
                    return a;
                }
            }
            return Arrays.asList(c);
        }

        @Override
        public String toString() {
            if(op.isNewline()){
                return
                        left +
                        " " + op.value +
                        right;
            }
            if(op.isSemiColon()){
                return expandCommands(this).stream().map(Object::toString).collect(Collectors.joining(";"));
            }
            return "(" +
                    left +
                    " " + op.value +
                    right +
                    ')';
        }
    }

    public class SuffixOpCommand implements JShellCommandNode {
        JShellCommandNode a;
        Token op;

        public SuffixOpCommand(JShellCommandNode a, Token op) {
            this.a = a;
            this.op = op;
        }

        @Override
        public int eval(JShellContext context) {
            switch (op.type) {
                case "&": {
                    return context.getShell().getEvaluator().evalSuffixAndOperation(a, context);
                }
            }
            throw new IllegalArgumentException("Unsupported yet");
        }
    }

    public class CondBloc {
        JShellCommandNode cond;
        JShellCommandNode block;

        public CondBloc(JShellCommandNode cond, JShellCommandNode block) {
            this.cond = cond;
            this.block = block;
        }

        public boolean eval(JShellContext context) {
//        System.out.println("+ IF " + conditionNode);
            boolean trueCond = false;
            if (cond != null) {
                try {
                    context.getShell().evalNode(cond, context);
                    trueCond = true;
                } catch (JShellUniformException ex) {
                    if (ex.isQuit()) {
                        ex.throwQuit();
                    }
                    trueCond = false;
                }
                if (trueCond) {
                    if (block != null) {
                        context.getShell().evalNode(block,context);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public class IfCommand implements JShellCommandNode {
        CondBloc _if;
        JShellCommandNode _then;
        List<CondBloc> _elif = new ArrayList<>();
        JShellCommandNode _else;

        @Override
        public int eval(JShellContext context) {
//        System.out.println("+ IF " + conditionNode);
            if (_if.eval(context)) {
                return 0;
            }
            for (CondBloc condBloc : _elif) {
                if (condBloc.eval(context)) {
                    return 0;
                }
            }
            if (_else != null) {
                return context.getShell().evalNode(_else,context);
            }
            return 1;
        }

    }

    public class WhileCommand implements JShellCommandNode {
        CondBloc _while;
        JShellCommandNode _do;
        JShellCommandNode _done;

        @Override
        public int eval(JShellContext context) {
            while (true) {
                if (!_while.eval(context)) {
                    return 0;
                }
            }
        }
    }

    public static class ArgumentsLine implements JShellCommandLineNode {
        List<Argument> args;

        public ArgumentsLine(List<Argument> args) {
            this.args = args;
        }

        public List<Argument> getArgs() {
            return args;
        }

        @Override
        public Iterator<JShellArgumentNode> iterator() {
            return (Iterator) args.iterator();
        }

        @Override
        public int eval(JShellContext context) {
            JShell shell = context.getShell();
            ArrayList<String> cmds = new ArrayList<String>();
            Map<String, String> usingItems = new LinkedHashMap<>();
            List<Argument> args2 = new ArrayList<>(args);
            boolean source = false;
            if (args2.size() > 0) {
                Argument arg = args2.get(0);
                List<JShellNode> anodes = arg.nodes;
                if (anodes.size() == 1
                        && anodes.get(0) instanceof TokenNode && ((TokenNode) anodes.get(0)).token.isDot()
                ) {
                    source = true;
                    args2.remove(0);
                }
            }
            if (!source) {
                while (args2.size() > 0) {
                    Argument arg = args2.get(0);
                    List<JShellNode> anodes = arg.nodes;
                    if (anodes.size() >= 2
                            && anodes.get(0) instanceof TokenNode && ((TokenNode) anodes.get(0)).token.isWord()
                            && anodes.get(1) instanceof TokenNode && ((TokenNode) anodes.get(1)).token.isEquals()
                    ) {
                        String varName = ((TokenNode) anodes.get(0)).evalString(context);
                        String[] varValues = (anodes.size() > 2) ? new Argument(anodes.subList(2, anodes.size())).evalString(context) : new String[]{""};
                        usingItems.put(varName, String.join(" ", varValues));
                        args2.remove(0);
                    } else {
                        break;
                    }
                }
            }
            for (Argument arg : args2) {
                cmds.addAll(Arrays.asList(arg.evalString(context)));
            }
            if (source) {
                cmds.add(0, "source");
                return shell.executePreparedCommand(cmds.toArray(new String[0]), true, true, true, context);
            } else {
                if (cmds.isEmpty() || (cmds.size() == 1 && cmds.get(0).isEmpty())) {
                    if (!usingItems.isEmpty()) {
                        context.vars().set((Map) usingItems);
                    }
                } else {
                    if (!usingItems.isEmpty()) {
                        context = shell.createNewContext(context);
                        context.vars().set(context.vars());
                        context.vars().set((Map) usingItems);
                    }
                    return shell.executePreparedCommand(cmds.toArray(new String[0]), true, true, true, context);
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < args.size(); i++) {
                Argument arg = args.get(i);
                if(i>0){
                    sb.append(" ");
                }
                sb.append(arg);
            }
            return sb.toString();
        }
    }

    public class Par implements JShellCommandNode {
        JShellNode element;

        public Par(JShellNode element) {
            this.element = element;
        }

        @Override
        public int eval(JShellContext context) {
            return context.getShell().evalNode(
                    ((JShellCommandNode) element),
                    context
            );
        }

        @Override
        public String toString() {
            return "(" +element +')';
        }
    }

    public class UnOpSuffix implements JShellCommandNode {
        JShellNode a;
        Token op;

        public UnOpSuffix(JShellNode a, Token op) {
            this.a = a;
            this.op = op;
        }

        @Override
        public String toString() {
            return a +
                    " " + op;
        }

        @Override
        public int eval(JShellContext context) {
            throw new NutsIllegalArgumentException(context.getSession(), NutsMessage.ofCstyle("not yet implemented UnOpSuffix %s",op.image));
        }
    }

    public class CommentedNode implements JShellCommandNode {
        JShellNode a;
        List<Comments> comments = new ArrayList<>();

        public CommentedNode(JShellNode a, Comments comments) {
            if (a instanceof CommentedNode) {
                this.a = ((CommentedNode) a).a;
                this.comments.add(comments);
                this.comments.addAll(((CommentedNode) a).comments);
            } else {
                this.a = a;
                this.comments.add(comments);
            }
        }

        @Override
        public int eval(JShellContext context) {
            if (a != null) {
                return context.getShell().evalNode(
                        ((JShellCommandNode) a),context
                );
            }
            return 0;
        }
        @Override
        public String toString() {
            return a.toString();
        }
    }

    public class UnOpPrefix implements JShellCommandNode {
        JShellNode a;
        Token op;

        public UnOpPrefix(Token op, JShellNode a) {
            this.a = a;
            this.op = op;
        }

        @Override
        public String toString() {
            return op + " " + a;
        }

        @Override
        public int eval(JShellContext context) {
            throw new NutsIllegalArgumentException(context.getSession(), NutsMessage.ofCstyle("not yet implemented UnOpPrefix %s",op.image));
        }
    }

    public class BinOp implements JShellCommandNode {
        JShellNode a;
        Token op;
        JShellNode b;

        public BinOp(JShellNode a, Token op, JShellNode b) {
            this.a = a;
            this.op = op;
            this.b = b;
        }

        @Override
        public String toString() {
            return a +
                    " " + op +
                    " " + b;
        }

        @Override
        public int eval(JShellContext context) {
            throw new NutsIllegalArgumentException(context.getSession(), NutsMessage.ofCstyle("not yet implemented BinOp %s",op.image));
        }
    }

    public static class Argument implements JShellArgumentNode {
        List<JShellNode> nodes;
        public Argument(List<JShellNode> nodes) {
            this.nodes = nodes;
        }

        @Override
        public String toString() {
            StringBuilder sb=new StringBuilder();
            for (JShellNode node : nodes) {
                sb.append(node);
            }
            return sb.toString();
        }

        public String[] evalString(JShellContext context) {
            StringBuilder sb = new StringBuilder();
            for (JShellNode node : nodes) {
                sb.append(evalNodeString(node, context));
            }
            String value = sb.toString();
            if(value.equals("~")){
                value=context.getHome();
            }else if(value.startsWith("~/") || value.startsWith("~\\")){
                String c = context.getHome();
                value = c + value.substring(1);
            }
            boolean wasAntiSlash = false;
            boolean applyWildCard = false;
            StringBuilder sb2 = new StringBuilder();
            for (char c : value.toCharArray()) {
                if (wasAntiSlash) {
                    wasAntiSlash = false;
                    sb2.append(c);
                } else {
                    switch (c) {
                        case '\\': {
                            wasAntiSlash = true;
                            break;
                        }
                        case '*':
                        case '?': {
                            sb2.append(c);
                            applyWildCard = true;
                            break;
                        }
                        default: {
                            sb2.append(c);
                            break;
                        }
                    }
                }
            }
            if (applyWildCard) {
                NutsPath pp=NutsPath.of(value,context.getSession());
                if(!pp.isAbsolute()){
                    pp=pp.toAbsolute(context.getCwd());
                }
                String[] r = pp.walkGlob().map(NutsPath::toString,"toString").toArray(String[]::new);
                if(r.length>0){
                    return r;
                }
            }
            return new String[]{sb2.toString()};
        }
    }

    public class Comments implements JShellNode {
        List<Token> tokens;

        public Comments(List<Token> tokens) {
            this.tokens = tokens;
        }

        @Override
        public String toString() {
            StringBuilder sb=new StringBuilder();
            for (Token token : tokens) {
                sb.append(token.getImage());
            }
            return sb.toString();
        }

    }

//    public void pushBack(JShellNode n){
//        buffer.addFirst(n);
//    }
}
