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
 * @param <T>
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@Data
@EqualsAndHashCode(exclude = "id")
@ToString
public abstract class GenericLexicalTokenImpl<T extends CharSequence> implements IGenericLexicalToken<T> {

    @Setter(AccessLevel.NONE)
    private final UUID id;
    private final Comparator<? super T> comparator;
    private final T data;

    public GenericLexicalTokenImpl() {
        this((T) StringUtils.EMPTY, (Comparator<? super T>) DEFAULT_TOKEN_COMPARATOR);
    }

    public GenericLexicalTokenImpl(final T data, final Comparator<? super T> comparator) {
        this.id = UUID.randomUUID();
        this.data = data;
        this.comparator = comparator;
    }

    @Override
    public int length() {
        return Optional.ofNullable(this.data).map(data -> data.length()).orElse(0);
    }

    @Override
    public char charAt(int index) {
        return Optional.ofNullable(this.data).map(data -> data.charAt(index)).orElse((char) 0);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return Optional.ofNullable(this.data).map(data -> data.subSequence(start, end)).orElse(StringUtils.EMPTY);
    }
}
