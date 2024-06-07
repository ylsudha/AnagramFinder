package com.global.commtech.test.anagramfinder.processor;

import com.global.commtech.test.anagramfinder.service.AnagramWriterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
public class AnagramProcessorTest {
	@InjectMocks AnagramProcessor anagramProcessor;
	@InjectMocks AnagramWriterService anagramWriterService;

	@Test
	public void testAnagramsDataoutputToConsole(final CapturedOutput capturedOutput) {
		Map<String, List<String>> anagramMapWithSortedKey = anagramProcessor.groupAnagrams(Arrays.asList("","l","ll","ll","abc","cab","cba","fun","hello","ellho"));
		anagramWriterService.printAnagrams(anagramMapWithSortedKey);
		assertThat(capturedOutput.getOut()).contains("l");
		assertThat(capturedOutput.getOut()).contains("ll,ll");
		assertThat(capturedOutput.getOut()).contains("abc");
		assertThat(capturedOutput.getOut()).contains("cab,cba");
		assertThat(capturedOutput.getOut()).contains("fun");
		assertThat(capturedOutput.getOut()).contains("hello","ellho");

	}


	@Test
	public void testAnagramsGroupingAndSortingKey() {
		Map<String, List<String>> anagramMapWithSortedKey = anagramProcessor.groupAnagrams(Arrays.asList("l","ll","ll","abc","cab","cba","fun","hello","ellho"));
		assertThat(anagramMapWithSortedKey.keySet().contains("lll"));
		assertThat(anagramMapWithSortedKey.get("abc").contains(Arrays.asList("abc","cab","cba")));
		assertEquals(anagramMapWithSortedKey.keySet().contains("lll"),false);
		assertThat(anagramMapWithSortedKey.get("ll").contains(Arrays.asList("abc","cab","cba")));

	}





}
