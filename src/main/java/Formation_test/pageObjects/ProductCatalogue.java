package Formation_test.pageObjects;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Formation_test.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends  AbstractComponent{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver ) {
		super(driver);
		this.driver = driver ; 
		PageFactory.initElements(driver,this);
	}
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button[class='btn w-10 rounded']");
	By toastMessage = By.cssSelector("button[class='btn w-10 rounded']");
	

	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(tagName="ngx-spinner")
	WebElement loadElement ;
	
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream().filter(item->
        item.findElement(By.cssSelector("div[class='card-body'] b")).getText().equals(productName)).findFirst().orElse(null);
	   return prod;
}
	
	
	
     public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductByName(productName);
	    if (prod == null) {
	        throw new IllegalArgumentException("Le produit " + productName + " n'a pas été trouvé.");
	    }
		//prod.findElement(addToCart).click();
		clickButton(prod.findElement(addToCart));
		 Thread.sleep(Duration.ofSeconds(5));
	    waitForElementToDisappear(loadElement);
	    waitForElementToAppear(toastMessage);
}
	
	
}