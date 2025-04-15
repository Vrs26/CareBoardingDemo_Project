package DemoTestCase;

import org.testng.annotations.Test;
import CareBoading_baseclass.BaseClass_EmailTransfer;
import org.openqa.selenium.WebDriver;
import Login_page.EmailCaregiverWebApp;
import Login_page.Appium_Class;
import org.testng.annotations.AfterMethod;

import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TC_EmailTransfer extends BaseClass_EmailTransfer {

    private WebDriver webDriver;
    private AndroidDriver mobileDriver;

    // Helper method to check if Appium server is running
    private boolean isAppiumServerRunning() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://127.0.0.1:4723/status").openConnection();
            connection.setConnectTimeout(1000);
            connection.connect();
            return connection.getResponseCode() == 200;
        } catch (IOException e) {
            return false;
        }
    }

    @Test
    public void runEmailTransferTest() throws Exception {
        // Web part - this should work regardless of Appium
        try {
            // Step 1: Initialize WebDriver and login to website
            webDriver = initWebDriver();
            webDriver.get("https://app.careboarding.com/");
            System.out.println("🔗 Navigated to: " + webDriver.getCurrentUrl());

            // Login to the website
            EmailCaregiverWebApp emailApp = new EmailCaregiverWebApp(webDriver);
            emailApp.login("shyam@benzatine.com", "12345678"); // Use config/env in real projects
            System.out.println("✅ Logged in successfully.");

            // Step 2: Click the caregiver in the side menu
            emailApp.navigateToCaregivers();
            System.out.println("📂 Navigated to Caregivers section.");

            // Step 3: Select the Search caregiver in the caregiver menu
            emailApp.clickSearchCaregivers();
            System.out.println("🔍 Clicked on Search caregivers.");
            
            // Step 4: Get the first caregiver email
            String caregiverEmail = emailApp.getFirstCaregiverEmail();
            System.out.println("📧 Extracted caregiver email: " + caregiverEmail);

            // Appium part - check if server is running first
            if (!isAppiumServerRunning()) {
                System.err.println("⚠️ Appium server is not running on http://127.0.0.1:4723");
                System.err.println("Please start the Appium server and try again.");
                return; // Skip the mobile part but don't fail the test
            }
            
            try {
                // Step 5: Initialize mobile driver for Appium
                System.out.println("🔄 Connecting to Appium server...");
                mobileDriver = initMobileDriver();
                System.out.println("📱 Mobile driver initialized successfully.");
                
                // Step 6: Create Appium page object and use the email for login
                Appium_Class appiumPage = new Appium_Class(mobileDriver);
                appiumPage.loginWithWebEmail(caregiverEmail);
                System.out.println("🔑 Logged into mobile app using email: " + caregiverEmail);
                
                System.out.println("✅ Complete test completed successfully.");
            } catch (Exception e) {
                System.err.println("❌ Mobile app part failed: " + e.getMessage());
                System.err.println("📋 TROUBLESHOOTING TIPS:");
                System.err.println("1. Verify the Android emulator/device is running and connected");
                System.err.println("2. Check the APK path: " + System.getProperty("user.dir") + "\\src\\test\\java\\Recourse\\47.apk");
                System.err.println("3. Make sure you have the correct Appium Java client version");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("❌ Web app part failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
            System.out.println("🧹 WebDriver closed.");
        }
        if (mobileDriver != null) {
            // Don't quit immediately to allow viewing test results
            // mobileDriver.quit();
            System.out.println("💡 Mobile driver remains open for verification. Close manually when done.");
        }
    }
} 