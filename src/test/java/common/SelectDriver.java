package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class SelectDriver {
    public WebDriver driverName(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        } else {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("-disable-notifications");
            options.addArguments("no-sandbox");
            options.addArguments("-disable-extensions");
            options.addArguments("start-maximized");
            return new ChromeDriver(options);
        }
    }

}
