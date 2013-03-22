package it.italiangrid.wnodes.model;

import java.util.ArrayList;
import java.util.List;

public class MarketPlace {
	
	private String name;
	private List<ResourceProvider> resourceProviders;
	private List<Tag> tags;

	/**
	 * @param tags
	 */
	public MarketPlace(String name, List<Tag> tags, List<ResourceProvider> resourceProviders) {
		this.name = name;
		this.tags = tags;
		this.resourceProviders = resourceProviders;
	}

	/**
	 * @param name
	 */
	public MarketPlace(String name) {
		this.name = name;
		this.tags = new ArrayList<Tag>();
		this.resourceProviders = new ArrayList<ResourceProvider>();
	}

	/**
	 * 
	 */
	public MarketPlace() {
		this.name = "";
		this.tags = new ArrayList<Tag>();
		this.resourceProviders = new ArrayList<ResourceProvider>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	/**
	 * @param tag add new tag at the market place
	 */

	public void addTag(Tag tag){
		tags.add(tag);
	}
	
	/**
	 * @param tag get tag from the market place
	 */
	public Tag getTag(int index){
		return tags.get(index);
	}
	
	/**
	 * @param tag get tag from the market place
	 */
	public Tag getTag(String name){
		
		for(Tag t: tags)
			if(t.getName().equals(name))
				return t;
		return null;
	}
	
	/**
	 * @return the tags
	 */
	public List<ResourceProvider> getResourceProviders() {
		return resourceProviders;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setResourceProviders(List<ResourceProvider> ResourceProviders) {
		this.resourceProviders = ResourceProviders;
	}
	
	/**
	 * @param tag add new tag at the market place
	 */

	public void addResourceProvider(ResourceProvider ResourceProvider){
		resourceProviders.add(ResourceProvider);
	}
	
	/**
	 * @param tag get tag from the market place
	 */
	public ResourceProvider getResourceProvider(int index){
		return resourceProviders.get(index);
	}
	
	/**
	 * @param tag get tag from the market place
	 */
	public ResourceProvider getResourceProvider(String name){
		
		for(ResourceProvider s: resourceProviders)
			if(s.getName().equals(name))
				return s;
		return null;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MarketPlace [name=" + name + ", ResourceProviders=" + resourceProviders + ", tags="
				+ tags + "]";
	}
}
