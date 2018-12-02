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

import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.IFactorialMetrics;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.impl.GenericMetricsImpl;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Default complex factorial metrics implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-07
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ComplexFactorialMetricsImpl extends GenericMetricsImpl<Long, Long> implements IFactorialMetrics<Long, Long> {

    /**
     * Default counting maps
     */
    private final Map<Long, Long> mapOfTwo;
    private final Map<Long, Long> mapOfFive;

    public ComplexFactorialMetricsImpl() {
        getLogger().debug("Initializing complex factorial metrics...");
        this.mapOfTwo = new HashMap<>();
        this.mapOfFive = new HashMap<>();
    }

    @Override
    public Long trailingZeros(final Long value) {
        long temp = value;
        if (temp < DIVISOR_5) {
            return 0L;
        }
        long countFive = 0;
        long countTwo = 0;
        for (int i = 1; i <= temp; i++) {
            if (i % DIVISOR_2 == 0) {
                countTwo += this.countExistance(i, DIVISOR_2, this.mapOfTwo);
            }
            if (i % DIVISOR_5 == 0) {
                countFive += this.countExistance(i, DIVISOR_5, this.mapOfFive);
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
