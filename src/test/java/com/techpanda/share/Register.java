package com.techpanda.share;

import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;
import pageObjects.techpanda.RegisterPO;

import java.util.Set;


public class Register extends BaseTest{
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeTest
    public void beforeClass(String appUrl, String browserName){
        driver = getBrowserDriver(appUrl, browserName);

        homePage = PageGenerator.getPage(HomePO.class, driver);

        loginPage = homePage.openLoginPage();
        registerPage = loginPage.clickCreateAnAccountLink();

        registerPage.enterToFirstName("Phuong");
        registerPage.enterToLastName("Nguyen");
        registerPage.enterToEmail("png" + getRandomNumber() + "@yopmail.com");
        registerPage.enterToPassword("123456789");
        registerPage.enterToConfirmPassword("123456789");
        registerPage.enterToRegisterButton();
        //myAccountPage = registerPage.acceptContinueAlert();
        myAccountPage = PageGenerator.getPage(MyAccountPO.class, driver);

        verifyEquals(myAccountPage.getSuccessMessage(),"Thank you for registering with Main Website Store.");

        cookies = myAccountPage.getPageCookies(driver);

        for (Cookie cookie: cookies){
            System.out.println(cookie );
        }

        //closeBrowser();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private HomePO homePage;
    private MyAccountPO myAccountPage;
    private RegisterPO registerPage;
    public static Set<Cookie> cookies;
}
