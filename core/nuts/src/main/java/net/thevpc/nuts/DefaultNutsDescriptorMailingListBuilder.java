package net.thevpc.nuts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DefaultNutsDescriptorMailingListBuilder implements NutsDescriptorMailingListBuilder {
    private String id;
    private String name;
    private String subscribe;
    private String unsubscribe;
    private String post;
    private String archive;
    private List<String> otherArchives;
    private Map<String, String> properties;
    private String comments;

    public DefaultNutsDescriptorMailingListBuilder() {
        this.otherArchives = new ArrayList<>();
        this.properties = new LinkedHashMap<>();
    }

    public DefaultNutsDescriptorMailingListBuilder(NutsDescriptorMailingList other) {
        this(other.getId(), other.getName(), other.getSubscribe(), other.getUnsubscribe(), other.getPost(), other.getArchive(),
                other.getOtherArchives(), other.getProperties(), other.getComments()
        );
    }

    public DefaultNutsDescriptorMailingListBuilder(String id, String name, String subscribe, String unsubscribe, String post, String archive, List<String> otherArchives, Map<String, String> properties, String comments) {
        this.id = id;
        this.name = name;
        this.subscribe = subscribe;
        this.unsubscribe = unsubscribe;
        this.post = post;
        this.archive = archive;
        this.otherArchives = otherArchives == null ? new ArrayList<>() : new ArrayList<>(otherArchives);
        this.properties = properties == null ? new LinkedHashMap<>() : new LinkedHashMap<>(properties);
        this.comments = comments;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public NutsDescriptorMailingListBuilder setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public NutsDescriptorMailingListBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getSubscribe() {
        return subscribe;
    }

    @Override
    public NutsDescriptorMailingListBuilder setSubscribe(String subscribe) {
        this.subscribe = subscribe;
        return this;
    }

    @Override
    public String getUnsubscribe() {
        return unsubscribe;
    }

    @Override
    public NutsDescriptorMailingListBuilder setUnsubscribe(String unsubscribe) {
        this.unsubscribe = unsubscribe;
        return this;
    }

    @Override
    public String getPost() {
        return post;
    }

    @Override
    public NutsDescriptorMailingListBuilder setPost(String post) {
        this.post = post;
        return this;
    }

    @Override
    public String getArchive() {
        return archive;
    }

    @Override
    public NutsDescriptorMailingListBuilder setArchive(String archive) {
        this.archive = archive;
        return this;
    }

    @Override
    public List<String> getOtherArchives() {
        return otherArchives;
    }

    @Override
    public NutsDescriptorMailingListBuilder setOtherArchives(List<String> otherArchives) {
        this.otherArchives = otherArchives;
        return this;
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public NutsDescriptorMailingListBuilder setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public NutsDescriptorMailingListBuilder setComments(String comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public NutsDescriptorMailingList readOnly() {
        return new DefaultNutsDescriptorMailingList(this);
    }

    @Override
    public NutsDescriptorMailingListBuilder builder() {
        return new DefaultNutsDescriptorMailingListBuilder(this);
    }

    @Override
    public NutsDescriptorMailingList build() {
        return readOnly();
    }

    @Override
    public NutsDescriptorMailingListBuilder copy() {
        return builder();
    }
}
