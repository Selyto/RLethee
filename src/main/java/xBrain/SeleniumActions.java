package xBrain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import objectRepository.Homepage;
import utils.Locators;

public abstract class SeleniumActions extends XController{

	public WebElement locateWebElement( Locators locator, String locValue ) {
		try {
			switch (locator) {
			case CLASSNAME:
				return driver.findElement(By.className(locValue));
			case CSS:
				return driver.findElement(By.cssSelector(locValue));
			case ID:
				return driver.findElement(By.id(locValue));
			case LINKTEXT:
				return driver.findElement(By.linkText(locValue.trim()));
			case NAME:
				return driver.findElement(By.name(locValue));
			case XPATH:
				return driver.findElement(By.xpath(locValue));
			case PARTIALLINKTEXT:
				return driver.findElement(By.partialLinkText(locValue));
			case TAGNAME:
				return driver.findElement(By.tagName(locValue));
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException();
		}
		return null;
	}
	

	public String getAttribute(Locators locator, String locatorValue, String attributeName) {
		return locateWebElement(locator, locatorValue).getAttribute(attributeName);
	}
	
	public void Validate( String logMessage, boolean expectedValue, boolean actualValue ) {
		if(expectedValue == actualValue)
			logger.createNode(logMessage + " - validation PASSED");
		else
			logger.createNode(logMessage + " - validation FAILED");
	}
	
	public void Validate( String logMessage, String expectedValue, String actualValue ) {
		if(expectedValue.equalsIgnoreCase(actualValue))
			logger.createNode(logMessage + " - validation PASSED");
		else
			logger.createNode(logMessage + " - validation FAILED");
	}
	
	public void Click( WebElement element ) {
		try {
			element.click();
		}
		catch(Exception e) {
			logger.createNode("Click failed on the element " + element.getText() + " due to " + e.toString());
			logger.fail("Click failed on the element " + element.getText() + " due to " + e.toString());
		}
	}
	
	public void isDisplayed( WebElement element ) {
		try {
			element.isDisplayed();
		}
		catch(Exception e) {
			logger.createNode("Click failed on the element " + element.getText() + " due to " + e.toString());
			logger.fail("Click failed on the element " + element.getText() + " due to " + e.toString());
		}
	}
	
	public void TypeIn( WebElement element, String term ) {
		try {
			element.sendKeys(term);
			}
		catch(Exception e) {
			logger.createNode("Click failed on the element " + element.getText() + " due to " + e.toString());
		}
	}
	
	public String[][] readExcel(String fileName, String sheetName) throws InvalidFormatException, IOException{
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		
		int i=0, j=0;
		XSSFWorkbook xwb = new XSSFWorkbook(new File(fileName));
		XSSFSheet xsheet = xwb.getSheet(sheetName);
		Iterator<Row> rows = xsheet.rowIterator();
		while(rows.hasNext()){
			Row nextRow = rows.next();
			Iterator<Cell> cells = nextRow.cellIterator();
			ArrayList<String> cellData = new ArrayList<String>();
			while(cells.hasNext()) {
				Cell cell = cells.next();
				switch(cell.getCellType()) {
				case NUMERIC:
					cellData.add(String.valueOf(cell.getNumericCellValue()));
					j++;
					break;
				case STRING:
					cellData.add(cell.getStringCellValue());
					j++;
					break;
					
				default:
					break;
				
				}
			}
			data.add(cellData);
		}
		xwb.close();
		String[][] objarray = new String[data.size()][];
		for (int z = 0;z < data.size(); z++) {
		    ArrayList<String> row = data.get(z);
		    objarray[z] = row.toArray(new String[row.size()]);
		}
		
		return objarray;
	}
	
	public Object[][] readExcel_CustomRange(String fileName, String sheetName, int startRow, int startCol,  int endRow, int endCol) throws InvalidFormatException, IOException{
		Object[][] data = new String[endRow - startRow][endCol - startCol];
		int x=0, y=0;
		XSSFWorkbook xwb = new XSSFWorkbook(new File(fileName));
		XSSFSheet xsheet = xwb.getSheet(sheetName);
		for(int i=startRow; i<=endRow;++i) {
			Row row = xsheet.getRow(i);
			for(int j=startCol;j<=endCol;++j) {
				Cell cell = row.getCell(j);
				switch(cell.getCellType()) {
				case NUMERIC:
					data[x][y] = String.valueOf(cell.getNumericCellValue());
					y++;
					break;
				case STRING:
					data[x][y] = cell.getStringCellValue();
					y++;
					break;
					
				default:
					break;
				
				}
			}
			x++;
		}
		xwb.close();
		
		return data; 
	}
	
}
