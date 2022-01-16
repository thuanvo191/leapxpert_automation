package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lib.action.WebAction;

public class MessagePage extends WebAction {

	@FindBy(xpath = "//*[@class='NavSideHeader_header-title__dUEhQ']")
	public WebElement title_chat;

	@FindBy(xpath = "//*[@class='LeftPanel_container__scroller__3fOJ6']")
	public WebElement panel_left;

	@FindBy(xpath = "//*[@data-testid='link-to-profile-page']")
	public WebElement avatar_profile;

	@FindBy(xpath = "(//*[@class='Account_button-btn__1Ntwm ']//*[contains(@class,'Account_button__1gGYS')])[1]")
	public WebElement btn_devices;

	@FindBy(xpath = "//*[contains(@class,'DeviceTab_link')]")
	public WebElement btn_linkDevice;

	@FindBy(xpath = "//*[contains(@class,'LinkDeviceModal_code-name')]")
	public WebElement lb_activationCode;
	
	@FindBy(xpath = "//*[contains(@class,'LinkDeviceModal_qr-code-wrapper')]")
	public WebElement img_qrCode;
	
	

	public MessagePage() {
		PageFactory.initElements(getDriver(), this);
	}

	public MessagePage verifyMessagePageDisplay() {
		waitVisibility(title_chat, 20);
		assertTrue(panel_left.isDisplayed(), "Verify message page displayed");
		return this;
	}

	public MessagePage viewProfile() {
		avatar_profile.click();
		return this;
	}

	public MessagePage viewDevices() {
		btn_devices.click();
		return this;
	}

	public MessagePage linkDevices() {
		btn_linkDevice.click();
		return this;
	}

	public MessagePage getActivationCode() {
		lb_activationCode.getText();
		return this;
	}
	
	public MessagePage verifyLinkDevicesPopinDisplay() {
		waitVisibility(img_qrCode, 20);
		assertTrue(img_qrCode.isDisplayed(), "Verify link device popin displayed");
		return this;
	}

}
