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

import java.util.Objects;

/**
 * Factorial numeric processor implementation
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public class FactorialNumericProcessor extends ABaseNumericProcessor<Integer, Long> implements IFactorialNumericProcessor<Integer, Long> {

    /**
     * Default empty numeric result
     */
    private static final Long DEFAULT_EMPTY_RESULT = Long.valueOf(0);
    /**
     * Default numeric divisors
     */
    private static final long DIVISOR_5 = 5;

    public FactorialNumericProcessor() {
        getLogger().debug("Initializing factorial numeric processor ...");
    }

    /**
     * Returns trailing 0s in a factorial of n
     *
     * @param value - value to be factorized
     * @return number of trailing zeros
     */
    @Override
    public Long countTrailingZeros(Integer value) {
        if (Objects.isNull(value)) {
            return DEFAULT_EMPTY_RESULT;
        }
        return trailingZeros(value);
    }

    private long trailingZeros(int num) {
        int count = 0;
        while (num > 0) {
            num /= DIVISOR_5;
            count += num;
        }
        return count;
    }

//    public int countTrailingZeros2(int value) {
//        int countOfZeros = 0;
//        for (int i = 2; i <= value; i++) {
//            countOfZeros += countFactorsOf5(i);
//        }
//        return countOfZeros;
//    }
//
//    private int countFactorsOf5(int i) {
//        int count = 0;
//        while (i % 5 == 0) {
//            count++;
//            i /= 5;
//        }
//        return count;
//    }
}
