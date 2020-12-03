package testscenarios;

import java.io.File;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testobjectrepository.HomePage;
import userdefinedlibraries.DriverSetup;
import userdefinedlibraries.ExcelReadWrite;
import userdefinedlibraries.Screenshot;

/**
 * This class is defined in order to implement the given 
 * test scenario of hotel availability. 
 * Separate methods are defined to execute at certain
 * priorities defined by the execution flow of the
 * different tests.
 *
 * @author M. Shakir Hussain (875381)
 * @version 1.0
 * @since 2020-11-02
 */

public class Hotels extends DriverSetup {
	public static WebDriver driver;   
	public static XSSFWorkbook workbook ; 
    public static XSSFSheet sheet;
    public static XSSFCell cell;
	public static XSSFRow Row;
    public static String browser,value;
    public static File src;
    public static int r;
    
    //Parameters annotation takes browsertype as input (chrome or firefox) 
	@Parameters("browser")
    @BeforeClass
    //driverConfig method is executed before the start of any tests
    public void driverConfig(String browser)
    {
		//respective browser's webdriver is retreived from DriverSetup class 
    	driver=DriverSetup.driverInstantiate(browser);   
    }
	
	@Test(priority = 1)
	public void testCaseReadExcel() {
		try {
			//data is read from excel using ExcelReadWrite class readExcel method
			ExcelReadWrite.readExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void testCaseStaysButton() {
		WebElement stays = HomePage.staysButton(driver);
		//webelement "Stays" is retreived and it is clicked
		stays.click();
	}
	
	@Test(priority = 3)
	public void testCaseLocationBox() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		//explicit wait of 30 seconds is given to check if all the elements are visible on the webpage
		wait.until(ExpectedConditions.visibilityOfAllElements(HomePage.locationTextBox(driver)));
		List<WebElement> locationBox = HomePage.locationTextBox(driver);
		//location text box div is retrived as first element from the list
		WebElement el = locationBox.get(0);
		WebDriverWait wait1 = new WebDriverWait(driver,30);
		//explicit wait of 30 seconds is given to check if the text box is clickable
		wait1.until(ExpectedConditions.elementToBeClickable(el));
		Actions action = new Actions(driver);
		//actions class is used to move to that element and perform the click operation
		action.moveToElement(el).click(el).perform();
	}
	
	@Test(priority = 4)
	public void testCaseLocationInput() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		List<WebElement> locationInput = HomePage.locationInput(driver);
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement el = locationInput.get(0);
		wait.until(ExpectedConditions.visibilityOf(el));
		//checking if location input text box is enabled or not
		if(el.isEnabled()) {
			//clearing the text already present in the text box
			el.clear();
			//object casting of JavascriptExecutor interface
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			//retreiving the city "Mumbai" using excel read
			String city = ExcelReadWrite.city;
			//setting the value of location input text box as city (Mumbai)
			jse.executeScript("arguments[0].value = '" +city+"';",el);	
		}
		el.click();	
	}
	
	@Test(priority = 5)
	public void testCaseCitySelect() {
		WebDriverWait wait = new WebDriverWait(driver,15);
		WebElement citySelection = HomePage.citySelect(driver);
		wait.until(ExpectedConditions.visibilityOf(citySelection));
		//clicking on "Mumabi, Maharashtra, India" from auto-suggestion list
		citySelection.click();
	}
	
	@Test(priority = 6)
	public void testCaseDate() {
		List<WebElement> start = HomePage.startDateInput(driver);
		//retreiving first webelement of start list
		WebElement startDate = start.get(0);
		for(int i=0;i<7;i++) {
			//clicking on the increment button of start date 7 times in order to make a booking for next week
			startDate.click();
		}
		List<WebElement> end = HomePage.endDateInput(driver);
		//retreiving first webelement of end list
		WebElement endDate = end.get(0);
		//clicking on increment button of end date once 
		endDate.click();
	}
	
	@Test(priority = 7)
	public void testCaseSearch() {
		WebElement searchButton = HomePage.searchButton(driver);
		//clicking on "Search" button
		searchButton.click();
	}
	
	@Test(priority = 8)
	public void screenshotInitial() {
		//taking a screenshot after filling in the form values
		Screenshot.screenshot(driver);
	}
	
	@Test(priority = 9)
	public void testCaseSetPrice() {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOf(HomePage.priceInput(driver)));
			WebDriverWait wait1 = new WebDriverWait(driver,10);
			wait1.until(ExpectedConditions.elementToBeClickable(HomePage.priceInput(driver)));
			WebElement setPrice = HomePage.priceInput(driver);
			//clicking on the span tag in order to make the price input textboxes visible
			setPrice.click();			
	}
	
	@Test(priority = 10)
	public void testCaseSetMin() {
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOf(HomePage.minPriceTextBox(driver)));
		WebElement minPrice = HomePage.minPriceTextBox(driver);
		WebDriverWait wait1 = new WebDriverWait(driver,15);
		//default minmimum value for price is 600
		String defaultMinValue = "600";
		//using explicit wait to wait until 600 is visible in min price text box input
		wait1.until(ExpectedConditions.attributeContains(minPrice,"min", defaultMinValue));
		//clearing the value present inside the input text box
		minPrice.clear();
		//"2000" is given as input for min price which is read from excel file
		minPrice.sendKeys(String.valueOf(ExcelReadWrite.minPrice) );	
	}
	
	@Test(priority = 11)
	public void testCaseSetMax() {
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOf(HomePage.maxPriceTextBox(driver)));
		WebElement maxPrice = HomePage.maxPriceTextBox(driver);
		//clearing the value present inside the input text box
		maxPrice.clear();
		//"3000" is given as input for max price which is read from excel file
		maxPrice.sendKeys(String.valueOf(ExcelReadWrite.maxPrice));
		//pressing "Enter" key to view the results for the respective price range 
		maxPrice.sendKeys(Keys.RETURN);
	}
	
	@Test(priority = 12)
	public void testCaseHotelCount() {
		WebElement resetElement = HomePage.resetText(driver).get(1);
		WebDriverWait wait = new WebDriverWait(driver,15);
		String resetText = "Reset";
		//using explicit to wait until the text "Reset" is visible on the page
		wait.until(ExpectedConditions.textToBePresentInElement(resetElement, resetText));
		//retreving the webelement which houses the count of hotels
		WebElement count = HomePage.hotelCount(driver);
		//getting the value of count 
		value = count.getText();		
	}
	
	@Parameters("browser")
	@Test(priority = 13)
	public void testCaseWriteExcel(String browser) {
		String browserType = browser;
		if(browserType.equalsIgnoreCase("chrome")) {
			//storing the current working row in r
			r = ExcelReadWrite.row;
			//navigating to the cell 4 at the current row
			cell = ExcelReadWrite.sheet.getRow(r).getCell(3);
			if (cell == null)
				
				//if cell is empty create at a cell at that index
				cell = ExcelReadWrite.sheet.getRow(r).createCell(3);
			//setting that particular cell with value that is the hotel count
			 cell.setCellValue(value);
			 //performing write option on the excel sheet
			 ExcelReadWrite.writeExcel();
			 //logging the hotel count on console
			 System.out.println("Hotel results when run in chrome browser are: " + value);
		}
		else if(browserType.equalsIgnoreCase("firefox")) {
			r = ExcelReadWrite.row;
			cell = ExcelReadWrite.sheet.getRow(r).getCell(4);
			if (cell == null)
				  cell = ExcelReadWrite.sheet.getRow(r).createCell(4);
			 cell.setCellValue(value);
			 ExcelReadWrite.writeExcel();
			 System.out.println("Hotel results when run in firefox browser are: " + value);
		}		 
	} 
	
	@Test(priority = 14)
	public void screenshotResult() {
		//taking a screenshot to validate the count of hotels
		Screenshot.screenshot(driver);
	}
	
	
	@AfterClass
	//this method is executed after all the test cases are executed
	public void driverExit() {
		//closing the current browser session
		DriverSetup.driverClose();
	}

}
