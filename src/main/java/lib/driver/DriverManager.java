package lib.driver;

import org.openqa.selenium.WebDriver;

import utilities.Constants;
import utilities.parse.ReadWriteJson;

public class DriverManager {

	public static String driverTypeValue;
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	

	/**
	 * Build a "local" browser instance
	 */
    
	public static WebDriver buildDriver() {
		WebDriver driver = null;
		driverTypeValue = getDriverType();
		switch (driverTypeValue) {
		case "webDriver":
			driver = new BrowserConfig().getWebDriver();
			break;
		case "androidDriver":
			driver = new AndroidAppiumConfig().getDriver();
			break;
		case "iosDriver":
			driver = new IOSAppiumConfig().getDriver();
			break;
		}

		setWebDriver(driver);
		return driver;
	}

	public static void closeDriver() {
		getDriver().quit();
	}

	public static String getDriverType() {
		return ReadWriteJson.getValueFromConfigFile(Constants.DRIVER_TYPE);
	}
	
	public static WebDriver getDriver() {
		return webDriver.get();
	}

	static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}


}
