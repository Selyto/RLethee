package testCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import objectRepository.Homepage;
import objectRepository.SearchresultsPage;
import xBrain.RetryTheTest;

public class Test001{

	Homepage home;
	
	@Test(dataProviderClass=utils.DataFetch.class, dataProvider="TC1")
	public void testMe0(String a, String b, String c) throws InvalidFormatException, IOException {
		System.out.println(a + b + c);
		
	}
	
	@Test(retryAnalyzer = RetryTheTest.class)
	public void testMe() throws InvalidFormatException, IOException {
		
		home = new Homepage();	//Change the class name as per your definition
		home.logfailure();
		home.Validate("is image alt", home.getTitle(), "Google");
		//SearchresultsPage spg = home.clicktoSearch("xyz");
		
	}
	
	@Test(retryAnalyzer = RetryTheTest.class)
	public void testMe2() {
		home = new Homepage();	//Change the class name as per your definition
		home.logfailure();
		home.Validate("is image alt", home.getTitle(), "Google");
		SearchresultsPage spg = home.clicktoSearch("xyz");
		
	}
}
