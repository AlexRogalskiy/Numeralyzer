/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.dispatcher;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveAction;

/**
 * Delegated recursive action implementation
 *
 * @param <T>
 * @param <U>
 * @author Alex
 * @version 1.0.0
 * @since 2018-11-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class DelegatedRecursiveAction<T, U extends DelegatedRecursiveAction<T, U>> extends RecursiveAction {

    /**
     * Default Logger instance
     */
    protected final Logger LOGGER = LogManager.getLogger(this.getClass());

    private final T value;

    public DelegatedRecursiveAction(final T value) {
        this.value = value;
    }

    @Override
    protected void compute() {
        if (this.validateCondition()) {
            DelegatedDispatcher.forkJoinPool.invokeAll(this.createSubtasks());
        } else {
            this.processing(this.value);
        }
    }

    protected abstract List<? extends Callable<U>> createSubtasks();

    protected abstract boolean validateCondition();

    protected void processing(final T value) {
        LOGGER.info(String.format("The result=(%s) was processed by thread=(%s)", value, Thread.currentThread().getName()));
    }
}
