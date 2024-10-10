
package formation.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Formation_test.pageObjects.CartePage;
import Formation_test.pageObjects.CheckoutPage;
import Formation_test.pageObjects.ConfirmationPage;
import Formation_test.pageObjects.ProductCatalogue;
import formation.testComponents.BaseTest;



public class LoginTestOrders extends BaseTest {

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder (HashMap<String,String>input) throws IOException, InterruptedException{
		String country="tunisia";
		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"),input.get("password"));
		// Add products in cart 
		List<WebElement> products= productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("product"));
		productCatalogue.addProductToCart(input.get("product"));
		CartePage cartePage = productCatalogue.GoToCartPage(); 
		Thread.sleep(Duration.ofSeconds(2));
		Boolean match = cartePage.verifyProductInCart(input.get("product"));
		Assert.assertTrue(match);
		//go to checkout page 
		CheckoutPage checkoutPage = productCatalogue.GoToCheckOutPage();
		Thread.sleep(Duration.ofSeconds(2));
		checkoutPage.placeOrderMethod(country);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Thread.sleep(Duration.ofSeconds(2));
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data  = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//formation//data//order.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	
	}
		
		
	}



















//		HashMap<String,String> map =new HashMap<String,String>(); 
//		map.put("email","wafatl@gmail.com");
//		map.put("password","Wa@123456");
//		map.put("product","ZARA COAT 3");
//		HashMap<String,String> map1 =new HashMap<String,String>(); 
//
//		map1.put("email","wafaTlili@gmail.com");
//		map1.put("password","Wafatlili@30");
//		map1.put("product","IPHONE 13 PRO");
//		return new Object[][] {{map}, {map1 }};
	
		//return new Object[][] {
			//{"wafatl@gmail.com","Wa@123456","IPHONE 13 PRO"},
			//{"wafaTlili@gmail.com","Wafatlili@30","ZARA COAT 3"}
			//};
	


