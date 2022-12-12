package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class DriverBase {
    public WebDriver driver;

    public DriverBase(String browser) {
        SelectDriver selectDriver = new SelectDriver();
        this.driver = selectDriver.driverName(browser);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void stop() {
        driver.close();
    }
    public WebElement findElementOrigin(By by){
        return driver.findElement(by);
    }
    public WebElement findElement(By by){
        return new WebDriverWait(driver,200).until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public WebElement isElementVisible(By by){
        return new WebDriverWait(driver,200).until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }
    public void get(String url){
        driver.get(url);
    }
    public void waitForElementInTime(String elementPath){
        try {
            new WebDriverWait(driver,200).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementPath)));
        }catch (TimeoutException t){
            System.out.println("Can not find element");
        }
    }

    public List<WebElement> findElements(String elementsPath){
        return driver.findElements(By.xpath(elementsPath));
    }

}
