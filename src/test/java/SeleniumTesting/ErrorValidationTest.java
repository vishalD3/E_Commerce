package SeleniumTesting;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CheckOutPage;
import PageObjects.LandingPage;
import PageObjects.ProductCataloguePage;
import TestComponents.BaseTest;
import TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	String productName = "ZARA COAT 3";
	
	
	@Test(groups = {"errorHandling"}, retryAnalyzer=Retry.class)
	public void erroValidLoginPage() {
		
		landingPage.LoginApplication("tester1_automation@gmail.com", "Huskar123");
		Assert.assertEquals("Incorrect email or password.", landingPage.loginErrorValidation());
		
	}

	@Test
	public void productNameValidation() {
		
		ProductCataloguePage productCataloguePage = landingPage.LoginApplication("tester1_automation@gmail.com", "Huskar123@#");
		List<WebElement> productList = productCataloguePage.getProductList();	
		productCataloguePage.addProductToCart(productName);
		CheckOutPage checkOut = productCataloguePage.clickOnCart();
		List<WebElement> CartList = checkOut.ListOfCartProduct();
		Boolean prodMatch = checkOut.productMatch("ZARA COAT 33");
		Assert.assertFalse(prodMatch);
	}

}


