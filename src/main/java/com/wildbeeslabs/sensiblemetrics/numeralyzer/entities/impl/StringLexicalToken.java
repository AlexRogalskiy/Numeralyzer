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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

/**
 * String lexical token implementation
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StringLexicalToken extends GenericLexicalToken<String> {

    /**
     * Default constructor
     */
    public StringLexicalToken() {
        this(StringUtils.EMPTY);
    }

    /**
     * Default constructor with input data to store
     *
     * @param value - input data to store
     */
    public StringLexicalToken(final String value) {
        this(value, (Comparator<? super String>) DEFAULT_TOKEN_COMPARATOR);
    }

    /**
     * Default constructor with input data and data comparator
     *
     * @param value      - input data to store
     * @param comparator - input data comparator
     */
    public StringLexicalToken(final String value, final Comparator<? super String> comparator) {
        super(value, comparator);
    }
}
