package it.italiangrid.wnodes.model;

public class VirtualMachine {
	
	private String hostname;
	private String architecture;
	private String cores;
	private String memory;
	private String status;
	private String uuid;
	private String speed;

	/**
	 * @param uuid 
	 * @param hostname
	 * @param architecture
	 * @param cores
	 * @param memory
	 * @param status
	 */
	public VirtualMachine(String uuid, String hostname, String architecture, String cores,
			String memory, String status, String speed) {
		this.uuid = uuid;
		this.hostname = hostname;
		this.architecture = architecture;
		this.cores = cores;
		this.memory = memory;
		this.status = status;
		this.speed = speed;
	}
	
	public VirtualMachine(){
		
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String toString(){
		String results;
		results = "\nUuid:\t\t" + this.uuid; 
		results += "\nHostname:\t" + this.hostname;
		results += "\nArchitetture:\t" + this.architecture;
		results += "\nCores:\t\t" + this.cores;
		results += "\nMemory:\t\t" + this.memory + "GB";
		results += "\nSpeed:\t\t" + this.speed;
		results += "\nStatus:\t\t" + this.status.toUpperCase() + "\n\n";
		return results;
	}
	
}
