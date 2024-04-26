package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

    public static boolean click(WebElement elementToClick, WebDriver driver) throws InterruptedException {
        try {
            elementToClick.click();
            return true;
        } catch (Exception e) {
            // handle the exception as needed
            return false;
        }
    }

    public boolean sendKeys(WebElement inputBox, String keysToSend) {
        try {
            inputBox.sendKeys(keysToSend);
            return true;
        } catch (Exception e) {
             // handle the exception as needed
            return false;
        }
    }

    public boolean navigate(WebDriver driver, String url) {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
             // handle the exception as needed
            return false;
        }
    }

    public static WebElement findElementWithRetry(WebDriver driver, By by, int retryCount) throws Exception {
        WebElement element = null;
        int attempts = 0;

        while (attempts < retryCount) {
            try {
                element = driver.findElement(by);
                break;
            } catch (NoSuchElementException e) {
               // handle the exception as needed  
            }
            attempts++;
            Thread.sleep(1000); 
        }
        if (element == null) {
            throw new NoSuchElementException("Element not found after " + retryCount + " attempts");
        }
        return element;
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
            return false;
        }
    }
    
}