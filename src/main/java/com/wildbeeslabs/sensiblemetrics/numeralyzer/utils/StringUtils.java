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

import com.wildbeeslabs.sensiblemetrics.numeralyzer.functions.Parser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Helper class to handle string operations
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public final class StringUtils {

    /**
     * Default logger instance
     */
    private static final Logger LOGGER = LogManager.getLogger(StringUtils.class);

    private StringUtils() {
        // PRIVATE EMPTY CONSTRUCTOR
    }

    /**
     * Unzips compressed string to raw format string output
     *
     * @param value input string.
     * @return String Unzip raw string.
     * @throws Exception On unzip operation.
     * @see Exception
     */
    public static String ungzip(final String value) throws Exception {
        if (Objects.isNull(value)) {
            return null;
        }
        return StringUtils.ungzip(value.getBytes(UTF_8));
    }

    /**
     * Unzips compressed array of bytes to raw format string output
     *
     * @param bytes input array of bytes.
     * @return String Unzip raw string.
     * @throws Exception On unzip operation.
     * @see Exception
     */
    public static String ungzip(final byte[] bytes) throws Exception {
        if (isGZIPStream(bytes)) {
            InputStreamReader isr = new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bytes)), UTF_8);
            StringWriter sw = new StringWriter();
            char[] chars = new char[1024];
            for (int len; (len = isr.read(chars)) > 0; ) {
                sw.write(chars, 0, len);
            }
            return sw.toString();
        } else {
            return (new String(bytes, 0, bytes.length, UTF_8));
        }
    }

    /**
     * Checks whether input array of bytes is GZIP formatted or not
     *
     * @param bytes input array of bytes.
     * @return boolean true - if GZIP formatted, false - otherwise.
     */
    private static boolean isGZIPStream(final byte[] bytes) {
        if (Objects.isNull(bytes) || 0 == bytes.length) {
            return false;
        }
        return (bytes[0] == (byte) GZIPInputStream.GZIP_MAGIC)
                && (bytes[1] == (byte) (GZIPInputStream.GZIP_MAGIC >>> Byte.SIZE));
    }

    /**
     * Converts input string from ISO-8859-1 to UTF-8 format
     *
     * @param value input string.
     * @return String result string.
     */
    public static String convertFromLatin1ToUtf8(final String value) {
        return convert(value, ISO_8859_1, UTF_8);
    }

    /**
     * Converts input string from UTF-8 to ISO-8859-1 format
     *
     * @param value input string.
     * @return String result string.
     */
    public static String convertFromUtf8ToLatin1(final String value) {
        return convert(value, UTF_8, ISO_8859_1);
    }

    /**
     * Converts input string from Windows-1251 to UTF-8 format
     *
     * @param value input string.
     * @return String result string.
     */
    public static String convertFromCp1251ToUtf8(final String value) {
        return convert(value, Charset.forName("Cp1251"), UTF_8);
    }

    /**
     * Converts input string from UTF-8 to Windows-1251 format
     *
     * @param value input string.
     * @return String result string.
     */
    public static String convertUtf8ToCp1251(final String value) {
        return convert(value, UTF_8, Charset.forName("Cp1251"));
    }

    /**
     * Converts string from input to output character encoding
     *
     * @param value         input string.
     * @param inputCharset  input character encoding,
     * @param outputCharset output character encoding.
     * @return
     */
    private static String convert(final String value, final Charset inputCharset, final Charset outputCharset) {
        if (Objects.isNull(value)) {
            return null;
        }
        return new String(value.getBytes(inputCharset), outputCharset);
    }

    /*
 Pattern regex = Pattern.compile("\\d+");
 String result = StringParser.replace("test6test12hoho", regex, (Matcher m) -> {
 return "" + (Integer.parseInt(m.group()) * 2);
 });
 */
    public static String replace(final String input, final Pattern regex, final Parser<Matcher, String> callback) {
        final StringBuffer resultString = new StringBuffer();
        final Matcher regexMatcher = regex.matcher(input);
        while (regexMatcher.find()) {
            regexMatcher.appendReplacement(resultString, callback.parse(regexMatcher));
        }
        regexMatcher.appendTail(resultString);
        return resultString.toString();
    }
}
