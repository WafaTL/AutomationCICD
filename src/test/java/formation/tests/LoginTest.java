package formation.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
@Test
	public void loginTetsOriginal() {
		String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		// login in 
		driver.findElement(By.id("userEmail")).sendKeys("wafaTlili@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Wafatlili@30");
		driver.findElement(By.id("login")).click();
		// check login in success 
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']")));
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));
		// Add products in cart 
		WebElement prod = products.stream().filter(product->
		product.findElement(By.xpath("//div[@class='card-body']/h5")).getText().equals(productName)).findFirst().orElse(null);
	    prod.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();

	    driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	    
	    // verify items in cart 
	   List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
	  items.stream().filter(item->item.getText().equalsIgnoreCase(productName));
		  Boolean match = items.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		  Assert.assertTrue(match);
		  // checkout 
		  WebElement submit = driver.findElement((By.cssSelector(".totalRow button")));
		  JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", submit);
		  driver.findElement(By.cssSelector(".totalRow button")).click();
		 Actions a = new Actions(driver);
		 a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"tunisia").build().perform();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		 driver.findElement(By.xpath("//button[contains(@class,'ta-item')]")).click();
		 WebElement submit_ = driver.findElement(By.cssSelector(".action__submit"));
		 js.executeScript("arguments[0].click();", submit_);
		 String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		 Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
		 
	 
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}

}
