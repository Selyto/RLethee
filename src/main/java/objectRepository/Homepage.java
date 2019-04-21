package objectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import xBrain.Foundation;

public class Homepage extends Foundation {

	@FindBy(id="hplogo")
	WebElement imgLogo;
	
	public String getTitle() {
		return imgLogo.getAttribute("alt");
	}
}
