package de.javaexceptionghg.bot.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.DeleteOptions;
import de.javaexceptionghg.bot.database.abstracts.IDatabaseProvider;
import lombok.Getter;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Getter
public class DatabaseProvider implements IDatabaseProvider {
    MongoClient mongoClient;
    MongoDatabase database;

    @Override
    public boolean connect() {
        mongoClient = new com.mongodb.MongoClient("");
        mongoClient.getDatabase("").getCollection("");
        return true;
    }

    @Override
    public void disconnect() {
        mongoClient.close();
    }

    @Override
    public boolean contains(String key, Object value, String collection) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return contains(map, collection);
    }

    @Override
    public boolean contains(Map<String, Object> map, String collection) {
        Document query = new Document();
        query.putAll(map);
            return contains(query, collection);
    }

    @Override
    public boolean contains(Document query, String collection) {
        return getDatabase().getCollection(collection).count(query) != 0;
    }

    @Override
    public boolean insert(Document document, String collection) {
        try {
            getDatabase().getCollection(collection).insertOne(document);
            return true;
        } catch (MongoException exception){
            return false;
        }
    }

    @Override
    public void getDocument(String key, Object value, String collection, IThrowableHandler<Document> iThrowableHandler) {
        try {
            iThrowableHandler.handle(getDatabase().getCollection(collection).find(new Document().append(key, value)).first());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean delete(Document document, String collection) {
        try {
            getDatabase().getCollection(collection).deleteOne(document);
            return true;
        }catch (MongoWriteException exception){
            exception.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(Document query, Document update, String collection) {
        try {
            getDatabase().getCollection(collection).updateOne(query, update);
            return true;
        }catch (MongoWriteException exception){
            return false;
        }
    }
}
