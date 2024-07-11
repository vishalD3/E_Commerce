package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CommonMethods.ReuseableCommonMethods;

public class OrderPage extends ReuseableCommonMethods {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Pagefactory
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ProductNameList;
	
	
	
	public Boolean VerifyOrderDisplay(String productName) {
		
		Boolean	prodMatch = ProductNameList.stream().anyMatch(cartproduct -> cartproduct.getText()
				.equalsIgnoreCase(productName));
		return prodMatch;
	}
	
	

	

}
