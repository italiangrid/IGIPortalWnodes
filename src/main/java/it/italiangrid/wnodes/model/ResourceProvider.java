package it.italiangrid.wnodes.model;

import java.util.List;

public class ResourceProvider {

	/**
	 * Name of resource provider
	 */
	private String name;
	/**
	 * Instance sizes of relative Resource Provider
	 */
	private List<Size> sizes;
	/**
	 * @param name
	 * @param sizes
	 */
	public ResourceProvider(String name, List<Size> sizes) {
		super();
		this.name = name;
		this.sizes = sizes;
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
	 * @return the sizes
	 */
	public List<Size> getSizes() {
		return sizes;
	}
	/**
	 * @param sizes the sizes to set
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
		return "ResourceProvider [name=" + name + ", sizes=" + sizes + "]";
	}
	
}
