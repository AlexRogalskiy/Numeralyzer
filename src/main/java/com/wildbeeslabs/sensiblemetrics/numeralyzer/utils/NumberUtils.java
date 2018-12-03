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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

/**
 * Helper class to handle number format operations
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public final class NumberUtils {

    /**
     * Default logger instance
     */
    private static final Logger LOGGER = LogManager.getLogger(NumberUtils.class);
    /**
     * Default number format pattern
     */
    public static final String DEFAULT_NUMBER_FORMAT_PATTERN = "#.##";
    /**
     * Default locale format instance
     */
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();
    /**
     * Default number format instance
     */
    private static final ThreadLocal<DecimalFormat> numberFormat = ThreadLocal.withInitial(() -> new DecimalFormat(DEFAULT_NUMBER_FORMAT_PATTERN, DecimalFormatSymbols.getInstance(DEFAULT_LOCALE)));

    /**
     * Default private constructor
     */
    private NumberUtils() {
        // PRIVATE EMPTY CONSTRUCTOR
    }

    public static String format(final Double value) {
        return numberFormat.get().format(value);
    }

    public static String formatByPattern(final Double value, final String pattern) {
        return formatByPattern(value, pattern, DEFAULT_LOCALE);
    }

    public static String formatByPattern(final Double value, final String pattern, Locale locale) {
        final DecimalFormat formatter = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(locale));
        return formatter.format(value);
    }

    public static int generate(int min, int max) {
        return new Random().ints(min, (max + 1)).findFirst().getAsInt();
    }
}
