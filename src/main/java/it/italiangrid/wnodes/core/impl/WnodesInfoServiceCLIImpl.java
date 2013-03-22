package it.italiangrid.wnodes.core.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.italiangrid.wnodes.core.WnodesInfoService;
import it.italiangrid.wnodes.exception.WnodesPortletException;
import it.italiangrid.wnodes.model.MarketPlace;
import it.italiangrid.wnodes.model.ResourceProvider;
import it.italiangrid.wnodes.model.Size;
import it.italiangrid.wnodes.model.Tag;

/**
 * The implementation, based on the WNoDeS CLI, of the service interface that
 * define the method for retrieve the information from WNoDeS used during the
 * creation of a new virtual machine.
 * 
 * @author dmichelotto
 * 
 */

public class WnodesInfoServiceCLIImpl implements WnodesInfoService {

	/**
	 * Logger of the class.
	 */
	private static final Logger log = LoggerFactory
			.getLogger(WnodesServiceCLIImpl.class);

	/**
	 * The user identifier.
	 */
	private String user = "";

	/**
	 * The constructor of the class.
	 * 
	 * @param user
	 *            - The user identifier.
	 */
	public WnodesInfoServiceCLIImpl(String user) {
		this.user = user;
	}

	/**
	 * Return the list of the virtual machine images that are available. Call
	 * the WNoDeS CLI command: wnodes_list_images_tags
	 * 
	 * @return Return the list of tags.
	 */
	public List<String> getTags() {

		/*
		 * commaND: wnodes_list_images_tags                    parse this
		 * output: Image Tag Details
		 * 
		 * Name arch wenmr_vcing_r i686 test_cloud x86_64 wnodes_tw x86_64
		 */

		try {
			String tomcatdir = System.getProperty("java.io.tmpdir");
			File dir = new File(tomcatdir + "/users/" + this.user);
			String cmd = "/usr/bin/wnodes/cli/size_images_info                   ";
			log.info("Command {}.", cmd);

			List<String> tags = new ArrayList<String>();
			Process p = Runtime.getRuntime().exec(cmd, null, dir);

			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while (((line = output.readLine()) != null)) {

				log.info("[Stdout] " + line);
				if ((!line.contains("Image Tag Details"))
						&& (!line.contains("Name          arch"))
						&& (!line.isEmpty())) {
					tags.add(line.trim().replace("i686", "")
							.replace("x86_64", "").replace("i386", ""));
				}

			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {

				log.error("[Stderr] " + line);
			}

			brCleanUp.close();

			return tags;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Return the list of the virtual machine dimension that are available.
	 * There aren't any implementation of the CLI for retrieve the size
	 * informations.
	 * 
	 * @return Return the list of sizes.
	 */
	public List<String> getSizes() {
		List<String> result = new ArrayList<String>();

		result.add("small");
		result.add("medium");
		result.add("large");
		// result.add("extralarge");

		return result;
	}
	
	/**
	 * Return the list of market place with the relative image tags that are available. Call
	 * the WNoDeS CLI command: size_images_info
	 * 
	 * @return Return the list of market place.
	 * @throws WnodesPortletException 
	 */
	public MarketPlace getMarketPlaces() throws WnodesPortletException{
		
		MarketPlace mp = new MarketPlace();
		
		try {
			
			String tomcatdir = System.getProperty("java.io.tmpdir");
			File dir = new File(tomcatdir + "/users/" + this.user);
			String cmd = "/usr/bin/wnodes/cli/size_images_info";
			log.info("Command {}.", cmd);
			
			Process p = Runtime.getRuntime().exec(cmd, null, dir);
			
			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();
			
			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;
			
			List<Size> sizes = new ArrayList<Size>();
			String rp = "";

			while (((line = output.readLine()) != null)) {
				if(line.contains("Resource Provider:")){
					if(!rp.isEmpty()){
						ResourceProvider resourceProvider = new ResourceProvider(rp, sizes);
						mp.addResourceProvider(resourceProvider);
					}
					rp = line.replace(" Resource Provider:", "");
				}else{
					if((!line.contains("TAG"))&&(!line.isEmpty())){
						List<String> tokens = new ArrayList<String>();
						Scanner s = new Scanner(line);
						while(s.hasNext()){
							String val = s.next();
							log.error(val);
							tokens.add(val);
						}
						Size t  = new Size(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3));
						sizes.add(t);
					}
				}
			}
			if(!rp.isEmpty()&&!sizes.isEmpty()){
				ResourceProvider resourceProvider = new ResourceProvider(rp, sizes);
				mp.addResourceProvider(resourceProvider);
			}
			output.close();
			boolean isErrors = false;
			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {
				isErrors = true;
				log.error("[Stderr] " + line);
			}

			brCleanUp.close();
			log.error(mp.toString());
			if(!isErrors){
				mp.setTags(getTags2());
			}
			
			log.error(mp.toString());
			return mp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	/**
	 * Return the list of the virtual machine images that are available. Call
	 * the WNoDeS CLI command: wnodes_list_images_tags
	 * 
	 * @return Return the list of tags.
	 * @throws WnodesPortletException 
	 */
	private List<Tag> getTags2() throws WnodesPortletException {

		/*
		 * commaND: wnodes_list_images_tags                    parse this
		 * output: Image Tag Details
		 * 
		 * Name arch wenmr_vcing_r i686 test_cloud x86_64 wnodes_tw x86_64
		 */

		try {
			String tomcatdir = System.getProperty("java.io.tmpdir");
			File dir = new File(tomcatdir + "/users/" + this.user);
			//String cmd = "/usr/bin/wnodes/cli/wnodes_list_images_tags                   ";
			String cmd = "/usr/bin/wnodes/cli/metadata_images_info                   ";
			log.info("Command {}.", cmd);

			List<Tag> tags = new ArrayList<Tag>();
			
			String[] envp = {"PYTHONPATH=/var/lib/stratuslab/python/"};
			Process p = Runtime.getRuntime().exec(cmd, envp, dir);

			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while (((line = output.readLine()) != null)) {
				
				if(line.contains("Execution:  Failed to find metadata entries: http://marketplace.egi.eu/metadata")){
					throw new WnodesPortletException("no-metadata-retrieved");
				}

				log.info("[Stdout] " + line);
				if ((!line.trim().contains("TAGOSOS-VERSIONOS-ARCHIDENTIFIERENDORSERDESCRIPTION"))
						&& (!line.isEmpty())) {
					Scanner s = new Scanner(line);
					List<String> tagToken = new ArrayList<String>();
					while (s.hasNext()){
						tagToken.add(s.next());
					}
//					TAG OS OS-VERSION  OS-ARCH IDENTIFIER ENDORSER DESCRIPTION
					tags.add(new Tag(tagToken.get(0), tagToken.get(3), tagToken.get(1), tagToken.get(2), tagToken.get(6), tagToken.get(5), tagToken.get(4), "WNoDeS"));
				}

			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {

				log.error("[Stderr] " + line);
			}

			brCleanUp.close();

			return tags;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
