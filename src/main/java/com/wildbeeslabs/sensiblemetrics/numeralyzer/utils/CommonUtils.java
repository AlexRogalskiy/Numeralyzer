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
package com.wildbeeslabs.sensiblemetrics.numeralyzer.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Helper class to common predicate / function operations
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public final class CommonUtils {

    private CommonUtils() {
        // PRIVATE EMPTY CONSTRUCTOR
    }

    public static final Predicate<Boolean> randomBool = predicate -> new Random().nextBoolean();
    public static final Predicate<String> notEmpty = (String it) -> StringUtils.isNotEmpty(it);
    public static final Predicate<String> notBlank = (String it) -> StringUtils.isNotBlank(it);

    public static <T> Predicate<T> not(final Predicate<T> t) {
        return t.negate();
    }

    public static <T> String replace(final String input, final Pattern regex, final Function<Matcher, String> function) {
        final StringBuffer resultString = new StringBuffer();
        final Matcher regexMatcher = regex.matcher(input);
        while (regexMatcher.find()) {
            regexMatcher.appendReplacement(resultString, function.apply(regexMatcher));
        }
        regexMatcher.appendTail(resultString);
        return resultString.toString();
    }

    public static <T> List<T> convert(final List<Optional<T>> listOfOptionals) {
        Objects.requireNonNull(listOfOptionals);
        return listOfOptionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static <T> List<Stream<T>> convertFlat(final List<Optional<T>> listOfOptionals) {
        Objects.requireNonNull(listOfOptionals);
        return (List<Stream<T>>) listOfOptionals.stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
    }
}
