package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

/*
 * Page Object class for the login page
 * Inherits from BasePage, so it automatically gets the driver and element initialization
 */

public class RWOSLoginPage extends BasePage{
	
	@FindBy(xpath = "//input[@id='userName']")
	WebElement txtUserName;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//*[@id='signIn']/span")
	WebElement btnSignIn;

	public void enterUsername(String username) {
        txtUserName.sendKeys(username);
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        btnSignIn.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
	
	
	
}
