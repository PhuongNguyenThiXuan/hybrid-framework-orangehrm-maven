package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;

public class Level_10_Multiple_Driver extends BaseTest {
    @Parameters({"userUrl", "adminUrl", "browser"})
    @BeforeClass
    public void beforeClass(String userUrl, String adminUrl, String browserName){
        //Gan du lieu
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        adminUser = "automationfc";
        adminPassword = "123456a@A";

        userFirstName = "Phuong";
        userLastName = "Nguyen";
        userEmailAddress = "phuong" + getRandomNumber() + "@yopmail.com";
        userPassword = "123456a@A";

        //Tuy vao muon goi url nao duoc mo len truoc
        userDriver = getBrowserDriver(userUrl, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class,userDriver);

        adminDriver = getBrowserDriver(adminUrl, browserName);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,adminDriver);
    }

    @Test
    public void OpenCart_01_Multiple_Driver(){
        //User Window
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, userDriver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        //Admin Window
        adminLoginPage.enterToUserName(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());

        //User Window chay tiep
        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, userDriver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());
    }

    @AfterClass
    public void quit(){
        closeBrowser(userDriver);
        closeBrowser(adminDriver);
    }

    private WebDriver userDriver;
    private WebDriver adminDriver;
    private String userUrl, adminUrl;
    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private UserMyAccountPO userMyAccountPage;

    String adminUser, adminPassword;
    String userFirstName, userLastName, userEmailAddress, userPassword;
}
