package rahulshettyacademy.test;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValiationTest extends BaseTest {

	@Test(groups = {"Errorhandling"})
	public void LoginErrorValiation() throws InterruptedException, FileNotFoundException, IOException {

		// String productname = "ZARA COAT 3";
		// ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		landingPage.login("swapnilyjoshi@gmail.com", "Edenred20183*");
		landingPage.getErrorMessage();
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

	}
	
	@Test
	public void ProductErrorValiation() throws InterruptedException, FileNotFoundException, IOException {
		
			String productname  = "ZARA COAT 3";
			ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
			landingPage.login("swapnilyjoshi@gmail.com","Edenred2018*");		
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			List<WebElement>products= productCatalogPage.getProductList();
			productCatalogPage.addProductToCart(productname);
			CartPage cartPage =productCatalogPage.navigateToCartPage();
		Boolean	match =cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
				
				
		}


}
