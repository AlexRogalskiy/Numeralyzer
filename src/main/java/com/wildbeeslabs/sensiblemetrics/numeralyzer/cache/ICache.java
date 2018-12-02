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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.cache;

import java.io.Serializable;
import java.util.Map;

/**
 * Cache interface declaration
 *
 * @param <K>
 * @param <V>
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public interface ICache<K, V> extends Serializable {

    /**
     * Returns old value from the cache by input key, otherwise updates the key with a default value
     *
     * @param key          - input key
     * @param defaultValue - default value to store
     * @return cache value by input key
     */
    V load(K key, V defaultValue);

    /**
     * Returns new cache loaded with input keys and default value (if not existed yet)
     *
     * @param iterable     - input collection of keys
     * @param defaultValue - default value to store
     * @return new cache with loaded keys
     */
    Map<K, V> loadAll(Iterable<? extends K> iterable, V defaultValue);

    /**
     * Updates cache with new value by input key
     *
     * @param key   - input key
     * @param value - input value
     */
    void write(K key, V value);

    /**
     * Updates cache with a collection of keys and values
     *
     * @param iterable - input collection of keys and values to be stored
     */
    void writeAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable);

    /**
     * Returns binary value based on existence of value by input key
     *
     * @param key - input key
     * @return true - if the value exists in the cache, false - otherwise
     */
    boolean contains(final K key);

    /**
     * Returns value from the cache by input key
     *
     * @param key - input key
     * @return value stored in the cache
     */
    V read(final K key);

    /**
     * Removes value from the cache by input key
     *
     * @param key - input key
     */
    void delete(K key);

    /**
     * Removes all values from the cache by input collection of keys
     *
     * @param iterable - input collection of keys to be removed
     */
    void deleteAll(Iterable<? extends K> iterable);
}
