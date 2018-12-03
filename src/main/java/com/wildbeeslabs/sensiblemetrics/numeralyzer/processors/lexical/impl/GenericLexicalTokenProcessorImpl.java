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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.lexical.impl;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.IGenericLexicalToken;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.functions.Filter;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.impl.BaseProcessorImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.lexical.IGenericLexicalTokenProcessor;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.utils.ConverterUtils;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Generic lexical token class to process input char sequences
 *
 * @param <T> - {@link CharSequence}
 * @param <E> - {@link IGenericLexicalToken}
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class GenericLexicalTokenProcessorImpl<T extends CharSequence, E extends IGenericLexicalToken<T>> extends BaseProcessorImpl implements IGenericLexicalTokenProcessor<T, E> {

    /**
     * Default constructor
     */
    public GenericLexicalTokenProcessorImpl() {
        getLogger().debug("Initializing generic lexical token processor...");
    }

    /**
     * Returns collection of lexical tokens by input stream
     *
     * @param stream - input stream
     * @return collection of lexical tokens
     */
    @Override
    public List<E> getLexicalTokens(final Stream<T> stream) {
        return this.getLexicalTokenList(stream, this.getDefaultFilter(), DEFAULT_TOKEN_DELIMITER);
    }

    /**
     * Returns lexical token list by input stream
     *
     * @param stream         - input stream
     * @param tokenFilter    - token filter function
     * @param tokenDelimiter - token delimiter
     * @return collection of lexical tokens
     */
    protected List<E> getLexicalTokenList(final Stream<T> stream, final Filter<CharSequence, CharSequence> tokenFilter, final String tokenDelimiter) {
        final Stream<T> filteredStream = ConverterUtils.getFilteredStream(stream, tokenFilter, tokenDelimiter);
        return ConverterUtils.convertToList(filteredStream, (token) -> createLexicalToken(token));
    }

    /**
     * Returns default lexical token filter
     *
     * @return default lexical token filter
     */
    protected Filter<CharSequence, CharSequence> getDefaultFilter() {
        return (token -> Optional.ofNullable(token).map(data -> data.toString().toLowerCase().trim()).orElse(StringUtils.EMPTY));
    }

    /**
     * Returns new lexical token with input value
     *
     * @param value - input value
     * @return new lexical token
     */
    protected abstract E createLexicalToken(final T value);
}
