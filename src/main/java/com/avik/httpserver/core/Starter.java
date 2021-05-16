package com.avik.httpserver.core;

import com.avik.httpserver.config.ServerConfig;
import com.avik.httpserver.config.ServerConfigManager;

public final class Starter  {
	
    public static void main( String[] args ) {
		  ServerConfig serConfig = ServerConfigManager.getInstance().getConfiguration(); 
		  HttpServer httpServer = new HttpServer(serConfig.getPort()); 
		  httpServer.start();
		 
    }
}
