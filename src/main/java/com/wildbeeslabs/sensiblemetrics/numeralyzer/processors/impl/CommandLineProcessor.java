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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.impl;

import com.wildbeeslabs.sensiblemetrics.numeralyzer.processors.ICommandLineProcessor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionHandlerFilter;
import org.kohsuke.args4j.spi.ExplicitBooleanOptionHandler;
import org.kohsuke.args4j.spi.StringOptionHandler;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class to process input CLI arguments
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
@Data
@EqualsAndHashCode
@ToString
public class CommandLineProcessor implements ICommandLineProcessor {

    /**
     * Default logger instance
     */
    private static final Logger LOGGER = LogManager.getLogger(CommandLineProcessor.class);
    /**
     * Default verbose mode
     */
    public static final String DEFAULT_VERBOSE_MODE = "auto";
    /**
     * Default verbose mode list
     */
    public static final List<String> DEFAULT_VERBOSE_MODE_LIST = Arrays.asList("auto", "short", "full");
    /**
     * Error status flag
     */
    private boolean errorFlag = true;

    @Option(name = "-v", aliases = {"--mode"}, usage = "sets verbose mode {auto | short | full}", metaVar = "VERBOSE MODE", handler = StringOptionHandler.class)
    private String mode;
    @Option(name = "-in", aliases = {"--input-source"}, required = true, usage = "sets input source", metaVar = "INPUT SOURCE")
    private File inputSource;
    @Option(name = "-out", aliases = {"--output-source"}, required = true, usage = "sets output source", metaVar = "OUTPUT SOURCE")
    private File outputSource;
    @Option(name = "-i", aliases = {"--ignore-case"}, usage = "enables/disables ignore case mode", metaVar = "IGNORE CASE MODE", handler = ExplicitBooleanOptionHandler.class)
    private boolean ignoreCase;

    private CmdLineParser parser;

    public CommandLineProcessor(final String... args) {
        this.parser = new CmdLineParser(this);
        this.initialize(args);
    }

    private void initialize(final String... args) {
        try {
            this.parser.parseArgument(args);
            this.initializeMode();
            this.initializeInputSource();
            this.initializeOutputSource();
            this.errorFlag = false;
        } catch (CmdLineException ex) {
            LOGGER.error(String.format("ERROR: cannot parse input / output arguments, message=(%s)", ex.getMessage()));
            LOGGER.error(String.format("Example: java -jar %s %s", "numeralyzer.jar", this.parser.printExample(OptionHandlerFilter.ALL)));
        }
    }

    private void initializeMode() throws CmdLineException {
        if (Objects.isNull(this.getMode())) {
            this.mode = DEFAULT_VERBOSE_MODE;
        } else {
            if (!DEFAULT_VERBOSE_MODE_LIST.contains(this.getMode())) {
                throw new CmdLineException(this.parser, String.format("Invalid argument: --mode is not a valid verbose mode [ requires {%s}", StringUtils.join(DEFAULT_VERBOSE_MODE_LIST, "|")), null);
            }
            this.mode = this.mode.toLowerCase();
        }
    }

    private void initializeInputSource() {
        if (Objects.isNull(this.getInputSource()) || !this.getInputSource().isFile()) {
            throw new IllegalArgumentException(String.format("Invalid argument: --input-source {%s} is not a valid input file", this.getInputSource()));
        }
    }

    private void initializeOutputSource() {
        if (Objects.isNull(this.getOutputSource()) || !this.getOutputSource().isFile()) {
            throw new IllegalArgumentException(String.format("Invalid argument: --output-source {%s} is not a valid output file", this.getOutputSource()));
        }
    }
}
