package com.labforward.notebookentrysearchservice.controller.response;

/**
 * Uniform response pattern class for whole application
 * @author parmik
 *
 */
public class Response {
	private int status;
	private Object data;
	
	public Response(int status, Object data) {
		this.status = status;
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public Object getData() {
		return data;
	}
}
