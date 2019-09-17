package com.ibm.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.util.ActionLib;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

public class LoginPage extends BasePage {

	private By loginFormContainer = By.id("loginFormContainer");
	private By userId = By.id("userid");
	private By password = By.id("password");
	private By loginButton = By.xpath("//div[4]//input[1]");
	private By rememberMe = By.xpath("rememberme");
	private By errorDiv = By.xpath("//div[@class='error-message']");	
	ActionLib actionLib = new ActionLib();

	/**
	 * 
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public String getTitle(WebDriver driver) throws Exception {
		Thread.sleep(2000);
		return driver.getTitle();
	}
}
