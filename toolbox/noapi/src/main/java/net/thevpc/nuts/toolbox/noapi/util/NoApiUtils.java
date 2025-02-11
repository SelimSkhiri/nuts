package net.thevpc.nuts.toolbox.noapi.util;

import net.thevpc.nuts.NutsBlankable;
import net.thevpc.nuts.NutsSession;
import net.thevpc.nuts.elem.*;
import net.thevpc.nuts.io.NutsPath;
import net.thevpc.nuts.lib.md.MdDocument;
import net.thevpc.nuts.lib.md.MdElement;
import net.thevpc.nuts.lib.md.MdFactory;
import net.thevpc.nuts.lib.md.MdWriter;
import net.thevpc.nuts.lib.md.asciidoctor.AsciiDoctorWriter;
import net.thevpc.nuts.spi.NutsPaths;
import net.thevpc.nuts.text.NutsTextStyle;
import net.thevpc.nuts.text.NutsTexts;
import net.thevpc.nuts.toolbox.noapi.model.FieldInfo;
import net.thevpc.nuts.toolbox.noapi.model.SupportedTargetType;
import net.thevpc.nuts.toolbox.noapi.model.TypeInfo;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class NoApiUtils {
    public static MdElement asText(String text) {
        List<MdElement> all = new ArrayList<>();
        int i = 0;
        while (i < text.length()) {
            int j = text.indexOf("##", i);
            if (j < 0) {
                all.add(MdFactory.text(text.substring(i)));
                break;
            }
            String a = text.substring(i, j);
            if (a.length() > 0) {
                all.add(MdFactory.text(a));
            }
            int j2 = text.indexOf("##", j + 2);
            if (j2 < 0) {
                all.add(MdFactory.codeBacktick3("", text.substring(j + 2)));
                break;
            } else {
                all.add(MdFactory.codeBacktick3("", text.substring(j + 2, j2)));
                i = j2 + 2;
            }
        }
        return MdFactory.ofListOrEmpty(all.toArray(new MdElement[0]));
    }

    public static NutsElement loadElement(NutsPath source, NutsSession session) {
        boolean json = false;
//        Path sourcePath = Paths.get(source).normalize().toAbsolutePath();
        try (BufferedReader r = new BufferedReader(new InputStreamReader(source.getInputStream()))) {
            String t;
            while ((t = r.readLine()) != null) {
                t = t.trim();
                if (t.length() > 0) {
                    if (t.startsWith("{")) {
                        json = true;
                    }
                    break;
                }
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }

        if (json) {
            return NutsElements.of(session).json().parse(source, NutsElement.class);
        } else {
//            return NutsElements.of(session).json().parse(inputStream, NutsElement.class);
            try (InputStream is = source.getInputStream()) {
                final Object o = new Yaml().load(is);
                return NutsElements.of(session).toElement(o);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
    }

    public static MdElement codeElement(TypeInfo o, boolean includeDesc, String extra, AppMessages msg) {
        String type = "javascript";
        if (o.getUserType().equals("object")) {
            type = "json";
        }

        String s = toCode(o, includeDesc, "", msg);
        if (extra != null) {
            s += extra;
        }
        return MdFactory.codeBacktick3(type, s);
    }

    public static MdElement jsonTextElementInlined(Object example) {
        if (NutsBlankable.isBlank(example)) {
            return MdFactory.text("");
        }
        String e = jsonTextString(example);
        return MdFactory.codeBacktick3("json", e);
    }

    public static MdElement jsonTextElement(Object example) {
        if (NutsBlankable.isBlank(example)) {
            return MdFactory.text("");
        }
        String e = jsonTextString(example);
        return MdFactory.codeBacktick3Paragraph("json", e);
    }

    public static String jsonTextString(Object example) {
        if (example instanceof NutsPrimitiveElement) {
            return ((NutsPrimitiveElement) example).toStringLiteral();
        }
        if (example instanceof NutsElementEntry) {
            return
                    jsonTextString(((NutsElementEntry) example).getKey())
                            + " : "
                            + jsonTextString(((NutsElementEntry) example).getValue());
        }
        if (example instanceof NutsArrayElement) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            Collection<NutsElement> entries = ((NutsArrayElement) example).items();
            sb.append(
                    entries.stream().map(NoApiUtils::jsonTextString).collect(Collectors.joining(", "))
            );
            sb.append("]");
            return sb.toString();
        }
        if (example instanceof NutsObjectElement) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            Collection<NutsElementEntry> entries = ((NutsObjectElement) example).entries();
            sb.append(
                    entries.stream().map(NoApiUtils::jsonTextString).collect(Collectors.joining(", "))
            );
            sb.append("}");
            return sb.toString();
        }
        return example.toString();
    }

    public static String toCode(TypeInfo o, boolean includeDesc, String indent, AppMessages msg) {
        String descSep = "";
        if (includeDesc) {
            if (!NutsBlankable.isBlank(o.getDescription())) {
                descSep = " // " + o.getDescription();
            }
        }
        if (o.getRef() != null) {
            return o.getRef() + descSep;
        } else if (o.getUserType().equals("object")) {
            StringBuilder sb = new StringBuilder("{");
            for (FieldInfo p : o.getFields()) {
                sb.append("\n").append(indent).append("  ").append(p.name).append(": ").append(toCode(p.schema, includeDesc, indent + "  ", msg));
            }
            sb.append("\n").append(indent).append("}");
            sb.append(descSep);
            return sb.toString();
        } else {
            String type = o.getUserType();
            switch (o.getUserType()) {
                case "string":
                case "enum": {
                    if (!NutsBlankable.isBlank(o.getMinLength()) && !NutsBlankable.isBlank(o.getMaxLength())) {
                        type += ("[" + o.getMinLength().trim() + "," + o.getMaxLength().trim() + "]");
                    } else if (!NutsBlankable.isBlank(o.getMinLength())) {
                        type += (">=" + o.getMinLength().trim());
                    } else if (!NutsBlankable.isBlank(o.getMaxLength())) {
                        type += ("<=" + o.getMinLength().trim());
                    }
                    if (o.getEnumValues() != null && o.getEnumValues().size() > 0) {
                        type += " " + msg.get("ALLOWED").get() + " {";
                        type += o.getEnumValues().stream().map(x -> x == null ? "null" : ("'" + x + "'")).collect(Collectors.joining(", "));
                        type += "}";
                    }
                    break;
                }
                case "integer":
                case "number": {
                    if (!NutsBlankable.isBlank(o.getMinLength()) && !NutsBlankable.isBlank(o.getMaxLength())) {
                        type += ("[" + o.getMinLength().trim() + "," + o.getMaxLength().trim() + "]");
                    } else if (!NutsBlankable.isBlank(o.getMinLength())) {
                        type += (">=" + o.getMinLength().trim());
                    } else if (!NutsBlankable.isBlank(o.getMaxLength())) {
                        type += ("<=" + o.getMinLength().trim());
                    }
                    if (o.getEnumValues() != null && o.getEnumValues().size() > 0) {
                        type += " " + msg.get("ALLOWED").get() + " {";
                        type += o.getEnumValues().stream().map(x -> x == null ? "null" : x).collect(Collectors.joining(", "));
                        type += "}";
                    }
                    break;
                }
                case "boolean": {
                    if (o.getEnumValues() != null && o.getEnumValues().size() > 0) {
                        type += " " + msg.get("ALLOWED").get() + " {";
                        type += o.getEnumValues().stream().map(x -> x == null ? "null" : x).collect(Collectors.joining(", "));
                        type += "}";
                    }
                }
            }
            return type + descSep;
        }
    }

    public static SupportedTargetType resolveTarget(String target, SupportedTargetType def) {
        if (target == null) {
            return def;
        } else if (target.endsWith(".pdf")) {
            return SupportedTargetType.PDF;
        } else if (target.equals(".adoc")) {
            return SupportedTargetType.ADOC;
        }
        return def;
    }

    public static NutsPath addExtension(NutsPath sourcePath, NutsPath parent, NutsPath target, SupportedTargetType targetType, String version, NutsSession session) {
        sourcePath = sourcePath.normalize().toAbsolute();
        String e = targetType.name().toLowerCase();
        if (parent == null) {
            parent = sourcePath.getParent();
        }
        if (NutsBlankable.isBlank(target) || target.getName().equals(".pdf") || target.getName().equals(".adoc") || target.getName().equals(".json")) {
            target = parent.resolve(sourcePath.getSmartBaseName()
                    + (NutsBlankable.isBlank(version)?"":("-" + version))
                    + "." + sourcePath.getSmartExtension());
        }
        return NoApiUtils.addExtension(target, e, session);
    }

    public static NutsPath addExtension(NutsPath source, String ext, NutsSession session) {
        NutsPath path = source.normalize().toAbsolute();
        String n = path.getName();
        n = NutsPath.of(n, session).getSmartBaseName() + "." + ext;
        return path.getParent().resolve(n);
    }

    public static void writeAdoc(MdDocument md, NutsPath target, boolean keep, SupportedTargetType type, NutsSession session) {
        boolean trace = keep && session.isPlainTrace();
        String temp = null;
        String adocFile = null;
        String pdfFile = null;
        boolean pdf = type == SupportedTargetType.PDF;
        if (pdf) {
            if (keep) {
                temp = NoApiUtils.addExtension(target, "adoc", session).toString();
            } else {
                temp = NutsPaths.of(session)
                        .createTempFile("temp.adoc", session).toString();
            }
            adocFile = temp;
        }

        try (MdWriter mw = new AsciiDoctorWriter(NutsPath.of(adocFile, session))) {
            mw.write(md);
        }
        if (trace) {
            if (pdf) {
                session.out().printf("generated src %s\n",
                        NutsTexts.of(session).ofStyled(
                                adocFile, NutsTextStyle.primary4()
                        )
                );
            }
        }
        if (pdf) {
            pdfFile = NoApiUtils.addExtension(target, "pdf", session).toString();
            if (new File(pdfFile).getParentFile() != null) {
                new File(pdfFile).getParentFile().mkdirs();
            }
            Asciidoctor asciidoctor = Asciidoctor.Factory.create();
            String outfile = asciidoctor.convertFile(new File(adocFile),
                    OptionsBuilder.options()
                            //                            .inPlace(true)
                            .backend("pdf")
                            .safe(SafeMode.UNSAFE)
                            .toFile(new File(pdfFile))
            );
            if (session.isPlainTrace()) {
                session.out().printf("generate  pdf file %s\n", NutsPath.of(pdfFile,session));
            }
            if (!keep) {
                new File(temp).delete();
            }
        }
    }

    public static String toValidFileName(String target) {
        StringBuilder targetSb = new StringBuilder();
        for (char c : target.toCharArray()) {
            switch (c) {
                case '\\':
                case '/':
                case '\n':
                case '\t':
                case '\0':
                case '<':
                case '>':
                case '?':
                case '*':
                case '[':
                case ']':
                case '(':
                case ')':
                case '{':
                case '}': {
                    targetSb.append('_');
                    break;
                }
                default: {
                    targetSb.append(c);
                }
            }
        }
        return target.toString();
    }
}
