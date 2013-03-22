package it.italiangrid.wnodes.utils;

import it.italiangrid.wnodes.exception.WnodesPortletException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class WnodesConfig {
	
	private static final Logger log = Logger.getLogger(WnodesConfig.class);
	
	public static String getProperties(String key) throws WnodesPortletException{
		String contextPath = WnodesConfig.class.getClassLoader()
				.getResource("").getPath();
		File test = new File(contextPath + "/content/Wnodes.properties");
		log.info("File: " + test.getAbsolutePath());
		if (test.exists()) {
			log.info("ESISTE!!");
			try {
				FileInputStream inStream = new FileInputStream(contextPath
						+ "/content/Wnodes.properties");

				Properties prop = new Properties();
				
				prop.load(inStream);

				inStream.close();
				if (prop.getProperty(key) != null)
					return prop.getProperty(key);
				else
					throw new WnodesPortletException("properties-not-found");

			} catch (IOException e) {
				e.printStackTrace();
				throw new WnodesPortletException("properties-file-not-found");
			}
		}else{
			throw new WnodesPortletException("properties-file-not-found");
		}
	}
	
	public static void setProperties(String key, String value) throws WnodesPortletException{
		String contextPath = WnodesConfig.class.getClassLoader()
				.getResource("").getPath();
		File test = new File(contextPath + "/content/Wnodes.properties");
		log.info("File: " + test.getAbsolutePath());
		if (test.exists()) {
			log.info("ESISTE!!");
			try {
				FileInputStream inStream = new FileInputStream(contextPath
						+ "/content/Wnodes.properties");

				Properties prop = new Properties();

				prop.load(inStream);

				inStream.close();
				
				prop.setProperty(key, value);
				
				FileOutputStream outStream = new FileOutputStream(contextPath
						+ "/content/Wnodes.properties");
				
				prop.store(outStream, null);
				outStream.close();
				

			} catch (IOException e) {
				e.printStackTrace();
				throw new WnodesPortletException("properties-file-not-found");
			}
		}else{
			throw new WnodesPortletException("properties-file-not-found");
		}
	}

}
