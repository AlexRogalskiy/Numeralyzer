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
package com.wildbeeslabs.sensiblemetrics.numeralyzer;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.impl.StringLexicalToken;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.factorial.impl.SimpleFactorialMetricsImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.ICommandLineProcessor;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.factorial.IGenericFactorialMetricsProcessor;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.factorial.impl.SimpleFactorialMetricsProcessorImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.impl.CommandLineProcessorImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.lexical.IGenericLexicalTokenProcessor;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.lexical.impl.StringLexicalTokenProcessorImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.utils.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;

/**
 * Numeric analyzer application to operate on input / output stream
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public class NumericAnalyzer {

    /**
     * Default logger instance
     */
    private static final Logger LOGGER = LogManager.getLogger(NumericAnalyzer.class);

    public void init(final String... args) {
        LOGGER.debug("Initializing command line processor...");
        final ICommandLineProcessor commandLineProcessor = new CommandLineProcessorImpl(args);

        LOGGER.debug("Initializing token processor...");
        List<StringLexicalToken> tokenList = null;
        final IGenericLexicalTokenProcessor<String, StringLexicalToken> tokenProcessor = new StringLexicalTokenProcessorImpl();
        if (Objects.nonNull(commandLineProcessor.getInputSource())) {
            tokenList = FileUtils.readFile(commandLineProcessor.getInputSource(), tokenProcessor);
        }
        if (Objects.nonNull(commandLineProcessor.getOutputSource())) {
            FileUtils.writeFile(commandLineProcessor.getOutputSource(), tokenList);
        }

        LOGGER.debug("Initializing factorial simple metrics processor...");
        final IGenericFactorialMetricsProcessor<Integer, Long, SimpleFactorialMetricsImpl> metricsProcessor = new SimpleFactorialMetricsProcessorImpl();
        metricsProcessor.countTrailingZeros(-59);
    }
}
