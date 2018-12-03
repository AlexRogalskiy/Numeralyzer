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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * Generic lexical token interface declaration
 *
 * @param <T> - {@link CharSequence}
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public interface IGenericLexicalToken<T extends CharSequence> extends CharSequence, Serializable {

    /**
     * Default lexical token comparator
     */
    LexicalTokenComparator<? extends CharSequence> DEFAULT_TOKEN_COMPARATOR = new LexicalTokenComparator<>();

    /**
     * Default lexical token comparator implementation
     *
     * @param <T>
     */
    class LexicalTokenComparator<T extends Comparable<? super T>> implements Comparator<T> {

        @Override
        public int compare(final T first, final T last) {
            return Objects.compare(first, last, this);
        }
    }

    /**
     * Returns UUID of the current token
     *
     * @return UUID of the current token
     */
    UUID getId();

    /**
     * Returns value of the current token
     *
     * @return value of the current token
     */
    T getData();
}
