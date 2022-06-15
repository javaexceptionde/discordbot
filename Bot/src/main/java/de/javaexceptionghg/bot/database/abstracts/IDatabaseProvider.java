/*
 *
 *  *     Copyright (C) 2022  Jonathan Benedikt Bull<jonathan@jbull.dev>
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *     (at your option) any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *     You should have received a copy of the GNU General Public License
 *  *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
