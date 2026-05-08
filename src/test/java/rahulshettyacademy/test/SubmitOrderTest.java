package rahulshettyacademy.test;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.data.jsonDataReader;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogPage;
import DataModel.LandingPageData;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input)
			throws InterruptedException, FileNotFoundException, IOException {

		ProductCatalogPage productCatalogPage = landingPage.login(input.get("email"), input.get("password"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> products = productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogPage.navigateToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.gotocheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalogPage productCatalogPage = landingPage.login("swapnilyjoshi@gmail.com", "Edenred2018*");
		OrderPage orderPage = productCatalogPage.navigateToOrderPage();
		Boolean match = orderPage.verifyOrderDisplay(productname);
		Assert.assertTrue(match);

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		 * HashMap<Object,Object> map = new HashMap<Object,Object>();
		 * map.put("uname","swapnilyjoshi@gmail.com"); map.put("pass","Edenred2018*");
		 * map.put("productname","ZARA COAT 3");
		 * 
		 * HashMap<Object,Object> map1 = new HashMap<Object,Object>();
		 * map1.put("uname","swapnilyjoshi@gmail.com"); map1.put("pass","Edenred2018*");
		 * map1.put("productname","ADIDAS ORIGINAL");
		 */

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/rahulshettyacademy/data/LandingPage.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

		// String data[][] = {{"swapnilyjoshi@gmail.com","Edenred2018*","ZARA COAT
		// 3"},{"swapnilyjoshi@gmail.com","Edenred2018*","ZARA COAT 3"}};
		// return data;
	}

	/*
	 * @DataProvider public Object[][] getDataOLD() {
	 * 
	 * 
	 * String data[][] = {{"swapnilyjoshi@gmail.com","Edenred2018*","ZARA COAT 3"},{
	 * "swapnilyjoshi@gmail.com","Edenred2018*","ZARA COAT 3"}}; return data; }
	 */

	@Test(dataProvider = "getDataProduct")
	public void submitOrder2(LandingPageData landingPageData)
			throws InterruptedException, FileNotFoundException, IOException {

		ProductCatalogPage productCatalogPage = landingPage.login(landingPageData.getEmail(), landingPageData.getPassword());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> products = productCatalogPage.getProductList();
		productCatalogPage.addProductToCart(landingPageData.getProduct());
		CartPage cartPage = productCatalogPage.navigateToCartPage();
		Boolean match = cartPage.verifyProductDisplay(landingPageData.getProduct());
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.gotocheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		
		landingPageData.setProduct("ADIDAS ORIGINALXXXX");
		System.out.println("After each run"+landingPageData.getProduct());
		System.out.println("commit from gixx");
		System.out.println("commit from gixx2");

	}

	@DataProvider
	public Object[][] getDataProduct() {
		return jsonDataReader.jsonDataAsReader("./src/test/java/rahulshettyacademy/data/LandingPage.json",
				new TypeReference<List<LandingPageData>>() {
				});

		
		
	}
	

}
