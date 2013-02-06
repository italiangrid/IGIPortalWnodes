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
import it.italiangrid.wnodes.model.MarketPlace;
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
			String cmd = "/usr/bin/wnodes/cli/wnodes_list_images_tags                   ";
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
	 */
	public List<MarketPlace> getMarketPlaces(){
		
		try {
			
			List<MarketPlace> mps = new ArrayList<MarketPlace>();
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
			
			MarketPlace mp = null;

			while (((line = output.readLine()) != null)) {

				log.error("[Stdout] " + line);
				
				if(!line.isEmpty()){
					if (line.contains(" Resource Provider")){
						if(mp!=null){
							mp.setTags(getTags2());
							mps.add(mp);
						}
						String name = line.replace(" Resource Provider:", "");
						mp = new MarketPlace(name);
					}else if(!line.contains("TAG")){
						List<String> tokens = new ArrayList<String>();
						Scanner s = new Scanner(line);
						while(s.hasNext()){
							String val = s.next();
							log.error(val);
							tokens.add(val);
						}
						Size t  = new Size(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3));
						mp.addSize(t);
					}
				}

			}
			mp.setTags(getTags2());
			mps.add(mp);
			
//			mp=new MarketPlace("prova");
//			mp.addSize(new Size("size1", "1", "1[GB]", "10GB"));
//			mp.addSize(new Size("size2", "2", "2[GB]", "20GB"));
//			mp.addSize(new Size("size3", "4", "4[GB]", "40GB"));
//			mp.addSize(new Size("size4", "8", "8[GB]", "80GB"));
//			mp.addTag(new Tag("provaTag1", "i386"));
//			mp.addTag(new Tag("provaTag2", "i686"));
//			mp.addTag(new Tag("provaTag3", "x86_64"));
//			
//			mps.add(mp);
//			
//			for(MarketPlace m: mps){
//				log.error(m.toString());
//			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {

				log.error("[Stderr] " + line);
			}

			brCleanUp.close();

			return mps;
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
	 */
	private List<Tag> getTags2() {

		/*
		 * commaND: wnodes_list_images_tags                    parse this
		 * output: Image Tag Details
		 * 
		 * Name arch wenmr_vcing_r i686 test_cloud x86_64 wnodes_tw x86_64
		 */

		try {
			String tomcatdir = System.getProperty("java.io.tmpdir");
			File dir = new File(tomcatdir + "/users/" + this.user);
			String cmd = "/usr/bin/wnodes/cli/wnodes_list_images_tags                   ";
			log.info("Command {}.", cmd);

			List<Tag> tags = new ArrayList<Tag>();
			Process p = Runtime.getRuntime().exec(cmd, null, dir);

			InputStream stdout = p.getInputStream();
			InputStream stderr = p.getErrorStream();

			BufferedReader output = new BufferedReader(new InputStreamReader(
					stdout));
			String line = null;

			while (((line = output.readLine()) != null)) {

				log.info("[Stdout] " + line);
				if ((!line.contains("Image Tag Details"))
						&& (!line.contains("Name"))
						&& (!line.isEmpty())) {
//					tags.add(line.trim().replace("i686", "")
//							.replace("x86_64", "").replace("i386", ""));
					Scanner s = new Scanner(line);
					List<String> tagToken = new ArrayList<String>();
					while (s.hasNext()){
						tagToken.add(s.next());
					}
					tags.add(new Tag(tagToken.get(0), tagToken.get(1), "Sl6", "6.3", "Description", "endorser"));
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
