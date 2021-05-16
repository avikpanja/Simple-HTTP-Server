package com.avik.httpserver.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;

public class Util {
	
	private static final String serverContext; 
	
	static {
		String path = Util.class.getProtectionDomain().getCodeSource().getLocation().getFile();
    	if(path.contains(".jar")) {
    		path = path + "/";
    	}
		serverContext = path;
	}
	
	public static String getServerContext() {
		return serverContext;
	}
	
	public Properties readProperties(final String absPath, final String filename){
		Properties prop = new Properties();
		InputStream inStream = null;
		try {
			inStream = getClass().getClassLoader().getResourceAsStream(absPath+filename);
			prop.load(inStream);
			System.out.println(filename + " file loaded successfully");
		} catch (FileNotFoundException e) {
			System.out.println("File " + filename + " not found");
		} catch (IOException e) {
			System.out.println("Unable to load the file:" + filename);
		} finally {
			try {
				inStream.close();
			} catch (IOException e) {
				System.out.println("Exception occured while closing the file:" + filename);
			}
		}
		return prop;
	}
	
	public static JSONObject readJSON(final File file) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
			System.out.println(file.getName() + " file loaded successfully");
		} catch (FileNotFoundException e) {
			System.out.println("File " + file.getName() + " not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public JSONObject readJSON(final String absPath, final String filename) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		InputStream inStream = null;
		try {
			inStream = getClass().getClassLoader().getResourceAsStream(absPath+filename);
			Reader reader = new InputStreamReader(inStream);
			/*String path = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			Class cls = this.getClass();
			System.out.println("class: "+cls.getName());
			ClassLoader loader = cls.getClassLoader();
			System.out.println("classloader: "+loader.getClass().getName());
			URL url = loader.getResource(absPath+filename);
			System.out.println("url:"+url.getPath());
			File temp = new File(this.getClass().getClassLoader().getResource(absPath+filename).getPath());//.toURI());
			System.out.println("temp: "+ temp);
			File file = new File(temp.toString());//+ "/" + absPath + filename);
*/			jsonObject = (JSONObject) jsonParser.parse(reader);
			System.out.println(filename + " file loaded successfully");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} /*catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return jsonObject;
	}
	
	private String getJarFolder() {
	    String name = this.getClass().getName().replace('.', '/');
	    String s = this.getClass().getResource("/" + name + ".class").toString();
	    s = s.replace('/', File.separatorChar);
	    s = s.substring(0, s.indexOf(".jar")+4);
	    s = s.substring(s.lastIndexOf(':')-1);
	    return s.substring(0, s.lastIndexOf(File.separatorChar)+1);
	  } 

}
