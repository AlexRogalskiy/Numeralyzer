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
package com.wildbeeslabs.numeralizer;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.entities.impl.StringLexicalToken;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.factorial.impl.ComplexFactorialMetricsImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.factorial.impl.SimpleFactorialMetricsImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.lexical.IStringLexicalTokenMetrics;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.metrics.lexical.impl.StringLexicalTokenMetricsImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.factorial.impl.ComplexFactorialMetricsProcessorImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.factorial.impl.SimpleFactorialMetricsProcessorImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.lexical.IGenericLexicalTokenProcessor;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.lexical.impl.StringLexicalTokenProcessorImpl;
import com.wildbeeslabs.sensiblemetrics.numeralyzer.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test cases for numeric analyzer application
 *
 * @author Alex
 * @version 1.0.0
 * @since 2018-11-30
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class NumericAnalyzerTest {

    /**
     * Default logger instance
     */
    private static final Logger LOGGER = LogManager.getLogger(NumericAnalyzerTest.class);

    private IGenericLexicalTokenProcessor<String, StringLexicalToken> tokenProcessor = new StringLexicalTokenProcessorImpl();

    @Before
    public void setUp() {
        LOGGER.debug("Initializing vowel lexical token analyzer...");
        this.tokenProcessor = new StringLexicalTokenProcessorImpl();
    }

    @Test
    public void testReadNotExistInputData() {
        String inputFile = "src/test/resources/not-exist-input.txt";
        List<StringLexicalToken> lexicalTokens = FileUtils.readFile(new File(inputFile), this.tokenProcessor);
        Assert.assertEquals("Checking the size of the lexical token list: ", 0, lexicalTokens.size());
    }

    @Test
    public void testReadInputData() {
        String inputFile = "src/test/resources/input.txt";
        List<StringLexicalToken> lexicalTokens = FileUtils.readFile(new File(inputFile), this.tokenProcessor);
        Assert.assertEquals("Checking the size of the lexical token list: ", 6, lexicalTokens.size());

        inputFile = "src/test/resources/input-empty.txt";
        lexicalTokens = FileUtils.readFile(new File(inputFile), this.tokenProcessor);
        Assert.assertEquals("Checking the size of token list:", 0, lexicalTokens.size());
    }

    @Test
    public void testSimpleFactorialProcessorImpl() {
        final String inputFile = "src/test/resources/input.txt";
        final List<StringLexicalToken> lexicalTokens = FileUtils.readFile(new File(inputFile), this.tokenProcessor);

        LOGGER.debug(String.format("Input collection of lexical tokens: {%s}", StringUtils.join(lexicalTokens, "|")));

        final IStringLexicalTokenMetrics tokenMetrics = new StringLexicalTokenMetricsImpl();
        final SimpleFactorialMetricsProcessorImpl metricsProcessor = new SimpleFactorialMetricsProcessorImpl();
        metricsProcessor.setMetrics(new SimpleFactorialMetricsImpl());

        final List<Long> resultList = lexicalTokens.stream().map(token -> {
            final Long tokenData = tokenMetrics.valueOf(token, (StringLexicalToken value) -> Long.parseLong(value.getData().replaceAll("[\\D]", "")));
            return metricsProcessor.countTrailingZeros(tokenData);
        }).collect(Collectors.toList());
        Assert.assertEquals("Checking the size of token list: ", 6, resultList.size());
    }

    @Test
    public void testComplexFactorialProcessorImpl() {
        final String inputFile = "src/test/resources/input.txt";
        final List<StringLexicalToken> lexicalTokens = FileUtils.readFile(new File(inputFile), this.tokenProcessor);

        LOGGER.debug(String.format("Input collection of lexical tokens: {%s}", StringUtils.join(lexicalTokens, "|")));

        final IStringLexicalTokenMetrics tokenMetrics = new StringLexicalTokenMetricsImpl();
        final ComplexFactorialMetricsProcessorImpl metricsProcessor = new ComplexFactorialMetricsProcessorImpl();
        metricsProcessor.setMetrics(new ComplexFactorialMetricsImpl());

        final List<Long> resultList = lexicalTokens.stream().map(token -> {
            final Long tokenData = tokenMetrics.valueOf(token, (StringLexicalToken value) -> Long.parseLong(value.getData().replaceAll("[\\D]", "")));
            return metricsProcessor.countTrailingZeros(tokenData);
        }).collect(Collectors.toList());
        Assert.assertEquals("Checking the size of token list: ", 6, resultList.size());
    }

    @Test
    public void testSimpleFactorialOutputData() {
        final String inputFile = "src/test/resources/input2.txt";
        final List<StringLexicalToken> lexicalTokens = FileUtils.readFile(new File(inputFile), this.tokenProcessor);

        final IStringLexicalTokenMetrics tokenMetrics = new StringLexicalTokenMetricsImpl();
        final SimpleFactorialMetricsProcessorImpl metricsProcessor = new SimpleFactorialMetricsProcessorImpl();
        metricsProcessor.setMetrics(new SimpleFactorialMetricsImpl());

        final List<Long> resultList = lexicalTokens.stream().map(token -> {
            final Long tokenData = tokenMetrics.valueOf(token, (StringLexicalToken value) -> Long.parseLong(value.getData().replaceAll("[\\D]", "")));
            return metricsProcessor.countTrailingZeros(tokenData);
        }).collect(Collectors.toList());

        String outputFile = "src/test/resources/output.txt";
        LOGGER.debug(String.format("Output collection of factorized lexical tokens: {%s}", StringUtils.join(resultList, "|")));
        FileUtils.writeFile(new File(outputFile), resultList);

        try (final Stream<String> stream = Files.lines(Paths.get(outputFile), FileUtils.DEFAULT_FILE_CHARACTER_ENCODING)) {
            final Optional<String> firstLine = stream.findFirst();
            Assert.assertTrue(firstLine.isPresent());
            Assert.assertEquals("Checking the first line of output token list: ", "2113935", firstLine.get());
        } catch (IOException ex) {
            LOGGER.error(String.format("ERROR: cannot read from input file=%s, message=%s", String.valueOf(outputFile), ex.getMessage()));
        }
    }

    @Test
    public void testComplexFactorialOutputData() {
        final String inputFile = "src/test/resources/input.txt";
        final List<StringLexicalToken> lexicalTokens = FileUtils.readFile(new File(inputFile), this.tokenProcessor);

        final IStringLexicalTokenMetrics tokenMetrics = new StringLexicalTokenMetricsImpl();
        final ComplexFactorialMetricsProcessorImpl metricsProcessor = new ComplexFactorialMetricsProcessorImpl();
        metricsProcessor.setMetrics(new ComplexFactorialMetricsImpl());

        final List<Long> resultList = lexicalTokens.stream().map(token -> {
            final Long tokenData = tokenMetrics.valueOf(token, (StringLexicalToken value) -> Long.parseLong(value.getData().replaceAll("[\\D]", "")));
            return metricsProcessor.countTrailingZeros(tokenData);
        }).collect(Collectors.toList());

        String outputFile = "src/test/resources/output.txt";
        LOGGER.debug(String.format("Output collection of factorized lexical tokens: {%s}", StringUtils.join(resultList, "|")));
        FileUtils.writeFile(new File(outputFile), resultList);

        try (final Stream<String> stream = Files.lines(Paths.get(outputFile), FileUtils.DEFAULT_FILE_CHARACTER_ENCODING)) {
            final Optional<String> firstLine = stream.findFirst();
            Assert.assertTrue(firstLine.isPresent());
            Assert.assertEquals("Checking the first line of output token list: ", "19", firstLine.get());
        } catch (IOException ex) {
            LOGGER.error(String.format("ERROR: cannot read from input file=%s, message=%s", String.valueOf(outputFile), ex.getMessage()));
        }
    }

    @After
    public void tearDown() {
    }
}
