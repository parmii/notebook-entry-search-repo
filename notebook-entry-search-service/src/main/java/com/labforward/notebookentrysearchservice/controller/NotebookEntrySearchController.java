package com.labforward.notebookentrysearchservice.controller;

import static com.labforward.notebookentrysearchservice.constants.Constants.GET_FREQUENCY_FILE_SUMMARY;
import static com.labforward.notebookentrysearchservice.constants.Constants.GET_FREQUENCY_SUMMARY;
import static com.labforward.notebookentrysearchservice.constants.Constants.GET_SIMILARWORDS_FILE_SUMMARY;
import static com.labforward.notebookentrysearchservice.constants.Constants.GET_SIMILARWORDS_SUMMARY;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.labforward.notebookentrysearchservice.controller.response.Response;
import com.labforward.notebookentrysearchservice.exception.NotebookEntrySearchException;
import com.labforward.notebookentrysearchservice.service.NotebookEntrySearchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Rest Controller for all the endpoints for notebook and its entries
 * 
 * @author parmik
 *
 */
@RestController
public class NotebookEntrySearchController {

	private NotebookEntrySearchService notebookService;

	public NotebookEntrySearchController(NotebookEntrySearchService notebookService) {
		this.notebookService = notebookService;
	}

	/**
	 * This method gives the frequency of the word in notebook entry
	 * 
	 * @param wordToBeCounted - word which needs to be counted
	 * @param entryID         - notebook entry ID which has to be searched for the
	 *                        word
	 * @return Response
	 */
	@Operation(summary = GET_FREQUENCY_SUMMARY)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class)) }) })
	@GetMapping(value = "/frequency")
	public ResponseEntity<Response> getWordFrequency(@RequestParam("word") String wordToBeCounted,
			@RequestParam("entryID") int entryID) throws NotebookEntrySearchException {
		Response response = notebookService.getWordFrequecny(entryID, wordToBeCounted);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method gives the list of similar words for the word in notebook entry
	 * 
	 * @param wordToBeCounted - word which needs to be searched
	 * @param entryID         - notebook entry ID which has to be searched for the
	 *                        word
	 * @return Response
	 */
	@Operation(summary = GET_SIMILARWORDS_SUMMARY)
	@GetMapping(value = "/similarwords")
	public ResponseEntity<Response> getSimilarWords(@RequestParam("word") String wordToBeCounted,
			@RequestParam("entryID") int entryID) throws NotebookEntrySearchException {
		Response response = notebookService.getSimilarWords(entryID, wordToBeCounted);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method gives the frequency of the word in notebook entry which is in the
	 * form of a big file
	 * 
	 * @param wordToBeCounted - word which needs to be counted
	 * @param entryID         - notebook entry ID which has to be searched for the
	 *                        word
	 * @return Response 
	 */
	@Operation(summary = GET_FREQUENCY_FILE_SUMMARY)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class)) }) })
	@GetMapping(value = "/file/frequency")
	public ResponseEntity<Response> getWordFrequency(@RequestParam("word") String wordToBeCounted) throws Exception {
		Response response = notebookService.getWordFrequecny(wordToBeCounted);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * This method gives the list of similar words for the word in notebook entry
	 * which is in the form of a big file
	 * 
	 * @param wordToBeCounted - word which needs to be searched
	 * @param entryID         - notebook entry ID which has to be searched for the
	 *                        word
	 * @return Response 
	 */
	@Operation(summary = GET_SIMILARWORDS_FILE_SUMMARY)
	@GetMapping(value = "/file/similarwords")
	public ResponseEntity<Response> getSimilarWords(@RequestParam("word") String wordToBeCounted) throws Exception {
		Response response = notebookService.getSimilarWords(wordToBeCounted);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}