package allureReports;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	
	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initialize_driver() {		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		tDriver.set(driver);		
		return getDriver();		
	}

	public static synchronized WebDriver getDriver() {		
		return tDriver.get();
	}

}
