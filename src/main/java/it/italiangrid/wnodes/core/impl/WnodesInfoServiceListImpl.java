package it.italiangrid.wnodes.core.impl;

import java.util.ArrayList;
import java.util.List;

import it.italiangrid.wnodes.core.WnodesInfoService;

/**
 * The implementation, based on a List, of the service interface that define the
 * method for retrieve the information from WNoDeS used during the creation of a
 * new virtual machine. This class is used only for test.
 * 
 * @author dmichelotto
 * 
 */
public class WnodesInfoServiceListImpl implements WnodesInfoService {

	/**
	 * Return the list of the virtual machine images that are available.
	 * 
	 * @return Return the list of tags.
	 */
	public List<String> getTags() {
		List<String> result = new ArrayList<String>();

		result.add("wnodes_tw");
		result.add("wnodes_bo");

		return result;
	}

	/**
	 * Return the list of the virtual machine dimension that are available.
	 * 
	 * @return Return the list of sizes.
	 */
	public List<String> getSizes() {
		List<String> result = new ArrayList<String>();

		result.add("small");
		result.add("medium");
		result.add("big");

		return result;
	}

}
