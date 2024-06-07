# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 17

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="data/example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams

## Solution
1) To avoid storing the entire file in memory, words will be read and stored in a list in batches. Each batch will be processed, and the list will be cleared for the next set of words. A buffered reader will be used to read portions of the file incrementally instead of loading the entire file into memory at once.
2) The letters of each word will be sorted using standard Java libraries. Once sorted, all anagrams will produce the same sequence of characters. Therefore, if the sorted sequences match, the words are confirmed to be anagrams.
3) Identified anagrams will be stored in a map, with the sorted string as the key and a list of anagrams as the values.
4) The lists of anagrams will be printed to the console, and the process will be repeated for the next set of words from the file.

## Big O Analysis
Time complexity
    - Sorting a word: O(n log n) Quicksort (java outbox)where n is the length of the word.
    - Grouping words: O(k * n log n) where k is the number of words and n is the average length of the words.
    - Overall: O(k * n log n).
Space complexity
- Array for storing the words O(n)
- Keys O(m) average number of keys 
- Overall : O(n+m)


## Data Structures

- Used a `HashMap` to group anagrams efficiently by their sorted character representation O(1) for insertion, search.
- Used a `ArrayList`   to store different size word list each time (which can be avoided if needed)

## Future Improvements

- Implement a more efficient way to handle very large files, such as processing in chunks with multi-threading or using parallel streams. 
 -If solution demands considering moving to cache, in-memory databases, or traditional databases to avoid memory issues, tuning memory settings, and increasing system compute power if needed.
- Add more detailed error handling and user-friendly messages.
- Provide options for different output formats as required.
- Utilize more patterns and functional programming techniques to evolve the solution as needed
- Implement more efficient I/O techniques for performance if needed
- Explore more efficient sorting algorithms for performance if needed

## Clean Code Principles

- The code follows separation of concerns by dividing the logic following SRP and one or two design patterns and testcases.
- The code is modular and easy to maintain with meaningful names and clear logic.


