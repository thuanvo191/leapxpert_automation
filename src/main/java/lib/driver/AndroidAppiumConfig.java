package lib.driver;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import utilities.Constants;
import utilities.parse.ReadWriteJson;

public class AndroidAppiumConfig implements ISeleniumConfig {

    public AppiumDriver getDriver() {
        DesiredCapabilities capabilities = ISeleniumConfig.buildDesiredCapabilities();
        capabilities.setCapability(Constants.APP_PACKAGE, ReadWriteJson.getValueFromConfigFile(Constants.APP_PACKAGE));
        capabilities.setCapability(Constants.APP_ACTIVITY, ReadWriteJson.getValueFromConfigFile(Constants.APP_ACTIVITY));

        try {
            return new AndroidDriver<MobileElement>(
                    new URL(ReadWriteJson.getValueFromConfigFile(Constants.HUB_URL)),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
