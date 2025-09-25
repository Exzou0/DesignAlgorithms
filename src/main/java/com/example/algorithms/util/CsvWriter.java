package com.example.algorithms.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.StringJoiner;


public class CsvWriter {
    private final Path path;
    private final Object lock = new Object();


    public CsvWriter(String filepath, String... header) throws IOException {
        this.path = Paths.get(filepath);
        ensureParentExists();
        if (Files.notExists(path)) {
            String headerLine = String.join(",", header) + System.lineSeparator();
            Files.write(path, headerLine.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE_NEW);
        }
    }

    private void ensureParentExists() throws IOException {
        Path parent = path.getParent();
        if (parent != null && Files.notExists(parent)) {
            Files.createDirectories(parent);
        }
    }

    public void writeRow(String... cols) throws IOException {
        StringJoiner sj = new StringJoiner(",");
        for (String c : cols) {
            sj.add(escape(c));
        }
        String line = sj.toString() + System.lineSeparator();
        synchronized (lock) {
            Files.write(path, line.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        boolean needQuotes = s.contains(",") || s.contains("\"") || s.contains("\n") || s.contains("\r");
        if (!needQuotes) return s;
        return "\"" + s.replace("\"", "\"\"") + "\"";
    }
}
