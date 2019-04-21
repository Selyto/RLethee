package xBrain;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestListener extends Foundation implements ITestListener, IAnnotationTransformer{

	ExtentReports extent;
	ExtentTest logger;
	ExtentHtmlReporter htmlReport;

	@Override
	public void onTestStart(ITestResult result) {
		openBrowser();
		navigateandGoto();
		System.out.println("got in");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		System.out.println("PASS");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test Case Failed", ExtentColor.RED));
		 try {
			 logger.fail("Sanpshot: " + logger.addScreenCaptureFromPath(getScreenShot(Browser(), result.getName())));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 System.out.println("FAIL");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		readConfig();
		startReport();
		System.out.println("Engine initaitked");
	}

	@Override
	public void onFinish(ITestContext context) {
		killAll();
		extent.flush();
		System.out.println("All over");
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		 annotation.setRetryAnalyzer(RetryTheTest.class);
		
	}
	
	public void startReport(){
		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\RLetheReport.html");
		htmlReport.loadXMLConfig(System.getProperty("user.dir")+"\\extent-config.xml", false);
		extent = new ExtentReports ();
		extent.attachReporter(htmlReport);
		
	}
	 public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 // after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }
	
}
