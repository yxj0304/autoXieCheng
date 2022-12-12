package cases;

import common.DriverBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import page.TrainsInfo;
import utils.PropertiesUtil;

import java.util.List;

import static java.lang.Thread.*;


public class Scenario1 extends CaseBase {
    private DriverBase driver;

    @BeforeMethod
    public void initAll() {
        //MacOS: 10.13.6 (Windows暂未包括)；Browser: Chrome(firefox暂未包括)
        String browserType = PropertiesUtil.getValue("config.properties", "browserType");
        this.driver = initDriver(browserType);
    }

    @Test
    public void checkCostLeastTickets() {
        driver.get("https://www.ctrip.com/");

        //Go to train ticket address
        TrainsInfo trainsInfo = new TrainsInfo(driver);
        trainsInfo.clickElement("train tab");
        Assert.assertEquals(trainsInfo.getElementText("train page label"), "火车票订单");
        //点击往返，输入离开及返回日期，输入出发地及目的地
        trainsInfo.clickElement("round button");
        trainsInfo.clickTheDepartDate();
        System.out.println("click depart date done");
        trainsInfo.clickTheReturnDate();
        System.out.println("click return date done");
        trainsInfo.sendTextOnElement("arrive city input","南京");
        trainsInfo.clickElement("the first item");
        System.out.println("input return city succeed");
        trainsInfo.sendTextOnElement("depart city input","上海");
        trainsInfo.clickElement("the first item");
        System.out.println("input depart city succeed");
        //点击搜索，等待加载完成，判断页面完成加载
        trainsInfo.clickElement("search");
        trainsInfo.waitElementToShow("expand depart list");
        Assert.assertEquals(trainsInfo.getElementText("search result page title"), "往返购票");
        //往返程都点击展开列表
        trainsInfo.clickElement("expand depart list");
        trainsInfo.clickElement("expand return list");
        //选中二等座
        trainsInfo.clickElement("depart secondary seat");
        trainsInfo.clickElement("return secondary seat");
        //点击时间排序，选择从短到长
        trainsInfo.clickElement("sort by time depart");
        trainsInfo.waitElementToShow("short to long time depart");
        trainsInfo.clickElement("short to long time depart");
        trainsInfo.waitSeconds((long) 5);
        trainsInfo.clickElement("sort by time return");
        trainsInfo.waitElementToShow("short to long time return");
        trainsInfo.clickElement("short to long time return");
        //从页面获取所有往返程车次信息，并打印出时间最短车次
        trainsInfo.printTheShortestTimeTrain("depart tickets");
        trainsInfo.printTheShortestTimeTrain("return tickets");
    }

    @AfterMethod
    public void closeAll() {
       this.driver.stop();
    }

}

