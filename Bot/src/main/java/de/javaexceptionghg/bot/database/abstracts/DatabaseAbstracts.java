package de.javaexceptionghg.bot.database.abstracts;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.util.List;
import java.util.Map;


public abstract class DatabaseAbstracts {
    /**
     * Opens a single connection to mongodb!
     * <p>
     * These connections can be closed by invoking {@link #disconnect()}
     */
    public abstract void connect();

    /**
     * Closes mongo's connection to the database.
     * <p>
     * If you wish to re-open those connections, invoke {@link #connect()}
     */
    public abstract void disconnect();

    /**
     * Gets the {@link MongoClient} instance to operate with the database connections
     * via different plugins.
     *
     * @return the {@link MongoClient} instance
     */
    public abstract MongoClient getMongoClient();

    /**
     * Gets the {@link MongoDatabase} instance to operate with the default database via
     * different plugins.
     *
     * @return the {@link MongoDatabase} instance
     */
    public abstract MongoDatabase getMongoDatabase();

    /**
     * Gets a {@link MongoCollection} of the mongodb database server by its specified name.
     *
     * @param name the name of the {@link MongoCollection}
     * @return the {@link MongoCollection} instance
     */
    public abstract MongoCollection<Document> getCollection(String name);

    /**
     * Gets a {@link FindIterable} of the mongodb database server from a specified
     * {@link MongoCollection}.
     * This loops and returns every existing documents. Use this carefully on busy collections!
     *
     * @param collection the collection name of the collection to query in
     * @return the {@link FindIterable} instance
     */
    public abstract FindIterable<Document> getIterable(String collection);

    /**
     * Gets a {@link FindIterable} of the mongodb database server from a specified
     * {@link MongoCollection} and by a specified query document.
     * This query document must contain at least one field (key, value pair) to query
     * successfully.
     *
     * @param collection the collection name of the collection to query in
     * @param query      query type
     * @return the {@link FindIterable} instance
     */
    public abstract FindIterable<Document> getIterable(String collection, Document query);

    /**
     * Gets a {@link FindIterable} of the mongodb database server from a specified
     * {@link MongoCollection} and by a specified key, value query pair.
     * The queried key must not be null, otherwise the query isn't successful.
     *
     * @param collection the collection name of the collection to query in
     * @param key        query key type
     * @param value      query value type
     * @return the {@link FindIterable} instance
     */
    public abstract FindIterable<Document> getIterable(String collection, String key, Object value);

    /**
     * Gets a {@link FindIterable} of the mongodb database server from a specified
     * {@link MongoCollection} and by a specified key, value pair {@link Map}.
     * Neither of the containing keys in the provided key, value {@link Map} must be null,
     * otherwise the query isn't successful
     *
     * @param collection the collection name of the collection to query in
     * @param fields     query key, value (fields) {@link Map} type
     * @return the {@link FindIterable} instance
     */
    public abstract FindIterable<Document> getIterable(String collection, Map<String, Object> fields);

    /**
     * Gets a {@link Document} of the mongodb database server from a specified {@link MongoCollection}
     * and by a specified query document.
     * This query document must contain at least one field (key, value pair) to query
     * successfully.
     * Note: Neither of the containing keys must be null!
     *
     * @param collection the collection name of the collection to query in
     * @param query      query type
     * @return the {@link Document} instance
     */
    public abstract Document getDocument(String collection, Document query);

    /**
     * Gets a {@link Document} of the mongodb database server from a specified {@link MongoCollection}
     * and by a specified key, value query pair.
     * The queried key must not be null, otherwise the query isn't successful.
     *
     * @param collection the collection name of the collection to query in
     * @param key        query key type
     * @param value      query value type
     * @return the {@link Document} instance
     */
    public abstract Document getDocument(String collection, String key, Object value);

    /**
     * Gets a {@link Document} of the mongodb database server from a specified {@link MongoCollection}
     * and by a specified key, value pair {@link Map}.
     * Neither of the containing keys in the provided key, value {@link Map} must be null,
     * otherwise the query isn't successful.
     *
     * @param collection the collection name of the collection to query in
     * @param fields     query key, value (fields) {@link Map} type
     * @return the {@link Document} instance
     */
    public abstract Document getDocument(String collection, Map<String, Object> fields);

    /**
     * Gets a {@link List} of specified {@link Document}s of the mongodb database server from
     * a specified collection and by a specified query document.
     * This query document must contain at least one field (key, value pair) to query
     * successfully.
     * Note: Neither of the containing keys must be null!
     *
     * @param collection the collection name of the collection to query in
     * @param query      query type
     * @return the {@link List} instance containing the queried documents
     */
    public abstract List<Document> getDocumentList(String collection, Document query);

    /**
     * Gets a {@link List} of specified {@link Document}s of the mongodb database server from
     * a specified collection and by a specified key, value pair.
     * The queried key must not be null, otherwise the query isn't successful.
     *
     * @param collection the collection name of the collection to query in
     * @param key        query key type
     * @param value      query value type
     * @return the {@link List} instance containing the queried documents
     */
    public abstract List<Document> getDocumentList(String collection, String key, Object value);

    /**
     * Gets a {@link List} specified of {@link Document}s of the mongodb database server from
     * a specified collection and by a specified key, value pair {@link Map}.
     * Neither of the containing keys in the provided key, value {@link Map} must be null,
     * otherwise the query isn't successful.
     *
     * @param collection the collection name of the collection to query in
     * @param fields     query key, value (fields) {@link Map} type
     * @return the {@link List} instance containing the queried documents
     */
    public abstract List<Document> getDocumentList(String collection, Map<String, Object> fields);

    /**
     * Gets if a specified {@link MongoCollection} of the mongodb database server contains
     * a specified query document. If it does, it will return true, if not, false.
     *
     * @param document   query type
     * @param collection the name of the {@link MongoCollection} to query in
     * @return whether it does
     */
    public abstract Boolean contains(Document document, String collection);

    /**
     * Gets if a specified {@link MongoCollection} of the mongodb database server contains
     * a specified key, value pair. If it does, it will return true, if not, false.
     *
     * @param key        query key type
     * @param value      query value type
     * @param collection the name of the {@link MongoCollection} to query in
     * @return whether it does
     */
    public abstract Boolean contains(String key, Object value, String collection);

    /**
     * Gets if a specified {@link MongoCollection} of the mongodb database server contains
     * a specified key, value pair {@link Map}. If it does, it will return true, if not, false.
     *
     * @param fields     query key, value (fields) {@link Map} type
     * @param collection the name of the {@link MongoCollection} to query in
     * @return whether it does
     */
    public abstract Boolean contains(Map<String, Object> fields, String collection);

    /**
     * Inserts a specified {@link Document} to a specified {@link MongoCollection} on the
     * mongodb database server.
     * Note: Neither of the containing keys must be null!
     *
     * @param collection the name of the {@link MongoCollection} to query in
     * @param document   the {@link Document} instance to insert
     */
    public abstract void insert(String collection, Document document);

    /**
     * Inserts a specified {@link List} of specified {@link Document}s to a specified
     * {@link MongoCollection} on the mongodb database server.
     * Note: Neither of the containing keys in the provided {@link Document}s {@link List}
     * must be null!
     *
     * @param collection the name of the {@link MongoCollection} to query in
     * @param documents  the {@link List} instance containing {@link Document}s to insert
     */
    public abstract void insert(String collection, List<? extends Document> documents);

    /**
     * Deletes a specified {@link Document} from a specified {@link MongoCollection} on the
     * mongodb database server.
     * Note: Neither of the containing keys must be null!
     *
     * @param collection the name of the {@link MongoCollection} to query in
     * @param document   the {@link Document} instance to delete
     */
    public abstract void delete(String collection, Document document);

    /**
     * Deletes a by a key, value pair specified {@link Document} from a specified
     * {@link MongoCollection} on the mongodb database server.
     * The queried key must not be null, otherwise the deletion isn't successful.
     *
     * @param collection the name of the {@link MongoCollection} to query in
     * @param key        query key type
     * @param value      query value type
     */
    public abstract void delete(String collection, String key, Object value);

    /**
     * Deletes a by a key, value pair {@link Map} specified {@link Document} from
     * a specified {@link MongoCollection} on the mongodb database server.
     * Neither of the containing keys in the provided key, value {@link Map} must be null,
     * otherwise the deletion isn't successful.
     *
     * @param collection the name of the {@link MongoCollection} to query in
     * @param fields     query key, value (fields) {@link Map} type
     */
    public abstract void delete(String collection, Map<String, Object> fields);

    /**
     * Deletes a specified {@link List} of specified {@link Document}s from a specified
     * {@link MongoCollection} on the mongodb database server.
     * Note: Neither of the containing keys in the provided {@link Document}s {@link List}
     * must be null!
     *
     * @param collection the name of the {@link MongoCollection} to query in
     * @param documents  the {@link List} instance containing {@link Document}s to delete
     */
    public abstract void delete(String collection, List<? extends Document> documents);

    /**
     * Updates a specified {@link Document} by a specified query {@link Document} in a
     * specified {@link MongoCollection} on the mongodb database server.
     * Note: Neither of the containing keys must be null!
     *
     * @param query      query type
     * @param update     update type
     * @param collection the name of the {@link MongoCollection} to query in
     */
    public abstract void update(Document query, Document update, String collection);

    /**
     * Updates a by a key, value pair specified {@link Document} by a key, value pair
     * specified query {@link Document} in a specified {@link MongoCollection} with a
     * specified operator on the mongodb database server.
     * Note: Neither of the containing keys must be null!
     *
     * @param key        query key type
     * @param value      query value type
     * @param operator   the update operator. e.g. "$set"(to set a field), "$inc"(to increment a field)
     * @param field      update field type
     * @param into       update into type
     * @param collection the name of the {@link MongoCollection} to query in
     */

    public abstract void updateMany(Document query, Document update, String collection);

    public abstract void replace(Document query, Document replacement, String collection);
}
