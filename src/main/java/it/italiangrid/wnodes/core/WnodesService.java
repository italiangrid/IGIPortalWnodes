package it.italiangrid.wnodes.core;

import java.util.List;

import it.italiangrid.wnodes.model.VirtualMachine;
import it.italiangrid.wnodes.model.VirtualMachineCreation;

/**
 * The service interface that define the CRUD methods for the WNoDeS
 * interaction.
 * 
 * @author dmichelotto
 * 
 */
public interface WnodesService {

	/**
	 * Get the list of the virtual machines instantiated by the user.
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @return Return the list of the virtual machines.
	 */
	public List<VirtualMachine> getVirtualMachines(long userId);

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
	public VirtualMachine getVirtualMachine(long userId, String uuid);

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
	public String createVirtualMachines(long userId, VirtualMachineCreation vm);

	/**
	 * Delete a specific virtual machine identified by the location identifier.
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @param uuid
	 *            - The virtual machine location identifier.
	 * @return Return true if all is done or false if something went wrong.
	 */
	public boolean deleteVirtualMachines(long userId, String uuid);

}
