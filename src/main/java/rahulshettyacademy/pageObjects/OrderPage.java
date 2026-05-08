package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.pageObjects.CheckoutPage;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;

	public OrderPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> CartProducts;
	@FindBy(css= ".totalRow button")
	WebElement Checkout;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productnames;
	
	   
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = CartProducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
		   return match;
	}
	
	public CheckoutPage gotocheckOut()
	{
		jsClick(Checkout);
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	public Boolean verifyOrderDisplay(String productName)
	{
		boolean orderpresent = productnames.stream().anyMatch(rows -> rows.getText().equals(productName));
		return orderpresent;
	}
	
	//List<WebElement> totalRows = driver.findElements(By.cssSelector("td[_ngcontent-gcb-c38]"));
	
	    //.findFirst().orElse(null).getText();
	
}
