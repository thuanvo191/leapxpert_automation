package lib.driver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import utilities.Constants;
import utilities.parse.ReadWriteJson;

public interface ISeleniumConfig {
	static DesiredCapabilities buildDesiredCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME,
				ReadWriteJson.getValueFromConfigFile(Constants.BROWSER_NAME));
		capabilities.setCapability(CapabilityType.VERSION,
				ReadWriteJson.getValueFromConfigFile(Constants.PLATFORM_VERSION));
		capabilities.setCapability(CapabilityType.PLATFORM_NAME,
				ReadWriteJson.getValueFromConfigFile(Constants.PLATFORM_NAME));
		capabilities.setCapability(Constants.DEVICE_NAME, ReadWriteJson.getValueFromConfigFile(Constants.DEVICE_NAME));
		capabilities.setCapability(Constants.NEW_COMMAND_TIMEOUT,
				ReadWriteJson.getValueFromConfigFile(Constants.NEW_COMMAND_TIMEOUT));
		capabilities.setCapability(Constants.HUB_URL, ReadWriteJson.getValueFromConfigFile(Constants.HUB_URL));
		capabilities.setCapability(Constants.AUTOMATION_NAME,
				ReadWriteJson.getValueFromConfigFile(Constants.AUTOMATION_NAME));
		capabilities.setCapability(Constants.UDID, ReadWriteJson.getValueFromConfigFile(Constants.UDID));
		capabilities.setCapability(Constants.AUTO_LAUNCH, ReadWriteJson.getValueFromConfigFile(Constants.AUTO_LAUNCH));
		capabilities.setCapability(Constants.LANGUAGE, ReadWriteJson.getValueFromConfigFile(Constants.LANGUAGE));
		capabilities.setCapability(Constants.LOCALE, ReadWriteJson.getValueFromConfigFile(Constants.LOCALE));
		capabilities.setCapability(Constants.ORIENTATION, ReadWriteJson.getValueFromConfigFile(Constants.ORIENTATION));
		capabilities.setCapability(Constants.AUTO_WEBVIEW,
				ReadWriteJson.getValueFromConfigFile(Constants.AUTO_WEBVIEW));
		capabilities.setCapability(Constants.NO_RESET, ReadWriteJson.getValueFromConfigFile(Constants.NO_RESET));
		capabilities.setCapability(Constants.FULL_RESET, ReadWriteJson.getValueFromConfigFile(Constants.FULL_RESET));
		capabilities.setCapability(Constants.APP_NAME, Constants.USER_PATH + ReadWriteJson.getValueFromConfigFile(Constants.APP_NAME));
		return capabilities;
	}

}
