package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;



public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement Country;

	@FindBy(css = ".action__submit")
	WebElement Submit;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement SelectCountry;
	By results = By.cssSelector(".ta-results");

	public void selectCountry(String CountryName) throws InterruptedException {
		   Thread.sleep(3000);

		//Actions a = new Actions(driver);
		GetObjectActionClass().sendKeys(Country, CountryName).build().perform();
		waitForElementToAppear(results);
		jsClick(SelectCountry);
	}
	
	public  ConfirmationPage submitOrder()
	{
		jsClick(Submit);
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}