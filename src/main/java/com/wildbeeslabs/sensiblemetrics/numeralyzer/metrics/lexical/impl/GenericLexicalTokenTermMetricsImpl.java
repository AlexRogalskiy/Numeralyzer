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
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.lexical.IGenericLexicalTokenTermMetrics;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.utils.ConverterUtils;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

/**
 * Abstract lexical token term metrics implementation
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
public abstract class GenericLexicalTokenTermMetricsImpl<S extends CharSequence, M extends IGenericLexicalToken<S>, T extends IGenericLexicalTokenTerm<S, M>, E> extends GenericMetricsImpl<T, E> implements IGenericLexicalTokenTermMetrics<S, M, T, E> {

    /**
     * Default constructor
     */
    public GenericLexicalTokenTermMetricsImpl() {
        getLogger().debug("Initializing lexical token term metrics...");
    }

    /**
     * Returns summary statistics of the input lexical term
     *
     * @param value - input term
     * @return summary statistics
     */
    @Override
    public LongSummaryStatistics getStatistics(final T value) {
        return value.getTokens().stream().collect(Collectors.summarizingLong((token) -> token.length()));
    }

    /**
     * Returns average tokens length of the input lexical term
     *
     * @param value - input term
     * @return average tokens length
     */
    @Override
    public double getAverageTokenLength(final T value) {
        return value.getTokens().stream().mapToInt((token) -> token.length()).average().getAsDouble();
    }

    /**
     * Returns number of items of the input lexical term numerated by input mapper function
     *
     * @param value     - input lexical term
     * @param numerator - input numerator function
     * @return number of tokens in the term
     */
    @Override
    public long count(final T value, final Numerator<M, Long> numerator) {
        return ConverterUtils.reduceStreamBy(value.getTokens().stream().map(numerator), 0L, (i1, i2) -> (i1 + i2));
    }
}
