package it.italiangrid.wnodes.model;

public class VirtualMachineCreation {

	private String tag;
	private String size;
	private String vo;
	/**
	 * @param tag
	 * @param size
	 */
	public VirtualMachineCreation(String tag, String size, String vo) {
		this.tag = tag;
		this.size = size;
		this.vo = vo;
	}
	
	/**
	 * 
	 */
	public VirtualMachineCreation() {
	}

	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}

	public String getVo() {
		return vo;
	}
	
	public void setVo(String vo) {
		this.vo = vo;
	}
	
}
