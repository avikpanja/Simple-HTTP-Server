package com.avik.httpserver.core;

public class Request {
	
	private String method = "";
	private String fileName = "";
	private String fileExt = "";
	
	Request() {
		this.fileName="welcome";
		this.fileExt = ".html";
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
}
