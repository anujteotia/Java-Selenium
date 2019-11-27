package com.rbs.hackerearth.hackernewswrapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * 
 * @author AnujTeotia
 *
 */
public class HackerNewsWrapper {
	static final Logger logger = Logger.getLogger(HackerNewsWrapper.class);

	/**
	 * checkPostgreaterThan500votes
	 * 
	 * @param posts
	 *            is the Map which have all the parsed titles and votes from
	 *            the page.
	 * @param filename
	 *            is the name of test case.
	 * @return result : true/No. of post greater than 500 votes.
	 * @throws IOException
	 */
	public String checkPostgreaterThan500votes(Map<String, String> posts, String filename) throws IOException {
		String result = "No. of post greater than 500 votes=";
		int count = 0;
		try {
			File file = new File(System.getProperty("user.dir") + "/Output/" + filename);
			FileWriter fileWriter = new FileWriter(file);

			for (Entry<String, String> title : posts.entrySet()) {
				if (Integer.parseInt(title.getValue().split(" ")[0]) > 500) {
					fileWriter.write(title.getKey() + " " + title.getValue());
					fileWriter.write("\n");
					count++;
				}
			}
			logger.info("No. of title with more than 500 votes=" + count);
			result = "true";
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		}

		return result;
	}

	/**
	 * createStringFromString
	 * 
	 * @Description Creating substrings to validate more number of test cases.
	 * @param givenString
	 *            is the string to search.
	 * @return
	 */
	public LinkedHashSet<String> createStringFromString(String givenString) {
		LinkedHashSet<String> generatedList = new LinkedHashSet<>();
		String sub = null;
		for (int i = 0; i < givenString.length()-1; i++) {
			 sub = givenString.substring(0, 2 + i);
			 generatedList.add(sub);
		}
		return generatedList;
	}
}
