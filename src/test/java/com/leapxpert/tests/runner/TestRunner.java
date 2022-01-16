package com.leapxpert.tests.runner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import lib.driver.DriverManager;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.leapxpert.tests.stepdefinition" }, plugin = {
		"pretty:target/site/pretty/report.html", "html:target/site/cucumber-pretty", "json:target/site/cucumber.json" })
public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	public WebDriver driver;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		DriverManager.buildDriver();
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
		// the 'featureWrapper' parameter solely exists to display the feature file in a
		// test report
		driver = DriverManager.getDriver();
		testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());

	}

	/**
	 * Returns two dimensional array of PickleEventWrapper scenarios with their
	 * associated CucumberFeatureWrapper feature.
	 *
	 * @return a two dimensional array of scenarios features.
	 */
	@DataProvider(name = "scenarios")
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
	}

}
