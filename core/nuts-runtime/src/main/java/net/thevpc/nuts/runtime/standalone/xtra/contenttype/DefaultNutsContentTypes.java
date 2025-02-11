package net.thevpc.nuts.runtime.standalone.xtra.contenttype;

import net.thevpc.nuts.*;
import net.thevpc.nuts.io.NutsContentTypes;
import net.thevpc.nuts.io.NutsPath;
import net.thevpc.nuts.runtime.standalone.io.util.CoreIOUtils;
import net.thevpc.nuts.spi.NutsContentTypeResolver;
import net.thevpc.nuts.spi.NutsSupportLevelContext;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultNutsContentTypes implements NutsContentTypes {
    private final NutsSession session;
    private NutsWorkspace ws;

    public DefaultNutsContentTypes(NutsSession session) {
        this.session = session;
        this.ws = session.getWorkspace();
    }

    @Override
    public String probeContentType(Path path) {
        return probeContentType(path == null ? null : NutsPath.of(path, session));
    }

    @Override
    public String probeContentType(File path) {
        return probeContentType(path == null ? null : NutsPath.of(path, session));
    }

    @Override
    public String probeContentType(URL path) {
        return probeContentType(path == null ? null : NutsPath.of(path, session));
    }

    @Override
    public String probeContentType(NutsPath path) {
        List<NutsContentTypeResolver> allSupported = session.extensions()
                .createAllSupported(NutsContentTypeResolver.class, path);
        NutsSupported<String> best = null;
        for (NutsContentTypeResolver r : allSupported) {
            NutsSupported<String> s = r.probeContentType(path, session);
            if (s != null && s.isValid()) {
                if (best == null || s.getSupportLevel() > best.getSupportLevel()) {
                    best = s;
                }
            }
        }
        if (best == null) {
            return null;
        }
        return best.getValue();
    }

    @Override
    public List<String> findExtensionsByContentType(String contentType) {
        List<NutsContentTypeResolver> allSupported = session.extensions()
                .createAllSupported(NutsContentTypeResolver.class, null);
        LinkedHashSet<String> all = new LinkedHashSet<>();
        for (NutsContentTypeResolver r : allSupported) {
            List<String> s = r.findExtensionsByContentType(contentType, session);
            if (s != null) {
                all.addAll(s.stream().filter(x->!NutsBlankable.isBlank(x)).collect(Collectors.toList()));
            }
        }
        return new ArrayList<>(all);
    }

    @Override
    public List<String> findContentTypesByExtension(String extension) {
        List<NutsContentTypeResolver> allSupported = session.extensions()
                .createAllSupported(NutsContentTypeResolver.class, null);
        LinkedHashSet<String> all = new LinkedHashSet<>();
        for (NutsContentTypeResolver r : allSupported) {
            List<String> s = r.findContentTypesByExtension(extension, session);
            if (s != null) {
                all.addAll(s.stream().filter(NutsBlankable::isBlank).collect(Collectors.toList()));
            }
        }
        return new ArrayList<>(all);
    }

    @Override
    public String probeContentType(InputStream stream) {
        byte[] buffer = CoreIOUtils.readBestEffort(4096, stream, session);
        return probeContentType(buffer);
    }

    @Override
    public String probeContentType(byte[] bytes) {
        List<NutsContentTypeResolver> allSupported = session.extensions()
                .createAllSupported(NutsContentTypeResolver.class, bytes);
        NutsSupported<String> best = null;
        for (NutsContentTypeResolver r : allSupported) {
            NutsSupported<String> s = r.probeContentType(bytes, session);
            if (s != null && s.isValid()) {
                if (best == null || s.getSupportLevel() > best.getSupportLevel()) {
                    best = s;
                }
            }
        }
        if (best == null) {
            return null;
        }
        return best.getValue();
    }

    @Override
    public int getSupportLevel(NutsSupportLevelContext context) {
        return DEFAULT_SUPPORT;
    }
//
//    private static class Shared {
//
//    }
}
