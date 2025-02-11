package net.thevpc.nuts.indexer.services;

import net.thevpc.nuts.*;
import net.thevpc.nuts.elem.NutsElements;
import net.thevpc.nuts.indexer.NutsIndexerUtils;
import net.thevpc.nuts.util.NutsStringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataService {

    public void indexData(Path dirPath, Map<String, String> data) {
        this.indexMultipleData(dirPath, Collections.singletonList(data));
    }

    public void indexMultipleData(Path dirPath, List<Map<String, String>> data) {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = null;
        IndexWriterConfig config = null;
        IndexWriter writer = null;
        try {
            index = FSDirectory.open(dirPath);
            config = new IndexWriterConfig(analyzer);
            writer = new IndexWriter(index, config);
            for (Map<String, String> entity : data) {
                Document document = mapToDocument(entity);
                writer.addDocument(document);
            }
            writer.close();
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void updateData(Path dirPath, Map<String, String> olddata, Map<String, String> data) {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = null;
        IndexWriterConfig config = null;
        IndexWriter writer = null;
        try {
            index = FSDirectory.open(dirPath);
            config = new IndexWriterConfig(analyzer);
            writer = new IndexWriter(index, config);
            Query query = NutsIndexerUtils.mapToQuery(olddata);
            writer.deleteDocuments(query);
            Document document = mapToDocument(data);
            writer.addDocument(document);
            writer.close();
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private Document mapToDocument(Map<String, String> data) {
        Document document = new Document();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            document.add(new StringField(entry.getKey(), NutsStringUtils.trim(entry.getValue()), Field.Store.YES));
        }
        return document;
    }

    public void deleteData(Path dirPath, Map<String, String> data) {
        this.deleteMultipleData(dirPath, Collections.singletonList(data));
    }

    public void deleteMultipleData(Path dirPath, List<Map<String, String>> data) {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = null;
        IndexWriterConfig config = null;
        IndexWriter writer = null;
        try {
            index = FSDirectory.open(dirPath);
            config = new IndexWriterConfig(analyzer);
            writer = new IndexWriter(index, config);
            for (Map<String, String> entity : data) {
                Query query = NutsIndexerUtils.mapToQuery(entity);
                writer.deleteDocuments(query);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Map<String, String>> getAllData(Path dirPath) {
        return searchData(dirPath, null, null);
    }

    public List<Map<String, String>> searchData(Path dirPath, Map<String, String> data, Query query) {
        Directory index = null;
        IndexReader reader = null;
        IndexSearcher searcher = null;
        TopDocs topDocs = null;
        List<Map<String, String>> result = new ArrayList<>();
        try {
            index = FSDirectory.open(dirPath);
            reader = DirectoryReader.open(index);
            searcher = new IndexSearcher(reader);
            topDocs = searcher.search(data == null
                            ? (query == null ? new MatchAllDocsQuery() : query)
                            : (query == null ? NutsIndexerUtils.mapToQuery(data) : query),
                    Integer.MAX_VALUE);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = null;
                HashMap<String, String> res = new HashMap<>();
                document = searcher.doc(scoreDoc.doc);
                for (IndexableField field : document.getFields()) {
                    res.put(field.name(), field.stringValue());
                }
                result.add(res);
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return result;
    }

    public List<Map<String, String>> getAllDependencies(NutsSession session, Path dirPath, NutsId id) {
        List<Map<String, String>> rows = searchData(dirPath, NutsIndexerUtils.nutsIdToMap(id), null);
        if (rows.isEmpty()) {
            return null;
        }
        Map<String, String> row = rows.get(0);
        if (!row.containsKey("allDependencies")) {
            List<NutsId> allDependencies = session.search()
                    .setBasePackage(false)
                    .setInlineDependencies(true)
                    .addId(id)
                    .setFailFast(false)
                    .setContent(false)
                    .getResultIds().toList();
            Map<String, String> oldRow = new HashMap<>(row);
            row.put("allDependencies", NutsElements.of(session).json()
                    .setValue(allDependencies.stream().map(Object::toString)
                            .collect(Collectors.toList()))
                            .setNtf(false)
                    .format()
                    .toString()
            );
            updateData(dirPath, oldRow, row);
        }
        String[] array = NutsElements.of(session).json().parse(new StringReader(row.get("allDependencies")), String[].class);
        List<Map<String, String>> allDependencies = Arrays.stream(array)
                .map(s -> NutsIndexerUtils.nutsIdToMap(NutsId.of(s).get(session)))
                .collect(Collectors.toList());
        return allDependencies;
    }

    public List<Map<String, String>> getDependencies(NutsSession session, Path dirPath, NutsId id) {
        List<Map<String, String>> rows = searchData(dirPath, NutsIndexerUtils.nutsIdToMap(id), null);
        if (rows.isEmpty()) {
            return null;
        }
        Map<String, String> row = rows.get(0);
        String[] array = NutsElements.of(session).json().parse(new StringReader(row.get("dependencies")), String[].class);
        List<Map<String, String>> dependencies = Arrays.stream(array)
                .map(s -> NutsIndexerUtils.nutsIdToMap(NutsId.of(s).get(session)))
                .collect(Collectors.toList());
        return dependencies;
    }

    public List<Map<String, String>> getAllVersions(NutsSession session, Path dirPath, NutsId id) {
        Map<String, String> data = NutsIndexerUtils.nutsIdToMap(id);
        List<Map<String, String>> rows =
                searchData(dirPath, null, NutsIndexerUtils.mapToQuery(data, "version"));
        return rows;

    }
}
