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
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.SeleniumWrapper;

public class TestCase_02 {
   static RemoteWebDriver driver;
   public static String lastGeneratedUserName;

   // ExtentReports and ExtentTest instances from the singleton
   static ExtentReports extent;
   static ExtentTest test;

   @BeforeSuite(alwaysRun = true)
   public static void createDriver() throws MalformedURLException {
       DriverSingleton sbc1 = DriverSingleton.getInstanceOfSingletonBrowserClass();
       driver = sbc1.getDriver();

       // Initialize ExtentReports and specify the file path
       extent = ReportSingleton.getInstance();
       // Start a new test
       test = extent.startTest("Verify Search and Filters", "Test Description");
   }

   @Test(
      description = "Verify that Search and filters work fine",
      priority = 2,
      dataProvider = "data-provider",
      dataProviderClass = DP.class,
      groups = {"Search and Filter flow"}
   )
   public static void TestCase02(String CityName, String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws InterruptedException {
      HomePage home = new HomePage(driver);
      home.navigateToHome();

      Boolean status = home.searchElement("qwe"); // Searching for an item "qwe"
      Assert.assertTrue(status, "Initial search for 'qwe' failed"); // Asserting the initial search
      
      home.searchElement(CityName); // Searching for another item based on the test data
      status = home.searchResultlist(CityName); // Checking if the item is found
      
      Assert.assertTrue(status, "Item not found"); // Assertion expecting the CityName item to be found
      

      AdventureDetailsPage details = new AdventureDetailsPage(driver);
      status = details.resultBeforeFilters(ExpectedUnFilteredResults);
      Assert.assertTrue(status, "Quantity didn't match");

      AdventurePage adventurePage = new AdventurePage(driver);
      status = adventurePage.filterByDuration(DurationFilter);
      Assert.assertTrue(status, "Filter not applied");
      status = adventurePage.selectCategory(Category_Filter);
      Assert.assertTrue(status, "Category not applied");
      status = details.resultAfterFilters(ExpectedFilteredResults);
      Assert.assertTrue(status, "Quantity didn't match");
      adventurePage.clearDurationFilter();
      adventurePage.clearCategoryFilter();
      status = details.resultBeforeFilters(ExpectedUnFilteredResults);
      Assert.assertTrue(status, "Quantity didn't match");
      home.performLogOut();
   }
}