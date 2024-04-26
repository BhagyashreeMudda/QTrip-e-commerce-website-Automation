package qtriptest.tests;

import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import qtriptest.SeleniumWrapper;

public class TestCase_03 {
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
        test = extent.startTest("Verify Booking and Cancellation Flow", "Test Description");
    }

    
   @Test(
      description = "Verify user registration - login - logout",
      priority = 3,
      dataProvider = "data-provider",
      dataProviderClass = DP.class,
      groups = {"Booking and Cancellation Flow"}
   )
   public static void TestCase03(String NewUserName, String Password, String SearchCity, String AdventureName, String GuestName, String Date, String count) throws InterruptedException {
      HomePage home = new HomePage(driver);
      home.navigateToHome();
      home.clickOnRegister();
      RegisterPage register = new RegisterPage(driver);
      Boolean status = register.registerPageIsDisplayed();
      Assert.assertTrue(status, "Register page not displayed");
      status = register.registerUser(NewUserName, Password, true);
      Assert.assertTrue(status, "Registration failed for new user");
      lastGeneratedUserName = register.lastGeneratedUsername;
      LoginPage login = new LoginPage(driver);
      status = login.performLogin(lastGeneratedUserName, Password);
      Assert.assertTrue(status, "Login for registered user failed");
      home.searchElement(SearchCity);
      status = home.searchResultlist(SearchCity);
      Assert.assertTrue(status, "Item not found");
      AdventurePage adventure = new AdventurePage(driver);
      adventure.searchAdventure(AdventureName);
      status = adventure.clickAdventureResult();
      Assert.assertTrue(status, "No result found");
      adventure.enterPersonDetails(GuestName, Date, count);
      status = adventure.adventureSuccessMsg();
      Assert.assertTrue(status, "Message not displayed");
      HistoryPage history = new HistoryPage(driver);
      history.clickOnReservation();
      Thread.sleep(2000L);
      WebElement transactionId = driver.findElement(By.xpath("//table/tbody/tr/th"));
      status = transactionId.isDisplayed();
      Assert.assertTrue(status, "Transaction Id not displayed");
      history.clickOnCancel();
      Thread.sleep(2000L);
      driver.navigate().refresh();
      Thread.sleep(2000L);
      WebElement noReservationMsg = driver.findElement(By.id("no-reservation-banner"));
      noReservationMsg.isDisplayed();
      home.performLogOut();
   }
}