package qtriptest.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage
 {


    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";




    public HomePage(RemoteWebDriver driver)
    {
        this.driver = driver;
    }






    public void navigateToHome()
    {
        if(!this.driver.getCurrentUrl().equals(this.url))
        {
            this.driver.get(this.url);
        }
    }









    public Boolean performLogOut()
    {
        try
         {
            //find log out button and click on it
            WebElement logoutButton = driver.findElement(By.xpath("//div[contains(text() , 'Logout')]"));
            logoutButton.click();
            //Verify user is logged out
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.invisibilityOf(logoutButton));

            return true;
        } 
        catch (Exception e)
         {
            //TODO: handle exception
            return false;
        }
    }







    public void clickOnRegister() throws InterruptedException
    {
        WebElement registerButton = driver.findElement(By.xpath("//a[contains(text() , 'Register')]"));
        registerButton.click();
        Thread.sleep(2000);
    }






    public Boolean searchElement(String Item)
    {
        try
         {
            WebElement searchBox = driver.findElement(By.id("autocomplete"));
            Thread.sleep(1000);
            searchBox.clear();
            searchBox.sendKeys(Item);
            Thread.sleep(1000);
            return true;
        }
         catch (Exception e) 
         {
            return false;
        }
    }









    public Boolean noItemFound() throws InterruptedException
    {
        try
         {
            Thread.sleep(2000);
            WebElement noItem = driver.findElement(By.xpath("//h5[contains(text() , 'No City found')]"));
            Thread.sleep(2000);
            noItem.isDisplayed();
            return true;
        } 
        catch (Exception e)
         {
            //TODO: handle exception
            return false;
        }
    }










    
    public Boolean searchResultlist(String ItemInlist)
    {
        try
         {
            Thread.sleep(1000);
            WebElement listItem = driver.findElement(By.id("results"));
            Thread.sleep(2000);
            String nameOfItem = listItem.getText();
            if(ItemInlist.equalsIgnoreCase(nameOfItem))
            {
                listItem.click();
                Thread.sleep(2000);
                return true;
            }
            else
            {
                return false;
            }
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
            return false;
        }
    }




}







    




