/*
 * The MIT License
 *
 * Copyright 2018 WildBees Labs.
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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.impl;


import com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.IGenericLexicalToken;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

/**
 * Abstract generic lexical token class to store information on a raw data chunk
 *
 * @param <T> - {@link CharSequence}
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@Data
@EqualsAndHashCode(exclude = "id")
@ToString
public abstract class GenericLexicalToken<T extends CharSequence> implements IGenericLexicalToken<T> {

    /**
     * Unique token identifier
     */
    @Setter(AccessLevel.NONE)
    private final UUID id;
    /**
     * Token comparator
     */
    private final Comparator<? super T> comparator;
    /**
     * Token stored data
     */
    private final T data;

    /**
     * Default constructor
     */
    public GenericLexicalToken() {
        this(null);
    }

    /**
     * Default constructor with input data to store
     *
     * @param value - input data
     */
    public GenericLexicalToken(final T value) {
        this(value, (Comparator<? super T>) DEFAULT_TOKEN_COMPARATOR);
    }

    /**
     * Default constructor with input data to store and data comparator
     *
     * @param data       - input data to store
     * @param comparator - input data comparator
     */
    public GenericLexicalToken(final T data, final Comparator<? super T> comparator) {
        this.id = UUID.randomUUID();
        this.data = data;
        this.comparator = comparator;
    }

    /**
     * Returns length of the current token (in chars)
     *
     * @return length of the token (in chars)
     */
    @Override
    public int length() {
        return Optional.ofNullable(this.data).map(data -> data.length()).orElse(0);
    }

    /**
     * Returns character at input position in the current token
     *
     * @param index - input position index
     * @return token character at position
     */
    @Override
    public char charAt(int index) {
        return Optional.ofNullable(this.data).map(data -> data.charAt(index)).orElse((char) 0);
    }

    /**
     * Returns sub-sequence of the current token (from start to end)
     *
     * @param start - start position index
     * @param end   - end position index
     * @return sub-sequence of the current token
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        return Optional.ofNullable(this.data).map(data -> data.subSequence(start, end)).orElse(StringUtils.EMPTY);
    }
}
