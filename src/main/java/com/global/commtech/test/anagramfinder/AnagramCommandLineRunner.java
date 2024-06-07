package com.global.commtech.test.anagramfinder;

import com.global.commtech.test.anagramfinder.exception.FileProcessingException;
import com.global.commtech.test.anagramfinder.service.AnagramService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {

    @Autowired AnagramService anagramService;

    @Override
    public void run(final String... args) throws FileProcessingException {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");

        anagramService.processAnagrams(file);


    }

}
