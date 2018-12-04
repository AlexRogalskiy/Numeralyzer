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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.lexical.impl;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.IGenericLexicalToken;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.functions.Parser;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.impl.GenericMetricsImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.lexical.IGenericLexicalTokenMetrics;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract lexical token metrics implementation
 *
 * @param <S> - {@link CharSequence}
 * @param <T> - {@link IGenericLexicalToken}
 * @param <E> - {@link Object}
 * @author Alex
 * @version 1.0.0
 * @since 2018-11-30
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class GenericLexicalTokenMetricsImpl<S extends CharSequence, T extends IGenericLexicalToken<S>, E> extends GenericMetricsImpl<T, E> implements IGenericLexicalTokenMetrics<S, T, E> {

    /**
     * Default constructor
     */
    public GenericLexicalTokenMetricsImpl() {
        getLogger().debug("Initializing lexical token metrics...");
    }

    /**
     * Returns length of the current input token
     *
     * @param value - input token
     * @return tokens length
     */
    @Override
    public int length(final T value) {
        return value.getData().length();
    }

    /**
     * Returns new representation of input token value by parser function
     *
     * @param value  - input value
     * @param parser - input parser function
     * @return representation of input token
     */
    @Override
    public E valueOf(final T value, final Parser<T, E> parser) {
        return parser.parse(value);
    }

    /**
     * Returns character set of the current input token
     *
     * @param value - input token
     * @return tokens character set
     */
    @Override
    public Set<Integer> characterSet(final T value) {
        return new HashSet<>(this.characterList(value));
    }

    /**
     * Returns character list of the current input token
     *
     * @param value - input token
     * @return tokens character list
     */
    private List<Integer> characterList(final T value) {
        return Stream.of(value.getData())
                .flatMapToInt(CharSequence::chars)
                .mapToObj(c -> Integer.valueOf(c))
                .collect(Collectors.toList());
    }
}
