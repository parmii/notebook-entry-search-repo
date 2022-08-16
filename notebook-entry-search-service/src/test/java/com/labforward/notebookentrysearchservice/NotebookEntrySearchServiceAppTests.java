package com.labforward.notebookentrysearchservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.labforward.notebookentrysearchservice.controller.NotebookEntrySearchController;
import com.labforward.notebookentrysearchservice.service.NotebookEntrySearchService;
import com.labforward.notebookentrysearchservice.service.NotebookEntrySearchServiceImpl;

import junit.framework.Assert;

@SpringBootTest
class NotebookEntrySearchServiceAppTests {

	@Autowired
	BeanFactory beanFactory;

	@Test
	void contextLoads() {
		NotebookEntrySearchService notebookEntrySearchService = beanFactory.getBean(NotebookEntrySearchService.class);
		Assert.assertTrue(notebookEntrySearchService instanceof NotebookEntrySearchServiceImpl);

		NotebookEntrySearchController notebookEntrySearchController = beanFactory
				.getBean(NotebookEntrySearchController.class);
		Assert.assertNotNull(notebookEntrySearchController);
	}
}
