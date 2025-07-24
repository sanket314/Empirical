package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import base.ReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.ArrayList;

 public class SignUpButtonTest { 

		ExtentReports extent;
	    ExtentTest test;

	    @BeforeClass
	    public void setUpReport() 
	    {
	        extent = ReportManager.createReport(this.getClass().getSimpleName());
	    }
	    
	   @Test
	    public void testSearchInputVisible() 
	   {
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        
	        try {
	        	
	        	 test = extent.createTest("SignUpButton Test");
	    	     test.info("Test Started: Verifying W3 school webpage");
	            
	    	     driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_links_target");

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	            // Switch to frame
	            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeResult"));

	            // Click the Visit W3Schools link inside iframe
	            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Visit W3Schools!']")));
	            link.click();

	            // Wait until two tabs are open
	            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

	            // Switch to new tab
	            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
	            driver.switchTo().window(tabs.get(1));

	            //Assertion
	            WebElement searchInput = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='ga-fp']")));
	            Assert.assertTrue(searchInput.isDisplayed());
	            
	            test.pass("SignUp Button displayed successfully.");
	            test.pass("Result shown.");
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
	
	

