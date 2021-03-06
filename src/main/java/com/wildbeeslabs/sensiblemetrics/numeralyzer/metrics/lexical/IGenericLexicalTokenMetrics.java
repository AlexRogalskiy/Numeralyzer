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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.lexical;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.IGenericLexicalToken;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.functions.Parser;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.IGenericMetrics;

import java.util.Set;

/**
 * Lexical token metrics interface declaration
 *
 * @param <S> - {@link CharSequence}
 * @param <T> - {@link IGenericLexicalToken}
 * @param <E> - {@link Object}
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public interface IGenericLexicalTokenMetrics<S extends CharSequence, T extends IGenericLexicalToken<S>, E> extends IGenericMetrics<T, E> {

    /**
     * Returns character set of the input lexical token
     *
     * @return character set of the input lexical token
     */
    Set<Integer> characterSet(final T value);

    /**
     * Returns length of the input lexical token in chars
     *
     * @return length of the input lexical token
     */
    int length(final T value);

    /**
     * Returns formatted representation of the input lexical token by parser function
     *
     * @param value  - input value
     * @param parser - input parser function
     * @return formatted representation of input value
     */
    E valueOf(final T value, final Parser<T, E> parser);
}
