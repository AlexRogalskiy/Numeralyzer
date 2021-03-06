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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.processors;

import java.io.File;

/**
 * Command line processor interface declaration
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public interface ICommandLineProcessor extends IBaseProcessor {

    /**
     * Verbose mode enumeration
     */
    enum VerboseMode {
        AUTO, SHORT, FULL;
    }

    /**
     * Returns output interactive mode {@link VerboseMode}
     *
     * @return output interactive mode
     */
    VerboseMode getMode();

    /**
     * Returns input source file {@link File} instance
     *
     * @return input source {@link File} instance
     */
    File getInputSource();

    /**
     * Returns output source file {@link File} instance
     *
     * @return output source {@link File} instance
     */
    File getOutputSource();

    /**
     * Returns ignore case mode, true - ignore case is enabled, false - otherwise
     *
     * @return true - if ignore case mode is enabled, false - otherwise
     */
    boolean isIgnoreCase();
}
