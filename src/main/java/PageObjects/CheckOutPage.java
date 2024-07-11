package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CommonMethods.ReuseableCommonMethods;

public class CheckOutPage extends ReuseableCommonMethods {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Pagefactory
	
	@FindBy(css=".cartSection h3")
	List<WebElement> CartList;
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement clickCheckOutBtn;
	
	
	public List<WebElement> ListOfCartProduct() {
		
		List<WebElement> cartProductList= CartList;
		return CartList;
	}

	public Boolean productMatch(String productName) {
		
		Boolean	prodMatch = ListOfCartProduct().stream().anyMatch(cartproduct -> cartproduct.getText()
				.equalsIgnoreCase(productName));
		return prodMatch;
	}
	
	
	public PlaceOrderPage clickCheckOut() {
		jScriptExecutorClick(clickCheckOutBtn);
		PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
		return placeOrderPage;
	}
	
	

}
