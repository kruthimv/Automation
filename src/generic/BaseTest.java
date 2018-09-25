package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
	static
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	public WebDriver driver;
	@BeforeMethod
	public void preCondition()
	{
 		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.actitime.com/login.do");
		
	}
	@AfterMethod
	public void postCondition()
	{
		driver.close();
	}

}
