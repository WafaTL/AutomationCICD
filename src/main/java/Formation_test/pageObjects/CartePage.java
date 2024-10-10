package Formation_test.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Formation_test.AbstractComponents.AbstractComponent;


public class CartePage extends  AbstractComponent {
	WebDriver driver ;
	public CartePage(WebDriver driver) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(driver,this);	}
	
	
	By cartList    = By.cssSelector(".cartSection h3");
	
	@FindBy(css=".cartSection h3")
	List<WebElement > cartProducts;
	
	
	
	public boolean verifyProductInCart(String productName) {
	waitForElementToAppear(cartList);
	 Boolean match = cartProducts.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
	 return match;

}
	
		
	
	
	
	

	
}


