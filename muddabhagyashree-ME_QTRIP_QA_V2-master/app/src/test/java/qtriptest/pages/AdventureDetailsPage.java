package qtriptest.pages;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AdventureDetailsPage 
{
    RemoteWebDriver driver;



    public AdventureDetailsPage(RemoteWebDriver driver)
    {
        this.driver = driver;
    }







    public Boolean resultBeforeFilters(String beforeQuantity)
    {
        try
         {
            Thread.sleep(2000);
            List<WebElement> allAdventures = driver.findElements(By.className("col-6"));
            Thread.sleep(5000);
            String size = String.valueOf(allAdventures.size());
            System.out.println(size);
            if(size.equals(beforeQuantity))
            {
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










    public Boolean resultAfterFilters(String afterQuantity)
    {
        try
         {
            Thread.sleep(2000);
            List<WebElement> allAdventures = driver.findElements(By.className("col-6"));
            Thread.sleep(5000);
            String size = String.valueOf(allAdventures.size());
            if(size.equals(afterQuantity))
            {
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