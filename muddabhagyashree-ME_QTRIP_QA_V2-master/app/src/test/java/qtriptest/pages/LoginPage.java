package qtriptest.pages;



import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginPage 
{


    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";




    public LoginPage(RemoteWebDriver driver)
    {
        this.driver = driver;
    }






    public Boolean performLogin(String email , String password)
    {
        
        WebElement email_textbox = driver.findElement(By.name("email"));
        email_textbox.sendKeys(email);

        WebElement password_textbox = driver.findElement(By.name("password"));
        password_textbox.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text() , 'Login')]"));
        loginButton.click();

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.invisibilityOf(loginButton));

        return this.verifyUserLoggedIn();
    }









    public Boolean verifyUserLoggedIn()
    {
        try
         {
            WebElement reservationText = driver.findElement(By.xpath("//a[contains(text() , 'Reservations')]"));
            return reservationText.isDisplayed();
        }
         catch (Exception e)
          {
            //TODO: handle exception
            return false;
        }
    }






}
