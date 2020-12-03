package userdefinedlibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class is defined to perform the read and write of data
 * through excel file which is stored inside the datatables
 * package named "HotelData.xlsx"
 *
 * @author M. Shakir Hussain (875381)
 * @version 1.0
 * @since 2020-11-02
 */

public class ExcelReadWrite {
	public static File src;
	public static String filePath = "C:\\Users\\875381\\eclipse-workspace\\HotelAvailability\\src\\DataTables\\HotelData.xlsx";
	public static FileInputStream fip;
	public static FileOutputStream fop;
	public static XSSFWorkbook workbook; 
	public static XSSFSheet sheet;
	public static int row;
	public static XSSFCell cell;
	public static XSSFRow Row;
	public static String city;
    public static int minPrice;
    public static int maxPrice;
	
	public static int readExcel() {
		try
	    {
			//creating source file instance
			src=new File(filePath);
			//creating input file stream instance
			fip = new FileInputStream(src);
			//creating workbook instance for the input stream
		    workbook = new XSSFWorkbook(fip); 
		    //getting the first sheet from the workbook
		    sheet = workbook.getSheetAt(0);
		    //traversing from 2nd row until last row in the sheet
	    	for(int i=1; i<=sheet.getLastRowNum(); i++)
	        {
	    		//checking if the current row is the second row
	            if(i==1)
	            {
	            	 //reading the second row first cell value into city variable
	                 city = (sheet.getRow(i).getCell(0)).getStringCellValue();
	                 //reading the second row second cell value into minPrice variable
	                 minPrice = (int) (sheet.getRow(i).getCell(1)).getNumericCellValue();
	                 //reading the second row third cell value into maxPrice variable
	                 maxPrice = (int)(sheet.getRow(i).getCell(2).getNumericCellValue());
	                 System.out.println("The city read from excel file: " + city);
	                 System.out.println("The minimum price read from excel file: " + minPrice);
	                 System.out.println("The maximum price read from excel file: " + maxPrice);
	                 row=i;	                 
	                 break;
	            }
	        }	    	
	    }catch (FileNotFoundException e) 
        	{
	    		e.printStackTrace();
        	}catch (IOException e)
			{
        		e.printStackTrace();
			}
		return row;
	}
	
	public static void writeExcel() {		
	  try
	  { 		  
		//Close input stream
	      fip.close();
	    //Create an object of FileOutputStream class to create write data in excel file
	      fop =new FileOutputStream(new File(filePath));
	    //write data in the excel file
	      workbook.write(fop);
	    //close output stream
	      fop.close();	   	         
	  } catch (FileNotFoundException e){
	         e.printStackTrace();
	     } catch (IOException e){
	         e.printStackTrace();
	     }
	  }

}
