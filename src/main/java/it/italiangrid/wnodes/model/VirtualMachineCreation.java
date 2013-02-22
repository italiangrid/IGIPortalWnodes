package it.italiangrid.wnodes.model;

/**
 * The virtual machine creation model class that contain the information for the
 * creation of a new virtual machine.
 * 
 * @author dmichelotto
 * 
 */
public class VirtualMachineCreation {

	/**
	 * The selected image tag.
	 */
	private String tag;

	/**
	 * The selected image size.
	 */
	private String size;

	/**
	 * The selected image Virtual Organization.
	 */
	private String vo;
	
	private int qta;
	
	private String identifier;
	private String endorser;

	public int getQta() {
		return qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}

	/**
	 * Constructor of the class.
	 * 
	 * @param tag
	 *            - The tag.
	 * @param size
	 *            - The size.
	 * @param vo
	 *            - The VO.
	 */
	public VirtualMachineCreation(String tag, String size, String vo, String identifier, String endorser) {
		this.tag = tag;
		this.size = size;
		this.vo = vo;
		this.identifier = identifier;
		this.endorser = endorser;
	}

	/**
	 * The default contructor of the class
	 */
	public VirtualMachineCreation() {
	}

	/**
	 * Getter for the tag attribute.
	 * 
	 * @return Return the selected tag.
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Setter for the tag attribute.
	 * 
	 * @param tag
	 *            - The selected tag.
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Getter for the size attribute.
	 * 
	 * @return Return the selected size.
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Setter for the size attribute.
	 * 
	 * @param size
	 *            - The selected size.
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Getter for the vo attribute.
	 * 
	 * @return Return the selected VO.
	 */
	public String getVo() {
		return vo;
	}

	/**
	 * Setter for the vo attribute.
	 * 
	 * @param vo
	 *            - The selected VO.
	 */
	public void setVo(String vo) {
		this.vo = vo;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the endorser
	 */
	public String getEndorser() {
		return endorser;
	}

	/**
	 * @param endorser the endorser to set
	 */
	public void setEndorser(String endorser) {
		this.endorser = endorser;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VirtualMachineCreation [tag=" + tag + ", size=" + size
				+ ", vo=" + vo + ", qta=" + qta + ", identifier=" + identifier
				+ ", endorser=" + endorser + "]";
	}

}
