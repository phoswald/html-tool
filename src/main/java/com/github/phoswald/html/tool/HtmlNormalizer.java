package com.github.phoswald.html.tool;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Document.OutputSettings.Syntax;

class HtmlNormalizer {

    private OutputSettings outputSettings = new OutputSettings() //
            .indentAmount(2);

    void setGenerateXhtml() {
        outputSettings.syntax(Syntax.xml);
    }

    void normalize(Path inputFile, Path outputFile) throws IOException {
        Document document = parseHtmlFile(inputFile);
        generateHtmlFile(outputFile, document);
    }

    private Document parseHtmlFile(Path inputFile) throws IOException {
        return Jsoup.parse(inputFile.toFile(), null);
    }

    private void generateHtmlFile(Path outputFile, Document document) throws IOException {
        document.outputSettings(outputSettings);
        try (Writer output = Files.newBufferedWriter(outputFile)) {
            output.write(document.html());
        }
    }
}
