package com.labforward.notebookentrysearchservice.service;

import com.labforward.notebookentrysearchservice.controller.response.Response;
import com.labforward.notebookentrysearchservice.exception.NotebookEntrySearchException;

public interface NotebookEntrySearchService {
	public Response getWordFrequecny(int entryId, String wordToBeCounted) throws NotebookEntrySearchException;
	public Response getSimilarWords(int entryId, String wordToBeSearched) throws NotebookEntrySearchException;
	public Response getWordFrequecny(String wordToBeCounted) throws Exception;
	public Response getSimilarWords(String wordToBeSearched) throws Exception;
}