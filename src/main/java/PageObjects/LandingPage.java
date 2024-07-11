package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CommonMethods.ReuseableCommonMethods;

public class LandingPage extends ReuseableCommonMethods {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//pageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwrd;
	
	@FindBy(id="login")
	WebElement SubmitBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMesage;
	
	
	public void goToUrl() {
		
		driver.get("https://rahulshettyacademy.com/client/");
	}
	

	public ProductCataloguePage LoginApplication (String email, String password) {
		userEmail.sendKeys(email);
		passwrd.sendKeys(password);
		SubmitBtn.click();
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
		return productCataloguePage;
	}
	
	public String loginErrorValidation() {
		
		waitForElementToAppear(errorMesage);
		return errorMesage.getText();
	}
	
	
	
}






