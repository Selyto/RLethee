package testCases;

import org.testng.annotations.Test;

import objectRepository.Homepage;
import xBrain.RetryTheTest;

public class Test001 extends Homepage{

	@Test(retryAnalyzer = RetryTheTest.class)
	public void testMe() {
		logfailure();
		Homepage hp = new Homepage();
		hp.getTitle();
		
	}
}
