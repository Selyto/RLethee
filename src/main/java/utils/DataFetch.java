package utils;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import xBrain.SeleniumActions;


public class DataFetch extends SeleniumActions {

	@DataProvider(name="TC1")
	public Object[][] getData() throws InvalidFormatException, IOException{
		return readExcel("TestData\\TestData.xlsx", "TestData");
	}
	
}
