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

import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.IBaseProcessor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Abstract base processor class with default implementation
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@Data
@EqualsAndHashCode
@ToString
public abstract class ABaseProcessorImpl implements IBaseProcessor {

    /**
     * Default logger instance
     */
    protected final Logger LOGGER = LogManager.getLogger(getClass());
    /**
     * Default stream delimiter
     */
    public static final String DEFAULT_TOKEN_DELIMITER = "[,./?;:!-\"\\s]+?";

    public ABaseProcessorImpl() {
        getLogger().debug("Initializing base processor ...");
    }

    protected <E> Stream<E> getFilteredStream(final Stream<E> stream, final Function<CharSequence, CharSequence> tokenFilter, final String tokenDelim) {
        return stream.flatMap(line -> Arrays.stream(String.valueOf(line).trim().split(tokenDelim)))
                .map(item -> tokenFilter.apply(item))
                .filter(StringUtils::isNotBlank)
                .map(item -> (E) item);
    }

    protected Logger getLogger() {
        return this.LOGGER;
    }

    protected Function<CharSequence, CharSequence> getDefaultFilter() {
        return ((word) -> String.valueOf(word).toLowerCase().trim());
    }
}
