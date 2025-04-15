package Login_page;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class Appium_Class extends Demo_AbstractClass {
    
    private String caregiverEmail;

    public Appium_Class(AppiumDriver mobileDriver) {
        super(mobileDriver);
        this.driver = mobileDriver;
        PageFactory.initElements(mobileDriver, this);
    }
    
    public void setCaregiverEmail(String email) {
        this.caregiverEmail = email;
    }

    public void ClicktheTodaySchedulebtn() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click "Continue"
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Continue"))).click();

        // Step 2: Click "Get Started"
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Get Started"))).click();
        Thread.sleep(2000);

        // Step 3: Enter Email
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ImageView"))); // You may need to adjust this index
        emailField.click();
        // Use the stored caregiver email if available, otherwise use default
        emailField.sendKeys(caregiverEmail != null ? caregiverEmail : "ABD");
      
        // Step 4: Enter Password
        WebElement passwordField = driver.findElement(AppiumBy.xpath("//android.widget.EditText"));
        passwordField.click();
        passwordField.sendKeys("patel@222");

        // Step 5: Click "Sign In"
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Sign In"))).click();
        
        //Click the Today's Schedule
       // driver.findElement(AppiumBy.xpath("//android.widget.ImageView[contains(@content-desc, 'Schedule For')]")).click();
        
        
        //Select the visit 
        // List<WebElement> buttons = driver.findElements(By.xpath("//android.widget.Button"));
         //buttons.get(1).click();
        
         
         //Click the Notification
         //driver.findElement(AppiumBy.xpath("//android.view.View[@content-desc=\"Clock In\"]")).click();
         
         //Click the clock-In Button 
         //driver.findElement(AppiumBy.accessibilityId("CLOCK IN")).click();
         
        
    
         //Click the Clock-Out Button
         //driver.findElement(AppiumBy.accessibilityId("CLOCK OUT")).click();
       
         
         
         
  	}
  	
    // New method to login with email from web app
    public void loginWithWebEmail(String email) throws InterruptedException {
        // Set the email for login
        setCaregiverEmail(email);
        
        // Proceed with regular login flow
        ClicktheTodaySchedulebtn();
    }
  
    }
