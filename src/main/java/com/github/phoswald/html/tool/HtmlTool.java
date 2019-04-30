package com.github.phoswald.html.tool;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.jsoup.helper.DataUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.jsoup.parser.Parser;

public class HtmlTool {

    private boolean inputXml = false;
    private OutputSettings outputSettings = new OutputSettings().indentAmount(2);

    private Document document;

    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            if (Objects.equals(args[0], "normalize")) {
                normalize(args);
            } else {
                System.err.println("ERROR: Invalid command: " + args[0]);
                System.exit(1);
            }
        } else {
            printUsage();
        }
    }

    private static void printUsage() {
        System.out.println("html-tool : Manipulate (X)HTML files");
        System.out.println("");
        System.out.println("Usage:");
        System.out.println("  $ html-tool normalize <input> <output> [--parse-xml] [--generate-xml]");
        System.exit(1);
    }

    private static void normalize(String[] args) throws IOException {
        if (args.length >= 3) {
            HtmlTool tool = new HtmlTool();
            Path inputFile = Paths.get(args[1]);
            Path outputFile = Paths.get(args[2]);
            for (int i = 3; i < args.length; i++) {
                if (Objects.equals(args[i], "--parse-xml")) {
                    tool.setParseXml();
                } else if (Objects.equals(args[i], "--generate-xml")) {
                    tool.setGenerateXml();
                } else {
                    System.err.println("ERROR: Invalid argument: " + args[i]);
                    System.exit(1);
                }
            }
            tool.normalize(inputFile, outputFile);
        } else {
            System.err.println("ERROR: Missing <input> or <output> files.");
            System.exit(1);
        }
    }

    void setParseXml() {
        inputXml = true;
    }

    void setGenerateXml() {
        outputSettings.syntax(Syntax.xml);
    }

    void normalize(Path inputFile, Path outputFile) throws IOException {
        parse(inputFile);
        generate(outputFile);
    }

    private void parse(Path inputFile) throws IOException {
        try (InputStream input = Files.newInputStream(inputFile)) {
            Parser parser = inputXml ? Parser.xmlParser() : Parser.htmlParser();
            document = DataUtil.load(input, null /* charsetName */, "" /* baseUri */, parser);
        }
    }

    private void generate(Path outputFile) throws IOException {
        document.outputSettings(outputSettings);
        try (Writer output = Files.newBufferedWriter(outputFile)) {
            output.write(document.html());
        }
    }
}
