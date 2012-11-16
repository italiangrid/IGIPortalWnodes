package it.italiangrid.wnodes.core.impl;

import java.util.ArrayList;
import java.util.List;

import it.italiangrid.wnodes.core.WnodesInfoService;

public class WnodesInfoServiceListImpl implements WnodesInfoService {


	public List<String> getTags() {
		List<String> result = new ArrayList<String>();
		
		result.add("wnodes_tw");
		result.add("wnodes_bo");
		
		return result;
	}

	public List<String> getSizes() {
		List<String> result = new ArrayList<String>();
		
		result.add("small");
		result.add("medium");
		result.add("big");
		
		return result;
	}

}
