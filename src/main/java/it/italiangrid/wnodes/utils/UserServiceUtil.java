package it.italiangrid.wnodes.utils;

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
	 * 
	 * @return Return true if all is done or false if something went wrong.
	 */
	public boolean createSshKey();

}
