package com.labforward.notebookentrysearchservice.util;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.labforward.notebookentrysearchservice.constants.Constants;
import com.labforward.notebookentrysearchservice.exception.NotebookEntrySearchException;

/**
 * Utility class responsible for finding similar words in different scenarios
 * plus supportive methods
 * @author parmik
 */

public class WordFindUtility {

	private WordFindUtility() {
	}

	/**
	 * Finds similar words of word(wordToBeSearched) from text(textToBesearched)
	 * @param wordToBeSearched
	 * @param textToBesearched
	 * @return List<String>
	 */
	public static List<String> getSimilarWords(String wordToBeSearched, String textToBesearched) {
		List<String> similarWords = new ArrayList<>();
		Optional<String> optionalText = Optional.ofNullable(textToBesearched);
		Optional<String> optionalWord = Optional.ofNullable(wordToBeSearched);

		if (optionalText.isPresent() && optionalWord.isPresent()) {
			Map<String, Long> countWordMap = Stream.of(textToBesearched.split("\\s+")).parallel()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

			countWordMap.forEach((word, frequency) -> {
				if (!word.equals(wordToBeSearched) && getLevenshteinDistance(word, wordToBeSearched) <= 1) {
					similarWords.add(word);
				}
			});
		}

		return similarWords;
	}

	/**
	 * Finds similar words of word This method is designed to search large files
	 * @param wordToBeSearched
	 * @return List<String>
	 */
	public static List<String> getSimilarWordinFile(String wordToBeSearched) throws Exception {
		List<String> similarWords = new ArrayList<>();
		Optional<String> optionalWord = Optional.ofNullable(wordToBeSearched);
		URL url = WordCountUtility.class.getClassLoader().getResource("labforward_notebook_entry.txt");
		Path path = Paths.get(url.toURI());

		if (optionalWord.isEmpty()) {
			return similarWords;
		}

		try (Stream<String> lines = Files.lines(path)) {
			Map<String, Long> countWordMap = lines.parallel().flatMap(line -> Stream.of(line.split("\\W+")))
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			countWordMap.forEach((word, frequency) -> {
				if (!word.equals(wordToBeSearched) && getLevenshteinDistance(word, wordToBeSearched) <= 1) {
					similarWords.add(word);
				}
			});
		} catch (IOException ioException) {
			throw new NotebookEntrySearchException(Constants.IO_EXCEPTION, null);
		}

		return similarWords;
	}

	/**
	 * Calculates Levenshtein Distance between two words
	 * @param word1
	 * @param word2
	 * @return int
	 */
	private static int getLevenshteinDistance(String word1, String word2) {
		int[][] levDistanceArr = new int[word1.length() + 1][word2.length() + 1];

		for (int i = 0; i <= word1.length(); i++) {
			for (int j = 0; j <= word2.length(); j++) {
				if (i == 0) {
					levDistanceArr[i][j] = j;
				} else if (j == 0) {
					levDistanceArr[i][j] = i;
				} else {
					levDistanceArr[i][j] = getMinValue(
							levDistanceArr[i - 1][j - 1]
									+ numberOfSubstitution(word1.charAt(i - 1), word2.charAt(j - 1)),
							levDistanceArr[i - 1][j] + 1, levDistanceArr[i][j - 1] + 1);
				}
			}
		}

		return levDistanceArr[word1.length()][word2.length()];
	}

	/**
	 * Compares two characters
	 * @param a
	 * @param b
	 * @return 0 or 1
	 */
	public static int numberOfSubstitution(char a, char b) {
		return a == b ? 0 : 1;
	}

	/**
	 * Returns minimum value among numbers
	 * 
	 * @param numbers
	 * @return int
	 */
	public static int getMinValue(int... numbers) {
		return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
	}
}
