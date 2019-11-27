package com.rbs.hackerearth.extrautils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author AnujTeotia
 *
 */
public class CaptureScreenshot {
	static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	static String imgname = null;
	static final Logger logger = Logger.getLogger(CaptureScreenshot.class);

	/**
	 * Method to Capture Screenshot for the failures in Selenium with TestName
	 * and Time stamp appended.
	 * 
	 * @param wb
	 *            web driver
	 * @param testcaseName
	 *            is test case name.
	 * @throws Exception
	 */
	public static void getSnapShot(WebDriver wb, String testcaseName) throws Exception {
		try {
			String imgpath = System.getProperty("user.dir").concat("\\Screenshot\\" + testcaseName);
			File f = new File(imgpath);
			if (!f.exists()) {
				f.mkdir();
			}
			Date d = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("dd_MM_yy_HH_mm_ss_a");
			String timestamp = sd.format(d);
			imgname = imgpath + "\\" + timestamp + ".png";

			// Snapshot code
			TakesScreenshot snpobj = ((TakesScreenshot) wb);
			File srcfile = snpobj.getScreenshotAs(OutputType.FILE);
			File destFile = new File(imgname);
			FileUtils.copyFile(srcfile, destFile);

		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}
	}
}