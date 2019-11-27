package com.rbs.hackerearth.extrautils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rbs.hackerearth.selenium.StepExecuter;
import com.rbs.hackerearth.selenium.StepExecuterValues;

/**
 * 
 * @author AnujTeotia
 *
 */
public class ReadExcelData {
	static final Logger logger = Logger.getLogger(ReadExcelData.class);
	
	/**
	 * readExcel
	 * @Description reading the Excel file .
	 * @param excelFilePath Path of Excel file.
	 * @throws IOException
	 */
	public static void readExcel(String excelFilePath) throws IOException {
		FileInputStream inputStream = null;

		Workbook workbook = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(inputStream);
			List<StepExecuterValues> holders = null;
			StepExecuterValues holder = null;

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				holder = new StepExecuterValues();
				Row nextRow = iterator.next();

				holder.setStep(nextRow.getCell(0).toString());
				holder.setTcName(nextRow.getCell(1).toString());
				holder.setKeyword(nextRow.getCell(2).toString());
				holder.setLocator(nextRow.getCell(3).toString());
				holder.setValue(nextRow.getCell(4).toString());
				holder.setInput(nextRow.getCell(5) != null ? nextRow.getCell(5).toString() : "");
				holder.setErrorMsg(nextRow.getCell(6) != null ? nextRow.getCell(6).toString() : "");
				if (StepExecuter.mapOfTestCases.containsKey(holder.getTcName())) {
					holders = StepExecuter.mapOfTestCases.get(holder.getTcName());
				} else {
					holders = new ArrayList<>();
				}
				holders.add(holder);
				StepExecuter.mapOfTestCases.put(holder.getTcName(), holders);

			}
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
			if (inputStream != null)
				inputStream.close();
		}

	}
}
