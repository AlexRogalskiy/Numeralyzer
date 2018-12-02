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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.impl.factorial;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Standard factorial metrics implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-07
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StandardFactorialMetricsImpl extends BaseFactorialMetricsImpl<Integer, Integer> {

    /**
     * Default constructor
     */
    public StandardFactorialMetricsImpl() {
        getLogger().debug("Initializing standard factorial metrics...");
    }

    @Override
    public long numOfTrailingZeros(final Integer value) {
        long countOfZeros = 0, temp = value;
        for (int i = 2; i <= temp; i++) {
            //countOfZeros += this.countFactorsOf5(i);
            countOfZeros += this.numOfFactors(i, (Integer n) -> this.countFactorsOf5(n));
        }
        return countOfZeros;
    }

    protected int countFactorsOf5(int value) {
        int count = 0;
        while (value % DIVISOR_5 == 0) {
            count++;
            value /= DIVISOR_5;
        }
        return count;
    }
}
