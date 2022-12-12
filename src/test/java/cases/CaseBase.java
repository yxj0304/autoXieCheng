package cases;

import common.DriverBase;
import org.openqa.selenium.WebDriver;

public class CaseBase {
    private WebDriver driver;
    private DriverBase driverBase;

    public DriverBase initDriver(String browser){
        this.driverBase = new DriverBase(browser);
        this.driver = driverBase.getDriver();
        return driverBase;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
