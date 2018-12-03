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
import com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.IGenericLexicalTokenTerm;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.functions.Numerator;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.impl.GenericMetricsImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.lexical.ILexicalTokenTermMetrics;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.utils.ConverterUtils;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

/**
 * Lexical token metrics implementation
 *
 * @param <S> - {@link CharSequence}
 * @param <M> - {@link IGenericLexicalToken}
 * @param <T> - {@link IGenericLexicalTokenTerm}
 * @param <E> - {@link Object}
 * @author Alex
 * @version 1.0.0
 * @since 2018-11-30
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LexicalTokenTermMetricsImpl<S extends CharSequence, M extends IGenericLexicalToken<S>, T extends IGenericLexicalTokenTerm<S, M>, E> extends GenericMetricsImpl<T, E> implements ILexicalTokenTermMetrics<S, M, T, E> {

    /**
     * Default constructor
     */
    public LexicalTokenTermMetricsImpl() {
        getLogger().debug("Initializing lexical token term metrics...");
    }

    /**
     * Returns summary statistics of the current input term
     *
     * @param value - input term
     * @return summary statistics
     */
    @Override
    public IntSummaryStatistics getStatistics(final T value) {
        return value.getTokens().stream().collect(Collectors.summarizingInt((token) -> token.length()));
    }

    /**
     * Returns average tokens length of the current input term
     *
     * @param value - input term
     * @return average tokens length
     */
    @Override
    public double getAverageTokenLength(final T value) {
        return value.getTokens().stream().mapToInt((token) -> token.length()).average().getAsDouble();
    }

    /**
     * Returns number of tokens of the current input term counted by input mapper function
     *
     * @param value  input term
     * @param mapper mapper function
     * @return number of tokens in the term
     */
    protected int count(final T value, final Numerator<M, Integer> mapper) {
        return ConverterUtils.reduceStreamBy(value.getTokens().stream().map(mapper), 0, (i1, i2) -> (i1 + i2));
    }
}
