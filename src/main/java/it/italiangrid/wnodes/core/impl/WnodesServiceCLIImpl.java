package it.italiangrid.wnodes.core.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.model.VirtualMachine;
import it.italiangrid.wnodes.model.VirtualMachineCreation;

public class WnodesServiceCLIImpl implements WnodesService {

	private static final Logger log = LoggerFactory
			.getLogger(WnodesServiceCLIImpl.class);
	
	public WnodesServiceCLIImpl(){
		
	}

	public List<VirtualMachine> getVirtualMachines(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public VirtualMachine getVirtualMachine(long userId, String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createVirtualMachines(long userId, VirtualMachineCreation vm) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteVirtualMachines(long userId, String uuid) {
		// TODO Auto-generated method stub
		return false;
	}

}
