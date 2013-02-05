package it.italiangrid.wnodes.model;

public class Tag {

	private String name;
	private String architecture;

	/**
	 * @param name
	 * @param architecture

	 */
	public Tag(String name, String architecture) {
		this.name = name;
		this.architecture = architecture;
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

	@Override
	public String toString() {
		return "Tag [name=" + name + ", architecture=" + architecture + "]";
	}
}
