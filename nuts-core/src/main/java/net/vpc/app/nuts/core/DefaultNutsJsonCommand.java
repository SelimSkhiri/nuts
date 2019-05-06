/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.nuts.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import net.vpc.app.nuts.NutsDependency;
import net.vpc.app.nuts.NutsDescriptor;
import net.vpc.app.nuts.NutsId;
import net.vpc.app.nuts.NutsJsonCommand;
import net.vpc.app.nuts.NutsVersion;
import net.vpc.app.nuts.core.util.CoreNutsUtils;

/**
 *
 * @author vpc
 */
public class DefaultNutsJsonCommand implements NutsJsonCommand {

    private static Gson GSON;
    private static Gson GSON_PRETTY;
    private boolean pretty=true;

    @Override
    public boolean isPretty() {
        return pretty;
    }

    @Override
    public NutsJsonCommand pretty() {
        return pretty(true);
    }

    @Override
    public NutsJsonCommand pretty(boolean pretty) {
        return setPretty(pretty);
    }

    @Override
    public NutsJsonCommand setPretty(boolean pretty) {
        this.pretty = pretty;
        return this;
    }

    @Override
    public String toJsonString(Object obj) {
        StringWriter w = new StringWriter();
        write(obj, w);
        return w.toString();
    }

    @Override
    public void write(Object obj, Writer out) {
        getGson(pretty).toJson(obj, out);
        try {
            out.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public <T> T read(Reader reader, Class<T> cls) {
        return getGson(true).fromJson(reader, cls);
    }

    @Override
    public <T> T read(Path path, Class<T> cls) {
        File file = path.toFile();
        try (FileReader r = new FileReader(file)) {
            return read(r, cls);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override
    public <T> T read(File file, Class<T> cls) {
        try (FileReader r = new FileReader(file)) {
            return read(r, cls);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public <T> void write(Object obj, File file) {
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (FileWriter w = new FileWriter(file)) {
            write(obj, w);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override
    public <T> void write(Object obj, Path path) {
        if (path.getParent() != null) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
        try (Writer w = Files.newBufferedWriter(path)) {
            write(obj, w);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override
    public <T> void write(Object obj, PrintStream printStream) {
        Writer w = new PrintWriter(printStream);
        write(obj, w);
        try {
            w.flush();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private static Gson getGson(boolean pretty) {
        if (pretty) {
            if (GSON_PRETTY == null) {
                GSON_PRETTY = prepareBuilder().setPrettyPrinting().create();
            }
            return GSON_PRETTY;
        } else {
            if (GSON == null) {
                GSON = prepareBuilder().create();
            }
            return GSON;
        }
    }

    public static GsonBuilder prepareBuilder() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(NutsId.class, new NutsIdJsonAdapter())
                .registerTypeHierarchyAdapter(NutsVersion.class, new NutsVersionJsonAdapter())
                .registerTypeHierarchyAdapter(NutsDescriptor.class, new NutsDescriptorJsonAdapter())
                .registerTypeHierarchyAdapter(NutsDependency.class, new NutsNutsDependencyJsonAdapter());
    }

    private static class NutsIdJsonAdapter implements
            com.google.gson.JsonSerializer<NutsId>,
            com.google.gson.JsonDeserializer<NutsId> {

        @Override
        public NutsId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String s = context.deserialize(json, String.class);
            if (s == null) {
                return null;
            }
            return CoreNutsUtils.parseRequiredNutsId(s);
        }

        @Override
        public JsonElement serialize(NutsId src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(src == null ? null : src.toString());
        }
    }

    private static class NutsVersionJsonAdapter implements
            com.google.gson.JsonSerializer<NutsVersion>,
            com.google.gson.JsonDeserializer<NutsVersion> {

        @Override
        public NutsVersion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String s = context.deserialize(json, String.class);
            if (s == null) {
                return null;
            }
            return DefaultNutsVersion.valueOf(s);
        }

        @Override
        public JsonElement serialize(NutsVersion src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize(src == null ? null : src.toString());
        }
    }

    private static class NutsDescriptorJsonAdapter implements
            com.google.gson.JsonSerializer<NutsDescriptor>,
            com.google.gson.JsonDeserializer<NutsDescriptor> {

        @Override
        public NutsDescriptor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            DefaultNutsDescriptorBuilder b = context.deserialize(json, DefaultNutsDescriptorBuilder.class);
            return b.build();
        }

        @Override
        public JsonElement serialize(NutsDescriptor src, Type typeOfSrc, JsonSerializationContext context) {
            if (src != null) {
                return context.serialize(new DefaultNutsDescriptorBuilder(src));
            }
            return context.serialize(src);
        }
    }

    private static class NutsNutsDependencyJsonAdapter implements
            com.google.gson.JsonSerializer<NutsDependency>,
            com.google.gson.JsonDeserializer<NutsDependency> {

        @Override
        public NutsDependency deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            DefaultNutsDependencyBuilder b = context.deserialize(json, DefaultNutsDependencyBuilder.class);
            return b.build();
        }

        @Override
        public JsonElement serialize(NutsDependency src, Type typeOfSrc, JsonSerializationContext context) {
            if (src != null) {
                return context.serialize(new DefaultNutsDependencyBuilder(src));
            }
            return context.serialize(src);
        }
    }

}
