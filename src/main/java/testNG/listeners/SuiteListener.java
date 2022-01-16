package testNG.listeners;

import lib.services.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import lib.services.ScreenshotTaker;
import utilities.Constants;

public class SuiteListener implements ISuiteListener {
	public static String configPath;
	public static String browserMode;

	// @Override
	public void onStart(ISuite suite) {
		browserMode = System.getenv("browsermode");
		ScreenshotTaker.cleanUpScreenShotDir();
		configPath = suite.getXmlSuite().getParameter(Constants.CONFIG_NAME);
		ExtentReport.getInstance(suite.getName());
	}

	// @Override
	public void onFinish(ISuite suite) {

	}
}
