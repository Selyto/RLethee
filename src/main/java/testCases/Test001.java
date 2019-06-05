package testCases;

import org.testng.annotations.Test;

import objectRepository.Homepage;
import objectRepository.SearchresultsPage;
import xBrain.RetryTheTest;

public class Test001{

	Homepage home;
	
	@Test(retryAnalyzer = RetryTheTest.class)
	public void testMe() {
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
