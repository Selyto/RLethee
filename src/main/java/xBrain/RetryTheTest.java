package xBrain;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTheTest implements IRetryAnalyzer {

	XController fd = new XController();
	int counter = 0;
	int rLimit = fd.getRetryCount();
	@Override
	public boolean retry(ITestResult result) {
		if(counter < fd.getRetryCount())
		 {
		 counter++;
		 return true;
		 }
		 return false;
	}

}
