package it.italiangrid.wnodes.core.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.italiangrid.wnodes.core.WnodesInfoService;

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
							.replace("x86_64", ""));
				}

			}
			output.close();

			BufferedReader brCleanUp = new BufferedReader(
					new InputStreamReader(stderr));
			while ((line = brCleanUp.readLine()) != null) {

				log.info("[Stderr] " + line);
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

}
