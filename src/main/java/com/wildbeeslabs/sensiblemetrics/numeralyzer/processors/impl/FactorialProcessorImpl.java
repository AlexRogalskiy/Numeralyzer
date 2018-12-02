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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.impl;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.IGenericMetrics;

import java.util.Objects;

/**
 * Factorial processor implementation
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public class FactorialProcessorImpl extends GenericFactorialProcessorImpl<Integer, Long> {

    public FactorialProcessorImpl(final IGenericMetrics<Integer, Long> metrics) {
        super(metrics);
        getLogger().debug("Initializing factorial numeric processor ...");
    }

    /**
     * Returns trailing 0s in a factorial of value
     *
     * @param value - value to be factorized
     * @return number of trailing zeros
     */
    @Override
    public Long countTrailingZeros(Integer value) {
        if (Objects.isNull(value) || value < 0) {
            return DEFAULT_EMPTY_RESULT;
        }
        return this.trailingZeros(value);
    }

    private long trailingZeros(int num) {
        int count = 0;
        while (num > 0) {
            num /= DIVISOR_5;
            count += num;
        }
        return count;
    }
}
