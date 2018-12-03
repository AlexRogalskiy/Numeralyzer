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
import com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.IGenericLexicalTokenTerm;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.utils.ConverterUtils;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Abstract generic lexical token term class to store information on a collection of data chunks
 *
 * @param <S> - {@link CharSequence}
 * @param <T> - {@link IGenericLexicalToken}
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@EqualsAndHashCode
@ToString
public abstract class GenericLexicalTokenTerm<S extends CharSequence, T extends IGenericLexicalToken<S>> implements IGenericLexicalTokenTerm<S, T> {

    /**
     * Collection of tokens
     */
    @Setter(AccessLevel.NONE)
    protected final List<T> tokens;

    /**
     * Default constructor
     */
    public GenericLexicalTokenTerm() {
        this.tokens = new ArrayList<>();
    }

    /**
     * Sets tokens from the input collection of tokens
     *
     * @param tokens - input collection of tokens
     */
    @Override
    public void setTokens(final Collection<? extends T> tokens) {
        this.tokens.clear();
        if (Objects.nonNull(tokens)) {
            this.tokens.addAll(tokens);
        }
    }

    /**
     * Adds token to the current token term
     *
     * @param token - token to be stored
     */
    @Override
    public void addToken(final T token) {
        if (Objects.nonNull(token)) {
            this.tokens.add(token);
        }
    }

    /**
     * Removes token from the current token term
     *
     * @param token - token to be removed
     */
    @Override
    public void removeToken(final T token) {
        if (Objects.nonNull(token)) {
            this.tokens.remove(token);
        }
    }

    /**
     * Returns size of the current token term
     *
     * @return size of the term
     */
    @Override
    public int size() {
        return this.tokens.size();
    }

    /**
     * Returns formatted string of tokens in the current term
     *
     * @return formatted string of tokens
     */
    public String toFormatString() {
        return ConverterUtils.joinWithPrefixPostfix(this.getTokens(), ", ", " [ ", " ] ");
    }
}
