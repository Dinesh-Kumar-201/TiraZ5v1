package hooks;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import factory.DriverManager;
import io.cucumber.java.*;
import utilities.TestProperties;

public class MyHooks {
	
	@BeforeAll
	public void setupSuite() {
	    System.setProperty("allure.results.directory", "target/allure-results");
	}

	
	/*
	 * "Before" annotation used to run the method before every scenario
	 * This method initializes the browser and sets up the session
	 */
	@Before
	@Parameters("browser")
	public void setup(@Optional String browser) {
		
		//if browser is null or explicitly not passed
		if(browser == null || browser.equalsIgnoreCase("null") || browser.trim().isEmpty()) {
			//try getting the browser from config.properties
			//create an instance of the TestProperties class to access config values
			TestProperties tp = new TestProperties();
			
			//read the browser name from the config.properties file
			browser = tp.getProperty("browser"); //read the browser name from the config.properties file
			//fallback to hardcoded default browser if still browser is null
			if(browser == null || browser.trim().isEmpty()) {
				browser = "chrome";
			}
		}
		
		// Initialize the WebDriver instance using the browser name
		DriverManager.initializeDriver(browser);
		
		//fetch the WebDriver instance for the current thread
		WebDriver driver = DriverManager.getDriver();
		
		//clean cookies to avoid session leftovers between tests
		driver.manage().deleteAllCookies();
		
		//maximize the browser window for better visibility
		driver.manage().window().maximize();
		
		//set implicit wait to handle dynamic elements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	/*
	 * "After" annotation used to run the method after every scenario
	 * This method takes screenshot on failure and closes the browser
	 */
	@After
	public void tearDown(Scenario scenario) {
		//get the scenario name and replace spaces with underscores for screenshot naming
		String scenarioName = scenario.getName().replaceAll(" ", "_");
		
		//check if the scenario has failed
		if(scenario.isFailed()) {
			
			//capture a screenshot in byte format from the current browser instance
			byte[] screenshot = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			
			//attach the screenshot to the Cucumber report
			scenario.attach(screenshot, "image/png", scenarioName);
		}
		
		//quit the WebDriver instance and remove it from ThreadLocal storage
		DriverManager.quitDriver();
	}
}
