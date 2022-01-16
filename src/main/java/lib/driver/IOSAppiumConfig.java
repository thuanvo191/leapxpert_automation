package lib.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.Constants;
import utilities.parse.ReadWriteJson;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSAppiumConfig implements ISeleniumConfig {

    public  AppiumDriver getDriver() {
        DesiredCapabilities capabilities = ISeleniumConfig.buildDesiredCapabilities();
        capabilities.setCapability(Constants.ORG_ID, ReadWriteJson.getValueFromConfigFile(Constants.ORG_ID));
        capabilities.setCapability(Constants.SIGNING_ID, ReadWriteJson.getValueFromConfigFile(Constants.SIGNING_ID));
        capabilities.setCapability(Constants.START_WDP, ReadWriteJson.getValueFromConfigFile(Constants.START_WDP));
        capabilities.setCapability(Constants.USE_WDA, ReadWriteJson.getValueFromConfigFile(Constants.USE_WDA));
        capabilities.setCapability(Constants.BUNDLE_ID, ReadWriteJson.getValueFromConfigFile(Constants.BUNDLE_ID));

        try {
            return new IOSDriver<MobileElement>(new URL(ReadWriteJson.getValueFromConfigFile(Constants.HUB_URL)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
