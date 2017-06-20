package com.netikras.studies.studentbuddy.commons.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by netikras on 16.11.21.
 */
public class MapBuilder<K, V> {

    private Map<K, V> map;

    public MapBuilder() {
        this.map = new HashMap<K, V>();
    }

    public MapBuilder(Map map) {
        this.map = map;
    }



    public static MapBuilder start(Map map) {
        return new MapBuilder<>(map);
    }


    public MapBuilder<K, V> addItem(K key, V value) {
        map.put(key, value);
        return this;
    }

    public MapBuilder<K, V> removeItem(K key) {
        map.remove(key);
        return this;
    }

    public Map<K, V> getMap() {
        return this.map;
    }


    public Collection<K> getKeys() {
        return getMap().keySet();
    }

    public Collection<V> getValues() {
        return getMap().values();
    }

}
