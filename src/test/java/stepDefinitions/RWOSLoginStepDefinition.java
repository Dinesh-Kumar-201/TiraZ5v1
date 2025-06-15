package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pageObjects.RWOSLoginPage;

public class RWOSLoginStepDefinition {

	WebDriver driver;
	RWOSLoginPage loginPage;
	
	public RWOSLoginStepDefinition() {
		super(driver);	//this needs a fix, have to add drivermanager methods, hooks methods
		loginPage = new RWOSLoginPage(driver);
	}
	
	@Given("User is on login page")
	public void user_is_on_login_page() {
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    driver.get("https://sit-rwos.netmeds.com/rwos/#/login");
	    driver.manage().window().maximize();
	}

	@When("User enters {string} and {string}")
	public void user_enters_username_and_password(String username, String password) {
	    loginPage.enterUsername(username);
	    loginPage.enterPassword(password);
		//driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(username);
	    //driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	}

	@And("Click on Sign In button")
	public void click_on_sign_in_button() {
		//driver.findElement(By.xpath("//*[@id='signIn']/span")).click();
		loginPage.clickLogin();
		try {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//button[.//span[text()='Yes']]")
		    ));
		    yesButton.click();
		} catch(Exception e) {
		    System.out.println("Error clicking 'Yes': " + e.getMessage());
		}

		
	}

	@Then("User is navigated to RWOS SIT Dashboard")
	public void user_is_navigated_to_rwos_sit_dashboard() {
//		WebElement salesMenu;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    try {
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(
	    	        By.xpath("//div[contains(text(),'Refreshing cache...')]")
	    	    ));
	    	    System.out.println("Loader gone. Safe to interact.");
	    	    System.out.println("Title: "+driver.getTitle());
		    	Assert.assertEquals(driver.getTitle(), "Netmeds RWOS - BWDI BTY");
//		    salesMenu = wait.until(ExpectedConditions.elementToBeClickable(
//		        By.xpath("//span[@ptooltip='Sales Transactions (Alt+F10)']")
//		    ));
	    	//WebElement salesMenu = driver.findElement(By.xpath("//span[@ptooltip='Sales Transactions (Alt+F10)']"));
	    	//Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Home / Dashboard']")).isDisplayed());
//	    	salesMenu.click();
	    } catch(Exception e) {
	    	//((JavascriptExecutor) driver).executeScript("arguments[0].click();", salesMenu);
	    	System.out.println("Error Message/log: "+e.getMessage()+" :end of error message/log");
	    }
	    driver.close();
	}


	
	
}
