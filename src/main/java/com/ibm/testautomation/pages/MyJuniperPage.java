package com.ibm.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.util.ActionLib;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

public class MyJuniperPage extends BasePage {

	private By loginViewContainer = By.xpath("//section[@class='nxcsc-external-login-view-container']");
	//private By custEmailRadio = By.xpath("");
	//private By userIdRadio = By.xpath("");
	private By custEmailInput = By.id("customer-email-address");
	private By loginReasonOpt = By.id("login-reason-opt");
	private By externalLogin = By.id("nxcsc-btn-external-login");
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
	
	//login by Customer Email Id
	public void externalLogin(WebDriver driver) throws InterruptedException, Throwable {
		if(actionLib.isElementExist(loginViewContainer, driver)) {
			
			actionLib.sendValueToInput(custEmailInput, driver, System.getProperty("custemail"));
			actionLib.selectByValue(loginReasonOpt, driver, System.getProperty("reason"));
			actionLib.click(externalLogin, driver);
			
			Thread.sleep(2000);
		}else {
			actionLib.wait(3000);
		}
	}

}
