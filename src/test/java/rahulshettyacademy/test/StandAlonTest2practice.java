package rahulshettyacademy.test;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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

public class StandAlonTest2practice {

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
			
		List<WebElement> cartpage = driver.findElements(By.cssSelector(".card-body"));
		// List<WebElement> cartpage = driver.findElements(By.cssSelector(".mb-3"));

		WebElement ls = cartpage.stream().filter(s->s.findElement(By.cssSelector("b"))
				.getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		//ls.forEach(f->System.out.println(f));
		System.out.println("With help of list->>  "+ls.getText());;
		Thread.sleep(2000);
		ls.findElement(By.cssSelector("button:last-of-type")).click();
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> myCartpage = driver.findElements(By.cssSelector(".card-body h3"));
		Boolean bool =  myCartpage.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
		
		
		
		
		//(s->s.findElements("h3"));
		
	
		
	}

}
