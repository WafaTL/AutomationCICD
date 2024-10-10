package formation.testComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Formation_test.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage ;
	public WebDriver InitilizeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Formation_test\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName =  System.getProperty("browser")!=null ? System.getProperty("browser"):
			  prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
		        
			}
			 driver = new ChromeDriver(options);
			 driver.manage().window().setSize(new Dimension(1440,900));
			
		} 
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
			"C:/Users/WAFA/formation-workspace/Login-page-test/geckodriver/geckodriver.exe");	
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			//Edge
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException {
		 driver = InitilizeDriver();
		 landingPage = new LandingPage(driver);
			landingPage.GoTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		
	String jsonContent = FileUtils.readFileToString(new File(filepath),
			StandardCharsets.UTF_8);
		
	ObjectMapper mapper = new ObjectMapper ();
	List<HashMap<String,String>>data = mapper.readValue(jsonContent, new TypeReference<List <HashMap<String,String>>>(){
	});
	return data ;
}
public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver ;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png") ;
		FileUtils.copyFile(source,file );
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
		
		
		
		
	}
  
}
