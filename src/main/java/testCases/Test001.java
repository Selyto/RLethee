package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import objectRepository.Homepage;
import xBrain.Foundation;
import xBrain.RetryTheTest;

public class Test001 {

	@Test(retryAnalyzer = RetryTheTest.class)
	public void testMe() {
		assertTrue(false);
		Homepage hp = new Homepage();
		hp.getTitle();
		
	}
}
