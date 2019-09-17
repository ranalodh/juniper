package com.ibm.testautomation.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CommonUtil {
	
	public static WebDriver webDriver = null; 
	private final static Logger LOGGER = Logger.getLogger(CommonUtil.class.getName());
	private String fromDate = null;
	private List<String> formattedResults = new ArrayList<String>();
	private List<Date> result = new ArrayList<Date>();

	/**
	 * 
	 * @param submissionDate
	 * @param completionDate
	 * @return
	 * @throws ParseException
	 */
	public boolean compareDate(String submissionDate, String completionDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(submissionDate);
        Date date2 = sdf.parse(completionDate);

        LOGGER.info("submissionDate : " + sdf.format(date1));
        LOGGER.info("completionDate : " + sdf.format(date2));

       if (date1.before(date2) || date1.equals(date2)) {
            return true;
        } else {
            return false;
        }

    }
	/**
	 * 
	 * @return
	 */
	public String toDate() {
		Date date = new Date();
		String toDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		return toDate;
	}
	/**
	 * 
	 * @return
	 */
	public String fromDate() {
		Calendar cal = Calendar.getInstance();
		if(System.getProperty("timeinterval") != null || System.getProperty("timeinterval") != "" || System.getProperty("timeinterval") == "0") {
			
			cal.add(Calendar.HOUR, - Integer.parseInt(System.getProperty("timeinterval")));
			
			LOGGER.info("timeinterval >> " + Integer.parseInt(System.getProperty("timeinterval")));
			LOGGER.info("cal.getTime() >> " + cal.getTime());
			
			Date backHour = cal.getTime();
			fromDate= new SimpleDateFormat("yyyy-MM-dd").format(backHour);
			
		}else {
			//cal.add(Calendar.HOUR, - Integer.parseInt("24"));
			fromDate = new CommonUtil().toDate();
		}
		
		return fromDate;
	}
	
	
	/*public String fromDate2() {
		Calendar cal = Calendar.getInstance();
		
			cal.add(Calendar.HOUR, - 22391);
			
			
		Date backHour = cal.getTime();
		String fromDate= new SimpleDateFormat("yyyy-MM-dd").format(backHour);
		return fromDate;
	}*/
	
	/**
	 * 
	 * @param driver
	 * @param appPort
	 * @throws IOException
	 */
  public void captureScreen(WebDriver driver, String appPort) throws IOException {
		
		String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
        String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_" + appPort + ".jpg";
        //Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE,500,true).save();
        TakesScreenshot screen = (TakesScreenshot) driver;		
		File src = screen.getScreenshotAs(OutputType.FILE);
		File target = new File(screenshotAbsolutePath);
		FileUtils.copyFile(src, target);
		
	}
  /**
   * 
   * @return
   */
  public static WebDriver getDriver() {

		try {
				//System.setProperty("webdriver.chrome.driver","src/main/java/chromedriver_linux64/chromedriver");
			    System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				//options.setBinary("/bin/google-chrome");
				options.setHeadless(true);
				webDriver = new ChromeDriver(options);					
				webDriver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
				webDriver.manage().window().maximize();		
				webDriver.navigate().refresh();
		}catch (Exception exception) {
			exception.printStackTrace();
		}
		return webDriver;
		}	
  /**
   * 
   * @param element
   * @param driver
   */
  public void hightlightElement(By element, WebDriver driver) {
	  WebElement element_node = driver.findElement(element);
	  JavascriptExecutor jse = (JavascriptExecutor) driver;
	  jse.executeScript("arguments[0].style.border='2px solid red'", element_node);
  }
  /**
   * 
   * @param pageRange
   * @return
   */
  public int getStartPageNo(String pageRange) {
	  return Integer.parseInt(pageRange.split(" ")[1]);
  }
  /**
   * 
   * @param pageRange
   * @return
   */
  public int getEndPageNo(String pageRange) {
	  return Integer.parseInt(pageRange.split(" ")[3]);
  } 
  /**
   * 
   * @param url
   * @return
   */
  public String getAppPort(String url) {
	  String portId = null;
	  portId = url.replace(".", "_").split("_")[0].substring(url.replace(".", "_").split("_")[0].length() - 6);
	  
	  return portId;
  }
  
/**
 * 
 * @param startDate
 * @param endDate
 * @return
 */
private List<Date> getListOfDaysBetweenTwoDates(Date startDate, Date endDate) {	
	   
	    Calendar start = Calendar.getInstance();
	    start.setTime(startDate);
	    Calendar end = Calendar.getInstance();
	    end.setTime(endDate);
	    end.add(Calendar.DAY_OF_YEAR, 1); 
	    while (start.before(end)) {
	        result.add(start.getTime());
	        start.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}	

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
  public List<String> getdateList(String startDate, String endDate){
	  try {
		SimpleDateFormat originalFormat = new SimpleDateFormat("dd/mm/yy");
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");		
		Date date;
			
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startSubmissionDate = sdf.parse(startDate);
		java.util.Date endSubmissionDate = sdf.parse(endDate);
		
		List<Date> resultS = new CommonUtil().getListOfDaysBetweenTwoDates(startSubmissionDate, endSubmissionDate);
		
		for (Date result : resultS) {
			date = originalFormat.parse(DateFormat.getDateInstance(DateFormat.SHORT).format(result));
			formattedResults.add(targetFormat.format(date));
		}
		
	  LOGGER.info("Submission Date List based on Time Interval - " + formattedResults);
	  
	  }catch(Exception e) {
		  
	  }
	  return formattedResults;
  }
}
