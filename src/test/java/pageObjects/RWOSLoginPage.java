package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class RWOSLoginPage extends BasePage{
	
	public RWOSLoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='userName']")
	WebElement txtUserName;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//*[@id='signIn']/span")
	WebElement btnSignIn;

	public WebElement getTxtUserName() {
		return txtUserName;
	}

	public void setTxtUserName(WebElement txtUserName) {
		this.txtUserName = txtUserName;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(WebElement txtPassword) {
		this.txtPassword = txtPassword;
	}

	public WebElement getBtnSignIn() {
		return btnSignIn;
	}

	public void setBtnSignIn(WebElement btnSignIn) {
		this.btnSignIn = btnSignIn;
	}
	
	
	
}
