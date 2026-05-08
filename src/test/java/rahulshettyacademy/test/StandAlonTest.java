package rahulshettyacademy.test;

import static org.testng.Assert.assertTrue;

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

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlonTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	
		String productname  = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("swapnilyjoshi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Edenred2018*");
		driver.findElement(By.id("login")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)).findFirst()
				.orElse(null);

		WebElement addToCartBtn = prod.findElement(By.cssSelector(".card-body button:last-of-type"));
        
		js.executeScript("arguments[0].click();", addToCartBtn);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		WebElement cart =  driver.findElement(By.cssSelector("[routerlink*='cart']"));
		   js.executeScript("arguments[0].click();", cart);
		
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartWrap li"));

		boolean zaraFound = cartItems.stream()
			    .anyMatch(item -> item.findElement(By.cssSelector(".cartSection h3")).getText().equalsIgnoreCase(productname));

			Assert.assertTrue(zaraFound);
			
			WebElement Checkout  = driver.findElement(By.cssSelector(".totalRow button"));
			js.executeScript("arguments[0].click();", Checkout);
			Thread.sleep(2000);
			Actions a = new Actions(driver);
			a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind").build().perform();	
			
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
			   WebElement country  = driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));
			   js.executeScript("arguments[0].click();", country);		
			   WebElement submit  = driver.findElement(By.cssSelector(".action__submit"));
			   js.executeScript("arguments[0].click();", submit);		 
			driver.findElement(By.cssSelector(".hero-primary")).getText();
			String success = driver.findElement(By.cssSelector(".hero-primary")).getText();
			Assert.assertEquals(success, "THANKYOU FOR THE ORDER.");
			
			
			
	}

}
