package Login_page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareboardingVisit extends Demo_AbstractClass {

    private WebDriver driver;

    public CareboardingVisit(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='emailAddress']")
    private WebElement email;

    @FindBy(xpath = "//*[@id='loginPassword']")
    private WebElement password;

    @FindBy(xpath = "//*[@for='remember']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//*[@id='loginButton']")
    private WebElement signInButton;

    @FindBy(xpath = "(//*[@id='outlined-basic'])[3]") // Fixed XPath
    private WebElement forgotPasswordButton;

    public void signInCaregiver(String logInLabel) throws InterruptedException {
        email.click();
        email.sendKeys("shyam@benzatine.com");

        pauserun4(); // Delay method from Demo_AbstractClass

        password.clear();
        password.sendKeys("12345678");

        pauserun2();

        rememberMeCheckbox.click();
        signInButton.click();

        Thread.sleep(5000);
        System.out.println("✅ Caregiver login attempted.");
    }
    
	
  //============== Click "Caregivers" Field and Verify Functionality===================== 
  	        
  	        @FindBy(xpath = "//p[normalize-space()='Caregivers']")
  		     private WebElement Caregivers;
  		
  		     public void Click_caregivers () {
  		     Caregivers.click();	
  	}
  	
  //======================= The "Caregiver" section in the side menu, then open the drop-down and select the "Search Caregiver" option===================

  		     @FindBy(xpath = "//*[@class='nav nav-treeview ']/li[1]/a")
  		     private WebElement Search_Caregivers;
  		
  		     public void Click_Search_Caregivers() {
  		     Search_Caregivers.click();		

     }
  		     
   //==================First name select in caregiver field==============
  		     
  		   public String SelectTheFirstEmail() {
   	        // Locate the email cell in the first row (assuming the email is in the second column)
   	        WebElement emailCell = driver.findElement(By.xpath("(//table//tr[1]//td[3])")); // Adjust the column index if needed

   	        // Extract the email text
   	        String email = emailCell.getText();

   	        // Print the email
   	        System.out.println("Email: " + email);
            
            // Return the email
            return email;
   	    }

		
}       
         
         
         
         