package objectRepository;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.Locators;
import xBrain.XController;

public class Homepage extends XController {

    String id="hplogo";
	
	public void HomePage() {
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
	
	public String getTitle() {
		return getAttribute(Locators.ID, id, "alt");
	}
	
	public void logfailure() {
		Validate("Verify nmae is similar", true, true);
	}
}
