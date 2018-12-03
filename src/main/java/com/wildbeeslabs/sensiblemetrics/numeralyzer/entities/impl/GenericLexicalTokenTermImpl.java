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
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Abstract generic lexical token term class to store information on a collection of data chunks
 *
 * @param <T>
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@Data
@EqualsAndHashCode
@ToString
public abstract class GenericLexicalTokenTermImpl<T extends IGenericLexicalToken<CharSequence>> implements IGenericLexicalTokenTerm<T> {

    @Setter(AccessLevel.NONE)
    protected final List<T> tokens;

    public GenericLexicalTokenTermImpl() {
        this.tokens = new ArrayList<>();
    }

    @Override
    public void setTokens(final Collection<? extends T> tokens) {
        this.tokens.clear();
        if (Objects.nonNull(tokens)) {
            this.tokens.addAll(tokens);
        }
    }

    @Override
    public void addToken(final T token) {
        if (Objects.nonNull(token)) {
            this.tokens.add(token);
        }
    }

    @Override
    public void removeToken(final T token) {
        if (Objects.nonNull(token)) {
            this.tokens.remove(token);
        }
    }

    @Override
    public int size() {
        return this.tokens.size();
    }

    public String toFormatString() {
        return ConverterUtils.joinWithPrefixPostfix(this.getTokens(), ", ", " [ ", " ] ");
    }
}
