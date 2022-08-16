package com.labforward.notebookentrysearchservice.repository;

import java.util.List;

import com.labforward.notebookentrysearchservice.exception.NotebookEntrySearchException;

public interface NotebookEntrySearchDao {
	public int getWordFrequencyInNotebookEntry(int entryId, String wordToBeCounted) throws NotebookEntrySearchException;
	public List<String> getSimilarWordsInNotebookEntry(int entryId, String wordToBeSearched) throws NotebookEntrySearchException;
}