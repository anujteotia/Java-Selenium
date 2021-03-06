# Java-Selenium
Cutomized selenium framework using JAVA
# @author Anuj Teotia

1. Extract the code and import it in Eclipse.

2. Install TestNg Plugin in Eclipse from Eclipse marketplace.

3. Everything will run from a java class "HackerNewsScenario" which is present in the package "com.rbs.hackerearth.hackernews". 


# Test cases in "HackerNewsScenario" class

1. Testcase Name  : traverseanddisplay
	This test case fullfills all the requirements asked in question. It searches for string "Hackathon" and stores the result in a text file which will be created by the name of testcase in "Output" folder. If there are titles which have more than 500 votes then they will be written in text file else it will be empty.

2. Testcase Name: traverseanddisplaydynamic [Extra Work]
	This test case takes "Hackathon" as input string and breaks it into substrings and checks for the response for every substring. if there are titles which have more than 500 votes they will be written into their corresponding files.

	Eg : Given String : Hackathon
		Substrings : Ha, Hac, Hack,Hacka, Hackat, Hackath, Hackatho, Hackathon.
		Output File Name : Test Case Name + Substring.
		
		traverseanddisplaydynamicHa.txt For Substring Ha.


# Additional Features

1. This is the Hybrid(Both keyword and data driven) Framework.

2. Package name : com.rbs.hackerearth.extrautils
	This contains 3 java files :
	
	a) CaptureScreenshot :   
		It Captures Screenshot for the failures in Selenium with TestName and Time stamp appended. and this Screenshot will be present in test case name folder which is in Screenshot folder.

	b) CreateFolder :
		This will create the folder if it is already not present.

	c) ReadExcelData :
		This class is parsing the Excel data row by row and setting the values.

3. Generating a log file("hackernews.log") which will be helpful in code debugging in case of any failure. This file will be created in "Log" folder.

4. logged exception in every class and throwing it to calling function at every place.

5. Use of Properties File to avoid any kind of hard coding.

6. For now it supports only chrome browser.

7. Javadoc has been created for every method.

8. Using TestNG and log4j Frameworks.


# com.rbs.hackerearth.hackernewswrapper

1. This package contains HackerNewsWrapper java class, which contains two methods :

	a) checkPostgreaterThan500votes : 
		Checking if the votes for a title are more than 500 than it is writting the titles and corresponding votes in a text file.
		
	b) createStringFromString :
		Creating substrings from a given string to validate more number of test cases.
		
	
# com.rbs.hackerearth.selenium
 
1. This Package contains 3 java classes.

	a) StepExecuter :
		This class is driving the complete selenium part. This is the framework which handle all the requirements of Selenium as per the requirements.
		
	b) StepExecuterValues : 
		Setting and getting the values present in Excel file.

	c) WebDriverInitialization : 
		Initializing the web driver.
		
		
# Important locations 

1. Response Files : Output Folder.
2. log4j properties file : TestData folder.
3. Properties file : TestData Folder5
4. Excel file : TestData folder.
5. Log file : Log Folder
6. Failure Screenshots : Screenshot folder.
7. Webdriver : WebDriver Folder.
8. code  : src package


# THE END :)

		

