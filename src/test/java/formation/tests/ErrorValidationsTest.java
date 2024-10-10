
package formation.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Formation_test.pageObjects.CartePage;
import Formation_test.pageObjects.ProductCatalogue;
import formation.testComponents.BaseTest;
import formation.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {
	
		@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
		public void LoginErrorValidation () throws IOException{
		landingPage.LoginApplication("wafaTlili@gmail.com", "akdlvngj");
	    Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());   ;
			System.out.println(landingPage.getErrorMessage());

		}
		
		@Test(expectedExceptions = IllegalArgumentException.class)
		public void ProductErrorValidation () throws IOException, InterruptedException{
			
			ProductCatalogue productCatalogue = landingPage.LoginApplication("wafaTlili@gmail.com", "Wafatlili@30");
			List<WebElement> products= productCatalogue.getProductList();
			String productName="ZARA COAT 33";
			productCatalogue.getProductByName(productName);
			productCatalogue.addProductToCart(productName);
			CartePage cartePage = productCatalogue.GoToCartPage(); 
			Boolean match = cartePage.verifyProductInCart(productName);
			Assert.assertFalse(match);
	}
		
	
}


