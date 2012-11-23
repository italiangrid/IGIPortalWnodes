package it.italiangrid.wnodes.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.model.VirtualMachine;
import it.italiangrid.wnodes.model.VirtualMachineCreation;

/**
 * The implementation, based on a List, of the service interface that define the
 * CRUD methods for the WNoDeS interaction. This class is used only for test.
 * 
 * @author dmichelotto
 * 
 */
public class WnodesServiceListImpl implements WnodesService {

	/**
	 * Logger of the class.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(WnodesServiceListImpl.class);

	/**
	 * The list that contain all the created virtual machine.
	 */
	private static List<VirtualMachine> vms = new ArrayList<VirtualMachine>();

	/**
	 * A counter used for the creation of the hostname
	 */
	private static int hostnameCount = 2;

	/**
	 * The constructor of the class.
	 */
	public WnodesServiceListImpl() {

	}

	/**
	 * Get the list of the virtual machines instantiated by the user.
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @return Return the list of the virtual machines.
	 */
	public List<VirtualMachine> getVirtualMachines(long userId) {

		log.info("Gettign all the user's virtual machines.");

		return vms;
	}

	/**
	 * Get a specific virtual machine identified by the location identifier.
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @param uuid
	 *            - The virtual machine location identifier.
	 * @return Return an instance of the class VirtualMachine that contain the
	 *         virtual machine informations.
	 */
	public VirtualMachine getVirtualMachine(long userId, String uuid) {
		log.info("Gettign specific user's virtual machines.");

		for (VirtualMachine vm : vms) {
			if (vm.getUuid().equals(uuid)) {
				return vm;
			}
		}

		return null;
	}

	/**
	 * Create a new virtual machine with the information contained in a instance
	 * of the class VirtualMachineCreatio.
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @param vm
	 *            - The instance of the class VirtualMachineCreatio that contain
	 *            the selected tag, size and VO.
	 * @return Return a string with the virtual machine identified if all is
	 *         done, otherwise return null if there was an exception or the
	 *         error message if something went wrong.
	 */
	public String createVirtualMachines(long userId, VirtualMachineCreation vmc) {
		log.info("Create new virtual machines.");

		String uuid = UUID.randomUUID().toString();

		VirtualMachine vm = new VirtualMachine(uuid, getHostname(),
				getArchFromVmc(vmc), getSizeFromVmc(vmc), getSizeFromVmc(vmc),
				"ACTIVE", "1.2");

		vms.add(vm);
		return uuid;
	}

	/**
	 * Delete a specific virtual machine identified by the location identifier.
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @param uuid
	 *            - The virtual machine location identifier.
	 * @return Return true if all is done or false if something went wrong.
	 */
	public boolean deleteVirtualMachines(long userId, String uuid) {

		log.info("Deleting specific user's virtual machines.");

		for (VirtualMachine vm : vms) {
			if (vm.getUuid().equals(uuid)) {
				vms.remove(vm);
				return true;
			}
		}

		return false;
	}

	private String getHostname() {
		// String hostname = "wnodesvm" + hostnameCount + ".cnaf.infn.it";
		hostnameCount = hostnameCount == 2 ? 4 : 2;
		// return hostname;
		return "gridlab0" + hostnameCount + ".cnaf.infn.it";
		// return null;
	}

	private String getSizeFromVmc(VirtualMachineCreation vmc) {

		List<String> sizes = new ArrayList<String>();

		sizes.add("small");
		sizes.add("medium");
		sizes.add("big");

		switch (sizes.indexOf(vmc.getSize())) {
		case 0:
			return "1";
		case 1:
			return "2";
		case 2:
			return "4";
		}
		return null;
	}

	private String getArchFromVmc(VirtualMachineCreation vmc) {

		List<String> tags = new ArrayList<String>();

		tags.add("wnodes_tw");
		tags.add("wnodes_bo");

		switch (tags.indexOf(vmc.getTag())) {
		case 0:
			return "x86_64";
		case 1:
			return "i386";
		}
		return null;
	}

}
