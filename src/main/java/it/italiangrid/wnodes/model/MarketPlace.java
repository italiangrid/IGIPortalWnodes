package it.italiangrid.wnodes.model;

import java.util.ArrayList;
import java.util.List;

public class MarketPlace {
	
	private String name;
	private List<Size> sizes;
	private List<Tag> tags;

	/**
	 * @param tags
	 */
	public MarketPlace(String name, List<Tag> tags, List<Size> sizes) {
		this.name = name;
		this.tags = tags;
		this.sizes = sizes;
	}

	/**
	 * @param name
	 */
	public MarketPlace(String name) {
		this.name = name;
		this.tags = new ArrayList<Tag>();
		this.sizes = new ArrayList<Size>();
	}

	/**
	 * 
	 */
	public MarketPlace() {
		this.name = "";
		this.tags = new ArrayList<Tag>();
		this.sizes = new ArrayList<Size>();
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
	public List<Size> getSizes() {
		return sizes;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}
	
	/**
	 * @param tag add new tag at the market place
	 */

	public void addSize(Size size){
		sizes.add(size);
	}
	
	/**
	 * @param tag get tag from the market place
	 */
	public Size getSize(int index){
		return sizes.get(index);
	}
	
	/**
	 * @param tag get tag from the market place
	 */
	public Size getSize(String name){
		
		for(Size s: sizes)
			if(s.getName().equals(name))
				return s;
		return null;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MarketPlace [name=" + name + ", sizes=" + sizes + ", tags="
				+ tags + "]";
	}
}
