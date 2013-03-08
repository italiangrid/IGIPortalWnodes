package it.italiangrid.wnodes.utils.impl;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.wnodes.exception.WnodesPortletException;
import it.italiangrid.wnodes.model.KeyPair;
import it.italiangrid.wnodes.utils.UserServiceUtil;
import it.italiangrid.wnodes.utils.WnodesConfig;

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
	public boolean createSshKey(UserInfo userInfo) {
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		log.info("Directory = " + key);

		File keyFile = new File(key);

		if (!keyFile.exists()) {
			
			String password = userInfo.getPersistentId();
			
			String[] cmd = { "/usr/bin/ssh-keygen", "-t", "rsa", "-N", password,
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
	 * @throws WnodesPortletException 
	 */
	public void storeKeys(KeyPair keyPair, UserInfo userInfo) throws IllegalStateException, IOException, WnodesPortletException {
		
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		String keyPub = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa.pub";
		log.info("keyFile = " + key);
		log.info("keyPubFile = " + keyPub);
		
		CommonsMultipartFile keyMultipartFile = keyPair.getPrivateKey();
		CommonsMultipartFile keyPubMultipartFile = keyPair.getPublicKey();

		File keyFile = new File(key);
		File keyFilePub = new File(keyPub);
		
		if(!keyFile.exists()&&!keyFilePub.exists()){
			
			FileOutputStream fos = new FileOutputStream(keyFile);
			fos.write(keyMultipartFile.getBytes());
			fos.close();
			fos = new FileOutputStream(keyFilePub);
			fos.write(keyPubMultipartFile.getBytes());
			fos.close();
			
			Runtime.getRuntime().exec("chmod 600 "+key);
			
			String password = userInfo.getPersistentId();
			
			String[] cmd = { "/usr/bin/ssh-keygen", "-p", "-P", keyPair.getPassword(), "-N", password,
					"-f", key };
			
			Process p = Runtime.getRuntime().exec(cmd);
			printOutput(p.getInputStream(), p.getErrorStream());
			
			if(!uploadKeys())
				throw new WnodesPortletException("Proxy Problem");
			
			String[] cmd2 = { "/usr/bin/ssh-keygen", "-p", "-P", password, "-N", "",
					"-f", key };
			
			Process p2 = Runtime.getRuntime().exec(cmd2);
			printOutput(p2.getInputStream(), p2.getErrorStream());
				
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
		
		try {
			String[] cmd = {"/usr/bin/myproxy-destroy", "-s", WnodesConfig.getProperties("myproxy.server"), "-l", userId+"_ssh"};
			
			Process p = Runtime.getRuntime().exec(cmd);
			printOutput(p.getInputStream(), p.getErrorStream());
			
		} catch (WnodesPortletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean sshKeyPubExistOnly() {
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		String keyPub = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa.pub";
		log.info("keyFile = " + key);
		log.info("keyPubFile = " + keyPub);

		File keyFile = new File(key);
		File keyFilePub = new File(keyPub);
		
		if(!keyFile.exists()&&keyFilePub.exists())
			return true;
		return false;
	}

	public boolean uploadKeys() {
		boolean status = false;
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		String proxy = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/x509up";
		try {
			String[] cmd = {"/usr/bin/myproxy-store", "-s", WnodesConfig.getProperties("myproxy.server"), "-l", userId+"_ssh", "-y", key, "-c", proxy};
		
		
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();
			
			BufferedReader output = new BufferedReader(
					new InputStreamReader(stdout));
			String line = null;
			
			while ((line = output.readLine()) != null) {
				log.error("[Stdout] " + line);
				if(line.contains("Credentials saved to myproxy server.")){
					status = true;
				}
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(
					stderr));
			while ((line = brCleanUp.readLine()) != null) {

//				if (!line.contains("....")) {
					log.error("[Stderr] " + line);
//				}
			}
			
		} catch (IOException e) {
			status = false;
			e.printStackTrace();
		} catch (WnodesPortletException e) {
			status = false;
			e.printStackTrace();
		}

		return status;
	}

	public boolean downloadKeys(UserInfo userInfo) {
		boolean status = false;
		String key = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/id_rsa";
		String proxy = System.getProperty("java.io.tmpdir") + "/users/" + userId
				+ "/toDelete";
		try {
			String password = userInfo.getPersistentId();
			String[] cmd = {"/usr/bin/python", "/upload_files/myproxy-retrieve-sshkey.py", WnodesConfig.getProperties("myproxy.server"), userId+"_ssh", proxy, key, password};
		
		
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();
			
			BufferedReader output = new BufferedReader(
					new InputStreamReader(stdout));
			String line = null;
			
			while ((line = output.readLine()) != null) {
				log.error("[Stdout] " + line);
				if(line.contains("myproxy-retrieve success")){
					status = true;
				}
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(new InputStreamReader(
					stderr));
			while ((line = brCleanUp.readLine()) != null) {

				if (!line.contains("....")) {
					log.error("[Stderr] " + line);
				}
			}
			
			File toDelete = new File(proxy);
			if(toDelete.exists())
				toDelete.delete();
			
		} catch (IOException e) {
			status = false;
			e.printStackTrace();
		} catch (WnodesPortletException e) {
			status = false;
			e.printStackTrace();
		}

		return status;
	}
}
