package com.github.phoswald.html.tool;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class HtmlTool {

    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            if (Objects.equals(args[0], "normalize")) {
                normalize(args);
            } else {
                System.err.println("ERROR: Invalid command: " + args[0]);
                printUsage();
            }
        } else {
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("html-tool : Do stuff with (X)HTML files");
        System.out.println("Usage:");
        System.out.println("  $ html-tool normalize <input> <output> [--generate-xhtml]");
    }

    private static void normalize(String[] args) throws IOException {
        if (args.length >= 3) {
            HtmlNormalizer normalizer = new HtmlNormalizer();
            Path inputFile = Paths.get(args[1]);
            Path outputFile = Paths.get(args[2]);
            for (int i = 3; i < args.length; i++) {
                if (Objects.equals(args[i], "--generate-xhtml")) {
                    normalizer.setGenerateXhtml();
                } else {
                    System.err.println("ERROR: Invalid argument: " + args[i]);
                }
            }
            normalizer.normalize(inputFile, outputFile);
        } else {
            System.err.println("ERROR: Missing <input> or <output> files.");
            printUsage();
        }
    }
}
