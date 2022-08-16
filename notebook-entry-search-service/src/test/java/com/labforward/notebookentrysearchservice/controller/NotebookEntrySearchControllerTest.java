package com.labforward.notebookentrysearchservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.labforward.notebookentrysearchservice.NotebookEntrySearchServiceApp;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NotebookEntrySearchServiceApp.class)
@AutoConfigureMockMvc

class NotebookEntrySearchControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void getWordFrequencyTest() throws Exception {
		this.mockMvc.perform(get("/frequency").queryParam("word", "word").queryParam("entryID", "1")).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	void getSimilarWordsTest() throws Exception {
		this.mockMvc.perform(get("/similarwords").queryParam("word", "word").queryParam("entryID", "1")).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	void getWordFrequencyFileTest() throws Exception {
		this.mockMvc.perform(get("/file/frequency").queryParam("word", "word")).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	void getSimilarWordsFileTest() throws Exception {
		this.mockMvc.perform(get("/file/similarwords").queryParam("word", "word")).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}
}