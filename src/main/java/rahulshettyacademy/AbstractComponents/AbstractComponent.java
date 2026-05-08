package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;


public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;
    
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	public CartPage navigateToCartPage()
 	{
 		jsClick(cartButton);
 		CartPage cartPage = new CartPage(driver);
 		return cartPage;
 	}

	public OrderPage navigateToOrderPage()
 	{
 		jsClick(orderHeader);
 		OrderPage orderPage = new OrderPage(driver);
 		return orderPage;
 	}
	
	
	public void waitForElementToAppear(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));

	}
	
	public void waitForWebElmentToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));

	}


	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void waitForElmentToDisapper(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public Actions GetObjectActionClass()
	{
		Actions action = new Actions(driver);
		return action;
	}
}
