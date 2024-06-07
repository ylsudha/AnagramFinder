package com.global.commtech.test.anagramfinder;

import static org.assertj.core.api.Assertions.assertThat;

import com.global.commtech.test.anagramfinder.exception.FileProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest(args = {"src/test/resources/example1.txt"})
@ExtendWith(OutputCaptureExtension.class)
class AnagramCommandLineRunnerIntegrationTest {

    @Autowired
    AnagramCommandLineRunner anagramCommandLineRunner;

    @Test
    void shouldFindAnagrams(final CapturedOutput capturedOutput) throws Exception {
        anagramCommandLineRunner.run("src/test/resources/example1.txt");
        assertThat(capturedOutput.getOut()).contains("abc,bac,cba");
        assertThat(capturedOutput.getOut()).contains("fun,unf");
        assertThat(capturedOutput.getOut()).contains("hello");
    }

    @Test
    void shouldfindAnagramsNonAscii(final CapturedOutput capturedOutput) throws Exception {
        anagramCommandLineRunner.run("src/test/resources/exampleAscii.txt");
        assertThat(capturedOutput.getOut()).contains("&iexcl");
        assertThat(capturedOutput.getOut()).contains("&cent;");
        assertThat(capturedOutput.getOut()).contains("&pound;,;undpo&");
    }

    @Test
    void shouldfindAnagramsLargeFile(final CapturedOutput capturedOutput) throws Exception {
        anagramCommandLineRunner.run("src/test/resources/example2.txt");
        assertThat(capturedOutput.getOut()).contains("aa");
        assertThat(capturedOutput.getOut()).contains("centiares,cisternae,creatines,iterances,nectaries");
        assertThat(capturedOutput.getOut()).contains("staccatos,stoccatas");
    }

    @Test
    void shouldfindAnagramsConvertingToCaseInsentiveLowercase(final CapturedOutput capturedOutput) throws Exception {
        anagramCommandLineRunner.run("src/test/resources/exampleCaseSensitive.txt");
        assertThat(capturedOutput.getOut()).contains("abc;,cab;");
        assertThat(capturedOutput.getOut()).contains("rat,tar");
    }

    @Test
    public void shouldNotThrowErrorOnEmptyFile(final CapturedOutput capturedOutput) throws FileProcessingException {
        anagramCommandLineRunner.run("src/test/resources/exampleCaseSensitive.txt");
    }



}