package formation.stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Formation_test.pageObjects.CartePage;
import Formation_test.pageObjects.CheckoutPage;
import Formation_test.pageObjects.ConfirmationPage;
import Formation_test.pageObjects.LandingPage;
import Formation_test.pageObjects.ProductCatalogue;
import formation.testComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl  extends BaseTest{

	public LandingPage landingPage ;
	ProductCatalogue productCatalogue ;
	ConfirmationPage confirmationPage ;
	CartePage cartePage ;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = LaunchApplication();
	}
	
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_userName_and_password(String username,String password) {
		
		productCatalogue = landingPage.LoginApplication(username, password);
}
    @When ("^I add (.+) to cart$") 
    public void I_add_to_cart(String productName) throws InterruptedException {
    	List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);
		productCatalogue.addProductToCart(productName);
		 
    	
   }
    @And ("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName) throws InterruptedException  {
        System.out.println("Product name received: " + productName); // Debugging log
        cartePage = productCatalogue.GoToCartPage(); 
    	Thread.sleep(Duration.ofSeconds(2));
    	
		Boolean match = cartePage.verifyProductInCart(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = productCatalogue.GoToCheckOutPage();
		Thread.sleep(Duration.ofSeconds(2));
		checkoutPage.placeOrderMethod("tunisia");
		 confirmationPage = checkoutPage.submitOrder();
    }
    @Then ("{string} is displayed on ConfirmationPage")
    	public void is_displayed_on_ConfirmationPage(String string) {
    	String message = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
    	
    }
    @Then("{string} message is displayed")
    public void message_is_displayed(String string) {
	    Assert.assertEquals(string, landingPage.getErrorMessage());
	    driver.close();
	    

    }




}
