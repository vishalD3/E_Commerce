package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import CommonMethods.ReuseableCommonMethods;

public class FinalOrderConfirmationPage extends ReuseableCommonMethods {

	WebDriver driver;
	
	public FinalOrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy (css=".hero-primary")
	WebElement ConfirmMessage;
	
	public String finalMesage(String conFmessaage) {
	String finalMsg = ConfirmMessage.getText();
	 return conFmessaage;
	
	 
	}
	
	

}
