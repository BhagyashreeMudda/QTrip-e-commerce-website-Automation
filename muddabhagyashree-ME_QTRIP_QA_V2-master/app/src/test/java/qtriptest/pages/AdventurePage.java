package qtriptest.pages;



import java.util.List;
import org.apache.commons.collections4.bag.TreeBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage
 {

    RemoteWebDriver driver;
    
    public AdventurePage(RemoteWebDriver driver)
    {
        this.driver = driver;
    }





    
    public Boolean filterByDuration(String durationToSelect)
    {
        try
         {
            WebElement filters = driver.findElement(By.xpath("//div[contains(@class , 'filter-bar-tile d-flex align-items-center')][1]/select"));
            Select duration = new Select(filters);
            duration.selectByVisibleText(durationToSelect);
            return true;
        } 
        catch (Exception e)
         {
            //TODO: handle exception
            return false;
        }
    }









    public Boolean selectCategory(String categoryToSelect)
    {
        try
         {
            WebElement categoryFilter = driver.findElement(By.xpath("//div[contains(@class , 'filter-bar-tile d-flex align-items-center')][2]/select"));
            Select category = new Select(categoryFilter);
            category.selectByVisibleText(categoryToSelect);
            return true;
        } 
        catch (Exception e) 
        {
            //TODO: handle exception
            return false;
        }
    }







    public void clearDurationFilter()
    {
        try
         {
            WebElement clear = driver.findElement(By.xpath("//div[@onclick='clearDuration(event)']"));
            clear.click();
        }
         catch (Exception e) 
         {
            //TODO: handle exception
            System.out.println(e);
        }
    }









    public void clearCategoryFilter()
    {
        try
         {
            WebElement clear = driver.findElement(By.xpath("//div[@onclick='clearCategory(event)']"));
            clear.click();
        } 
        catch (Exception e)
         {
            //TODO: handle exception
            System.out.println(e);
        }
    }








    public void searchAdventure(String adventureName)
    {
        try
         {
            Thread.sleep(2000);
            WebElement searchAdv = driver.findElement(By.id("search-adventures"));
            Thread.sleep(2000);
            searchAdv.sendKeys(adventureName);
        }
         catch (Exception e)
          {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }








    public Boolean clickAdventureResult()
    {
        try
         {
            WebElement advResult = driver.findElement(By.className("col-6"));
            //Thread.sleep(2000);
            advResult.click();
            return true;
        } 
        catch (Exception e)
         {
            //TODO: handle exception
            return false;
        }
    }







    public void enterPersonDetails(String name , String date , String valueOfPerson)
    {
        try
         {
            Thread.sleep(2000);
            //Enter name
            WebElement nameElement = driver.findElement(By.name("name"));
            nameElement.sendKeys(name);

            //Enter date
            WebElement dateElement = driver.findElement(By.name("date"));
            dateElement.sendKeys(date);

            //Enter person quantity
            WebElement quantity = driver.findElement(By.xpath("//input[@type='number']"));
            quantity.clear();
            quantity.sendKeys(valueOfPerson);

            WebElement reserveButton = driver.findElement(By.className("reserve-button"));
            reserveButton.click();
            Thread.sleep(2000);
        } 
        catch (Exception e)
         {
            //TODO: handle exception
            System.out.println(e);
        }
    }









    // public void clickOnReserve(){
    //     WebElement click = driver.findElement(By.className("reserve-button"));
    //     click.click();
    // }

    public Boolean adventureSuccessMsg()
    {
        Boolean status;
        try
         {
            Thread.sleep(2000);
            WebElement msg = driver.findElement(By.id("reserved-banner"));
            status = msg.isDisplayed();
            return status;
        } 
        catch (Exception e)
         {
            //TODO: handle exception
            return false;
        }
    }





}