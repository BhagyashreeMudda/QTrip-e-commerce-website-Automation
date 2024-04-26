package qtriptest.tests;

import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import qtriptest.SeleniumWrapper;

public class TestCase_01 {
   static RemoteWebDriver driver;
   public static String lastGeneratedUserName;

   static ExtentReports extent;
   static ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        DriverSingleton sbc1 = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = sbc1.getDriver();

        // Initialize ExtentReports and specify the file path
        extent = ReportSingleton.getInstance();
        // Start a new test
        test = extent.startTest("Verify user registration - login - logout", "Test Description");
    }

   @Test(description = "Verify user registration - login - logout",priority = 1,dataProvider = "data-provider",dataProviderClass = DP.class,groups = {"Login Flow"})
   public static void TestCase01(String Username, String Password) throws InterruptedException {
      HomePage home = new HomePage(driver);
      home.navigateToHome();
      home.clickOnRegister();
      RegisterPage register = new RegisterPage(driver);
      Boolean status = register.registerPageIsDisplayed();
	  
      Assert.assertTrue(status, "Register page not displayed");
      status = register.registerUser(Username, Password, true);
      Assert.assertTrue(status, "Registration failed for new user");
	  
      lastGeneratedUserName = register.lastGeneratedUsername;
      LoginPage login = new LoginPage(driver);
	  
      status = login.performLogin(lastGeneratedUserName, Password);
      Assert.assertTrue(status, "Login for registered user failed");
	  
      home.performLogOut();
   }
}