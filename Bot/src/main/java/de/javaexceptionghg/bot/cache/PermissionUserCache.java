package de.javaexceptionghg.bot.cache;

import de.javaexceptionghg.bot.permission.PermissionUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PermissionUserCache implements Map<String, PermissionUser> {
    private final Map<String, PermissionUser> map = new HashMap<>();

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
    public PermissionUser get(Object key) {
        return map.get(key);
    }

    @Nullable
    @Override
    public PermissionUser put(String key, PermissionUser value) {
        return map.put(key, value);
    }

    @Override
    public PermissionUser remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends PermissionUser> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<PermissionUser> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, PermissionUser>> entrySet() {
        return map.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return map.equals(o);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

}
