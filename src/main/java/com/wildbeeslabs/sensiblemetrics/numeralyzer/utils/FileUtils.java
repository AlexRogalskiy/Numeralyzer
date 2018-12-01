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

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Helper class to handle file read / write operations
 *
 * @author alexander.rogalskiy
 * @version 1.0
 * @since 2018-11-30
 */
public final class FileUtils {

    /**
     * Default logger instance
     */
    private static final Logger LOGGER = LogManager.getLogger(FileUtils.class);
    /**
     * Default file character encoding
     */
    public static final Charset DEFAULT_FILE_CHARACTER_ENCODING = StandardCharsets.UTF_8;

    private FileUtils() {
        // PRIVATE EMPTY CONSTRUCTOR
    }

    public static List<String> readAllLines(final File inputFile) {
        Objects.requireNonNull(inputFile);
        List<String> resultList = Collections.EMPTY_LIST;
        try {
            resultList = Files.readAllLines(inputFile.toPath(), DEFAULT_FILE_CHARACTER_ENCODING);
        } catch (IOException ex) {
            LOGGER.error(String.format("ERROR: cannot read from input file=%s, message=%s", String.valueOf(inputFile), ex.getMessage()));
        }
        return resultList;
    }

    public static List<String> readFileByFilter(final File inputFile, final Predicate<String> predicate) {
        Objects.requireNonNull(inputFile);
        List<String> resultList = Collections.EMPTY_LIST;
        try (final BufferedReader br = Files.newBufferedReader(inputFile.toPath(), DEFAULT_FILE_CHARACTER_ENCODING)) {
            resultList = br.lines().filter(predicate).collect(Collectors.toList());
        } catch (IOException ex) {
            LOGGER.error(String.format("ERROR: cannot read from input file=%s, message=%s", String.valueOf(inputFile), ex.getMessage()));
        }
        return resultList;
    }

    public static <U extends CharSequence> void writeFile(final File outputFile, final Collection<? extends U> output) {
        Objects.requireNonNull(outputFile);
        Objects.requireNonNull(output);
        try (final PrintWriter writer = new PrintWriter(Files.newBufferedWriter(outputFile.toPath(), DEFAULT_FILE_CHARACTER_ENCODING))) {
            output.stream().forEach(writer::println);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            LOGGER.error(String.format("ERROR: cannot create output file=%s, message=%s", String.valueOf(outputFile), ex.getMessage()));
        } catch (IOException ex) {
            LOGGER.error(String.format("ERROR: cannot process read / writer operations on file=%s, message=%s", String.valueOf(outputFile), ex.getMessage()));
        }
    }

    public static void writeZipFile(final List<File> listFiles, final File outputZip) throws IOException {
        outputZip.getParentFile().mkdirs();
        try (final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputZip))) {
            for (final File file : listFiles) {
                final String filePath = file.getCanonicalPath();
                LOGGER.debug(String.format("Processing zip file: %s", filePath));

                final String zipFilePath = Paths.get(filePath).getFileName().toString();
                final ZipEntry zipEntry = new ZipEntry(zipFilePath);
                zipOutputStream.putNextEntry(zipEntry);

                try (final FileInputStream inputStream = new FileInputStream(file)) {
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = inputStream.read(bytes)) >= 0) {
                        zipOutputStream.write(bytes, 0, length);
                    }
                }
            }
        }
    }

    public static List<File> listFiles(final File inputDirectory) {
        final List<File> listFiles = new ArrayList<>();
        listFiles(listFiles, inputDirectory);
        return listFiles;
    }

    private static void listFiles(final List<File> listFiles, final File inputDirectory) {
        final File[] allFiles = inputDirectory.listFiles();
        for (final File file : allFiles) {
            if (file.isDirectory()) {
                listFiles(listFiles, file);
            } else {
                listFiles.add(file);
            }
        }
    }

    public static List<File> listDirectories(final File inputDirectory) {
        final List<File> listDirectories = new ArrayList<>();
        listDirectories(listDirectories, inputDirectory);
        return listDirectories;
    }

    private static void listDirectories(final List<File> listDirectories, final File inputDirectory) {
        final File[] directories = inputDirectory.listFiles(File::isDirectory);
        for (final File directory : directories) {
            listDirectories.add(directory);
            listDirectories(listDirectories, directory);
        }
    }
}
