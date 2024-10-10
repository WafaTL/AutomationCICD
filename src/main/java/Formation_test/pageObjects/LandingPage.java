package Formation_test.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Formation_test.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver ) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(driver,this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

public ProductCatalogue LoginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return(errorMessage.getText());
	}  
	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client/");

	}
}
