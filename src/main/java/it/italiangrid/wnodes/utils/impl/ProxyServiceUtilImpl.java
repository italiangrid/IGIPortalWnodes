package it.italiangrid.wnodes.utils.impl;

import it.italiangrid.wnodes.utils.ProxyServiceUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyServiceUtilImpl implements ProxyServiceUtil {
	
	private static final Logger log = LoggerFactory
			.getLogger(ProxyServiceUtilImpl.class);

	public List<String> getActiveProxy(long userId) {
		
		String dir = System.getProperty("java.io.tmpdir")+"/users/"+userId+"/";
		log.info("Directory = " + dir);
		
		File userDir = new File(dir);
		
		String[] files = userDir.list();
		
		List<String> proxies = new ArrayList<String>();
		
		for (String file : files) {
			if(file.contains("x509up."))
				proxies.add(file.replace("x509up.", ""));
		}
		
		return proxies;
	}

}
