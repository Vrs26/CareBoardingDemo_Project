package Login_page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;


public class EmailCaregiverWebApp {

    WebDriver driver;
    WebDriverWait wait;

    public EmailCaregiverWebApp(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize elements with PageFactory if needed
    }

    public void login(String username, String password) {
        try {
            // Debug: Print page title and URL
            System.out.println("Page Title: " + driver.getTitle());
            System.out.println("Current URL: " + driver.getCurrentUrl());

            WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email' or @name='email']")));
            emailInput.clear();
            emailInput.sendKeys(username);

            WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
            passwordInput.clear();
            passwordInput.sendKeys(password);

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='loginButton']")));
            loginButton.click();
        
            

            wait.until(d -> !d.getCurrentUrl().contains("login"));
            System.out.println("Login successful, new URL: " + driver.getCurrentUrl());

        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void navigateToCaregivers() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[3]/a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[1]/a"))).click();
    
    }

    @FindBy(xpath = "//*[@class='nav nav-treeview ']/li[1]/a")
    private WebElement searchCaregivers;

    public void clickSearchCaregivers() {
        wait.until(ExpectedConditions.elementToBeClickable(searchCaregivers)).click();
    }
    
    public void SelectTheFirstEmail() {
        // Locate the email cell in the first row (assuming the email is in the second column)
        WebElement emailCell = driver.findElement(By.xpath("(//table//tr[1]//td[3])")); // Adjust the column index if needed

        // Extract the email text
        String email = emailCell.getText();

        // Print the email
        System.out.println("Email: " + email);
    }
		    

    

    public String getFirstCaregiverEmail() {
        // Locate the email cell in the first row (assuming the email is in the third column)
        WebElement emailCell = driver.findElement(By.xpath("(//table//tr[1]//td[3])"));

        // Extract the email text
        String email = emailCell.getText();

        // Print the email for verification
        System.out.println("📧 Retrieved caregiver email: " + email);
        
        return email;
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();  // Initialize WebDriver (assuming ChromeDriver is being used)
        EmailCaregiverWebApp loginAutomation = new EmailCaregiverWebApp(driver);

        loginAutomation.login("testuser@example.com", "testpassword");

        loginAutomation.navigateToCaregivers();
    }
}