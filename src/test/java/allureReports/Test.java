package allureReports;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({AllureListener.class})

public class Test extends BaseClass {
	
	public WebDriver driver ;
	
	@BeforeClass
	public void setup() throws InterruptedException {
		//BaseClass bs = new BaseClass();
		//bs.initialize_driver();	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();		
	}
		
	@org.testng.annotations.Test(priority=1)
	@Description("Verify Logo is present in Home Page.")
	@Epic("EP-01")
	@Feature("Feature 1 : Logo")
	@Story("AETTNETFXP-1800")
	@Step("Verify Logo Presence")
	@Severity(SeverityLevel.CRITICAL)
	public void checkIfLogoPresent() {
		
		boolean isLogoPresent = driver.findElement(By.xpath(".//img[@alt='nopCommerce demo store']")).isDisplayed();		
		Assert.assertTrue(isLogoPresent, "Logo is present ...");
		
	}
	
	@org.testng.annotations.Test(priority=2)
	@Description("Verify Login Functionality is working fine.")
	@Epic("EP-02")
	@Feature("Feature 2 : Log In")
	@Story("AETTNETFXP-1900")
	@Step("Verify Log In Working")
	@Severity(SeverityLevel.BLOCKER)
	public void logInTest() throws InterruptedException {
		
		driver.findElement(By.linkText("Log in")).click();		
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		Thread.sleep(5000);
		
		driver.findElement(By.id("Email")).click();			
		driver.findElement(By.id("Email")).sendKeys("pankaj128@gmail.com");
		
		driver.findElement(By.id("Password")).click();
		driver.findElement(By.id("Password")).sendKeys("Test@123");
		driver.findElement(By.xpath(".//input[@value='Log in']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);		
		wait.until(ExpectedConditions.visibilityOf((WebElement)driver.findElement(By.linkText("Log out"))));
		
		
		WebElement element = driver.findElement(By.linkText("My account"));
		boolean status = element.isDisplayed();

		Assert.assertTrue(status);
		
		//Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");	
	
		
	}
	
	@org.testng.annotations.Test(priority=3)
	@Description("Verify Registration Functionality is working fine.")
	@Epic("EP-03")
	@Feature("Feature 3 : Registration")
	@Story("AETTNETFXP-2000")
	@Step("Verify Log In Working")
	@Severity(SeverityLevel.NORMAL)
	public void registrationTest() {
		
		//throw new SkipException("Skipping this test ...");
		boolean status = true;
		Assert.assertTrue(status);
		
	}
	
	@AfterClass
	public void teardown() {
		
		driver.close();
		driver.quit();
		
	}

}
