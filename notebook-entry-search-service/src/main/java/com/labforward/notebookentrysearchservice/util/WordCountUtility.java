package com.labforward.notebookentrysearchservice.util;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import com.labforward.notebookentrysearchservice.constants.Constants;
import com.labforward.notebookentrysearchservice.exception.NotebookEntrySearchException;

/**
 * Utility class responsible for counting word frequency in different scenarios
 * 
 * @author parmik
 *
 */

public class WordCountUtility {
	private WordCountUtility() {
	}

	/**
	 * Gets the frequency of the word(textToBesearched) appeared in
	 * text(textToBesearched)
	 * @param wordToBeSearched
	 * @param textToBesearched
	 * @return int
	 */
	public static int getWordFrequency(String wordToBeSearched, String textToBesearched) {
		Optional<String> optionalText = Optional.ofNullable(textToBesearched);
		Optional<String> optionalWord = Optional.ofNullable(wordToBeSearched);
		int frequency = 0;

		if (optionalText.isPresent() && optionalWord.isPresent()) {
			frequency += Stream.of(textToBesearched.split("\\s+")).parallel()
					.filter(word -> word.equals(wordToBeSearched)).count();
		}

		return frequency;
	}

	/**
	 * Gets the frequency of word-wordToBeSearched This method is designed to search
	 * very large file
	 * @param wordToBeSearched
	 * @return int
	 * @throws Exception
	 */
	public static int getWordFrequecnyInFile(String wordToBeSearched) throws Exception {
		int frequency = 0;
		Optional<String> optionalWord = Optional.ofNullable(wordToBeSearched);
		URL url = WordCountUtility.class.getClassLoader().getResource("labforward_notebook_entry.txt");
		Path filePath = Paths.get(url.toURI());

		if (optionalWord.isEmpty()) {
			return 0;
		}
		
		try (Stream<String> lines = Files.lines(filePath)) {
			Stream<String> words = lines.parallel().flatMap(line -> Stream.of(line.split("\\W+")));
			frequency += words.filter(word -> word.equals(wordToBeSearched)).count();
		} catch (IOException ioException) {
			throw new NotebookEntrySearchException(Constants.IO_EXCEPTION, null);
		}
		return frequency;
	}
}
