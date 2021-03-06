package it.italiangrid.wnodes.core.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.italiangrid.wnodes.core.WnodesService;
import it.italiangrid.wnodes.model.VirtualMachine;
import it.italiangrid.wnodes.model.VirtualMachineCreation;

/**
 * The implementation, based on the WNoDeS CLI, of the service interface that define the CRUD methods for the WNoDeS
 * interaction.
 * 
 * @author dmichelotto
 * 
 */
public class WnodesServiceCLIImpl implements WnodesService {

	/**
	 * Logger of the class.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(WnodesServiceCLIImpl.class);

	/**
	 * The constructor of the class.
	 */
	public WnodesServiceCLIImpl() {

	}


	/**
	 * Get the list of the virtual machines instantiated by the user.
	 * Call the WNoDeS CLI command: wnodes_list_images
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @return Return the list of the virtual machines.
	 */
	public List<VirtualMachine> getVirtualMachines(long userId) {
		log.info("CLI Implementation");
		// TODO Auto-generated method stub
		/*
		 * command: wnodes_list_images 
		 * outputs:
		 * https://test-wnodes-web01.cnaf.infn
		 * .it:8443/resource/compute/99cfc95a-6095-4aaa-ad1a-2424a6880d91
		 * https:/
		 * /test-wnodes-web01.cnaf.infn.it:8443/resource/compute/99cfc95a-
		 * 6095-4aaa-ad1a-2424a6880d91
		 * https://test-wnodes-web01.cnaf.infn.it:8443
		 * /resource/compute/99cfc95a-6095-4aaa-ad1a-2424a6880d91
		 */

		try {
			String tomcatdir = System.getProperty("java.io.tmpdir");
			File dir = new File(tomcatdir + "/users/" + userId);
			String cmd = "/usr/bin/wnodes/cli/list_instances";
			log.info("Command {}.", cmd);

			List<VirtualMachine> vms = new ArrayList<VirtualMachine>();
			Process p = Runtime.getRuntime().exec(cmd, null, dir);

			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while (((line = output.readLine()) != null)) {

				log.error("[Stdout] " + line);
				if(line.contains("https://")){
					vms.add(getVirtualMachine(userId, line));
					log.error("added");
				}
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {

				log.error("[Stderr] " + line);
			}

			brCleanUp.close();

			return vms;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Get a specific virtual machine identified by the location identifier.
	 * Call the WNoDeS CLI command: wnodes_list_image
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @param uuid
	 *            - The virtual machine location identifier.
	 * @return Return an instance of the class VirtualMachine that contain the
	 *         virtual machine informations.
	 */
	public VirtualMachine getVirtualMachine(long userId, String uuid) {
		log.info("CLI Implementation");
		/*
		 * command: wnodes_list_image -l
		 * https://test-wnodes-web01.cnaf.infn.it:8443
		 * /resource/compute/99cfc95a-6095-4aaa-ad1a-2424a6880d91
		 * 
		 * outputs: Image Details
		 * 
		 * hostname status arch memory cores speed null ACTIVE x86_64 1.7 1 0.0
		 */

		try {
			String tomcatdir = System.getProperty("java.io.tmpdir");
			File dir = new File(tomcatdir + "/users/" + userId);
			String[] cmd = { "/usr/bin/wnodes/cli/show_instance", "-l",
					uuid };
			log.info("Command {}.", cmd[0] + " " + cmd[1] + " " + cmd[2]);

			VirtualMachine vm = new VirtualMachine();
			Process p = Runtime.getRuntime().exec(cmd[0] + " " + cmd[1] + " " + cmd[2], null, dir);

			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while (((line = output.readLine()) != null)) {

				log.info("[Stdout] " + line);

				if ((!line.contains("Instance Details"))
						&& (!line.contains("hostname")) && (!line.isEmpty())) {
					log.error(line);
					StringTokenizer st = new StringTokenizer(line);
					List<String> results = new ArrayList<String>();
					
					
					
					
					while (st.hasMoreTokens()) {
						results.add(st.nextToken());
						
					}
					for(String s : results)
						log.error("###### " +s);
					vm = new VirtualMachine(uuid, results.get(0),
							results.get(2), results.get(4), results.get(3),
							results.get(1), "0.0"); //"gridlab04.cnaf.infn.it" 
					log.info(vm.getSpeed());
					log.info("[Stdout] " + vm.toString());
				}

			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {

				log.error("[Stderr] " + line);
			}

			brCleanUp.close();

			return vm;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Create a new virtual machine with the information contained in a instance
	 * of the class VirtualMachineCreatio.
	 * Call the WNoDeS CLI command: wnodes_create_image
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
	public String createVirtualMachines(long userId, VirtualMachineCreation vm) {
		log.info("CLI Implementation");
		/*
		 * command:wnodes_create_image -t wnodes_tw
		 * 
		 * outputs:
		 * https://test-wnodes-web01.cnaf.infn.it:8443/resource/compute/
		 * 79afb073-d1b7-472c-900b-824ccef21ede
		 */

		try {
			String tomcatdir = System.getProperty("java.io.tmpdir");
			File dir = new File(tomcatdir + "/users/" + userId);
			String result = null;
			String[] cmd = { "/usr/bin/wnodes/cli/create_instance", "--imageid",
					vm.getTag()+","+vm.getIdentifier()+","+vm.getEndorser(), "--imagename", vm.getSize(), "--vo", vm.getVo() };
			log.error("Command {}.", cmd[0] + " " + cmd[1] + " " + cmd[2] + " "
					+ cmd[3] + " " + cmd[4] + " " + cmd[5] + " " + cmd[6]);	
			
//			String[] envp = {"PYTHONPATH=/var/lib/stratuslab/python/"};
			Map<String,String> sysEnv = System.getenv();
			
			Set<String> list  = sysEnv.keySet();
			Iterator<String> iter = list.iterator();
			String enviroment = "";			
			while(iter.hasNext()) {
			     String key = iter.next();
			     String value = sysEnv.get(key);
			     enviroment +="@@"+key+"="+value;
			}
			enviroment += "@@PYTHONPATH=/var/lib/stratuslab/python/";
			String[] envp = enviroment.split("@@");
			
			for(int i=0; i<envp.length; i++)
					log.error("ENVP: " + envp[i]);
			
			Process p = Runtime.getRuntime().exec(cmd, envp, dir);

			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while (((line = output.readLine()) != null)) {

				log.error("[Stdout] " + line);
				result = line;
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {

				log.error("[Stderr] " + line);
			}

			brCleanUp.close();

			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Delete a specific virtual machine identified by the location identifier.
	 * Call the WNoDeS CLI command: wnodes_delete_image
	 * 
	 * @param userId
	 *            - The user identifier.
	 * @param uuid
	 *            - The virtual machine location identifier.
	 * @return Return true if all is done or false if something went wrong.
	 */
	public boolean deleteVirtualMachines(long userId, String uuid) {
		log.info("CLI Implementation");
		/*
		 * command wnodes_delete_image -l
		 * https://test-wnodes-web01.cnaf.infn.it:
		 * 8443/resource/compute/99cfc95a-6095-4aaa-ad1a-2424a6880d91 
		 * 
		 * result: 
		 * if empty ok else some errors
		 */

		try {
			String tomcatdir = System.getProperty("java.io.tmpdir");
			File dir = new File(tomcatdir + "/users/" + userId);
			String result = null;
			String[] cmd = { "/usr/bin/wnodes/cli/delete_instance", "--locations="+uuid.trim() };
			log.error("Command {}.", cmd[0] + " " + cmd[1]);

			Process p = Runtime.getRuntime().exec(cmd, null, dir);

			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while (((line = output.readLine()) != null)) {

				log.error("[Stdout] " + line);
				result = line;
			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {

				log.error("[Stderr] " + line);
				result = line;
			}

			brCleanUp.close();
			if (result != null)
				return false;
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

}
