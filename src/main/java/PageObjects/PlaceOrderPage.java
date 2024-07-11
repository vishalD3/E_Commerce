package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import CommonMethods.ReuseableCommonMethods;

public class PlaceOrderPage extends ReuseableCommonMethods {
	WebDriver driver;
	
	
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Pagefactory
	
	@FindBy (css= ".actions a")
	WebElement movetoPlaceOrderBtn;
	
	@FindBy (css= "[placeholder='Select Country']")
	WebElement EnterNameCountry;
	
	@FindBy (css= ".ta-results")
	WebElement results;
	
	@FindBy (xpath= "(//button[contains(@class,'ta-item')])[2]")
	WebElement clickResults;
	
	
	public void SearchCountry(String countryName) {
		
		Actions a = new Actions(driver);
		waitForElementToAppear(movetoPlaceOrderBtn);
		a.moveToElement(movetoPlaceOrderBtn);
		 a.sendKeys(EnterNameCountry,"india").build().perform();

	}
	
	public FinalOrderConfirmationPage clickCountryName() {
		waitforElementtoClick(results);
		clickResults.click();
		movetoPlaceOrderBtn.click();
		FinalOrderConfirmationPage FinalPage = new FinalOrderConfirmationPage(driver);
		return FinalPage;
	}
		
}
