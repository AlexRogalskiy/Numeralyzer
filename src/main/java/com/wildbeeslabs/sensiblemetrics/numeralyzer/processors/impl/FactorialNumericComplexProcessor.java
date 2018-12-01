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

import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.IFactorialNumericProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Factorial numeric complex processor implementation
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public class FactorialNumericComplexProcessor extends ABaseNumericProcessor<Long, Long> implements IFactorialNumericProcessor<Long, Long> {

    /**
     * Default empty numeric result
     */
    private static final Long DEFAULT_EMPTY_RESULT = Long.valueOf(0);
    /**
     * Default numeric divisors
     */
    private static final long DIVISOR_2 = 2;
    private static final long DIVISOR_5 = 5;

    /**
     * Default count numeric maps
     */
    private final Map<Long, Long> mapOfTwo;
    private final Map<Long, Long> mapOfFive;

    public FactorialNumericComplexProcessor() {
        getLogger().debug("Initializing factorial numeric complex processor ...");
        mapOfTwo = new HashMap<>();
        mapOfFive = new HashMap<>();
    }

    /**
     * Returns trailing 0s in a factorial of n
     *
     * @param value - value to be factorized
     * @return number of trailing zeros
     */
    @Override
    public Long countTrailingZeros(Long value) {
        if (Objects.isNull(value)) {
            return DEFAULT_EMPTY_RESULT;
        }
        return trailingZeros(value);
    }

    private long trailingZeros(long n) {
        if (n < DIVISOR_5) {
            return 0;
        }
        long countFive = 0;
        long countTwo = 0;
        for (int i = 1; i <= n; i++) {
            if (i % DIVISOR_2 == 0) {
                countTwo += countExistance(i, DIVISOR_2, mapOfTwo);
            }
            if (i % DIVISOR_5 == 0) {
                countFive += countExistance(i, DIVISOR_5, mapOfFive);
            }
        }
        return (countFive < countTwo) ? countFive : countTwo;
    }

    private long countExistance(long n, long m, Map<Long, Long> map) {
        long temp = n;
        long count = 0;
        double num = (double) n;
        while (num / m == n / m) {
            count++;
            n = n / m;
            num = (double) n;
            if (map.containsKey(n)) {
                count += map.get(n);
                break;
            }
        }
        if (!map.containsKey(temp)) {
            map.put(temp, count);
        }
        return count;
    }
}
