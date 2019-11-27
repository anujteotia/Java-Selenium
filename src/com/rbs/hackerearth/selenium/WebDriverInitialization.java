package com.rbs.hackerearth.selenium;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 
 * @author AnujTeotia
 *
 */
public class WebDriverInitialization {
static final Logger logger = Logger.getLogger(StepExecuter.class);
static WebDriver wb;

/**
 * initateDriver
 * @Description Initializing the web driver.
 * @param callerFunction This is properties file.
 * @return web driver.
 */
public static WebDriver initateDriver(Properties callerFunction){
	DesiredCapabilities capabilities=null;
	try{
	if(callerFunction.get("driver")!=null && ("chrome").equalsIgnoreCase(callerFunction.get("driver").toString())){
		if(wb==null){
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/WebDriver/"+"chromedriver.exe");
	
		capabilities= DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		wb = new ChromeDriver(capabilities);
		}
	}
	return wb;
	}catch(Exception e){
		logger.error(e.getStackTrace());
		throw e;
	}
		
}
}
