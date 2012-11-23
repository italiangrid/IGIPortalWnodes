package it.italiangrid.wnodes.model;

/**
 * The virtual machine model class that contain the information of a specific
 * virtual machine.
 * 
 * @author dmichelotto
 * 
 */
public class VirtualMachine {

	/**
	 * The hostname of the virtual machine.
	 */
	private String hostname;

	/**
	 * The architecture of the virtual machine.
	 */
	private String architecture;

	/**
	 * The number of core of the virtual machine.
	 */
	private String cores;

	/**
	 * The dimension of the RAM of the virtual machine.
	 */
	private String memory;

	/**
	 * The status of the virtual machine: ACTIVE or INACTIVE.
	 */
	private String status;

	/**
	 * The location unique identifier of the virtual machine.
	 */
	private String uuid;

	/**
	 * The clock speef of the core of the virtual machine.
	 */
	private String speed;

	/**
	 * Constructor of the class.
	 * 
	 * @param uuid
	 *            - The location unique identifier.
	 * @param hostname
	 *            - The hostname.
	 * @param architecture
	 *            - The architecture.
	 * @param cores
	 *            - The number of cores.
	 * @param memory
	 *            - The RAM
	 * @param status
	 *            - The status.
	 */
	public VirtualMachine(String uuid, String hostname, String architecture,
			String cores, String memory, String status, String speed) {
		this.uuid = uuid;
		this.hostname = hostname;
		this.architecture = architecture;
		this.cores = cores;
		this.memory = memory;
		this.status = status;
		this.speed = speed;
	}

	/**
	 * Default constructor.
	 */
	public VirtualMachine() {

	}

	/**
	 * Getter of hostname attribute.
	 * 
	 * @return Return the hostname.
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * Setter of hostname attribute.
	 * 
	 * @param hostname
	 *            - The hostname.
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * Getter of arctitecture attribute.
	 * 
	 * @return Return the arctitecture.
	 */
	public String getArchitecture() {
		return architecture;
	}

	/**
	 * Setter of arctitecture attribute.
	 * 
	 * @param arctitecture
	 *            - The arctitecture.
	 */
	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	/**
	 * Getter of core attribute.
	 * 
	 * @return Return the core.
	 */
	public String getCores() {
		return cores;
	}

	/**
	 * Setter of core attribute.
	 * 
	 * @param core
	 *            - The core.
	 */
	public void setCores(String cores) {
		this.cores = cores;
	}

	/**
	 * Getter of memory attribute.
	 * 
	 * @return Return the memory.
	 */
	public String getMemory() {
		return memory;
	}

	/**
	 * Setter of memory attribute.
	 * 
	 * @param memory
	 *            - The memory.
	 */
	public void setMemory(String memory) {
		this.memory = memory;
	}

	/**
	 * Getter of status attribute.
	 * 
	 * @return Return the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter of status attribute.
	 * 
	 * @param status
	 *            - The status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Getter of location unique identifier attribute.
	 * 
	 * @return Return the location unique identifier.
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Setter of location unique identifier attribute.
	 * 
	 * @param uuid - The location unique identifier.
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Getter of speed attribute.
	 * 
	 * @return Return the speed.
	 */
	public String getSpeed() {
		return speed;
	}

	/**
	 * Setter of speed attribute.
	 * 
	 * @param speed
	 *            - The speed.
	 */
	public void setSpeed(String speed) {
		this.speed = speed;
	}

	/**
	 * toString method override.
	 */
	public String toString() {
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
