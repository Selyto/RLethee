package xBrain;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTheTest implements IRetryAnalyzer {

	Foundation fd = new Foundation();
	int counter = 0;
	int rLimit = fd.getRetryCount();
	@Override
	public boolean retry(ITestResult result) {
		if(counter < rLimit)
		 {
		 counter++;
		 return true;
		 }
		 return false;
	}

}
