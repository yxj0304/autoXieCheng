package page;

import common.DriverBase;
import common.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.w3c.dom.ls.LSException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class BasePage {
    public DriverBase driver;
    UIElement LAB_Train = new UIElement("//span[@class='order-link']");
    UIElement TAB_Train = new UIElement("//*[@id='nav_trains']");
    UIElement LAB_Space = new UIElement("//div[@class='spring-box-component']");


    public Map<String, UIElement> elementMap = new HashMap<String, UIElement>();

    public BasePage(DriverBase driver) {
        this.driver = driver;
        elementMap.put("train page label", LAB_Train);
        elementMap.put("train tab", TAB_Train);
        elementMap.put("space", LAB_Space);
    }


    public WebElement getElement(String name) {
        return driver.findElementOrigin(By.xpath(getWebElementPath(name)));
    }

    public String getWebElementPath(String name) {
        return elementMap.get(name.toLowerCase()).path;
    }

    public void clickElement(String name) {
        driver.waitForElementInTime(getWebElementPath(name));
        getElement(name).click();
    }

    public void sendTextOnElement(String name, String value) {
        driver.waitForElementInTime(getWebElementPath(name));
        getElement(name).clear();
        for (char c : value.toCharArray()) {
            getElement(name).sendKeys(String.valueOf(c));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cleanTextOnElement(String name) {
        driver.waitForElementInTime(getWebElementPath(name));
        try {
            sleep(10000);
            getElement(name).clear();
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getElementText(String name) {
        driver.waitForElementInTime(getWebElementPath(name));
        WebElement uiElement = getElement(name);
        return uiElement.getText();
    }

    public void waitElementToShow(String name) {
        driver.waitForElementInTime(getWebElementPath(name));
    }

    public void waitSeconds(Long seconds) {
        try {
            sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer switchToFloat(String time) {
        Integer day = 0;
        Integer hour = 0;
        Integer min = 0;
        if (time.length()>1 && time.contains("天")) {
            day = Integer.parseInt(time.substring(0, time.indexOf("天")));
            if ((time.contains("时"))&& (time.contains("分"))) {
                hour = Integer.parseInt(time.substring(time.indexOf("天")+1, time.indexOf("时")));
                min = Integer.parseInt(time.substring(time.indexOf("时")+1,time.indexOf("分")));
            }
            else if(time.contains("分")){
                min = Integer.parseInt(time.substring(time.indexOf("天")+1,time.indexOf("分")));
            }
        } else if(time.length()>1 && (!time.contains("天"))) {
            if (time.contains("时")&& time.contains("分")) {
                hour = Integer.parseInt(time.substring(0, time.indexOf("时")));
                min = Integer.parseInt(time.substring(time.indexOf("时")+1,time.indexOf("分")));
            }
            else if(time.contains("分")){
                min = Integer.parseInt(time.substring(0,time.indexOf("分")));
            }
        }

        Integer total = day * 24 * 60 + hour * 60 + min;

        return total;
    }

    public List<WebElement> findElementsList(String name) {
        List<WebElement> uiElements = driver.findElements(getWebElementPath(name));
        return uiElements;
    }
}
