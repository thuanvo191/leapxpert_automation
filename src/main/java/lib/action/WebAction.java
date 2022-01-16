package lib.action;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.utils.ExceptionUtil;

import lib.driver.DriverManager;
import lib.services.Logger;
import testNG.listeners.SuiteListener;


public class WebAction extends DriverManager {

	/**
	 * open url according size use browser mode to simulator size of website on
	 * desktop incase there's no mobile devices and web responsive
	 * 
	 * @param url
	 */
	public void openBrowser(String url) {
		String browserSize = SuiteListener.browserMode;
		switch (browserSize) {
		case "desktop":
			getDriver().manage().window().maximize();
			break;
		case "smartphone":
			if (getDriverType().equals("webDriver"))
				getDriver().manage().window().setSize(new Dimension(400, 800));
			break;
		case "tablet":
			if (getDriverType().equals("webDriver"))
				getDriver().manage().window().setSize(new Dimension(1024, 800));
			break;
		default:
			getDriver().manage().window().maximize();
			break;
		}

		getDriver().get(url);

	}

	public void scrollPage(int nPixels) {
		((JavascriptExecutor) getDriver()).executeScript("scroll(0, arguments[0]);", nPixels);
	}

	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void executeJS(String script) {
		((JavascriptExecutor) getDriver()).executeScript("script");
	}

	/**
	 * Assert two objects
	 * 
	 * @param expected
	 * @param actual
	 * @param description
	 */
	public void assertEquals(Object expected, Object actual, String description) {
		try {
			if (expected.equals(actual)) {
				Logger.log(this.getClass().getName(), description + " is OK", Logger.LogLevel.INFO);
			} else {
				Logger.log(this.getClass().getName(), description + " is not OK", Logger.LogLevel.WARNING);
			}
		} catch (Exception ex) {
			Logger.log(this.getClass().getName(), ExceptionUtil.getStackTrace(ex), Logger.LogLevel.FAIL);
		}
	}

	/**
	 * Assert contains text
	 * 
	 * @param expected
	 * @param actual
	 * @param description
	 */
	public void assertContains(String expected, String actual, String description) {
		try {
			if (expected.contains(actual)) {
				Logger.log(this.getClass().getName(), description, Logger.LogLevel.INFO);
			} else {
				Logger.log(this.getClass().getName(), description, Logger.LogLevel.WARNING);
			}
		} catch (Exception ex) {
			Logger.log(this.getClass().getName(), ExceptionUtil.getStackTrace(ex), Logger.LogLevel.FAIL);
		}
	}

	/**
	 * Assert element is displayed or other condition return true
	 * 
	 * @param condition
	 * @param message
	 */
	public void assertTrue(Boolean condition, String message) {
		try {
			if (condition) {
				Logger.log(this.getClass().getName(), message, Logger.LogLevel.INFO);
			} else {
				Logger.log(this.getClass().getName(), message, Logger.LogLevel.WARNING);
			}
		} catch (Exception ex) {
			Logger.log(this.getClass().getName(), ExceptionUtil.getStackTrace(ex), Logger.LogLevel.FAIL);
		}
	}

	/**
	 * Focus on field, blacked out and clear all text
	 * 
	 * @param element
	 */
	public void clear(WebElement element) {
		element.click();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		element.sendKeys(selectAll);
		element.clear();
	}

	/**
	 * wait element visible
	 * 
	 * @param element
	 * @param timeOut
	 */
	public void waitVisibility(WebElement element, long timeOut) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
