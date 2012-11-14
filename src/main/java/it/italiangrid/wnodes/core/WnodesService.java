package it.italiangrid.wnodes.core;

import java.util.List;

import it.italiangrid.wnodes.model.VirtualMachine;

public interface WnodesService {
	
	public List<VirtualMachine>getVirtualMachines(long userId);
	
	public VirtualMachine getVirtualMachine(long userId, String uuid);
	
	public String createVirtualMachines(long userId, VirtualMachine vm);
	
	public boolean deleteVirtualMachines(long userId, String uuid);
	
}
