package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogPage extends AbstractComponent{

	WebDriver driver;

	public ProductCatalogPage(WebDriver driver) {

		super(driver);
		this.driver = driver;// Once object is created this will intilize Local driver
		PageFactory.initElements(driver, this);// Once object is created PageFactory.initElements will initilize element
	}

	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		@FindBy(css = ".mb-3")
		List <WebElement> products;

	    By productBy = By.cssSelector(".mb-3");

	    By toastMessage = By.cssSelector("#toast-container");
	    
	    @FindBy(css = ".ng-animating")
		WebElement spinner;
	    
		public List<WebElement> getProductList() {

			waitForElementToAppear(productBy);
			return products;
			
		}
		
		public WebElement getProductByname(String productName)
		{
			WebElement prod = getProductList().stream()
					.filter(s -> s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst()
					.orElse(null);
			return prod;

		}
		
		//WebElement addToCartBtn = prod.findElement(By.cssSelector(".card-body button:last-of-type"));
		
		public void addProductToCart(String productName ) {
	    	
	    	WebElement prod = getProductByname(productName);
	    		    WebElement addToCartBtn = prod.findElement(By.cssSelector(".card-body button:last-of-type"));
	    		    jsClick(addToCartBtn);
	    		    waitForElementToAppear(toastMessage); 
	    		    waitForElmentToDisapper(spinner);
	    		    
	    }
}

