package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners(Result.class)
public abstract class BaseTest implements IAutoConst {
	public WebDriver driver;
	public String url = Utility.getPropertyValue(CONFIG_PATH, "URL");

	String strITO = Utility.getPropertyValue(CONFIG_PATH, "ITO");
	public long lngITO = Long.parseLong(strITO);

	public String strETO = Utility.getPropertyValue(CONFIG_PATH, "ETO");
	public long lngETO = Long.parseLong(strETO);

	static {
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
	}
	//

	@Parameters({ "ip", "browser" })
	@BeforeMethod
	public void openApp(@Optional("localhost") String ip, @Optional("chrome") String browser) {
		driver = Utility.openBrowser(driver, "localhost", "chrome");
		// driver = new ChromeDriver();// here remove webdriver , because to avoid null
		// pointer exception
		// in the line 29 we have to remove webdriver in the line 19, we have to
		// initialize the driver
		// varriable globally
		driver.manage().window().maximize();
		// System.out.println(duruation);
		driver.manage().timeouts().implicitlyWait(lngITO, TimeUnit.SECONDS);
		driver.get(url);

	}

	@AfterMethod

	public void closeApp(ITestResult result) {
		String name = result.getName();
		int status = result.getStatus();
		if (status == 2) {
			String path = Utility.getPhoto(driver, PHOTO_PATH);
			Reporter.log("Test:" + name + "is Failed and Photo is:" + path, true);

		} else {
			Reporter.log("Test:" + name + "is Passed", true);
		}
		driver.quit();
	}

}
