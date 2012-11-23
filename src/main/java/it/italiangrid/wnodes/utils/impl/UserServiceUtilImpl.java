package it.italiangrid.wnodes.utils.impl;

import it.italiangrid.wnodes.utils.UserServiceUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceUtilImpl implements UserServiceUtil {

	private static final Logger log = LoggerFactory
			.getLogger(UserServiceUtilImpl.class);

	private long userId = 0;

	public UserServiceUtilImpl(long userId) {
		this.userId = userId;
	}

	public List<String> getActiveProxy() {

		String dir = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/";
		log.info("Directory = " + dir);

		File userDir = new File(dir);

		String[] files = userDir.list();

		List<String> proxies = new ArrayList<String>();

		for (String file : files) {
			if (file.contains("x509up."))
				proxies.add(file.replace("x509up.", ""));
		}

		return proxies;
	}

	public boolean createSshKey() {
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		log.info("Directory = " + key);

		File keyFile = new File(key);

		if (!keyFile.exists()) {
			String[] cmd = { "/usr/bin/ssh-keygen", "-t", "rsa", "-N", "",
					"-f", key };
			try {
				Process p = Runtime.getRuntime().exec(cmd);

				printOutput(p.getInputStream(), p.getErrorStream());

				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	private void printOutput(InputStream stdout, InputStream stderr)
			throws IOException {

		BufferedReader output = new BufferedReader(
				new InputStreamReader(stdout));
		String line = null;

		while ((line = output.readLine()) != null) {
			log.info("[Stdout] " + line);
		}
		output.close();

		BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(
				stderr));
		while ((line = brCleanUp.readLine()) != null) {

			if (!line.contains("....")) {
				log.info("[Stderr] " + line);
			}
		}

	}

}
