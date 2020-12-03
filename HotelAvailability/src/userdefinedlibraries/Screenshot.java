package userdefinedlibraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * This class is defined to capture a screenshot of the webpage. 
 * Screenshots are stored in the relevant location in .png format.
 * 
 * @author M. Shakir Hussain (875381)
 * @version 1.0
 * @since 2020-11-02
 */

public class Screenshot {
	public static void screenshot(WebDriver driver){ 
		//creating src file instance for storing the screenshot using TakesScreenshot interface
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);       
		try
		{  //copying the  screenshot to desired location using copyFile method
			FileUtils.copyFile(src, new File("C:\\Users\\875381\\eclipse-workspace\\HotelAvailability\\TCScreenshot\\"+System.currentTimeMillis()+".png"));
        }catch (IOException e)      
			{
        		System.out.println(e.getMessage());      
			}
	  }



}
