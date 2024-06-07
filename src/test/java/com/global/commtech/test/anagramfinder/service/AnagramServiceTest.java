package com.global.commtech.test.anagramfinder.service;

import com.global.commtech.test.anagramfinder.exception.FileProcessingException;
import com.global.commtech.test.anagramfinder.processor.AnagramProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnagramServiceTest {

	@InjectMocks AnagramService anagramService;
	@Mock AnagramProcessor anagramProcessor;

	@Test
	void shouldProcessAnagrams() throws FileProcessingException {
		anagramService.processAnagrams(new File("src/test/resources/example1.txt"));
		verify(anagramProcessor, times(2)).processAnagrams(any());

	}

}