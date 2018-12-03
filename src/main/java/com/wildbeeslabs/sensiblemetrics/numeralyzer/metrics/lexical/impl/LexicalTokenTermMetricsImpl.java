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
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.impl.GenericMetricsImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.lexical.ILexicalTokenTermMetrics;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.utils.ConverterUtils;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.IntSummaryStatistics;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Lexical token metrics implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2018-11-30
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LexicalTokenTermMetricsImpl<T extends IGenericLexicalTokenTerm<IGenericLexicalToken<CharSequence>>, E> extends GenericMetricsImpl<T, E> implements ILexicalTokenTermMetrics<T, E> {

    /**
     * Default constructor
     */
    public LexicalTokenTermMetricsImpl() {
        getLogger().debug("Initializing lexical token term metrics...");
    }

    @Override
    public IntSummaryStatistics getStatistics(final T value) {
        return value.getTokens().stream().collect(Collectors.summarizingInt((token) -> token.length()));
    }

    @Override
    public double getAverageTokenLength(final T value) {
        return value.getTokens().stream().mapToInt((token) -> token.length()).average().getAsDouble();
    }

    protected int count(final T value, final Function<IGenericLexicalToken<CharSequence>, Integer> mapper) {
        return ConverterUtils.reduceStreamBy(value.getTokens().stream().map(mapper), 0, (i1, i2) -> (i1 + i2));
    }
}
