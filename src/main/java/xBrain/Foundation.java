package xBrain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;

import utils.browser;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Foundation {

	protected static WebDriver driver;
	public Foundation objBrowser = null;
	Properties prop = null;
	FileInputStream is= null;
	String BROWSERNAME = "";
	String MODE = "";
	String URL = "";
	String ISREMOTE = "";
	String USERNAME = "";
	String PASSWORD = "";
	int RETRYCOUNT = 0;

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
		driver.quit();
	}
	
	public WebDriver Browser() {
		return driver;
	}
	
	public int getRetryCount() {
		return RETRYCOUNT;
	}
	
	
}
