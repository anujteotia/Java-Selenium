package com.rbs.hackerearth.selenium;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author AnujTeotia
 *
 */
public class StepExecuterValues {
	public static Map<String, String> lst = new HashMap<>();
	public static String dynamicValue;
	private String step;
	private String tcName;
	private String keyword;
	private String locator;
	private String value;
	private String input;
	private String errorMsg;

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
