package com.ibm.testautomation.stepdef;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.ibm.testautomation.listener.ExtentReportListener;
import com.ibm.testautomation.pages.LoginPage;
import com.ibm.testautomation.pages.MyJuniperPage;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyJuniperStepDef extends ExtentReportListener {

	PropertiesFileReader obj = new PropertiesFileReader();
	public static WebDriver driver = CommonUtil.getDriver();
	ExtentTest logInfo = null;
	SoftAssert softAssert = new SoftAssert();
	CommonUtil commonUtil = new CommonUtil();
	LoginPage loginPage = new LoginPage();
	
	/**
	 * 
	 * @param scenario
	 */
	@Before
	public void beforeScenario(Scenario scenario) {

		test = extent.createTest(scenario.getName());
		test = test.createNode(scenario.getName());
	}

	/**
	 * 
	 * @param scenario
	 */
	@After
	public void afterScenario(Scenario scenario) {

		if (driver != null) {
			driver.quit();
		}
	}

	@Given("^Open MyJuniper Application$")
	public void open_myjuniper_application() throws IOException {
		Properties properties = obj.getProperty();

		try {

			logInfo = test.createNode(new GherkinKeyword("Given"), "Open MyJuniper Application");
			loginPage.launchBrowser(System.getProperty("env"), driver);
			String expectedLoginPageTitle = properties.getProperty("loginpage.title");
			String actualLoginPageTitle = loginPage.getTitle(driver);
			Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle, "MyJuniper Login Page is not opening!");
			logInfo.pass("Successfully open MyJuniper Login Page on - " + System.getProperty("env"));
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			Assert.fail();
		}
	}
}
