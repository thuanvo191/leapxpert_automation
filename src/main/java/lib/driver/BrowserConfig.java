package lib.driver;

import static org.openqa.selenium.ie.InternetExplorerDriver.REQUIRE_WINDOW_FOCUS;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utilities.Constants;
import utilities.parse.ReadWriteJson;

public class BrowserConfig {
	private WebDriver driver;
	private String browserType;
	private String browserPath;

	public WebDriver getWebDriver() {
		browserType = ReadWriteJson.getValueFromConfigFile("browserName");
		browserPath = Constants.DRIVER_PATH;
		switch (browserType) {
		case "firefox":
			String firefoxDriverPath = browserPath + "\\geckodriver.exe";
			DesiredCapabilities firefoxCapabilities = new DesiredCapabilities();
			firefoxCapabilities.setJavascriptEnabled(true);
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 0);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.manager.focusWhenStarting", false);
			profile.setPreference("browser.download.useDownloadDir", true);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.setPreference("browser.download.manager.closeWhenDone", true);
			profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
			profile.setPreference("security.enable_java", true);
			profile.setPreference("plugin.state.java", 2);
			firefoxCapabilities.setCapability(FirefoxDriver.MARIONETTE, true);
			firefoxCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
			if (ReadWriteJson.getValueFromConfigFile("localExecution").equals("true")) {
				System.setProperty("webdriver.firefox.driver", firefoxDriverPath);
				driver = new FirefoxDriver();
			} else {
				System.setProperty("webdriver.firefox.driver", firefoxDriverPath);
				try {
					driver = new RemoteWebDriver(new URL(ReadWriteJson.getValueFromConfigFile("hubUrl")),
							firefoxCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			break;

		case "chrome":
			String chromeDriverPath = browserPath + "\\chromedriver.exe";
			DesiredCapabilities chromeCapabilities = new DesiredCapabilities();
			chromeCapabilities.setCapability("chrome.switches", Arrays.asList("--ignore-ssl-errors=yes"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--allow-insecure-localhost=yes");
			options.addArguments("--ignore-urlfetcher-cert-requests=yes");
			options.addArguments("--disable-infobars");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--test-type");
			chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
			chromeCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, false);
			chromeCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			chromeCapabilities.setCapability("browserstack.debug", true);
			chromeCapabilities.setCapability("unexpectedAlertBehaviour", "ignore");
			if (ReadWriteJson.getValueFromConfigFile("localExecution").equals("true")) {
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver(options);
			} else {
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				try {
					driver = new RemoteWebDriver(new URL(ReadWriteJson.getValueFromConfigFile("hubUrl")),
							chromeCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			}
			break;

		case "ie":
			String ieDriverPath = browserPath + "\\IEDriverServer.exe";
			DesiredCapabilities ieCapabilities = new DesiredCapabilities();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			ieCapabilities.setCapability(REQUIRE_WINDOW_FOCUS, false);
			ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			ieCapabilities.setCapability("enablePersistentHover", false);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setJavascriptEnabled(true);
			ieCapabilities.setCapability("browserstack.ie.enablePopups", "true");
			if (ReadWriteJson.getValueFromConfigFile("localExecution").equals("true")) {
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				driver = new InternetExplorerDriver();
			} else {
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				try {
					driver = new RemoteWebDriver(new URL(ReadWriteJson.getValueFromConfigFile("hubUrl")),
							ieCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

			break;
		default:
			break;

		}
		driver.manage().timeouts().implicitlyWait(Constants.SE_WAIT_IN_SECOND, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
