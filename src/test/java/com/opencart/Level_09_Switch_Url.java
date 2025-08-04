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

import java.util.Random;

public class Level_09_Switch_Url extends BaseTest {
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
        driver = getBrowserDriver(userUrl, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class,driver);
    }

    @Test
    public void OpenCart_01_Login_And_Logout(){
        userLoginPage = userHomePage.clickToMyAccount();

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        //User => Admin
        adminLoginPage = userRegisterPage.openAdminSite(driver, adminUrl);

        adminLoginPage.enterToUserName(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //Admin => User
        userHomePage = adminLoginPage.openUserSite(driver, userUrl);

        userLoginPage = userHomePage.clickToMyAccount();

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userMyAccountPage = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //User => Admin
        adminLoginPage = userMyAccountPage.openAdminSite(driver, adminUrl);

        adminLoginPage.enterToUserName(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        //Admin => User
        userHomePage = adminDashboardPage.openUserSite(driver, userUrl);
    }

    public void OpenCart_02_Login_Without_Logout() throws InterruptedException {

    }

    @AfterClass
    public void quit(){
        //closeBrowser();
    }

    private WebDriver driver;
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
