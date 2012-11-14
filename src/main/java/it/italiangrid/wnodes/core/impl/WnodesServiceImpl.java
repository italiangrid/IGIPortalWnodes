package it.italiangrid.wnodes.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.model.VirtualMachine;

public class WnodesServiceImpl implements WnodesService {
	
	private static final Logger log = LoggerFactory
			.getLogger(WnodesServiceImpl.class);
	
	public WnodesServiceImpl(){
		
	}

	public List<VirtualMachine> getVirtualMachines(long userId) {
		List<VirtualMachine> vms = new ArrayList<VirtualMachine>();
		
		log.info("Gettign all the user's virtual machines.");
		
		return vms;
	}

	public VirtualMachine getVirtualMachine(long userId, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createVirtualMachines(long userId, VirtualMachine vm) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteVirtualMachines(long userId, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

}
