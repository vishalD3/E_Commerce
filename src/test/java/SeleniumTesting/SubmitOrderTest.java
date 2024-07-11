package SeleniumTesting;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CheckOutPage;
import PageObjects.FinalOrderConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.OrderPage;
import PageObjects.PlaceOrderPage;
import PageObjects.ProductCataloguePage;
import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test (dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		
		
		String countryName = "india";
		String finalMsg = "THANKYOU FOR THE ORDER";
		
	
		ProductCataloguePage productCataloguePage = landingPage.LoginApplication(input.get("email"), input.get("Password"));
		List<WebElement> productList = productCataloguePage.getProductList();	
		productCataloguePage.addProductToCart(input.get("product"));
		CheckOutPage checkOut = productCataloguePage.clickOnCart();
		List<WebElement> CartList = checkOut.ListOfCartProduct();
		Boolean prodMatch = checkOut.productMatch(input.get("product"));
		Assert.assertTrue(prodMatch);
		PlaceOrderPage placeOrderPage = checkOut.clickCheckOut();
		placeOrderPage.SearchCountry(countryName);
		FinalOrderConfirmationPage FinalPage = placeOrderPage.clickCountryName();		
		FinalPage.finalMesage(finalMsg);
		Assert.assertTrue(finalMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
		
	}
	
	
		@Test (dependsOnMethods = {"submitOrder"})
		public void OrderHistoryTest() {
			ProductCataloguePage productCataloguePage = landingPage.LoginApplication("tester1_automation@gmail.com", "Huskar123@#");
			OrderPage orderPage = productCataloguePage.goToOrdersPage();
			Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
			
		}
		
		
		@DataProvider
		public Object[][] getData() throws IOException{
			
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//puchaseData//PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
		}
		
}
		
		
//		@DataProvider
//		public Object[][] getData() {
//			return new Object[][] {{"tester1_automation@gmail.com","Huskar123@#","ZARA COAT 3"},{"tester1_automation@gmail.com","Huskar123@#","IPHONE 13 PRO"}};
//		}
		
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		
//		map.put("email", "tester1_automation@gmail.com");
//		map.put("Password", "Huskar123@#");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		
//		map1.put("email", "tester1_automation@gmail.com");
//		map1.put("Password", "Huskar123@#");
//		map1.put("product", "IPHONE 13 PRO");		


