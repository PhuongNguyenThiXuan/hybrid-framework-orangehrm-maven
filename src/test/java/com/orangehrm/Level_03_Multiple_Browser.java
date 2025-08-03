package com.orangehrm;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//Extend: không khai báo, không khởi tạo
public class Level_03_Multiple_Browser extends BaseTest {
    private WebDriver driver;
    private BasePage basePage;
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        this.appUrl = appUrl;
        basePage = BasePage.getInstance();
        driver = getBrowserDriver(appUrl, browserName);

        System.out.println("driver trong class test: " + driver.toString());
    }

    @Test
    public void Login_01_Empty(){
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKey(driver,"//input[@name='username']","");
        basePage.sendKey(driver,"//input[@name='password']","");
        basePage.clickToElement(driver,"//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='username']//parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='password']//parent::div/following-sibling::span"),"Required");
    }

   // @Test
    public void Login_02_Invalid_UserName(){
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKey(driver,"//input[@name='username']","Admin@gmail.com");
        basePage.sendKey(driver,"//input[@name='password']","admin123");
        basePage.clickToElement(driver,"//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]"),
                "Invalid credentials");
    }

   // @Test
    public void Login_03_Invalid_Password(){
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKey(driver,"//input[@name='username']","Admin");
        basePage.sendKey(driver,"//input[@name='password']","admin1234");
        basePage.clickToElement(driver,"//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]"),
                "Invalid credentials");
    }

   // @Test
    public void Login_04_Valid_UserName_Password(){
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKey(driver,"//input[@name='username']","Admin");
        basePage.sendKey(driver,"//input[@name='password']","admin123");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='oxd-topbar-header-title']//h6"),
                "Dashboard");
    }


    public boolean isAllLoadingSpinnerInvisible (){
        return basePage.waitListElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }
}
