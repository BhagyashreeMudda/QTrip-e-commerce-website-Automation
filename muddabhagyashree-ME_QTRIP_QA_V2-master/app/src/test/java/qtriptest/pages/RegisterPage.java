package qtriptest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qtriptest.SeleniumWrapper;

import java.sql.Timestamp;

public class RegisterPage {
    private final RemoteWebDriver driver;
    private final SeleniumWrapper seleniumWrapper;
    public String lastGeneratedUsername = "";
    private final String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";

    private final By registerLogoXpath = By.xpath("//h2[contains(text(), 'Register')]");

    public RegisterPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.seleniumWrapper = new SeleniumWrapper();
    }

    public void navigateToRegisterPage() {
        seleniumWrapper.navigate(driver, url);
    }

    public Boolean registerPageIsDisplayed() {
        try {
            WebElement registerLogo = seleniumWrapper.findElementWithRetry(driver, registerLogoXpath, 5);
            return seleniumWrapper.isDisplayed(registerLogo);
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean registerUser(String email, String password, Boolean generateRandomUser) throws InterruptedException {
        try {
            // Find email box
            WebElement emailTextbox = seleniumWrapper.findElementWithRetry(driver, By.name("email"), 5);

            // Get timestamp for generating a unique username
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String testDataUserName;
            if (generateRandomUser) {
                testDataUserName = email + String.valueOf(timestamp.getTime());
            } else {
                testDataUserName = email;
            }

            seleniumWrapper.sendKeys(emailTextbox, testDataUserName);

            // Find password and confirm password and enter
            WebElement passwordTextbox = seleniumWrapper.findElementWithRetry(driver, By.name("password"), 5);
            seleniumWrapper.sendKeys(passwordTextbox, password);

            WebElement confirmPasswordTextbox = seleniumWrapper.findElementWithRetry(driver, By.name("confirmpassword"), 5);
            seleniumWrapper.sendKeys(confirmPasswordTextbox, password);

            // Click on Register Button
            WebElement registerButton = seleniumWrapper.findElementWithRetry(driver, By.xpath("//button[contains(text(), 'Register')]"), 5);
            seleniumWrapper.click(registerButton, driver);

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login"));

            this.lastGeneratedUsername = testDataUserName;

            return driver.getCurrentUrl().endsWith("/login");
        } catch (Exception e) {
            // Log or handle the exception as needed
            return false;
        }
    }
}