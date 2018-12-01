package com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics;

/**
 * Default numerable interface
 *
 * @param <T>
 * @param <E>
 * @author Alex
 * @version 1.0.0
 * @since 2018-11-30
 */
public interface Numerable<T, E> {

    E numerate(final T value);
}
