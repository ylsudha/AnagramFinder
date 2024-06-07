package com.global.commtech.test.anagramfinder.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
public class AnagramWriterServiceTest {

	@InjectMocks AnagramWriterService anagramWriterService;

	@Test
	public void testAnagramsDataoutputToConsole(final CapturedOutput capturedOutput) {
		anagramWriterService.printAnagrams(testDataAnagramGroupData());
		assertThat(capturedOutput.getOut()).contains("abc,bac,cba");
		assertThat(capturedOutput.getOut()).contains("fun,unf");
		assertThat(capturedOutput.getOut()).contains("hello");
	}

	private Map<String, List<String>> testDataAnagramGroupData() {
		Map<String, List<String>> sortedKeyAnagramMap= new HashMap<>();
		sortedKeyAnagramMap.put("abc", Arrays.asList("abc","bac","cba"));
		sortedKeyAnagramMap.put("fnu", Arrays.asList("fun","unf"));
		sortedKeyAnagramMap.put("hello", Arrays.asList("hello"));
		return sortedKeyAnagramMap;
	}
}
