package objectRepository;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Locators;
import xBrain.SeleniumActions;

public class Homepage extends SeleniumActions {

    protected String id="hplogo";
    protected String btnSearch="btnK";
    protected String txtSearch="q";
	
	public Homepage() {
		Browser().manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(Browser(), 30);
		wait.until(ExpectedConditions.visibilityOf(locateWebElement(Locators.ID, id)));
	}
	
	public String getTitle() {
		return getAttribute(Locators.ID, id, "alt");
	}
	
	public void logfailure() {
		Validate("Verify nmae is similar", true, true);
	}
	
	public SearchresultsPage clicktoSearch(String term) {
		TypeIn(locateWebElement(Locators.NAME,txtSearch), term);
		Click(locateWebElement(Locators.NAME,btnSearch));
		return new SearchresultsPage();
	}
	
	
}
