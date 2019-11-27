package com.rbs.hackerearth.hackernews;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rbs.hackerearth.extrautils.CreateFolder;
import com.rbs.hackerearth.hackernewswrapper.HackerNewsWrapper;
import com.rbs.hackerearth.selenium.StepExecuter;
import com.rbs.hackerearth.selenium.StepExecuterValues;
import com.rbs.hackerearth.selenium.WebDriverInitialization;

/**
 * 
 * @author Anuj Teotia
 *
 */
public class HackerNewsScenario {

	static final Logger logger = Logger.getLogger(HackerNewsScenario.class);
	HackerNewsWrapper hackerNewsWrapper = new HackerNewsWrapper();
	WebDriver driver = null;
	String filename = System.getProperty("user.dir") + "/TestData/RBSHackerNews.properties";
	InputStream inputStream = null;
	Properties propFile = new Properties();
	String tcName = "";
	String tcStatus = "false";

	/**
	 * setUpClass
	 * 
	 * @throws IOException
	 * @Description Initializing log4j file and loading the properties in
	 *              propFile.
	 */
	@BeforeClass
	public void setUpClass() throws IOException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/TestData/log4j.properties");
		File file = new File(filename);
		try {
			inputStream = new FileInputStream(file);
			propFile.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * init
	 * 
	 * @Description Creating folders for Screenshot,Output and logs and
	 *              initiating the web driver.
	 */
	@BeforeMethod
	public void init() {
		CreateFolder.makeFolder("Screenshot");
		CreateFolder.makeFolder("Output");
		CreateFolder.makeFolder("Log");
		driver = WebDriverInitialization.initateDriver(propFile);
		driver.manage().window().maximize();
		driver.get(propFile.getProperty("Portal"));

	}

	/**
	 * traverseanddisplay
	 * 
	 * @Description Searching for string "Hackathon".
	 * @Validation Checking the count of more than 500 votes and writing in .txt
	 *             file which is in "Output" folder.
	 * @throws Exception
	 */
	@Test
	public void traverseanddisplay() throws Exception {
		tcName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("TestCaseName:" + tcName);
		tcStatus = StepExecuter.StepDriver(driver, propFile, tcName);
		Assert.assertEquals(tcStatus, "true");
		tcStatus = hackerNewsWrapper.checkPostgreaterThan500votes(StepExecuterValues.lst, tcName + ".txt");
		Assert.assertEquals(tcStatus, "true", tcStatus);
	}

	/**
	 * @Description Searching for string "Hackathon" dynamically Splitting the
	 *              string and checking for different combinations..
	 * @Validation Checking the count of more than 500 votes for every string
	 *             and writing in .txt file which is in "Output" folder .
	 * @throws Exception
	 */
	@Test
	public void traverseanddisplaydynamic() throws Exception {
		tcName = Thread.currentThread().getStackTrace()[1].getMethodName();
		logger.info("TestCaseName:" + tcName);
		Set<String> dynamic = hackerNewsWrapper.createStringFromString(propFile.getProperty("SearchQuery"));
		Iterator<String> iterator = dynamic.iterator();
		while (iterator.hasNext()) {
			StepExecuterValues.dynamicValue = iterator.next();
			logger.info("Value:" + StepExecuterValues.dynamicValue);
			tcStatus = StepExecuter.StepDriver(driver, propFile, tcName);
			Assert.assertEquals(tcStatus, "true");
			tcStatus = hackerNewsWrapper.checkPostgreaterThan500votes(StepExecuterValues.lst,
					tcName + StepExecuterValues.dynamicValue+ ".txt");
			Assert.assertEquals(tcStatus, "true", tcStatus);
			driver.get(propFile.getProperty("Portal"));
		}
	}

	/**
	 * @Description Closing the Web driver.
	 */
	@AfterClass
	public void close() {
		try {
			driver.quit();
		} catch (Exception e) {
			logger.info("Unable to quit the WebDriver");
			throw e;
		}
	}

}
