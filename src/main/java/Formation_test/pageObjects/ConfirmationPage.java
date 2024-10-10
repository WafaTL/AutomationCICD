package Formation_test.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Formation_test.AbstractComponents.AbstractComponent;


public class ConfirmationPage extends AbstractComponent {
	WebDriver driver ;
	 public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(driver,this);
	}
	 
	 @FindBy(css=".hero-primary")
		WebElement confirmOrder;
	 
	 
	 
	 public String   getConfirmationMessage() {
			return  confirmOrder.getText();
			
		}
}
