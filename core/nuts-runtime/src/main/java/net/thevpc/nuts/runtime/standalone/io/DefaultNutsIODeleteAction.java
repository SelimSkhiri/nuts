package net.thevpc.nuts.runtime.standalone.io;

import net.thevpc.nuts.*;
import net.thevpc.nuts.runtime.core.util.CoreIOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;

public class DefaultNutsIODeleteAction extends AbstractNutsIODeleteAction {
    private Exception error;

    public DefaultNutsIODeleteAction(NutsWorkspace ws) {
        super(ws);
    }

    private void grabException(IOException e) {
        this.error = e;
        if (isFailFast()) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public NutsIODeleteAction run() {
        checkSession();
        Path t = CoreIOUtils.toPath(getTarget());
        if (t == null) {
            if (getTarget() == null) {
                throw new NutsException(getSession(), NutsMessage.formatted("missing target to delete"));
            }
            throw new NutsException(getSession(), NutsMessage.cstyle("unsupported target to delete: %s",getTarget()));
        }
        if (!Files.exists(t)) {
            grabException(new FileNotFoundException(t.toString()));
            return this;
        }
        if (Files.isRegularFile(t)) {
            try {
                Files.delete(t);
            } catch (IOException e) {
                grabException(e);
                return this;
            }
            return this;
        }
        final int[] deleted = new int[]{0, 0, 0};
        NutsLogger LOG = getSession().log().of(CoreIOUtils.class);
        try {
            Files.walkFileTree(t, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        Files.delete(file);
                        if (LOG != null) {
                            LOG.with().session(getSession()).level(Level.FINEST).verb(NutsLogVerb.WARNING)
                                    .log( NutsMessage.jstyle("delete file {0}", file));
                        }
                        deleted[0]++;
                    } catch (IOException e) {
                        if (LOG != null) {
                            LOG.with().session(getSession()).level(Level.FINEST).verb(NutsLogVerb.WARNING)
                                    .log( NutsMessage.jstyle("failed deleting file : {0}", file));
                        }
                        deleted[2]++;
                        grabException(e);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    try {
                        Files.delete(dir);
                        if (LOG != null) {
                            LOG.with().session(getSession()).level(Level.FINEST).verb(NutsLogVerb.WARNING)
                                    .log( NutsMessage.jstyle("delete folder {0}", dir));
                        }
                        deleted[1]++;
                    } catch (IOException e) {
                        if (LOG != null) {
                            LOG.with().session(getSession()).level(Level.FINEST).verb(NutsLogVerb.WARNING)
                                    .log( NutsMessage.jstyle("failed deleting folder : {0}", dir));
                        }
                        deleted[2]++;
                        grabException(e);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            if (error != null) {
                grabException(e);
            }
        }
        return this;
    }
}
