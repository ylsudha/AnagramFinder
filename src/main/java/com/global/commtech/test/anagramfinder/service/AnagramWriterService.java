package com.global.commtech.test.anagramfinder.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AnagramWriterService {

    public void printAnagrams(Map<String, List<String>> anagramGroupList) {
        for (List<String> anagramGroup : anagramGroupList.values()) {
            System.out.println(String.join(",", anagramGroup));
        }
    }

}
