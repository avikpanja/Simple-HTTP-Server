package com.avik.httpserver.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import com.avik.httpserver.constant.Constant;
import com.avik.httpserver.util.Util;



public class HttpSocketHandler extends Thread {
	
	private Socket socket;
	
	public HttpSocketHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			Request request = new Request();
			Response response = new Response();
			prepareRequestObjectFromInputStream(is,request);
			handleRequestReponse(request, response);
			os.write(response.getByteResponse());
		}catch(Exception e) {
			System.out.println("Exception occurred");
			e.printStackTrace();
		}
		finally {
			try {
				if(socket!=null);
					socket.close();
			} catch(Exception e) {
				System.out.println("Exception occured while closing the socket");
			}
		}
	}
	
	private void prepareRequestObjectFromInputStream(InputStream istream, Request request) throws IOException {
		//DataInputStream dis = new DataInputStream(istream);
		BufferedReader reader = new  BufferedReader(new InputStreamReader(istream));
		//String line = dis.readUTF();
		//String line = dis.readLine();
		String line = reader.readLine();
		String [] words = line.split(" ");
		/*while(!line.isEmpty() ) {
			System.out.println(line);
			line = dis.readUTF();
			//line = dis.readLine();
		}*/
		request.setMethod(words[0].trim());
		words = words[1].split("/");
		if(words.length>0) {
			words = words[1].split("\\.");
			request.setFileName(words[0]);
			request.setFileExt("."+words[1]);
		}
	}
	
	private void handleRequestReponse(Request request, Response response) throws Exception {
		byte [] byteResponse = getReponse(request);
		response.setByteResponse(byteResponse);	
	}
	
	private byte[] getReponse(Request req) throws Exception {
		BufferedInputStream bis = null;
		String temp = Constant.HEADER;
		//File file = new File(Util.getServerContext()+Constant.PATH+req.getFileName()+req.getFileExt());
		File file = new File(getClass().getClassLoader().getResource(Constant.PATH+req.getFileName()+req.getFileExt()).toURI());
		if(!file.exists()) {
			//file = new File(Util.getServerContext()+Constant.PATH+Constant.ERROR404);
			file = new File(getClass().getClassLoader().getResource(Constant.PATH+Constant.ERROR404).toURI());
		}
		/*
		 * if(req.getFileExt().equals(".jpg")) { temp = temp.replace("<content-type>",
		 * "image/jpeg"); } else { temp = temp.replace("<content-type>", "text/html"); }
		 */
		//temp = temp.replace("<content-length>", String.valueOf((int)file.length()));
		byte [] head = temp.getBytes();
		byte [] body = new byte[(int)file.length()];
		//byte [] body = new byte[1000000];
		byte [] response = new byte[head.length + body.length];
		try {
			System.out.println("File: "+ file.getPath());
			bis = new BufferedInputStream(new FileInputStream(file));
			bis.read(body);
			System.arraycopy(head, 0, response, 0, head.length);
			System.arraycopy(body, 0, response, head.length, body.length);
			return response;
		} catch(Exception e) {
			throw e;
		} finally {
			if(bis!=null)
				bis.close();
		}
	}

}
