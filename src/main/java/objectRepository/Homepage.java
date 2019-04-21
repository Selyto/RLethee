package objectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import xBrain.XController;

public class Homepage extends XController {

	@FindBy(id="hplogo")
	WebElement imgLogo;
	
	public String getTitle() {
		return imgLogo.getAttribute("alt");
	}
	
	public void logfailure() {
		Validate("Verify nmae is similar", true, true);
	}
}
