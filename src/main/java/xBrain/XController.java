package xBrain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
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

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Locators;

public class XController implements ITestListener, IAnnotationTransformer{

	protected ExtentReports extent;
	protected static ExtentTest logger;
	protected ExtentHtmlReporter htmlReport;

	protected static WebDriver driver;
	Properties prop = null;
	FileInputStream is= null;
	String BROWSERNAME = "";
	String MODE = "";
	String URL = "";
	String ISREMOTE = "";
	String USERNAME = "";
	String PASSWORD = "";
	static int RETRYCOUNT = 0;
	
	@Override
	public void onTestStart(ITestResult result) {
		logger = extent.createTest(result.getName());
		openBrowser();
		navigateandGoto();
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		
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
	}

	@Override
	public void onFinish(ITestContext context) {
		killAll();
		extent.flush();
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
	 
	 public void openBrowser() 
	    { 
			
	        	switch(BROWSERNAME) {
	        	case "chrome":
	        		WebDriverManager.chromedriver().setup();
	        		ChromeOptions opt = new ChromeOptions();
	        		opt.setCapability(CapabilityType.BROWSER_NAME, "Google Chrome");
	        		if(!MODE.equalsIgnoreCase("visible"))
	        			opt.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
	        		driver = new ChromeDriver(opt);
	        		break;
	        	case "firefox":
	        		WebDriverManager.firefoxdriver().setup();
	        		driver = new FirefoxDriver();
	        		break;
	        	case "iexplorer":
	        		WebDriverManager.iedriver().setup();
	        		driver = new InternetExplorerDriver();
	        		break;
	        	default:
	        		System.out.println(BROWSERNAME + " not supported yet");
	        	}     
	        	driver.manage().window().maximize();
	    } 
		
		public void navigateandGoto() {
			driver.get(URL);
			System.out.println(driver.getTitle());
		}
		
		public void readConfig() {	
			try {
				prop = new Properties();
				FileInputStream is = new FileInputStream(new File(".\\config.Properties"));
				prop.load(is);
				BROWSERNAME = prop.getProperty("browser");
				MODE = prop.getProperty("mode");
				URL = prop.getProperty("url");
				ISREMOTE = prop.getProperty("isRemote");
				USERNAME = prop.getProperty("username");
				PASSWORD = prop.getProperty("password");
				RETRYCOUNT = Integer.parseInt(prop.getProperty("retryCount"));
			} catch (FileNotFoundException e) {
				System.out.println("Configuraton Read Error.");
			} catch (IOException e) {
				System.out.println("Configuraton Read Error.");
			}
			
		}
		
		public void killAll() {		
			Browser().quit();
		}
		
		public WebDriver Browser() {
			return driver;
		}
		
		public int getRetryCount() {
			return RETRYCOUNT;
		}
		
		public void Validate(String logMessage, boolean expectedValue, boolean actualValue) {
			if(expectedValue == actualValue)
				logger.createNode(logMessage + " - validation PASSED");
			else
				logger.createNode(logMessage + " - validation FAILED");
		}
		
		public void Validate(String logMessage, String expectedValue, String actualValue) {
			if(expectedValue.equalsIgnoreCase(actualValue))
				logger.createNode(logMessage + " - validation PASSED");
			else
				logger.createNode(logMessage + " - validation FAILED");
		}
		
		public WebElement locateWebElement(Locators locator, String locValue) {
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

	
}
