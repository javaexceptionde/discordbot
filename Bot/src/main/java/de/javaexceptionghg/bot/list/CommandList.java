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

package de.javaexceptionghg.bot.list;

import de.javaexceptionghg.bot.abstracts.Command;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class CommandList implements Map<String, Command> {

    private static CommandList instance;

    private Map<String, Command> map;

    public CommandList(){
        map = new ConcurrentHashMap<>();
    }

    public static CommandList getInstance() {
        return (instance != null ? instance : (instance = new CommandList()));
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Command get(Object key) {
        return map.get(key);
    }

    @Nullable
    @Override
    public Command put(String key, Command value) {
        return map.put(key, value);
    }

    @Override
    public Command remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends Command> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return keySet();
    }

    @NotNull
    @Override
    public Collection<Command> values() {
        return values();
    }

    @NotNull
    @Override
    public Set<Entry<String, Command>> entrySet() {
        return entrySet();
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super Command> action) {
        map.forEach(action);
    }

    @Nullable
    @Override
    public Command putIfAbsent(String key, Command value) {
        return map.putIfAbsent(key, value);
    }
}
