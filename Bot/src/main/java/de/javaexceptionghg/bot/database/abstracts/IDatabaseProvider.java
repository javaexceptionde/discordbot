package de.javaexceptionghg.bot.database.abstracts;

import com.mongodb.client.MongoDatabase;
import de.javaexceptionghg.bot.database.IThrowableHandler;
import org.bson.Document;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface IDatabaseProvider {

    boolean connect();

    void disconnect();

    boolean contains(String key, Object value, String collection);

    boolean contains(Map<String, Object> map, String collection);

    boolean contains(Document query, String collection);

    boolean insert(Document document, String collection);

    void getDocument(String key, Object value, String collection, IThrowableHandler<Document> documentCallable);

    boolean delete(Document document, String collection);

    boolean update(Document query, Document update, String collection);

}
