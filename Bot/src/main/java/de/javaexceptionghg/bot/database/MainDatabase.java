package de.javaexceptionghg.bot.database;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import de.javaexceptionghg.bot.IniFile;
import de.javaexceptionghg.bot.database.abstracts.DatabaseAbstracts;
import lombok.Getter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class MainDatabase extends DatabaseAbstracts {

    private final IniFile DB_CONFIG = new IniFile("database.ini");
    private final ExecutorService asyncQueue;

    private MongoClient client;
    private MongoDatabase database;

    public MainDatabase() {
        DB_CONFIG.addDefault("mongo_host", "");
        DB_CONFIG.addDefault("mongo_default_db", "");
        DB_CONFIG.addDefault("mongo_auth_db", "");
        DB_CONFIG.addDefault("mongo_user", "");
        DB_CONFIG.addDefault("mongo_pass", "");

        this.asyncQueue = Executors.newWorkStealingPool();
    }

    @Override
    public void connect() {
        final String mongo_host = DB_CONFIG.getProperty("mongo_host");
        final String mongo_auth_db = DB_CONFIG.getProperty("mongo_auth_db");
        final String mongo_default_db = DB_CONFIG.getProperty("mongo_default_db");
        final String mongo_user = DB_CONFIG.getProperty("mongo_user");
        final String mongo_pass = DB_CONFIG.getProperty("mongo_pass");

        final FutureTask<MongoDatabase> task = new FutureTask<>(() -> {
            Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
            /*final MongoCredential credential = MongoCredential.createCredential(
                    mongo_user,
                    mongo_auth_db,
                    mongo_pass.toCharArray()
            );*/
            // set max connections
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            builder.connectionsPerHost(2000);
            MongoClientOptions options = builder.build();

            client = new MongoClient(new ServerAddress(mongo_host), options);

            return client.getDatabase(mongo_default_db);
        });
        asyncQueue.execute(task);
        try {
            database = task.get();
            System.out.println("[MongoDB] Verbindung erfolgreich hergestellt!");
        } catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException("[MongoDB] Fehler beim Verbinden!", ex);
        }

    }

    @Override
    public void disconnect() {
        client.close();
    }

    @Override
    public MongoClient getMongoClient() {
        return client;
    }

    @Override
    public MongoDatabase getMongoDatabase() {
        return database;
    }

    @Override
    public MongoCollection<Document> getCollection(String name) {
        return database.getCollection(name);
    }

    @Override
    public FindIterable<Document> getIterable(String collection) {
        return getCollection(collection).find();
    }

    @Override
    public FindIterable<Document> getIterable(String collection, Document query) {
        return getCollection(collection).find(query);
    }

    @Override
    public FindIterable<Document> getIterable(String collection, String key, Object value) {
        return getIterable(collection, new Document(key, value));
    }

    @Override
    public FindIterable<Document> getIterable(String collection, Map<String, Object> fields) {
        return getIterable(collection, new Document(fields));
    }

    @Override
    public Document getDocument(String collection, Document query) {
        return getCollection(collection).find(query).first();
    }

    @Override
    public Document getDocument(String collection, String key, Object value) {
        return getDocument(collection, new Document(key, value));
    }

    @Override
    public Document getDocument(String collection, Map<String, Object> fields) {
        return getDocument(collection, new Document(fields));
    }

    @Override
    public List<Document> getDocumentList(String collection, Document query) {
        final List<Document> documents = new ArrayList<>();
        getIterable(collection, query).forEach((Block<Document>) documents::add);
        return documents;
    }

    @Override
    public List<Document> getDocumentList(String collection, String key, Object value) {
        return getDocumentList(collection, new Document(key, value));
    }

    @Override
    public List<Document> getDocumentList(String collection, Map<String, Object> fields) {
        return getDocumentList(collection, new Document(fields));
    }

    @Override
    public Boolean contains(Document document, String collection) {
        return getCollection(collection).count(document) != 0;
    }

    @Override
    public Boolean contains(String key, Object value, String collection) {
        return contains(new Document(key, value), collection);
    }

    @Override
    public Boolean contains(Map<String, Object> fields, String collection) {
        return contains(new Document(fields), collection);
    }

    @Override
    public void insert(String collection, Document document) {
        getCollection(collection).insertOne(document);
    }

    @Override
    public void insert(String collection, List<? extends Document> documents) {
        getCollection(collection).insertMany(documents);
    }

    @Override
    public void delete(String collection, Document document) {
        getCollection(collection).deleteOne(document);
    }

    @Override
    public void delete(String collection, String key, Object value) {
        delete(collection, new Document(key, value));
    }

    @Override
    public void delete(String collection, Map<String, Object> fields) {
        delete(collection, new Document(fields));
    }

    @Override
    public void delete(final String collection, List<? extends Document> documents) {
        for (Document document : documents)
            delete(collection, document);
    }

    @Override
    public void update(Document query, Document update, String collection) {
        getCollection(collection).updateOne(query, update);
    }

    @Override
    public void updateMany(Document query, Document update, String collection) {
        getCollection(collection).updateMany(query, update);
    }

    @Override
    public void replace(Document query, Document replacement, String collection) {
        getCollection(collection).replaceOne(query, replacement);
    }

    public void update(String key, Object value, String operator, String field, Object into, String collection) {
        update(new Document(key, value), new Document(operator, new Document(field, into)), collection);
    }

}

