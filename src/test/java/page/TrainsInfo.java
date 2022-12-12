package page;

import common.DriverBase;
import common.UIElement;
import entity.TrainTicket;
import org.checkerframework.checker.guieffect.qual.UI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrainsInfo extends BasePage {

    LocalDate localDate = LocalDate.now();
    LocalDate departDate = localDate.plusDays(7);
    LocalDate returnDate = localDate.plusDays(14);
    DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("yyyy年 MM月");
    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd");
    String departMonth = departDate.format(formatterMonth);
    String returnMonth = returnDate.format(formatterMonth);
    String departDay = departDate.format(formatterDate);
    String returnDay = returnDate.format(formatterDate);


    UIElement BTN_RoundTripTicket = new UIElement("//button[text()='往返']");
    UIElement INP_DepartCity = new UIElement("//input[@id='label-departStation']");
    UIElement INP_ArriveCity = new UIElement("//input[@id='label-arriveStation']");
    UIElement BTN_DepartDateExpandArrow = new UIElement("//div[@class='search-date']/div[1]");
    UIElement BTN_ReturnDateExpandArrow = new UIElement("//div[@class='search-date']/div[2]");
    UIElement LAB_Month1 = new UIElement("//ul[@class='widget-calendar-hd']/li[1]/h3");
    UIElement LAB_Month2 = new UIElement("//ul[@class='widget-calendar-hd']/li[2]/h3");
    UIElement BTN_ShangHai = new UIElement("//ul[@class='widget-city-bd']/li[text()='上海']");
    UIElement BTN_NanJing = new UIElement("//ul[@class='widget-city-bd']/li[text()='南京']");
    UIElement BTN_Search = new UIElement("//button[contains(@class,'btn-search')]");
    UIElement ITM_First = new UIElement("//ul[@class='widget-search-bd']/li[1]");
    UIElement LAB_SearchResultTitle = new UIElement("//div[@id='main']//h2");
    UIElement BTN_ItemMoreDepart = new UIElement("//div[@class='return-box']/div[1]//div[@class='item-more']");
    UIElement BTN_ItemMoreReturn = new UIElement("//div[@class='return-box']/div[2]//div[@class='item-more']");
    UIElement BTN_SecondarySeatDep = new UIElement("//div[@class='return-box']/div[1]//*[text()='二等座']/parent::*/i");
    UIElement BTN_SecondarySeatRtn = new UIElement("//div[@class='return-box']/div[2]//*[text()='二等座']/parent::*/i");
    UIElement BTN_SortByTimeDep = new UIElement("//div[@class='return-box']/div[1]//ul[@class='sort-left']/li[2]");
    UIElement BTN_SortByTimeRtn = new UIElement("//div[@class='return-box']/div[2]//ul[@class='sort-left']/li[2]");
    UIElement BTN_SortByTimeShortToLongDep = new UIElement("//body/div[4]//ul[@class='widget-select']/li[2]");
    UIElement BTN_SortByTimeShortToLongRtn = new UIElement("//body/div[5]//ul[@class='widget-select']/li[2]");
    UIElement ITM_DepTickets = new UIElement("//div[@class='return-box']/div[1]//div[contains(@id,'trainlistitem')]");
    UIElement ITM_RtnTickets = new UIElement("//div[@class='return-box']/div[2]//div[contains(@id,'trainlistitem')]");


    public TrainsInfo(DriverBase driver) {
        super(driver);
        elementMap.put("round button", BTN_RoundTripTicket);
        elementMap.put("depart city input", INP_DepartCity);
        elementMap.put("arrive city input", INP_ArriveCity);
        elementMap.put("expand depart date", BTN_DepartDateExpandArrow);
        elementMap.put("expand return date", BTN_ReturnDateExpandArrow);
        elementMap.put("current month", LAB_Month1);
        elementMap.put("next month", LAB_Month2);
        elementMap.put("shanghai", BTN_ShangHai);
        elementMap.put("nanjing", BTN_NanJing);
        elementMap.put("search", BTN_Search);
        elementMap.put("the first item", ITM_First);
        elementMap.put("search result page title", LAB_SearchResultTitle);
        elementMap.put("expand depart list", BTN_ItemMoreDepart);
        elementMap.put("expand return list", BTN_ItemMoreReturn);
        elementMap.put("depart secondary seat", BTN_SecondarySeatDep);
        elementMap.put("return secondary seat", BTN_SecondarySeatRtn);
        elementMap.put("sort by time depart", BTN_SortByTimeDep);
        elementMap.put("sort by time return", BTN_SortByTimeRtn);
        elementMap.put("short to long time depart", BTN_SortByTimeShortToLongDep);
        elementMap.put("short to long time return", BTN_SortByTimeShortToLongRtn);
        elementMap.put("depart tickets", ITM_DepTickets);
        elementMap.put("return tickets", ITM_RtnTickets);
    }

    public void clickTheDepartDate() {
        int index = 1;
        clickElement("expand depart date");
        System.out.println("expand depart date succeed");
        if (departMonth.equals(getElementText("current month"))) {
            index = 1;
        } else if (departMonth.equals(getElementText("next month"))) {
            index = 2;
        }
        String datePath = "//div[contains(@class,'widget-box widget-calendar')]/div[" + index + "]//*[text()='" + departDay + "']/parent::li";
        driver.findElement(By.xpath(datePath)).click();
    }

    public void clickTheReturnDate() {
        int index = 1;
        clickElement("expand return date");
        System.out.println("expand return date succeed");
        if (returnMonth.equals(getElementText("current month"))) {
            index = 1;
        } else if (returnMonth.equals(getElementText("next month"))) {
            index = 2;
        }
        String datePath = "//div[contains(@class,'widget-box widget-calendar')]/div[" + index + "]//*[text()='" + returnDay + "']/parent::li";
        driver.findElement(By.xpath(datePath)).click();
    }

    public void printTheShortestTimeTrain(String name) {
        ArrayList<TrainTicket> trainTickets = new ArrayList<>();
        List<WebElement> list = findElementsList(name);
        String wangfan = "";
        String preXpath = "";
        if (name.contains("depart")) {
            wangfan = "去程";
            preXpath = "//div[@class='return-box']/div[1]//div[@id='trainlistitem";
        } else if(name.contains("return")){
            wangfan = "返程";
            preXpath = "//div[@class='return-box']/div[2]//div[@id='trainlistitem";
        }

        for (int i = 0; i < list.size(); i++) {
            String priceXpath = "//div[@class='price']";
            String checiXpath = "//div[@class='checi']";
            String timeXpath = "//div[@class='haoshi']";
            timeXpath = preXpath + i + "']" + timeXpath;
            priceXpath = preXpath + i + "']" + priceXpath;
            checiXpath = preXpath + i + "']" + checiXpath;
            String time = (driver.findElement(By.xpath(timeXpath)).getText());
            String checi = driver.findElement(By.xpath(checiXpath)).getText();
            String price = driver.findElement(By.xpath(priceXpath)).getText();
            trainTickets.add(new TrainTicket(checi, price, time, wangfan));
        }
        System.out.println("all tickets added");

       // ArrayList<TrainTicket> shortestTrains = new ArrayList<>();
        for (int i = 0; i < trainTickets.size() - 1; i++) {
            if (switchToFloat(trainTickets.get(i).getTime()) == switchToFloat(trainTickets.get(i + 1).getTime())) {
               // shortestTrains.add(trainTickets.get(i));
                System.out.println(trainTickets.get(i).toString());
            }
            else {
                //shortestTrains.add(trainTickets.get(i));
                System.out.println(trainTickets.get(i).toString());
                break;
            }
        }

        System.out.println("shortest tickets printed");
    }


}
