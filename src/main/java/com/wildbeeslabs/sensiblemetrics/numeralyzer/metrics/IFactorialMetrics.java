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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.functions.Counter;

/**
 * Factorial metrics interface declaration
 *
 * @param <T>
 * @param <E>
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public interface IFactorialMetrics<T, E> extends IGenericMetrics<T, E> {

    /**
     * Default factor divisors
     */
    int DIVISOR_2 = 2;
    int DIVISOR_5 = 5;

    /**
     * Returns number of trailing zeros calculated based on the initial input value
     *
     * @param value - input value to be factorized
     * @return number of trailing zeros
     */
    long numOfTrailingZeros(final T value);

    /**
     * Returns number of factors calculated based on the initial input value
     *
     * @param value   - input value to be factorized
     * @param counter - calculation function to process the input value
     * @return number of factors
     */
    long numOfFactors(final T value, final Counter<T> counter);
}
