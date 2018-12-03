/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.wildbeeslabs.sensiblemetrics.numeralyzer.cache.impl;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.cache.ICache;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Delegated cache implementation
 *
 * @param <K> - {@link Object}
 * @param <V> - {@link Object}
 * @author Alex
 * @version 1.0.0
 * @since 2018-11-30
 */
@EqualsAndHashCode
@ToString
public class DelegatedCache<K, V> implements ICache<K, V> {

    /**
     * Default logger instance
     */
    protected final Logger LOGGER = LogManager.getLogger(getClass());

    /**
     * Default cache initial size
     */
    public static final int DEFAULT_CACHE_INITIAL_SIZE = 100;
    /**
     * Default map based cache instance
     */
    private final Map<K, V> cacheMap;

    /**
     * Default constructor
     */
    public DelegatedCache() {
        this.cacheMap = new ConcurrentHashMap<>(DEFAULT_CACHE_INITIAL_SIZE);
    }

    /**
     * Returns current value in the cache by key, otherwise updates with default value
     *
     * @param key          - input key
     * @param defaultValue - default value to store
     * @return current value by key
     */
    @Override
    public V load(final K key, final V defaultValue) {
        LOGGER.debug(String.format("DelegatedCache: loading key: {%s}", key));
        return this.cacheMap.putIfAbsent(key, defaultValue);
    }

    /**
     * Returns new map loaded with the input keys with default value
     *
     * @param iterable     - input collection of keys
     * @param defaultValue - default value to store
     * @return new map with loaded keys
     */
    @Override
    public Map<K, V> loadAll(final Iterable<? extends K> iterable, final V defaultValue) {
        LOGGER.debug(String.format("DelegatedCache: loading all keys: {%s}", StringUtils.join(iterable, "|")));
        this.cacheMap.clear();
        Optional.ofNullable(iterable).orElse(Collections.emptyList()).forEach(key -> load((K) key, defaultValue));
        return this.cacheMap;
    }

    /**
     * Stores input key with input value in the current cache
     *
     * @param key   - input key
     * @param value - input value
     */
    @Override
    public void write(final K key, final V value) {
        LOGGER.debug(String.format("DelegatedCache: writing entry with key: {%s}, value: {%s}", key, value));
        this.cacheMap.put(key, value);
    }

    /**
     * Stores input collection of entries {@link Map.Entry} in the current cache
     *
     * @param iterable - input collection of keys and values to be stored
     */
    @Override
    public void writeAll(final Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        LOGGER.debug(String.format("DelegatedCache: writing all entries: {%s}", StringUtils.join(iterable, "|"), iterable));
        Optional.ofNullable(iterable).orElse(Collections.emptyList()).forEach(entry -> write(entry.getKey(), entry.getValue()));
    }

    /**
     * Return binary value based on existence of the input key
     *
     * @param key - input key
     * @return true - if the key exists, false - otherwise
     */
    @Override
    public boolean contains(final K key) {
        LOGGER.debug(String.format("DelegatedCache: checking existence of value by key: {%s}", key));
        return this.cacheMap.containsKey(key);
    }

    /**
     * Returns value in the cache by input key
     *
     * @param key - input key
     * @return value by key
     */
    @Override
    public V read(final K key) {
        LOGGER.debug(String.format("DelegatedCache: getting value by key: {%s}", key));
        return this.cacheMap.get(key);
    }

    /**
     * Removes value in the current cache by input key
     *
     * @param key - input key
     */
    @Override
    public void delete(final K key) {
        LOGGER.debug(String.format("DelegatedCache: deleting key: {%s}", key));
        this.cacheMap.remove(key);
    }

    /**
     * Removes values in the cache by input collection of keys
     *
     * @param iterable - input collection of keys to be removed
     */
    @Override
    public void deleteAll(final Iterable<? extends K> iterable) {
        LOGGER.debug(String.format("DelegatedCache: deleting all keys: {%s}", StringUtils.join(iterable, "|")));
        Optional.ofNullable(iterable).orElse(Collections.emptyList()).forEach(key -> delete((K) key));
    }
}