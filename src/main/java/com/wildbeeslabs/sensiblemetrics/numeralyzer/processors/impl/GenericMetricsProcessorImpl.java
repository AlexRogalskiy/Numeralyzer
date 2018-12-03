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
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.IGenericMetricsProcessor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Generic metrics processor implementation
 *
 * @param <T> - {@link Object}
 * @param <E> - {@link Object}
 * @param <R> - {@link IGenericMetrics}
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class GenericMetricsProcessorImpl<T, E, R extends IGenericMetrics<T, E>> extends BaseProcessorImpl implements IGenericMetricsProcessor<T, E, R> {

    /**
     * Default metrics instance
     */
    private R metrics;

    /**
     * Default constructor
     */
    public GenericMetricsProcessorImpl() {
        getLogger().debug("Initializing generic metrics processor ...");
    }

    /**
     * Returns current metrics instance
     *
     * @return metrics instance
     */
    protected R getMetrics() {
        return metrics;
    }

    /**
     * Sets input metrics instance
     *
     * @param metrics - input metrics instance
     */
    @Override
    public void setMetrics(final R metrics) {
        this.metrics = metrics;
    }

    /**
     * Returns default result
     *
     * @return default result
     */
    protected abstract E getDefaultResult();
}
