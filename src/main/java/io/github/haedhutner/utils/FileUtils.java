package io.github.haedhutner.utils;

import java.io.*;
import java.nio.file.Files;

public final class FileUtils {

    public static String resourceToString(String resourcePath) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);

        if (in == null) throw new ResourceNotFoundException(resourcePath);

        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            reader.lines().forEach(line -> {
                if (!line.isEmpty()) builder.append(line).append("\n");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static String fileToString(File file) {
        StringBuilder builder = new StringBuilder();
        try {
            Files.lines(file.toPath()).forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static class ResourceNotFoundException extends RuntimeException {
        ResourceNotFoundException(String resource) {
            super("Failed to find the resource: " + resource);
        }
    }
}
