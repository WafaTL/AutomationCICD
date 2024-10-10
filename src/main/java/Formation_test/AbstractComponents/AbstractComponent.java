package Formation_test.AbstractComponents;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Formation_test.pageObjects.CartePage;
import Formation_test.pageObjects.CheckoutPage;
import Formation_test.pageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver ;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements(driver,this);

	}
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartPage;
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderPage;
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public void waitForElementToAppear(By toastMessage) {
	 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));

	}
	public void waitForWebElementToAppear(WebElement element) {
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOf(element));

		}

	public void waitForElementToDisappear(WebElement element ) {
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void clickButton(WebElement cartBy) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", cartBy);
}
	public CartePage GoToCartPage() {
	    clickButton(cartPage);
	 return new CartePage(driver);

	}
	public CheckoutPage GoToCheckOutPage() {
	    clickButton(checkoutButton);
	    return new CheckoutPage(driver);

	}
	public OrderPage GoToOrderPage() {
	    clickButton(orderPage);
	 OrderPage ordersPage= new OrderPage(driver);
	 return ordersPage;

	}
	
	
	
	
	
	
	
}
