package com.avik.httpserver.constant;

public interface Constant {
	String HEADER = "HTTP/1.1 "
			+ "\naccept-ranges: bytes"
			+ "\ncontent-type: <content-type>"
			//+ "\ncontent-length: <content-length>"
			+ "\r\n\r\n";
	
	String ERROR404 =  "error404.html";
	String PATH = "static/";
}
