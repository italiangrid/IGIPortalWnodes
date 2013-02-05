package it.italiangrid.wnodes.model;

public class Size {

	private String name;
	private String cores;
	private String memory;
	private String disk;

	/**
	 * @param name
	 * @param cores
	 * @param memory
	 * @param disk
	 */
	public Size(String name, String cores, String memory, String disk) {
		this.name = name;
		this.cores = cores;
		this.memory = memory;
		this.disk = disk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCores() {
		return cores;
	}

	public void setCores(String cores) {
		this.cores = cores;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	@Override
	public String toString() {
		return "Size [name=" + name + ", cores=" + cores + ", memory=" + memory + ", disk=" + disk + "]";
	}
}
