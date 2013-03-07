package it.italiangrid.wnodes.utils.impl;

import it.italiangrid.wnodes.utils.UserServiceUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * The implementation of the util interface that define some utility method.
 * 
 * @author dmichelotto
 * 
 */
public class UserServiceUtilImpl implements UserServiceUtil {

	/**
	 * Logger of the class.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(UserServiceUtilImpl.class);

	/**
	 * The user identifier.
	 */
	private long userId = 0;

	/**
	 * Constructor of the class.
	 * 
	 * @param userId
	 *            - The user identifier.
	 */
	public UserServiceUtilImpl(long userId) {
		this.userId = userId;
	}

	/**
	 * Get the list of the proxies downloaded by the user.
	 * 
	 * @return Return the list of the proxies.
	 */
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
	
	/**
	 * Check if the key pair was already created/uploaded.
	 * @return true if the key pair was already created/uploaded, false otherwise.
	 */
	public boolean sshKeysExist(){
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		String keyPub = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa.pub";
		log.info("keyFile = " + key);
		log.info("keyPubFile = " + keyPub);

		File keyFile = new File(key);
		File keyFilePub = new File(keyPub);
		
		if(keyFile.exists()&&keyFilePub.exists())
			return true;
		return false;
		
	}

	/**
	 * Create key pair keys for ssh access.
	 */
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

	/**
	 * Create a ssh key pair for the virtual machine login without password.
	 * 
	 * @return Return true if all is done or false if something went wrong.
	 */
	private void printOutput(InputStream stdout, InputStream stderr)
			throws IOException {

		BufferedReader output = new BufferedReader(
				new InputStreamReader(stdout));
		String line = null;

		while ((line = output.readLine()) != null) {
			log.error("[Stdout] " + line);
		}
		output.close();

		BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(
				stderr));
		while ((line = brCleanUp.readLine()) != null) {

			if (!line.contains("....")) {
				log.error("[Stderr] " + line);
			}
		}

	}

	/**
	 * Store in the user folder the key pair.
	 * 
	 * @param keyMultipartFile - private key
	 * @param keyPubMultipartFile - public key
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void storeKeys(CommonsMultipartFile keyMultipartFile,
			CommonsMultipartFile keyPubMultipartFile) throws IllegalStateException, IOException {
		
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		String keyPub = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa.pub";
		log.info("keyFile = " + key);
		log.info("keyPubFile = " + keyPub);

		File keyFile = new File(key);
		File keyFilePub = new File(keyPub);
		
		if(!keyFile.exists()&&!keyFilePub.exists()){
			
//			keyMultipartFile.transferTo(keyFile);
//			keyPubMultipartFile.transferTo(keyFilePub);
			
			FileOutputStream fos = new FileOutputStream(keyFile);
			fos.write(keyMultipartFile.getBytes());
			fos.close();
			fos = new FileOutputStream(keyFilePub);
			fos.write(keyPubMultipartFile.getBytes());
			fos.close();
			
			Runtime.getRuntime().exec("chmod 600 "+key);
			
		}
		
	}

	/**
	 * Delete ssh key pair.
	 */
	public void destroyKeyPair() {
		// TODO Auto-generated method stub
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		String keyPub = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa.pub";
		log.info("keyFile = " + key);
		log.info("keyPubFile = " + keyPub);

		File keyFile = new File(key);
		File keyFilePub = new File(keyPub);
		
		if(keyFile.exists())
			keyFile.delete();
		
		if(keyFilePub.exists())
			keyFilePub.delete();
	}

}
