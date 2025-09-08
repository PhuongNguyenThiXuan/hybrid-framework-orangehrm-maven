package com.jquery;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;

import java.util.List;

public class Level_12_DataTable extends BaseTest {
    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
        //Tuy vao muon goi url nao duoc mo len truoc
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);
    }

    @Test (enabled = false)
    public void Table_01_Paging(){
        //1. Mo ra trang bat ki dua vao so trang truyen vao
        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageActiveByNumber("5"));
        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));
        homePage.openPageByNumber("20");
        Assert.assertTrue(homePage.isPageActiveByNumber("20"));
    }

    @Test (enabled = false)
    public void Table_02_Search(){
        //2. Search o bat ki header nao
        homePage.enterToHeaderTextboxByName("Country","Afghanistan");

        //3. Verify bat ki thong tin cua 1 country nao
        Assert.assertTrue(homePage.isPageInfoDisplayed("384187", "Afghanistan", "407124", "791312"));

        homePage.refreshPage(driver);
        homePage.sleepInSecond(2);

        homePage.enterToHeaderTextboxByName("Total","24853148");
        homePage.refreshPage(driver);
        homePage.sleepInSecond(2);

        homePage.enterToHeaderTextboxByName("Females","276880");
        homePage.refreshPage(driver);
        homePage.sleepInSecond(2);
    }

    @Test (enabled = false)
    public void Table_03_Action() {
        homePage.enterToHeaderTextboxByName("Country", "Afghanistan");
        homePage.sleepInSecond(2);

        //4. Deleted/edit any data
        homePage.clickToActionByCountryName("Afghanistan", "remove");
        homePage.refreshPage(driver);

        homePage.clickToActionByCountryName("Afghanistan", "edit");
        homePage.refreshPage(driver);
    }

    @Test (enabled = false)
    public void Table_04_Index() {
        homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickLoadDataButton();
        homePage.enterToTextBoxByColumnNameAndRowIndex("Company", "2", "Company");
        homePage.enterToTextBoxByColumnNameAndRowIndex("Contact Person", "2", "Contact");
        homePage.enterToTextBoxByColumnNameAndRowIndex("Order Placed", "2", "11");
        homePage.selectToDropDownByColumnNameAndRowIndex("Country", "2", "Japan");
        homePage.checkToCheckBoxByColumnNameAndRowIndex("NPO?", "2");
        homePage.actionToRowByRowIndex("2", "Insert");

    }

    @Test (enabled = true)
    public void Table_05_Get_All_Value() {
        List<String> countryActualValue =  homePage.getColumnAllValueByColumnName("Country");
        System.out.println(countryActualValue);
        System.out.println(countryActualValue.size());
    }

    @AfterClass
    public void quit(){
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
}
