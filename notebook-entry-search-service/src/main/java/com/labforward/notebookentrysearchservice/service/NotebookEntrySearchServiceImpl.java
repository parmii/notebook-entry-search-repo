package com.labforward.notebookentrysearchservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.labforward.notebookentrysearchservice.controller.response.Response;
import com.labforward.notebookentrysearchservice.exception.NotebookEntrySearchException;
import com.labforward.notebookentrysearchservice.repository.NotebookEntrySearchDao;
import com.labforward.notebookentrysearchservice.util.WordCountUtility;
import com.labforward.notebookentrysearchservice.util.WordFindUtility;

@Service
public class NotebookEntrySearchServiceImpl implements NotebookEntrySearchService {

	@Autowired
	private NotebookEntrySearchDao notebookDao;

	public NotebookEntrySearchServiceImpl(NotebookEntrySearchDao notebookDao) {
		this.notebookDao = notebookDao;
	}

	/**
	 * This method gets the frequency of word 
	 * @param entryId - ID of notebook entry
	 * @param wordToBeCounted - word to be looked up in the notebook entry
	 * @return Response
	 */
	public Response getWordFrequecny(int entryId, String wordToBeCounted) throws NotebookEntrySearchException {
		int frequency = notebookDao.getWordFrequencyInNotebookEntry(entryId, wordToBeCounted);
		return new Response(HttpStatus.OK.value(), frequency);
	}

	/**
	 * This method gets the similar words list
	 * @param entryId - ID of notebook entry
	 * @param wordToBeSearched - word for which similar words need to be found
	 * @return Response
	 */

	public Response getSimilarWords(int entryId, String wordToBeSearched) throws NotebookEntrySearchException {
		List<String> similarWords = notebookDao.getSimilarWordsInNotebookEntry(entryId, wordToBeSearched);
		return new Response(HttpStatus.OK.value(), similarWords);
	}

	/**
	 * This method gets the frequency of word present in large file entry
	 * @param wordToBeCounted - word to be looked up in the notebook entry
	 * @return Response
	 */
	@Override
	public Response getWordFrequecny(String wordToBeCounted) throws Exception {
		int frequency = WordCountUtility.getWordFrequecnyInFile(wordToBeCounted);
		return  new Response(HttpStatus.OK.value(), frequency);
	}

	/**
	 * This method gets the similar words lit and returns it in Response form It
	 * searches in a big file
	 * @param wordToBeSearched - word for which similar words need to be found
	 * @return Response
	 */
	@Override
	public Response getSimilarWords(String wordToBeSearched) throws Exception {
		List<String> similarWords = WordFindUtility.getSimilarWordinFile(wordToBeSearched);
		return new Response(HttpStatus.OK.value(), similarWords);
	}

}
