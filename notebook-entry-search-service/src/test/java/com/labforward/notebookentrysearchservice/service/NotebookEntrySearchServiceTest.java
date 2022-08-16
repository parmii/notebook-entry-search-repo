package com.labforward.notebookentrysearchservice.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.labforward.notebookentrysearchservice.NotebookEntrySearchServiceApp;
import com.labforward.notebookentrysearchservice.constants.Constants;
import com.labforward.notebookentrysearchservice.controller.response.Response;
import com.labforward.notebookentrysearchservice.exception.NotebookEntrySearchException;
import com.labforward.notebookentrysearchservice.repository.NotebookEntrySearchDao;
import com.labforward.notebookentrysearchservice.repository.NotebookEntrySearchDaoImpl;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NotebookEntrySearchServiceApp.class)
class NotebookEntrySearchServiceTest {

	@Autowired
	private NotebookEntrySearchService notebookEntrySearchService;

	NotebookEntrySearchDao notebookDao;

	@Mock
	Criteria criteria;

	@BeforeEach
	public void setUp() {
		List<String> similarWords = new ArrayList<>(List.of("Word"));

		try {
			notebookDao = Mockito.mock(NotebookEntrySearchDaoImpl.class);
			Mockito.when(notebookDao.getWordFrequencyInNotebookEntry(anyInt(), anyString())).thenReturn(0);
			Mockito.when(notebookDao.getWordFrequencyInNotebookEntry(2, "Word")).thenReturn(3);
			Mockito.when(notebookDao.getSimilarWordsInNotebookEntry(anyInt(), anyString())).thenReturn(similarWords);
		} catch (Exception e) {

		}
	}

	@Test
	void getWordFrequencyInNotebookEntryTest() {
		try {
			notebookEntrySearchService = new NotebookEntrySearchServiceImpl(notebookDao);
			Response responseForWordExists = notebookEntrySearchService.getWordFrequecny(2, "Word");
			Response responseForNoWord = notebookEntrySearchService.getWordFrequecny(anyInt(), anyString());
			Assert.assertEquals(Integer.parseInt(responseForWordExists.getData().toString()), 3);// This is based on
																									// data in data.sql
																									// stored in H2 DB
			Assert.assertEquals(Integer.parseInt(responseForNoWord.getData().toString()), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void getSimilarWordsInNotebookEntryest() {
		try {
			notebookEntrySearchService = new NotebookEntrySearchServiceImpl(notebookDao);
			Response response = notebookEntrySearchService.getSimilarWords(1, "word");
			Assert.assertEquals(response.getStatus(), 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testfailures() {
		try {
			notebookDao = Mockito.mock(NotebookEntrySearchDaoImpl.class);
			
			Mockito.when(notebookDao.getWordFrequencyInNotebookEntry(anyInt(), anyString())).thenReturn(0);
			Mockito.doThrow(new NotebookEntrySearchException(Constants.INTERNAL_SERVER_ERROR, null));

			Mockito.when(notebookDao.getSimilarWordsInNotebookEntry(anyInt(), anyString())).thenReturn(null);
			Mockito.doThrow(new NotebookEntrySearchException(Constants.INTERNAL_SERVER_ERROR, null));

			Response response = notebookEntrySearchService.getWordFrequecny(anyInt(), anyString());
			Assert.assertEquals(response.getStatus(), 500);

			Response responseSimilarWords = notebookEntrySearchService.getSimilarWords(anyInt(), anyString());
			Assert.assertEquals(responseSimilarWords.getStatus(), 500);
		} catch (Exception e) {
		}
	}
}