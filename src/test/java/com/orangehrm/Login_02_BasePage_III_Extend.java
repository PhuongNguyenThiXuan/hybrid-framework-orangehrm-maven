package com.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

//Extend: không khai báo, không khởi tạo
public class Login_02_BasePage_III_Extend extends BasePage {
    private WebDriver driver;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void Login_01_Empty(){
        openPageUrl(driver, appUrl);

        sendKey(driver,"//input[@name='username']","");
        sendKey(driver,"//input[@name='password']","");
        clickToElement(driver,"//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver,"//input[@name='username']//parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(getElementText(driver,"//input[@name='password']//parent::div/following-sibling::span"),"Required");
    }

    @Test
    public void Login_02_Invalid_UserName(){
        openPageUrl(driver, appUrl);

        sendKey(driver,"//input[@name='username']","Admin@gmail.com");
        sendKey(driver,"//input[@name='password']","admin123");
        clickToElement(driver,"//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]"),
                "Invalid credentials");
    }

    @Test
    public void Login_03_Invalid_Password(){
        openPageUrl(driver, appUrl);

        sendKey(driver,"//input[@name='username']","Admin");
        sendKey(driver,"//input[@name='password']","admin1234");
        clickToElement(driver,"//button[contains(@class, 'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class,'content-text')]"),
                "Invalid credentials");
    }

    @Test
    public void Login_04_Valid_UserName_Password(){
        openPageUrl(driver, appUrl);

        sendKey(driver,"//input[@name='username']","Admin");
        sendKey(driver,"//input[@name='password']","admin123");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(getElementText(driver,"//div[@class='oxd-topbar-header-title']//h6"),
                "Dashboard");
    }


    public boolean isAllLoadingSpinnerInvisible (){
        return waitListElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }
}
