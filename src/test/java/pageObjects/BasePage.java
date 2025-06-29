package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import factory.DriverManager;


/*
 * Base class for all PageObject classes 
 * Holds common logic like driver initialization and PageFactory
 */

public class BasePage {
	
	protected WebDriver driver;	//protected - so that only subclasses can use this driver
	
	//driver is not passed, bcs it is centally managed through DriverManager
	public BasePage() {
		//fetches current thread's driver instance
		this.driver = DriverManager.getDriver();
		//initializes all @FindBy WebElements for the subclass page object
		PageFactory.initElements(driver, this);
	}

}
