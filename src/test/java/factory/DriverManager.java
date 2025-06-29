package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * This class is responsible for managing webdriver instance in a thread-safe way.
 * Here I have used Threadlocal class to ensure that each thread gets its own 
 * webdriver instance on running the execution in parallel
 */

public class DriverManager {

	// ThreadLocal ensures that each thread has its own WebDriver instance.
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	//This method initializes the webdriver based on the browser passed as a parameter
	public static void initializeDriver(String browser) {
		WebDriver webDriver = null;	//local variable
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup(); //automatically setups the chrome driver
			//customizing the chrome browser settings
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");	//disables browser notification
			options.addArguments("--disable-geolocation");	//disables browser location popup
			options.addArguments("--disable-media-stream");	//disables media stream popup (camera/mic)
			//this will launch chrome browser with added options
			webDriver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
        	webDriver = new EdgeDriver();
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }
		
		driver.set(webDriver);
	}
	
	//This method return the initiated webdriver instance for the current thread
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	//This method quits the webdriver instance for the current thread & removes storage
	public static void quitDriver() {
		if(driver != null) {
			driver.get().quit();	//closes all the browser window and ends the browser session
			driver.remove();	//removes driver instance from threadlocal storage
		}
	}
	
}
