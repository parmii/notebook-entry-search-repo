package com.labforward.notebookentrysearchservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import com.labforward.notebookentrysearchservice.dto.NotebookEntry;
import com.labforward.notebookentrysearchservice.exception.NotebookEntrySearchException;
import com.labforward.notebookentrysearchservice.util.WordCountUtility;
import com.labforward.notebookentrysearchservice.util.WordFindUtility;

@Repository
public class NotebookEntrySearchDaoImpl implements NotebookEntrySearchDao {
	
	private EntityManager entityManager;

	public NotebookEntrySearchDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
	
	/**
	 * Get the frequency of the word in notebook entry
	 * @param entryId
	 * @param wordToBeCounted
	 * @return int
	 */

	public int getWordFrequencyInNotebookEntry(int entryId, String wordToBeCounted) throws NotebookEntrySearchException {
		NotebookEntry notebookEntry = entityManager.find(NotebookEntry.class, entryId);
		Optional<NotebookEntry> optionalNotebookEntry = Optional.ofNullable(notebookEntry);

		if (optionalNotebookEntry.isPresent()) {
			return WordCountUtility.getWordFrequency(wordToBeCounted, notebookEntry.getEntryText());
		}
		else {
			return 0;
		}

	}
	
	/**
	 * Gets list of similar words of word in notebook entry
	 * @param entryId
	 * @param wordToBeSearched
	 * t@return List<String>
	 */
	public List<String> getSimilarWordsInNotebookEntry(int entryId, String wordToBeSearched) throws NotebookEntrySearchException {
		NotebookEntry notebookEntry = entityManager.find(NotebookEntry.class, entryId);
		Optional<NotebookEntry> optionalNotebookEntry = Optional.ofNullable(notebookEntry);

		if (optionalNotebookEntry.isPresent()) {
			return WordFindUtility.getSimilarWords(wordToBeSearched, notebookEntry.getEntryText());
		}
		else {
			return new ArrayList<>();
		}
	}

}
