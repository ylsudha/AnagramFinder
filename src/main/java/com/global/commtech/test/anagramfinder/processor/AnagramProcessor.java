package com.global.commtech.test.anagramfinder.processor;

import com.global.commtech.test.anagramfinder.service.AnagramWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class AnagramProcessor {
    @Autowired AnagramWriterService anagramWriterService;

    public void processAnagrams(List<String> words) {
        var anagramGroups = groupAnagrams(words);
        anagramWriterService.printAnagrams(anagramGroups);
    }

    public Map<String, List<String>> groupAnagrams(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(this::sortString));
    }

    private String sortString(String word) {
        char[] wordChars = word.toCharArray();
        Arrays.sort(wordChars);
        return new String(wordChars);
    }

}


