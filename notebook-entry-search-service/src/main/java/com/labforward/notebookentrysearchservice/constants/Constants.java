package com.labforward.notebookentrysearchservice.constants;

public class Constants {
	private Constants() {}
	public static final String INTERNAL_SERVER_ERROR="Unable to complete your request.";
	public static final String GET_NOTEBOOKS_SUMMARY="This method gives the list of notbooks present in the database.";
	public static final String GET_FREQUENCY_SUMMARY="This method returns count of word occured in the given notebook entry.";
	public static final String GET_SIMILARWORDS_SUMMARY="This method returns the list of similar words occured in the given notebook entry.";
	public static final String GET_FREQUENCY_FILE_SUMMARY="This method returns count of word occured in the given notebook entry which is a big file.";
	public static final String GET_SIMILARWORDS_FILE_SUMMARY="This method returns the list of similar words occured in the given notebook entry which is a big file.";
	public static final String IO_EXCEPTION="Input/output operation error.";
}
