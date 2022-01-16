package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lib.action.WebAction;
import lib.services.Logger;
import utilities.parse.ReadWriteJson;

public class LoginPage extends WebAction {

	@FindBy(xpath = "//*[@placeholder ='Company']")
	public WebElement input_companyName;

	@FindBy(xpath = "//*[@class ='Login_button__146BY Login_otp__2YBY6 ']")
	public WebElement btn_next;

	@FindBy(xpath = "//*[@placeholder ='Username']")
	public WebElement input_username;

	@FindBy(xpath = "//*[@placeholder ='Password']")
	public WebElement input_password;

	@FindBy(xpath = "//*[@class ='Login_button__146BY']")
	public WebElement btn_login;

	@FindBy(xpath = "//*[@type='tel']")
	public List<WebElement> input_securityCode;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public LoginPage open() {
		String url = ReadWriteJson.getValueFromConfigFile("url");
		openBrowser(url);
		Logger.log(this.getClass().getName(), "Open url", Logger.LogLevel.INFO);
		return this;
	}

	public LoginPage fillUserName(String username) {
		input_username.sendKeys(username);
		return this;
	}
	
	public LoginPage fillCompanyName(String companyName) {
		input_companyName.sendKeys(companyName);
		return this;
	}

	public LoginPage fillPassword(String password) {
		input_password.sendKeys(password);
		return this;
	}

	public LoginPage fillSecurityCode(String security) {
		for (int i = 0; i < input_securityCode.size(); i++) {
			String key = Character.toString(security.charAt(i));
			input_securityCode.get(i).sendKeys(key);
		}

		return this;
	}

	public LoginPage next() {
		btn_next.click();
		return this;
	}

	public LoginPage submit() {
		btn_login.click();
		return this;
	}

}
