package it.italiangrid.wnodes.model;

public class Tag {

	private String name;
	private String architecture;
	private String os;
	private String osVersion;
	private String description;
	private String endorser;

	/**
	 * @param name
	 * @param architecture
	 * @param os
	 * @param osVersion
	 * @param description
	 * @param endorser
	 */
	public Tag(String name, String architecture, String os, String osVersion,
			String description, String endorser) {
		super();
		this.name = name;
		this.architecture = architecture;
		this.os = os;
		this.osVersion = osVersion;
		this.description = description;
		this.endorser = endorser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	
	@Override
	public String toString() {
		return "Tag [name=" + name + ", architecture=" + architecture + ", os="
				+ os + ", osVersion=" + osVersion + ", description="
				+ description + ", endorser=" + endorser + "]";
	}
}
