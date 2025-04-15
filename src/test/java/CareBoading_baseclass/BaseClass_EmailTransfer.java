package CareBoading_baseclass;

import org.openqa.selenium.WebDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.net.URL;
import java.time.Duration;
import java.net.MalformedURLException;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseClass_EmailTransfer {
    protected WebDriver webDriver;
    protected AndroidDriver mobileDriver;

    @BeforeTest
    public void setup() {
        // Initialize drivers in individual test classes or as needed
    }

    @AfterTest
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
        if (mobileDriver != null) {
            mobileDriver.quit();
        }
    }

    protected WebDriver initWebDriver() {
        webDriver = new EdgeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return webDriver;
    }

    protected AndroidDriver initMobileDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
            .setDeviceName("MyAppiumEmulator1")
            .setAutomationName("UiAutomator2")
            .setApp(System.getProperty("user.dir") + "\\src\\test\\java\\Recourse\\47.apk")
            .setAutoGrantPermissions(true)
            .setNewCommandTimeout(Duration.ofSeconds(180))
            .setNoReset(false);

        // Try both URL formats to handle different Appium server versions
        try {
            // Modern Appium 2.0+ URL format
            System.out.println("Trying to connect to Appium server at http://127.0.0.1:4723");
            mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (Exception e) {
            System.out.println("First attempt failed. Trying legacy Appium server URL format...");
            // Legacy Appium 1.x URL format with /wd/hub
            mobileDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        }
        
        return mobileDriver;
    }
} 