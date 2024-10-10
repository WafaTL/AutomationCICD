package Formation_test.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Formation_test.AbstractComponents.AbstractComponent;
public class OrderPage extends  AbstractComponent {
	WebDriver driver ;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(driver,this);	
	}

	By cartList    = By.cssSelector("tr td:nth-child(3)");

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement > productNames;

	public boolean verifyOrderDisplay(String productName) {
		waitForElementToAppear(cartList);
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}

}