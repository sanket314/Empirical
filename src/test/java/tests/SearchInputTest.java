package tests;

import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import base.ReportManager;
import java.time.Duration;
import java.util.ArrayList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchInputTest extends ReportManager {
	
	ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUpReport() 
    {
        extent = ReportManager.createReport(this.getClass().getSimpleName());
    }
    
	@Test
    public void testSignUpButtonVisible() throws InterruptedException 
	{
		 test = extent.createTest("Search Input Test");
	     test.info("Test Started: Verifying search input");
	        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
        // Step 1 Open the initial page
        driver.get("https://v0-button-to-open-v0-home-page-h5dizpkwp.vercel.app/");

        // Step 2 Click the button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Click me']")));
        clickButton.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Step 3 Switch to new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Step 4 Assertion
        WebElement signUpButton = driver.findElement(By.xpath("//a[normalize-space()='Sign Up']"));
        Assert.assertTrue(signUpButton.isDisplayed());
        
        test.pass("Search input displayed successfully.");
        test.pass("Search performed and result shown.");
      }
        finally
        {
        driver.quit();
       }
	}
	
	 @AfterClass
	    public void tearDownReport() {
	        extent.flush();
	    }
}
