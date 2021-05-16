package com.avik.httpserver.config;

import java.io.File;
import java.util.Properties;

import org.json.simple.JSONObject;

import com.avik.httpserver.util.Util;

public class ServerConfigManager {
	
	//private static final String SERVER_CONFIG_PATH = "src/main/resources/config"; 
	private static final String SERVER_CONFIG_PATH = "config/";
	private static final String FILENAME = "configProp.properties";
	
	private static ServerConfigManager serverConfigMgr;
	private ServerConfig serverConfig;
	
	private ServerConfigManager() throws InstantiationException {
		if(serverConfigMgr!=null)
		 throw new InstantiationException("Unable to create new instance");
		serverConfig = new ServerConfig();
	}
	
	public ServerConfig getConfiguration() {
		return this.serverConfig;
	}
	
	
	public static ServerConfigManager getInstance() {
		if(serverConfigMgr==null) { 
			try {
				serverConfigMgr = new ServerConfigManager();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			serverConfigMgr.populateServerConfigObject();
		}
		return serverConfigMgr;
	}
	
	private void populateServerConfigObject() {
		Properties prop = null;
		JSONObject json = null;
		String configFile;
		prop = new Util().readProperties(SERVER_CONFIG_PATH, FILENAME);
		if(!prop.isEmpty()) {
			configFile = prop.getProperty("server.config");
			if(configFile!=null)  {
				json = new Util().readJSON(SERVER_CONFIG_PATH, configFile);
				if(json!=null) {
					json = (JSONObject)json.get("base");
					serverConfig.setPort(Integer.parseInt((String)json.get("port")));
					serverConfig.setRoot((String)json.get("webroot"));
				}
			}
		}
	}
	
}
