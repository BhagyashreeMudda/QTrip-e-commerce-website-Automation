package qtriptest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HistoryPage

{
    RemoteWebDriver driver;



    public HistoryPage(RemoteWebDriver driver)
    {
        this.driver = driver;
    }




    public void clickOnReservation()
    {
        WebElement click = driver.findElement(By.xpath("//a[contains(text() , 'Reservations')]"));
        click.click();
    }







    public void clickOnCancel()
    {
        WebElement click = driver.findElement(By.className("cancel-button"));
        click.click();
    }






    
}