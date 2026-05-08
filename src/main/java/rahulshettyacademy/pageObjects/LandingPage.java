package rahulshettyacademy.pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    // ⭐ 1. private final driver
      WebDriver driver;

    // ⭐ 2. @FindBy FIRST + private final + null (CRITICAL!)
    @FindBy(id = "userEmail")
      WebElement userEmailField = null;

    @FindBy(id = "userPassword")
      WebElement userPasswordField = null;

    @FindBy(id = "login")
      WebElement loginButton = null;

    @FindBy(css = "[class*='flyInOut']")
      WebElement errorMessage = null;

    // ⭐ 3. SINGLE Clean Constructor
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ⭐ 4. Fluent Navigation (Best Practice)
    public LandingPage goTo() {
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        return this;  // Chainable
    }

    // ⭐ 5. Business Method (High-Level API)
    public ProductCatalogPage login(String username, String password) {
        userEmailField.sendKeys(username);
        userPasswordField.sendKeys(password);
        //loginButton.click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        // 1. Scroll to center
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", loginButton);
        
        // 2. Wait clickable
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        
        // 3. JS Click (Bypasses ALL overlays)
        js.executeScript("arguments[0].click();", loginButton);

        return new ProductCatalogPage(driver);
    }

    // ⭐ 6. Error Validation (Uses AbstractComponent)
    public String getErrorMessage() {
    	waitForWebElmentToAppear(errorMessage);  // Fixed typo
        return errorMessage.getText();
    }

    
}