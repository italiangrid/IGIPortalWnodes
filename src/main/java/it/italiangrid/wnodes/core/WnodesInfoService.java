package it.italiangrid.wnodes.core;

import it.italiangrid.wnodes.exception.WnodesPortletException;
import it.italiangrid.wnodes.model.MarketPlace;

import java.util.List;

/**
 * The service interface that define the method for retrieve the information
 * from WNoDeS used during the creation of a new virtual machine.
 * 
 * @author dmichelotto
 * 
 */

public interface WnodesInfoService {

	/**
	 * Return the list of the virtual machine images that are available.
	 * 
	 * @return Return the list of tags.
	 */
	public List<String> getTags();

	/**
	 * Return the list of the virtual machine dimension that are available.
	 * 
	 * @return Return the list of sizes.
	 */
	public List<String> getSizes();
	
	/**
	 * Return the list of market place with the relative image tags that are available. Call
	 * the WNoDeS CLI command: size_images_info
	 * 
	 * @return Return the list of market place.
	 * @throws WnodesPortletException 
	 */
	public MarketPlace getMarketPlaces() throws WnodesPortletException;

}
