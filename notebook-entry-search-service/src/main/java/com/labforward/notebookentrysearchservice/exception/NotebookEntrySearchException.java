package com.labforward.notebookentrysearchservice.exception;

/**
 * Custom Exception class for the application
 * @author parmik
 *
 */
public class NotebookEntrySearchException extends Exception {
	private static final long serialVersionUID = 16L;

	public NotebookEntrySearchException(String message, Throwable cause) {
		super(message, cause);
	}
}
