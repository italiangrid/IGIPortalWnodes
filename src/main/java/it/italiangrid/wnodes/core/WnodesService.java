package it.italiangrid.wnodes.core;

import java.util.List;

import it.italiangrid.wnodes.model.VirtualMachine;
import it.italiangrid.wnodes.model.VirtualMachineCreation;

public interface WnodesService {

	public List<VirtualMachine> getVirtualMachines(long userId);

	public VirtualMachine getVirtualMachine(long userId, String uuid);

	public String createVirtualMachines(long userId, VirtualMachineCreation vm);

	public boolean deleteVirtualMachines(long userId, String uuid);

}
