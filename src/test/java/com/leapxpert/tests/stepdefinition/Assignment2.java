package com.leapxpert.tests.stepdefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.LoginPage;
import pages.MessagePage;

public class Assignment2 {

	LoginPage loginPage;
	MessagePage messagePage;

	@Given("Access the web application")
	public void access_the_web_application() {
		loginPage = new LoginPage();
		loginPage.open();
	}

	@When("Enter the company name as {string}")
	public void enter_the_company_name_as(String string) {
		loginPage.fillCompanyName(string);
	}

	@When("Click on Next button")
	public void click_on_Next_button() {
		loginPage.next();
	}

	@When("Enter the Username  as {string}")
	public void enter_the_Username_as(String string) {
		loginPage.fillUserName(string);
	}

	@When("Enter the Password  as {string}")
	public void enter_the_Password_as(String string) {
		loginPage.fillPassword(string);
	}

	@When("Click on Login button")
	public void click_on_Login_button() {
		loginPage.submit();
	}

	@When("Enter the security code as {string}")
	public void enter_the_security_code_as(String string) {
		loginPage.fillSecurityCode(string);
	}

	@Then("Verify Message Homepage display")
	public void verify_Message_Homepage_display() {
		messagePage = new MessagePage();
		messagePage.verifyMessagePageDisplay();
	}

	@When("Click on profile icon to view My profile")
	public void click_on_profile_icon_to_view_My_profile() {
		messagePage.viewProfile();
	}

	@When("Click on Devices on My profile")
	public void click_on_Devices_on_My_profile() {
		messagePage.viewDevices();
	}

	@When("Click on Link Device")
	public void click_on_Link_Device() {
		messagePage.linkDevices();
	}

	@Then("Verify Link Device popin display")
	public void verify_Link_Device_popin_display() {
		messagePage.verifyLinkDevicesPopinDisplay();
	}

	@Then("Get activation code")
	public void get_activation_code() {
		messagePage.getActivationCode();
	}

}
