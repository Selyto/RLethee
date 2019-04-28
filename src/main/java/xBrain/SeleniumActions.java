package xBrain;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import objectRepository.Homepage;
import utils.Locators;

public abstract class SeleniumActions extends XController{

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
	
	public void Click(WebElement element) {
		try {
			element.click();
		}
		catch(Exception e) {
			logger.createNode("Click failed on the element " + element.getText() + " due to " + e.toString());
			logger.fail("Click failed on the element " + element.getText() + " due to " + e.toString());
		}
	}
	
	public void TypeIn(WebElement element, String term) {
		try {
			element.sendKeys(term);
			}
		catch(Exception e) {
			logger.createNode("Click failed on the element " + element.getText() + " due to " + e.toString());
		}
	}
	
}
