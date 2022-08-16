package com.labforward.notebookentrysearchservice.repository;

import static org.mockito.ArgumentMatchers.anyString;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.labforward.notebookentrysearchservice.dto.NotebookEntry;
import com.labforward.notebookentrysearchservice.util.WordCountUtility;
import com.labforward.notebookentrysearchservice.util.WordFindUtility;
import junit.framework.Assert;

@SpringBootTest
public class NotebookEntrySearchDaoTest {
	@Mock
	NotebookEntrySearchDao notebookEntrySearchDao;
	@Mock
	EntityManager entityManager;
	
	@Before
	void setup() throws Exception {
		notebookEntrySearchDao = new NotebookEntrySearchDaoImpl(entityManager);
		
		NotebookEntry notebookEntry = new NotebookEntry();
		notebookEntry.setId(2);
		notebookEntry.setEntryText("Word Word Lab Word");
		notebookEntry.setNotebookId(1);
		entityManager = Mockito.mock(EntityManager.class);
		Mockito.when(entityManager.find(NotebookEntry.class, 2)).thenReturn(notebookEntry);
		Mockito.when(WordCountUtility.getWordFrequency(anyString(), anyString())).thenReturn(0);
		Mockito.when(WordFindUtility.getSimilarWords(anyString(), anyString())).thenReturn(new ArrayList<String>());
		
	}
	
	@Test
	void getWordFrequencyInNotebookEntryTest() throws Exception {
		int frequency = notebookEntrySearchDao.getWordFrequencyInNotebookEntry(0, null);
		Assert.assertEquals(frequency, 0);
	}
	
	@Test
	void getSimilarWordsInNotebookEntryTest() throws Exception {
		List<String> similarWords = notebookEntrySearchDao.getSimilarWordsInNotebookEntry(0, null);
		Assert.assertEquals(similarWords.size(), 0);
	}
	
}
