package it.italiangrid.wnodes.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * The util interface that define some utility method.
 * 
 * @author dmichelotto
 * 
 */
public interface UserServiceUtil {

	/**
	 * Get the list of the proxies downloaded by the user.
	 * 
	 * @return Return the list of the proxies.
	 */
	public List<String> getActiveProxy();

	/**
	 * Create a ssh key pair for the virtual machine login without password.
	 * 
	 * @return Return true if all is done or false if something went wrong.
	 */
	public boolean createSshKey();
	
	/**
	 * Check if the key pair was already created/uploaded.
	 * 
	 * @return true if the key pair was already created/uploaded, false otherwise.
	 */
	public boolean sshKeysExist();

	/**
	 * Store in the user folder the key pair.
	 * 
	 * @param keyMultipartFile - private key
	 * @param keyPubMultipartFile - public key
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void storeKeys(CommonsMultipartFile keyMultipartFile,
			CommonsMultipartFile keyPubMultipartFile) throws IllegalStateException, IOException;

	/**
	 * Delete ssh key pair.
	 */
	public void destroyKeyPair();

}
