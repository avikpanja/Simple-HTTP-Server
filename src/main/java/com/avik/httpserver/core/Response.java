package com.avik.httpserver.core;

public class Response {
	private String response = "";
	private byte [] byteResponse;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	public byte[] getByteResponse() {
		return byteResponse;
	}	
	
	public void setByteResponse(byte[] byteResponse) {
		this.byteResponse = byteResponse;
	}
}
