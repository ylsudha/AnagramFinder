package com.global.commtech.test.anagramfinder.service;

import com.global.commtech.test.anagramfinder.exception.FileProcessingException;
import com.global.commtech.test.anagramfinder.processor.AnagramProcessor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@AllArgsConstructor
public class AnagramService {

	private AnagramProcessor anagramProcessor;

	public void processAnagrams(File file) throws FileProcessingException {

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			List<String> words = new ArrayList<>();
			String word;
			int wordSize=-1;
			while ((word = reader.readLine()) != null) {
				wordSize = initialiseWordSize(word, wordSize);
				if (!wordsOfSameSize(word, wordSize)) {
					processAnagramsForWordsOfSameSize(words);
					resetWorldList(words);
					wordSize = getCurrentWordSize(word);

				}
				words.add(word.toLowerCase(Locale.ROOT));

			}
			processesLastSetOfWords(words);

		} catch (IOException e) {
			throw new FileProcessingException("error processing file: "+file.getName(),e.getCause());
		}

	}

	private void processesLastSetOfWords(List<String> words) {
		anagramProcessor.processAnagrams(words);
	}

	private int getCurrentWordSize(String word) {
		return word.length();
	}

	private void resetWorldList(List<String> words) {
		words.clear();
	}

	private void processAnagramsForWordsOfSameSize(List<String> words) {
		anagramProcessor.processAnagrams(words);
	}

	private int initialiseWordSize(String word, int wordSize) {
		if(wordSize == -1) {
			wordSize = word.length();
		}
		return wordSize;
	}

	private boolean wordsOfSameSize(String word, int currentWordSize) {
		return word.length() == currentWordSize;
	}




}
