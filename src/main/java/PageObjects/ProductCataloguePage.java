package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import CommonMethods.ReuseableCommonMethods;

public class ProductCataloguePage extends ReuseableCommonMethods {
	
	WebDriver driver;
	
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//pageFactory
	@FindBy(css=".mb-3")
	List<WebElement> productList;
	
	@FindBy(css=".ng-animating")
	WebElement animationSpinner;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement ClickCartBtn;
	
	//Locators:
	By products = By.cssSelector(".mb-3");
	By addtoCart = By.cssSelector(".card-body button:last-of-type");
	By toasterMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(products);
		return productList;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod= getProductByName(productName);
		prod.findElement(addtoCart).click();	
		waitForElementToAppear(toasterMessage);
		waitForElementToDisappear(animationSpinner);
	}
	
	public CheckOutPage clickOnCart() {
		waitForElementToAppear(ClickCartBtn);
		ClickCartBtn.click();
		CheckOutPage checkOut = new CheckOutPage(driver);
		return checkOut;
		
	}
		
}






