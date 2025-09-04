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

public class Level_12_DataTable extends BaseTest {
    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
        //Tuy vao muon goi url nao duoc mo len truoc
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);
    }

    @Test (enabled = true)
    public void Table_01_Paging(){
        //1. Mo ra trang bat ki dua vao so trang truyen vao
        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageActiveByNumber("5"));
        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));
        homePage.openPageByNumber("20");
        Assert.assertTrue(homePage.isPageActiveByNumber("20"));
    }

    @Test
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

    @AfterClass
    public void quit(){
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
}
