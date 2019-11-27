package com.rbs.hackerearth.extrautils;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * 
 * @author AnujTeotia
 *
 */
public class CreateFolder {
	
	private CreateFolder(){
		
	}
	static final Logger logger = Logger.getLogger(CreateFolder.class);

	/**
	 * 
	 * @param name of the Folder you want to create.
	 */
	public static void makeFolder(String name) {
		File folderName = new File(System.getProperty("user.dir") + "/" + name);
		if (!folderName.exists()) {
			logger.info("creating directory: " + folderName.getName());
			boolean result = false;

			try {
				folderName.mkdir();
				result = true;
			} catch (Exception e) {
				logger.info(e.getMessage());
				throw e;
			}
			if (result) {
				logger.info("Folder created " + name);
			}
		} else {
			logger.info(name + " already Exist!!");
		}

	}
}
