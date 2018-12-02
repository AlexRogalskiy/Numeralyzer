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

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Delegated cache implementation
 *
 * @param <K>
 * @param <V>
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

    private final Map<K, V> cacheMap;

    public DelegatedCache() {
        this.cacheMap = new ConcurrentHashMap<>(DEFAULT_CACHE_INITIAL_SIZE);
    }

    @Override
    public V load(final K key, final V defaultValue) {
        LOGGER.debug(String.format("DelegatedCache: loading key: {%s}", key));
        if (!this.cacheMap.containsKey(key)) {
            this.cacheMap.put(key, defaultValue);
        }
        return this.cacheMap.get(key);
    }

    @Override
    public Map<K, V> loadAll(final Iterable<? extends K> iterable, final V defaultValue) {
        LOGGER.debug(String.format("DelegatedCache: loading all keys: {%s}", StringUtils.join(iterable, "|")));
        this.cacheMap.clear();
        if (Objects.nonNull(iterable)) {
            for (final K key : iterable) {
                load(key, defaultValue);
            }
        }
        return this.cacheMap;
    }

    @Override
    public void write(final K key, final V value) {
        LOGGER.debug(String.format("DelegatedCache: writing entry with key: {%s}, value: {%s}", key, value));
        this.cacheMap.put(key, value);
    }

    @Override
    public void writeAll(final Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        LOGGER.debug(String.format("DelegatedCache: writing all entries: {%s}", StringUtils.join(iterable, "|"), iterable));
        if (Objects.nonNull(iterable)) {
            for (Map.Entry<? extends K, ? extends V> entry : iterable) {
                write(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public void delete(final K key) {
        LOGGER.debug(String.format("DelegatedCache: deleting key: {%s}", key));
        this.cacheMap.remove(key);
    }

    @Override
    public void deleteAll(final Iterable<? extends K> iterable) {
        LOGGER.debug(String.format("DelegatedCache: deleting all keys: {%s}", StringUtils.join(iterable, "|")));
        if (Objects.nonNull(iterable)) {
            for (final K key : iterable) {
                delete(key);
            }
        }
    }
}