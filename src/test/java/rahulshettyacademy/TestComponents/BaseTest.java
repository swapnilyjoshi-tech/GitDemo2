package rahulshettyacademy.TestComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
    public Properties prop;
    public LandingPage landingPage;

	public WebDriver initilizeDriver() throws FileNotFoundException, IOException
	
	
	{
		
		prop = new Properties();

        String propPath = System.getProperty("user.dir") + 
                "/src/main/java/rahulshettyacademy/resources/GlobalData.properties";

        prop.load(new FileInputStream(propPath));
        
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
        // browserName  = prop.getProperty("browser");
        
        if(browserName.contains("chrome")) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		if(browserName.contains("headless")) {
			option.addArguments("--headless=new");
	        option.addArguments("--window-size=1920,1080");
	        option.addArguments("--disable-gpu");
	        option.addArguments("--no-sandbox");
	        option.addArguments("--disable-dev-shm-usage");
		}
		
		
		driver = new ChromeDriver(option);
		
        }
        else if (browserName.equalsIgnoreCase("edge")) {

    		//WebDriverManager.edgedriver().setup();
            System.setProperty("webdriver.edge.driver", 
                    "C:\\Users\\Swapnil\\Downloads\\edgedriver_win64\\msedgedriver.exe");

    		driver = new EdgeDriver();
    		
            }

        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
        
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String path) throws IOException
	{
		   //String filePath = System.getProperty("user.dir") + 
	         //       "/src/test/java/rahulshettyacademy/data/Purchaseorder.json";
		  
		   File jsonFile = new File(path);
	        String jsonContent = FileUtils.readFileToString(jsonFile, "UTF-8"); 
	        
	        ObjectMapper mapper = new ObjectMapper();
	        List<HashMap<String,String>> data = mapper.readValue
	        		(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
	        return data;
					
	}
	
	public String getsScreenShot(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";

	}


	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws FileNotFoundException, IOException
	{
		driver =initilizeDriver();
		landingPage  = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
		
	}
}
