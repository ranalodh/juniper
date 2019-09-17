package com.ibm.testautomation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ActionLib {

	private final static Logger LOGGER = Logger.getLogger(ActionLib.class.getName());
	Select dropdown = null;

	public void click(By objectref, WebDriver driver) throws Throwable {
		try {

			Thread.sleep(500);

			LOGGER.info("Into Click() :: Object Reference Is:" + objectref);
			LOGGER.info("Clicking on Webelement: " + objectref);

			LOGGER.info("...................Click Event Started........");

			driver.findElement(objectref).click();

			LOGGER.info("...............Click Event Completed........");

			// captureScreenShot(driver);
			Thread.sleep(100);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning("Not able to Click --- " + e.getMessage());

		}
	}

	public void wait(WebDriver driver) throws Throwable

	{

		try {
			LOGGER.info("Wait for 10 seconds");
			Thread.sleep(500);
			LOGGER.info("Into wait() :: Driver Is:" + driver);

			LOGGER.info("...................wait Started........");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			LOGGER.info("...................wait Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able to Wait --- " + e.getMessage());

		}
	}

	public boolean isElementExist(By objectref, WebDriver driver) throws Throwable

	{
		boolean isElementExist = false;
		try {

			LOGGER.info("Locating object " + objectref);
			Thread.sleep(500);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);

			LOGGER.info("...................findObject Event Started........");

			int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				isElementExist = true;

			} else {
				isElementExist = false;
			}

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return isElementExist;

	}

	public int getElementSize(By objectref, WebDriver driver) throws Throwable

	{
		int elementSize = 0;
		try {

			LOGGER.info("Locating object " + objectref);
			Thread.sleep(500);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);

			LOGGER.info("...................findObject Event Started........");

			int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				elementSize = E;

			} else {
				elementSize = E;
			}

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return elementSize;

	}

	public boolean validateListItems(By objectref, WebDriver driver, List<String> expectedOptions) throws Throwable

	{

		Boolean isMatched = false;

		List<String> options = new ArrayList<String>();
		try {

			LOGGER.info("Locating object " + objectref);
			Thread.sleep(500);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("Into findObject() :: expectedOptions Reference Is:" + expectedOptions);

			options = getAllOptions(objectref, driver);

			if (options.equals(expectedOptions)) {
				isMatched = true;
				LOGGER.info("isMatched " + isMatched);
			} else {
				isMatched = false;
				LOGGER.info("isMatched " + isMatched);
			}

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}

		return isMatched;

	}

	public List<String> getAllOptions(By dropdown, WebDriver driver) {

		List<String> options = new ArrayList<String>();
		try {
			for (WebElement option : new Select(driver.findElement(dropdown)).getOptions()) {
				String optionTxt = option.getText().trim();
				if (!optionTxt.equals(null) && !optionTxt.equals("Select Role") && !optionTxt.equals("Select Report")) {
					LOGGER.info("Options are : " + optionTxt);
					options.add(optionTxt);
				}
			}
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());
		}
		LOGGER.info("options " + options);
		return options;
	}

	public String getElementText(By objectref, WebDriver driver, String attributeName) throws Throwable

	{
		String elementText = null;
		try {

			LOGGER.info("Locating object " + objectref);
			Thread.sleep(500);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);

			LOGGER.info("...................findObject Event Started........");

			int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				elementText = driver.findElement(objectref).getAttribute(attributeName);
				LOGGER.info("Object Value --- " + elementText);

			} else {
				elementText = "No Element Found";
			}

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return elementText;

	}

	public String getAlertText(WebDriver driver) throws Throwable

	{
		String alertText = null;
		try {
			Thread.sleep(500);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			alertText = driver.switchTo().alert().getText();
			LOGGER.info("alertText --- " + alertText);
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return alertText;

	}

	public void alertAccess(WebDriver driver) throws Throwable

	{
		try {
			Thread.sleep(500);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			driver.switchTo().alert().accept();

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
	}

	public void alertDismiss(WebDriver driver) throws Throwable

	{
		try {
			Thread.sleep(500);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			driver.switchTo().alert().dismiss();

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
	}

	public void selectByIndex(By objectref, WebDriver driver, int index) throws Throwable

	{

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);

			Thread.sleep(500);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByIndex(index);
			} else {
				LOGGER.info("Object Not found");
			}

			LOGGER.info("...............Dropdown Event Completed........");
			Thread.sleep(3000);

		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}

	public void selectByVisibleText(By objectref, WebDriver driver, String visibleText) throws Throwable

	{

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);

			Thread.sleep(500);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByVisibleText(visibleText);
			} else {
				LOGGER.info("Object Not found");
			}

			LOGGER.info("...............Dropdown Event Completed........");
			Thread.sleep(3000);

		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}

	public void selectByValue(By objectref, WebDriver driver, String value) throws Throwable

	{

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);

			Thread.sleep(500);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByVisibleText(value);
			} else {
				LOGGER.info("Object Not found");
			}

			LOGGER.info("...............Dropdown Event Completed........");
			Thread.sleep(3000);

		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}

	public void sendValueToInput(By objectref, WebDriver driver, String value) throws Throwable

	{

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);

			Thread.sleep(500);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				driver.findElement(objectref).sendKeys(value);
			} else {
				LOGGER.info("Object Not found");
			}

			LOGGER.info("...............sendValueToInput Event Completed........");
			Thread.sleep(3000);

		} catch (Exception e) {
			LOGGER.warning("Not able to find Input element --- " + e.getMessage());

		}
	}

	public void switchWindow(WebDriver driver) throws Throwable

	{
		try {
			Thread.sleep(500);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("...................switchWindow Event Started........");
			String parentWinHandle = driver.getWindowHandle();
			LOGGER.info("Parent window handle: " + parentWinHandle);
			Set<String> winHandles = driver.getWindowHandles();
			// Loop through all handles
			for (String handle : winHandles) {
				if (!handle.equals(parentWinHandle)) {
					driver.switchTo().window(handle);
					Thread.sleep(1000);
					LOGGER.info("Title of the new window: " + driver.getTitle());
				}
			}

			LOGGER.info("...............switchWindow Event Completed........");
			Thread.sleep(3000);

		} catch (Exception e) {
			LOGGER.warning("Not able to switchWindow --- " + e.getMessage());

		}
	}

	public void javascriptClick(By objectref, WebDriver driver) {
		try {
			Thread.sleep(500);
			LOGGER.info("Into javascriptClick() :: Driver Is:" + driver);
			LOGGER.info("Into javascriptClick() :: Object Reference Is:" + objectref);
			LOGGER.info("...................javascriptClick Event Started........");

			WebElement element = driver.findElement(objectref);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

			LOGGER.info("...............javascriptClick Event Completed........");
			Thread.sleep(3000);

		} catch (Exception e) {
			LOGGER.warning("Not able click javascriptClick --- " + e.getMessage());

		}
	}

}
