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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.impl.factorial;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.impl.factorial.ComplexFactorialMetricsImpl;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;

/**
 * Complex factorial metrics processor implementation
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ComplexFactorialMetricsProcessorImpl extends BaseFactorialMetricsProcessorImpl<Long, Long, ComplexFactorialMetricsImpl> {

    public ComplexFactorialMetricsProcessorImpl() {
        getLogger().debug("Initializing complex factorial metrics processor...");
    }

    /**
     * Returns trailing 0s in a factorial of value
     *
     * @param value - value to be factorized
     * @return number of trailing zeros
     */
    @Override
    public Long countTrailingZeros(Long value) {
        if (Objects.isNull(value) || value < 0) {
            return getDefaultResult();
        }
        return this.getMetrics().trailingZeros(value);
    }

    @Override
    protected Long getDefaultResult() {
        return Long.valueOf(0);
    }
}
