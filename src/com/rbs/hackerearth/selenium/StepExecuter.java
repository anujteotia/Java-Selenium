package com.rbs.hackerearth.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rbs.hackerearth.extrautils.CaptureScreenshot;
import com.rbs.hackerearth.extrautils.ReadExcelData;

/**
 * 
 * @author AnujTeotia
 *
 */
public class StepExecuter {
	public static Map<String, List<StepExecuterValues>> mapOfTestCases = new TreeMap<>();
	static List<StepExecuterValues> listoFTestSteps = null;
	static WebDriverWait driverWait = null;
	static int waitInMillisecons = 60000;
	static int waitForSixMilliseconds = 6000;
	static WebElement element = null;
	static WebElement element1 = null;
	static List<WebElement> elements = null;
	static String tcStatus = "Something Went Worng";

	private StepExecuter() {

	}

	static final Logger logger = Logger.getLogger(StepExecuter.class);

	/**
	 * StepDriver
	 * 
	 * @Description Performing the required selenium operations.
	 * @param wb
	 *            is Web driver
	 * @param properties
	 *            file to be loaded.
	 * @param tcName
	 *            is the name of test case.
	 * @return Test case status.
	 * @throws Exception
	 */

	public static String StepDriver(WebDriver wb, Properties properties, String tcName) throws Exception {
		StepExecuterValues.lst=new TreeMap<>();
		int i = 0;
		int steprepeat = 0;
		int count = 1;
		try {
			if (mapOfTestCases.isEmpty()) {
				ReadExcelData.readExcel(
						System.getProperty("user.dir") + "/TestData/" + properties.getProperty("testDataPath"));
			}
			driverWait = new WebDriverWait(wb, waitInMillisecons);
			listoFTestSteps = new ArrayList<>();
			listoFTestSteps = mapOfTestCases.get(tcName);

			for (; i < listoFTestSteps.size(); ++i) {
				switch (listoFTestSteps.get(i).getLocator().toLowerCase()) {
				case "id":
					element = driverWait
							.until(ExpectedConditions.elementToBeClickable(By.id(listoFTestSteps.get(i).getValue())));
					break;
				case "xpath":
				case "class":
					element = driverWait.until(
							ExpectedConditions.elementToBeClickable(By.xpath(listoFTestSteps.get(i).getValue())));
					break;
				case "xpathlist":
					elements = wb.findElements(By.xpath(listoFTestSteps.get(i).getValue().split("#")[0]));
					break;
				case "xpathdynamic":
					String[] value = listoFTestSteps.get(i).getValue().split(":");
					element = driverWait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(value[0] + (++count) + value[1])));
					break;
				default:
					break;
				}
				switch (listoFTestSteps.get(i).getKeyword().toLowerCase()) {
				case "click":
					Thread.sleep(waitForSixMilliseconds);
					element.click();
					logger.info(element + " : Clicked Successfully");
					break;
				case "sendkeys":
					Thread.sleep(waitForSixMilliseconds);
					element.sendKeys(listoFTestSteps.get(i).getInput());
					logger.info(listoFTestSteps.get(i).getInput() + " Entered Successfully at " + element);
					break;
				case "sendkeyscopydynamic":
					Thread.sleep(waitForSixMilliseconds);
					logger.info("SendkeysCopyDynamic:" + StepExecuterValues.dynamicValue);
					element.sendKeys(StepExecuterValues.dynamicValue);
					logger.info(StepExecuterValues.dynamicValue + " Entered dynamically at " + element);
					break;
				case "enter":
					Thread.sleep(waitForSixMilliseconds);
					Actions actions = new Actions(wb);
					actions.sendKeys(Keys.ENTER).build().perform();
					logger.info("Enter pressed Successfully");
					break;
				case "repeatsteps":
					if (Integer.parseInt(listoFTestSteps.get(i).getInput().split(",")[0]) > steprepeat) {
						steprepeat++;
						i -= Integer.parseInt(listoFTestSteps.get(i).getInput().split(",")[1]);
					}
					break;
				case "gettextlist":
					Thread.sleep(waitForSixMilliseconds);
					for (int j = 1; j <= elements.size(); ++j) {
						element = wb.findElement(By.xpath(listoFTestSteps.get(i).getValue().split("#")[1] + j + "]"));
						element1 = wb.findElement(By.xpath(listoFTestSteps.get(i).getValue().split("#")[2] + j + "]"));
						StepExecuterValues.lst.put(element1.getText(), element.getText());
					}
					break;
				default:
					break;

				}
				tcStatus = "true";
			}

		} catch (Exception e) {
			tcStatus = "failed";
			logger.error("Step No:" + listoFTestSteps.get(i).getStep());
			logger.error("testCaseName:" + tcName);
			logger.error("Keyword:" + listoFTestSteps.get(i).getKeyword());
			logger.error("Locator:" + listoFTestSteps.get(i).getLocator());
			logger.error("Value:" + listoFTestSteps.get(i).getValue());
			logger.error("Input:" + listoFTestSteps.get(i).getInput());
			logger.error("Error:" + listoFTestSteps.get(i).getErrorMsg());
			logger.error("Selenium stackTrace" + ":" + e.getStackTrace());
			CaptureScreenshot.getSnapShot(wb, tcName);
			throw e;
		}
		return tcStatus;
	}
}
