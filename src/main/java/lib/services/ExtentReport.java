package lib.services;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lib.driver.DriverManager;
import org.openqa.selenium.Platform;
import testNG.listeners.TestListener;
import utilities.Constants;
import utilities.Utility;

import java.io.IOException;
import java.nio.file.Paths;

public class ExtentReport {

    public static ExtentReports extentReportConfig, extentReport;
    private static Platform platform;
    public static ExtentTest test = null;
    private static MediaEntityModelProvider provider;

    //Create an extent report instance
    //Select the extent report file location based on platform
    public static ExtentReports getInstance(String reportName) {
        return extentReport == null ? createInstance(reportName) : extentReport;
    }

    public static ExtentReports getExtentReports() {
        extentReport = extentReportConfig;
        return extentReport;
    }

    public static ExtentReports createInstance(String reportName) {
        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform, reportName);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        ExtentHtmlReporterConfiguration config = htmlReporter.config();
        config.setTestViewChartLocation(ChartLocation.BOTTOM);
        config.setChartVisibilityOnOpen(true);
        config.setTheme(Theme.STANDARD);
        config.setDocumentTitle(fileName);
        config.setEncoding("utf-8");
        config.setReportName(fileName);
        extentReportConfig = new ExtentReports();
        extentReportConfig.attachReporter(htmlReporter);
        return extentReportConfig;
    }



    //Select the extent report file location based on platform
    private static String getReportFileLocation(Platform platform, String reportName) {

        Utility.createReportPath(Paths.get(Constants.REPORT_PATH).toString());
        return Paths.get(Constants.REPORT_PATH, reportName.concat(Constants.HTML_SUFFIX)).toString();
    }

    // Get current platform
    private static Platform getCurrentPlatform() {
        return platform == null ? Platform.getCurrent() : platform;
    }

   public static MediaEntityModelProvider logWithScreenshot(Status logStatus, String step, String screenshotPath) {

        if (TestListener.getExtentTest() != null) {
            try {
                provider = MediaEntityBuilder
                        .createScreenCaptureFromPath(screenshotPath).build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (provider != null) {
                TestListener.getExtentTest().log(logStatus, step, provider);
            } else {
                TestListener.getExtentTest().log(logStatus, step);
            }

        }
        return provider;
    }

    public static MediaEntityModelProvider logWithScreenshot(String screenshotPath) {

        if (TestListener.getExtentTest() != null) {
            try {
                provider = MediaEntityBuilder
                        .createScreenCaptureFromPath(screenshotPath).build();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return provider;
    }

    public static void log(Status testStatus, String step) {
        if (DriverManager.buildDriver() == null) {
            return;
        }
        if (ExtentReport.getExtentReports() != null) {
                TestListener.getExtentTest().log(testStatus,step);
        }
    }

    public static void endTest() {
        if (extentReport != null) {
            extentReport.flush();
        }
    }

}
