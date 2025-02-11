package net.thevpc.nuts.runtime.standalone.workspace.cmd.settings.ndi.win;

import net.thevpc.nuts.*;
import net.thevpc.nuts.cmdline.NutsCommandLine;
import net.thevpc.nuts.io.NutsIOException;
import net.thevpc.nuts.io.NutsPath;
import net.thevpc.nuts.io.NutsPrintStream;
import net.thevpc.nuts.runtime.standalone.io.util.CoreIOUtils;
import net.thevpc.nuts.runtime.optional.mslink.OptionalMsLinkHelper;
import net.thevpc.nuts.runtime.standalone.workspace.cmd.settings.util.PathInfo;
import net.thevpc.nuts.runtime.standalone.workspace.cmd.settings.ndi.FreeDesktopEntry;
import net.thevpc.nuts.runtime.standalone.workspace.cmd.settings.ndi.base.AbstractFreeDesktopEntryWriter;
import net.thevpc.nuts.util.NutsUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WindowFreeDesktopEntryWriter extends AbstractFreeDesktopEntryWriter {
    private final NutsSession session;
    private final NutsPath desktopPath;

    public WindowFreeDesktopEntryWriter(NutsPath desktopPath, NutsSession session) {
        this.session = session;
        this.desktopPath = desktopPath;
    }

    @Override
    public PathInfo[] writeShortcut(FreeDesktopEntry descriptor, NutsPath path, boolean doOverride, NutsId id) {
        path = NutsPath.of(ensureName(path == null ? null : path.toString(), descriptor.getOrCreateDesktopEntry().getName(), "lnk"),session);
        FreeDesktopEntry.Group g = descriptor.getOrCreateDesktopEntry();
        if (g == null || g.getType() != FreeDesktopEntry.Type.APPLICATION) {
            throw new IllegalArgumentException("invalid entry");
        }
        String wd = g.getPath();
        if (wd == null) {
            wd = System.getProperty("user.home");
        }
        NutsPath q = path;
        boolean alreadyExists = q.exists();
        if (alreadyExists && !doOverride) {
            return new PathInfo[]{new PathInfo("desktop-shortcut", id, q, PathInfo.Status.DISCARDED)};
        }
        PathInfo.Status newStatus = new OptionalMsLinkHelper(g.getExec(), wd, g.getIcon(), q.toString(), session).write();
        return new PathInfo[]{new PathInfo("desktop-shortcut", id, q, newStatus)};
    }

    @Override
    public PathInfo[] writeDesktop(FreeDesktopEntry descriptor, String fileName, boolean doOverride, NutsId id) {
        fileName = Paths.get(ensureName(fileName, descriptor.getOrCreateDesktopEntry().getName(), "lnk")).getFileName().toString();
        NutsPath q = desktopPath.resolve(fileName);
        return writeShortcut(descriptor, q, doOverride, id);
    }

    @Override
    public PathInfo[] writeMenu(FreeDesktopEntry descriptor, String fileName, boolean doOverride, NutsId id) {
        List<PathInfo> result = new ArrayList<>();
        FreeDesktopEntry.Group root = descriptor.getOrCreateDesktopEntry();
        if (root == null || root.getType() != FreeDesktopEntry.Type.APPLICATION) {
            throw new IllegalArgumentException("invalid entry");
        }
        String wd = root.getPath();
        if (wd == null) {
            wd = System.getProperty("user.home");
        }
        String[] cmd = NutsCommandLine.parseDefault(root.getExec()).get(session).setExpandSimpleOptions(false).toStringArray();
        List<String> categories = new ArrayList<>(root.getCategories());
        if (categories.isEmpty()) {
            categories.add("/");
        }
        for (String category : categories) {
            List<String> part = Arrays.stream((category == null ? "" : category).split("/")).filter(x -> !x.isEmpty()).collect(Collectors.toList());

            NutsPath m = NutsPath.ofUserHome(session).resolve("AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs");
            if (!part.isEmpty() && part.get(0).equals("Applications")) {
                part.remove(0);
            }
            if (part.size() > 0) {
                m = m.resolve(String.join("\\", part));
                m.mkdirs();
            }

            NutsPath q = m.resolve(descriptor.getOrCreateDesktopEntry().getName() + ".lnk");
            boolean alreadyExists = q.exists();
            if (alreadyExists && !doOverride) {
                result.add(new PathInfo("desktop-menu", id, q, PathInfo.Status.DISCARDED));
            } else {
                PathInfo.Status newStatus = new OptionalMsLinkHelper(root.getExec(), wd, root.getIcon(), q.toString(), session).write();
                result.add(new PathInfo("desktop-menu", id, q, newStatus));
            }

        }
        return result.toArray(new PathInfo[0]);
    }

    public void write(FreeDesktopEntry file, Path out) {
        FreeDesktopEntry.Group g = file.getOrCreateDesktopEntry();
        if (g == null || g.getType() != FreeDesktopEntry.Type.APPLICATION) {
            throw new IllegalArgumentException("invalid entry");
        }
        String wd = g.getPath();
        if (wd == null) {
            wd = System.getProperty("user.home");
        }
        new OptionalMsLinkHelper(g.getExec(), wd, g.getIcon(), out.toString(), session).write();
    }

    public boolean tryWrite(FreeDesktopEntry file, Path out) {
        byte[] old = CoreIOUtils.loadFileContentLenient(out);
        if (old.length == 0) {
            write(file, out);
            return true;
        }
        write(file, out);
        byte[] next = CoreIOUtils.loadFileContentLenient(out);
        return !Arrays.equals(old, next);
    }

    public boolean tryWrite(FreeDesktopEntry file, File out) {
        return tryWrite(file, out.toPath());
    }

    public void write(FreeDesktopEntry file, File out) {
        try (PrintStream p = new PrintStream(out)) {
            write(file, p);
        } catch (IOException ex) {
            throw new NutsIOException(session, ex);
        }
    }

    public void write(FreeDesktopEntry file, NutsPrintStream out) {
        write(file, out.asPrintStream());
    }

    public void write(FreeDesktopEntry file, PrintStream out) {
        out.println("#!/usr/bin/env xdg-open");
        for (FreeDesktopEntry.Group group : file.getGroups()) {
            out.println();
            String gn = group.getGroupName();
            NutsUtils.requireNonBlank(gn,session,"group name");
            FreeDesktopEntry.Type t = group.getType();
            NutsUtils.requireNonBlank(t,session,"type");
            out.println("[" + gn.trim() + "]");
            for (Map.Entry<String, Object> e : group.toMap().entrySet()) {
                Object v = e.getValue();
                if (v instanceof Boolean || v instanceof String) {
                    out.println(e.getKey() + "=" + e.getValue());
                } else if (v instanceof List) {
                    char sep = ';';
                    out.println(e.getKey() + "=" +
                            ((List<String>) v).stream().map(x -> {
                                StringBuilder sb = new StringBuilder();
                                for (char c : x.toCharArray()) {
                                    if (c == sep || c == '\\') {
                                        sb.append('\\');
                                    }
                                    sb.append(c);
                                }
                                return sb.toString();
                            }).collect(Collectors.joining("" + sep))
                    );
                } else {
                    throw new IllegalArgumentException("unsupported value type for " + e.getKey());
                }
            }

        }
    }

}
