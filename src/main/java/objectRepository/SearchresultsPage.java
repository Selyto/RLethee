package objectRepository;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Locators;
import xBrain.SeleniumActions;
import xBrain.XController;

public class SearchresultsPage extends SeleniumActions {

    protected String id="//div[text()='All']";
	
	public SearchresultsPage() {
		Browser().manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(Browser(), 30);
		wait.until(ExpectedConditions.visibilityOf(locateWebElement(Locators.XPATH, id)));
	}
	
	public String getTitle() {
		return getAttribute(Locators.XPATH, id, "alt");
	}
	
	public void logfailure() {
		Validate("Verify nmae is similar", true, true);
	}
	
	
	
}
