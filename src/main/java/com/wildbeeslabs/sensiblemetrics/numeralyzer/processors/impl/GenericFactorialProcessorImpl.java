package com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.impl;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.IGenericMetrics;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.IGenericFactorialProcessor;

/**
 * Generic factorial processor implementation
 *
 * @param <T>
 * @param <E>
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public abstract class GenericFactorialProcessorImpl<T, E> extends ABaseProcessorImpl<T, E> implements IGenericFactorialProcessor<T, E> {

    /**
     * Default empty numeric result
     */
    public static final Long DEFAULT_EMPTY_RESULT = Long.valueOf(0);
    /**
     * Default numeric divisors
     */
    protected static final long DIVISOR_2 = 2;
    protected static final long DIVISOR_5 = 5;


    public GenericFactorialProcessorImpl(final IGenericMetrics<T, E> metrics) {
        super(metrics);
        getLogger().debug("Initializing generic factorial numeric processor ...");
    }

    protected int countTrailingZeros2(int value) {
        int countOfZeros = 0;
        for (int i = 2; i <= value; i++) {
            countOfZeros += countFactorsOf5(i);
        }
        return countOfZeros;
    }

    protected int countFactorsOf5(int i) {
        int count = 0;
        while (i % 5 == 0) {
            count++;
            i /= 5;
        }
        return count;
    }
}
