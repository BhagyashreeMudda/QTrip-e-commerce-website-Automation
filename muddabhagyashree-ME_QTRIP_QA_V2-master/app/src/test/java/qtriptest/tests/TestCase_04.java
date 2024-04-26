package qtriptest.tests;

import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import qtriptest.SeleniumWrapper;

public class TestCase_04 {
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
        test = extent.startTest("Verify Reliability Flow", "Test Description");
    }

   @Test(
      description = "Verify user registration - login - logout",
      priority = 4,
      dataProvider = "data-provider",
      dataProviderClass = DP.class,
      groups = {"Reliability Flow"}
   )
   public static void TestCase04(String NewUserName, String Password, String dataset1, String dataset2, String dataset3) throws InterruptedException {
      HomePage home = new HomePage(driver);
      home.navigateToHome();
      home.clickOnRegister();
      RegisterPage register = new RegisterPage(driver);
      Boolean status = register.registerPageIsDisplayed();
      Assert.assertTrue(status, "Register page not displayed");
      status = register.registerUser("testUser@", "test1234", true);
      Assert.assertTrue(status, "Registration failed for new user");
      lastGeneratedUserName = register.lastGeneratedUsername;
      LoginPage login = new LoginPage(driver);
      status = login.performLogin(lastGeneratedUserName, "test1234");
      Assert.assertTrue(status, "Login for registered user failed");
      String[] DS1 = dataset1.split(";");
      String city1 = DS1[0];
      String adventure1 = DS1[1];
      String name1 = DS1[2];
      String date1 = DS1[3];
      String personValue1 = DS1[4];
      home.searchElement(city1);
      status = home.searchResultlist(city1);
      Assert.assertTrue(status, "Item not found");
      AdventurePage adventure = new AdventurePage(driver);
      adventure.searchAdventure(adventure1);
      status = adventure.clickAdventureResult();
      Assert.assertTrue(status, "No result found");
      adventure.enterPersonDetails(name1, date1, personValue1);
      status = adventure.adventureSuccessMsg();
      Assert.assertTrue(status, "Message not displayed");
      home.navigateToHome();
      String[] DS2 = dataset2.split(";");
      String city2 = DS2[0];
      String adventure2 = DS2[1];
      String name2 = DS2[2];
      String date2 = DS2[3];
      String personValue2 = DS2[4];
      home.searchElement(city2);
      status = home.searchResultlist(city2);
      Assert.assertTrue(status, "Item not found");
      adventure.searchAdventure(adventure2);
      status = adventure.clickAdventureResult();
      Assert.assertTrue(status, "No result found");
      adventure.enterPersonDetails(name2, date2, personValue2);
      status = adventure.adventureSuccessMsg();
      Assert.assertTrue(status, "Message not displayed");
      home.navigateToHome();
      String[] DS3 = dataset3.split(";");
      String city3 = DS3[0];
      String adventure3 = DS3[1];
      String name3 = DS3[2];
      String date3 = DS3[3];
      String personValue3 = DS3[4];
      home.searchElement(city3);
      status = home.searchResultlist(city3);
      Assert.assertTrue(status, "Item not found");
      adventure.searchAdventure(adventure3);
      status = adventure.clickAdventureResult();
      Assert.assertTrue(status, "No result found");
      adventure.enterPersonDetails(name3, date3, personValue3);
      status = adventure.adventureSuccessMsg();
      Assert.assertTrue(status, "Message not displayed");
      home.performLogOut();
   }

   @AfterSuite
    public void closeBrowser() {
        driver.quit();
        
        // End the test and write the test to the filesystem
        extent.endTest(test);
        extent.flush();
    }
}