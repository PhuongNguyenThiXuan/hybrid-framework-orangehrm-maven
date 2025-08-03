package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
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
import pageObjects.openCart.user.UserRegisterPO;

import java.util.Random;

public class Level_09_Switch_Url extends BaseTest {
    @Parameters({"userUrl", "adminUrl", "browser"})
    @BeforeClass
    public void beforeClass(String userUrl, String adminUrl, String browserName){
        //Gan du lieu
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        rand = new Random();

        adminUser = "automationfc";
        adminPassword = "123456a@A";
        userFirstName = "Phuong";
        userLastName = "Nguyen";
        userEmailAddress = "phuong" + rand.nextInt(99999) + "@yopmail.com";
        userPassword = "123456a@A";

        //Tuy vao muon goi url nao duoc mo len truoc
        driver = getBrowserDriver(userUrl, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class,driver);
    }

    @Test
    public void OpenCart_01_Login_And_Logout() throws InterruptedException {
        userLoginPage = userHomePage.clickToMyAccount();

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Thread.sleep(2000);
        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        adminLoginPage = userRegisterPage.openAdminSite(driver, adminUrl);

        adminLoginPage.enterToUserName(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        userHomePage = adminLoginPage.openUserSite(driver, userUrl);
    }


    @AfterClass
    public void quit(){
        //driver.quit();
    }

    private WebDriver driver;
    private String userUrl, adminUrl;
    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    Random rand;
    String adminUser, adminPassword;
    String userFirstName, userLastName, userEmailAddress, userPassword;
}
