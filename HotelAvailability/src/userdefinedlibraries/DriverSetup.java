package userdefinedlibraries;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class defines the Web driver setup required for Selenium testing. 
 * In this class, two browsers' drivers are instantiated namely chrome and firefox.
 * Two methods are defined in this class, first one is to instantiate 
 * the respective drivers and the second one to close the driver current session.
 *
 * @author M. Shakir Hussain (875381)
 * @version 1.0
 * @since 2020-11-02
 */

public class DriverSetup{
	public static WebDriver driver;
	public static String exePath;
	public static String url = "https://www.kayak.co.in/";
	public static String browserType;
	
	public static WebDriver driverInstantiate(String browser) {
		browserType= browser;
		if(browserType.equalsIgnoreCase("chrome")) {
			exePath = "C:\\Development_Avecto\\chromedriver_win32 - 83.00\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", exePath);
			//chrome driver instantiation
			driver = new ChromeDriver();				        	        
	    }
		else if(browserType.equalsIgnoreCase("firefox")) {
			exePath = "C:\\Development_Avecto\\firefoxdriver\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", exePath);
			//firefox driver instantiation
			driver = new FirefoxDriver();				        	        
	    }
		//maximizing the window handle
		driver.manage().window().maximize();
		//providing an implicit wait of 30 seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//navigating to the url = "https://www.kayak.co.in/"
		driver.get(url);
		//deleting all previous cookies to avoid auto-filling of input fields
		driver.manage().deleteAllCookies();
		return driver;	  
	}	
	
	public static void driverClose()
	  {
		//closing the browser's current session
		DriverSetup.driver.close();	   
	  }



}
