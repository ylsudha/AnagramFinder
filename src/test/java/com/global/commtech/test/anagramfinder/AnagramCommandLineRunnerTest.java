package com.global.commtech.test.anagramfinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.global.commtech.test.anagramfinder.exception.FileProcessingException;
import com.global.commtech.test.anagramfinder.service.AnagramService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnagramCommandLineRunnerTest {

    @InjectMocks
    AnagramCommandLineRunner anagramCommandLineRunner;

    @Mock AnagramService anagramService;

    @Test
    void shouldThrowExceptionWhenNoArgumentsPresent() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run());
        assertThat(exception.getMessage()).isEqualTo("Please ensure that the input file is provided");
    }

    @Test
    void shouldThrowExceptionWhenMoreThanOneArgumentIsPassed() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run("one", "two"));
        assertThat(exception.getMessage()).isEqualTo("Please ensure that the input file is provided");
    }

    @Test
    void shouldThrowExceptionWhenInputFileDoesNotExist() {
        final var exception = assertThrows(Exception.class, () -> anagramCommandLineRunner.run("notExists"));
        assertThat(exception.getMessage()).isEqualTo("notExists Does not exist");
    }

    @Test
        void shouldThrowExceptionWhenErrorinFileProcessing() throws FileProcessingException {
        doThrow(new FileProcessingException("error",null)).when(anagramService).processAnagrams(any());
        assertThrows(FileProcessingException.class, () -> anagramCommandLineRunner.run("src/test/resources/example1.txt"));

    }

}