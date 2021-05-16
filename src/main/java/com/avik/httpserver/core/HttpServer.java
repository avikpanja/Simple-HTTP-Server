package com.avik.httpserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer extends Thread{
	
	private int port;
	
	public HttpServer(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(port);
			System.out.println("Server started listining on " + server.getLocalPort() + " port");
			while(true) {
				socket = server.accept();
				new HttpSocketHandler(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("************* Server Stopped **************");
	}
}
