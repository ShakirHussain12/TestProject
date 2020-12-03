package testobjectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class is defined to locate the webelements present on the 
 * webpage and store them in separate webelement references. 
 * Separate methods are used for various webelements.
 *
 * @author M. Shakir Hussain (875381)
 * @version 1.0
 * @since 2020-11-02
 */

public class HomePage {
	public static WebDriver driver;
	
	public static WebElement staysButton(WebDriver driver){
		//locating the "Stays" tab via its xpath
		WebElement stays = driver.findElement(By.xpath("//a[contains(@aria-label,'hotels')]"));
		return stays;	
	}
	
	public static List<WebElement> locationTextBox(WebDriver driver) {
		//locating the location text box div via xpath, stored in list as more than one webelement is returned
		List<WebElement> location = driver.findElements(By.xpath("//div[contains(@id,\"-fieldGridLocation\")]"));
		return location;
	}
	
	public static List<WebElement> locationInput(WebDriver driver) {
		//locating the input location text box via xpath, stored in list as more than one webelement is returned
		List<WebElement> locInput = driver.findElements(By.xpath("//input[contains(@aria-label,\"Destination input\")]"));
		return locInput;
	}
	
	public static WebElement citySelect(WebDriver driver) {
		//locating "Mumbai, Maharashtra, India" from auto-suggestion list
		WebElement city = driver.findElement(By.xpath("//*[contains(@id,\"city-31288\")]"));
		return city;
	}
	
	public static List<WebElement> startDateInput(WebDriver driver) {
		//locating the increment button of start date input via xpath
		List<WebElement> startDate = driver.findElements(By.xpath("//button[contains(@id,'start-inc')]"));
		return startDate;
	}
	
	public static List<WebElement> endDateInput(WebDriver driver){
		//locating the increment button of end date input via xpath
		List<WebElement> endDate = driver.findElements(By.xpath("//button[contains(@id,'end-inc')]"));
		return endDate;
	}
	
	public static WebElement searchButton(WebDriver driver) {
		//locating the "Search" button via xpath
		WebElement search = driver.findElement((By.xpath("//button[contains(@id,'-submit')]")));
		return search;
	}
	
	public static WebElement priceInput(WebDriver driver) {
		//locating the price input span tag via xpath
		WebElement price = driver.findElement(By.xpath("//span[@class=\"min\"]"));
		return price;
	}
	
	public static WebElement minPriceTextBox(WebDriver driver) {
		//locating the minimum price input text box via xpath
		WebElement minPrice = driver.findElement(By.xpath("//*[contains(@id,\"-minInput-input\")]"));
		return minPrice;
	}
	
	public static WebElement maxPriceTextBox(WebDriver driver) {
		//locating the maximum price input text box via xpath
		WebElement maxPrice = driver.findElement(By.xpath("//*[contains(@id,\"-maxInput-input\")]"));
		return maxPrice;
	}
	
	public static List<WebElement> resetText(WebDriver driver) {
		//locating the text "Reset" on the webpage via xpath
		List<WebElement> reset = driver.findElements(By.xpath("//a[contains(@id,\"-price-reset\")]"));
		return reset;
	}
	
	public static WebElement hotelCount(WebDriver driver) {
		//locating the count of hotels text on the webpage via xpath
		WebElement count = driver.findElement(By.xpath("//*[contains(@id,\"-filteredCount\")]"));
		return count;
	}

}
