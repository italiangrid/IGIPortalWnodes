package it.italiangrid.wnodes.model;

public class Image {

	private String name;
	private String architecture;
	private String os;
	private String osVersion;
	private String description;
	private String endorser;
	private String identifier;
	private String resourceProvider;
	private String platform;

	/**
	 * @param name
	 * @param architecture
	 * @param os
	 * @param osVersion
	 * @param description
	 * @param endorser
	 */
	public Image(String name, String architecture, String os, String osVersion,
			String description, String endorser, String identifier, String resourceProvider, String platform) {
		super();
		this.name = name;
		this.architecture = architecture;
		this.os = os;
		this.osVersion = osVersion;
		this.description = description;
		this.endorser = endorser;
		this.identifier = identifier;
		this.resourceProvider = resourceProvider;
		this.platform = platform;
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
	 * @return the resourceProvider
	 */
	public String getResourceProvider() {
		return resourceProvider;
	}

	/**
	 * @param resourceProvider the resourceProvider to set
	 */
	public void setResourceProvider(String resourceProvider) {
		this.resourceProvider = resourceProvider;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Image [name=" + name + ", architecture=" + architecture
				+ ", os=" + os + ", osVersion=" + osVersion + ", description="
				+ description + ", endorser=" + endorser + ", identifier="
				+ identifier + ", resourceProvider=" + resourceProvider
				+ ", platform=" + platform + "]";
	}
}
