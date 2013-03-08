package it.italiangrid.wnodes.utils;

import it.italiangrid.portal.dbapi.domain.UserInfo;
import it.italiangrid.wnodes.exception.WnodesPortletException;
import it.italiangrid.wnodes.model.KeyPair;

import java.io.IOException;
import java.util.List;

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
	 * @param userInfo 
	 * 
	 * @return Return true if all is done or false if something went wrong.
	 */
	public boolean createSshKey(UserInfo userInfo);
	
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
	public void storeKeys(KeyPair keyPair, UserInfo userInfo) throws IllegalStateException, IOException, WnodesPortletException;

	/**
	 * Delete ssh key pair.
	 */
	public void destroyKeyPair();

	public boolean sshKeyPubExistOnly();

	public boolean uploadKeys();
	
	public boolean downloadKeys(UserInfo userInfo);

}
