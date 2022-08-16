package com.labforward.notebookentrysearchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.labforward.notebookentrysearchservice.constants.Constants;

/**
 * Exception Handler Class for the application
 * @author parmik
 *
 */
@ControllerAdvice
public class NotebookEntrySearchExceptionHandler {
	@ExceptionHandler(value = NotebookEntrySearchException.class)
	public ResponseEntity<Object> exception(NotebookEntrySearchException exception) {
		return new ResponseEntity<>(Constants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
