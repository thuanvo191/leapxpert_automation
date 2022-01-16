package testNG.listeners;



import com.aventstack.extentreports.*;
import com.aventstack.extentreports.gherkin.model.Feature;
import lib.driver.DriverManager;
import lib.services.ScreenshotTaker;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import lib.services.ExtentReport;



public class TestListener extends ExtentReport implements ITestListener {

	private static ExtentReports extent = null;
	public static ExtentTest extentTest;
	private static MediaEntityModelProvider provider;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();


	// helps to generate the logs in test report.
	public void onTestStart(ITestResult result) {
		extent = ExtentReport.getExtentReports();
		extentTest = extent.createTest(Feature.class,result.getParameters()[1].toString(), result.getParameters()[0].toString());
		setExtentTest();
	}

	public void onTestSuccess(ITestResult result) {
		if (getExtentTest().getStatus() == Status.ERROR) {
			getExtentTest().log(Status.ERROR, "Test error");
		} else {
			getExtentTest().log(Status.PASS, "Test passed");
		}
	}

	public void onTestFailure(ITestResult testResult) {
		String methodName  = testResult.getName().toString().trim();
		String failedScreenShot = ScreenshotTaker.takeScreenShot(methodName);
		if (failedScreenShot == null) {
			getExtentTest().log(Status.FAIL, testResult.getThrowable().toString());
		} else {
			provider = ExtentReport.logWithScreenshot(failedScreenShot);
			if (provider != null) {
				getExtentTest().log(Status.FAIL, testResult.getThrowable().toString(), provider);
			} else {
				getExtentTest().log(Status.FAIL, testResult.getThrowable().toString());
			}
		}
	}

	public void onFinish(ITestContext context) {
		ExtentReport.endTest();
		DriverManager.closeDriver();
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {

	}

	public static ExtentTest getExtentTest() {
		return test.get();
	}

	public static ThreadLocal<ExtentTest> setExtentTest() {
		test.set(extentTest);
		return test;
	}
}
