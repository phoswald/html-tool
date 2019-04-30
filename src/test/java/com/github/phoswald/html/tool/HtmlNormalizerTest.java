package com.github.phoswald.html.tool;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class HtmlNormalizerTest {

    private HtmlNormalizer testee = new HtmlNormalizer();

    @Test
    void normalize_validWithHtml_success() throws IOException {
        // Arrange
        Path input = Paths.get("src/test/resources/sample-input.html");
        Path expected = Paths.get("src/test/resources/sample-output.html");
        Path actual = Paths.get("target/test/output/sample-output.html");

        Files.createDirectories(actual.getParent());

        // Act
        testee.normalize(input, actual);

        // Assert
        assertEqualTextFiles(expected, actual);
    }

    @Test
    void normalize_validWithXml_success() throws IOException {
        // Arrange
        Path input = Paths.get("src/test/resources/sample-input.html");
        Path expected = Paths.get("src/test/resources/sample-output.xhtml");
        Path actual = Paths.get("target/test/output/sample-output.xhtml");

        Files.createDirectories(actual.getParent());

        // Act
        testee.setGenerateXhtml();
        testee.normalize(input, actual);

        // Assert
        assertEqualTextFiles(expected, actual);
    }

    private void assertEqualTextFiles(Path expectedFile, Path actualFile) throws IOException {
        String expectedText = String.join("\n", Files.readAllLines(expectedFile));
        String actualText = String.join("\n", Files.readAllLines(actualFile));
        assertTrue(expectedText.equals(actualText), () -> "Files differ: " + expectedFile + ", " + actualFile);
    }
}
