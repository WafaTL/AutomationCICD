package Formation_test.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Formation_test.AbstractComponents.AbstractComponent;


public class CheckoutPage extends  AbstractComponent{
	WebDriver driver ;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(driver,this);
	}
	
	By countryList   = By.cssSelector(".list-group");
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountry;
	@FindBy(xpath="//button[contains(@class,'ta-item')]")
	WebElement item;
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	
	
	public void placeOrderMethod(String countryName) {
		 Actions a = new Actions(driver);
		a.sendKeys(selectCountry,countryName).build().perform();
		 waitForElementToAppear(countryList);
		 item.click();
		
		 
}
	public ConfirmationPage submitOrder() {
		clickButton(placeOrderButton);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		 
	}
	
	
	
	
	
	
	
	
	
	

}
