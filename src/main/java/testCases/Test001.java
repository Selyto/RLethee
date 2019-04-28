package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
		SearchresultsPage spg = home.clicktoSearch("xyz");
		spg.Validate("searchresults", spg.getTitle(),  "xyz - Google Search");
	}
}
