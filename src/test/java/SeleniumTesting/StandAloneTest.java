package SeleniumTesting;


import java.awt.event.ActionEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		//Screen Maxmize
		driver.manage().window().maximize();
				
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Invoke Url
		driver.get("https://rahulshettyacademy.com/client/");
		
		//Enter email id and password to do login
		driver.findElement(By.id("userEmail")).sendKeys("tester1_automation@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Huskar123@#");
		driver.findElement(By.id("login")).click();
		
		
		
		//Wait until Card is displayed
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//Get text name of the product
		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = productList.stream().filter(product -> product.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		
		//Click and add item in cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//verify if toaster message is displayed after adding product to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//wait until spinner animation
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//click cart btn
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//find text for item in cart section
		
		List<WebElement> cartProductList = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//Check if product text is matching.
		Boolean	prodMatch = cartProductList.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(prodMatch);
		
		//click on Checkout button
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
	     executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[@class='totalRow']/button")));
		
		//used Action class for adding text in dynamic text box
	     Actions a = new Actions(driver);
	     a.moveToElement(driver.findElement(By.cssSelector(".actions a")));
		 a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india")
		.build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//Click on place order button
		
		driver.findElement(By.cssSelector(".actions a")).click();
		
		//Capture Thank you text
		executor.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.cssSelector(".hero-primary")));
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.quit();
	}

}
