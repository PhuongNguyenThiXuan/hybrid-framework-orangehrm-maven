package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v134.page.Page;
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

    @Test (enabled = false)
    public void OpenCart_01_Login_And_Logout(){
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

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
        userRegisterPage.openAdminSite(driver, adminUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUserName(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //Admin => User
        userHomePage = adminLoginPage.openUserSite(driver, userUrl);

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userMyAccountPage = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //User => Admin
        userMyAccountPage.openAdminSite(driver, adminUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUserName(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        //Admin => User
        adminDashboardPage.openUserSite(driver, userUrl);
        userHomePage = PageGenerator.getPage(UserHomePO.class, driver);
    }

    @Test (enabled = false)
    public void OpenCart_02_Login_Without_Logout() {
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        //Khong logout => nen van dang o page Register

        //User => Admin
        userRegisterPage.openAdminSite(driver, adminUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUserName(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        //Khong logout => nen van dang o page Customer

        //Admin => User
        userHomePage = adminLoginPage.openUserSite(driver, userUrl);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //User => Admin
        userMyAccountPage.openAdminSite(driver, adminUrl);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class, driver);
        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
    }

    @Test
    public void OpenCart_03_Multiple_Tab() {
        //User login
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmail(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());
        userWindowID = userRegisterPage.getCurrentWindowID(driver);

        //Khong logout => nen van dang o page Register
        //From User page open Admin page
        userRegisterPage.openUrlByNewTAB(driver, adminUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUserName(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        adminWindowID = adminCustomerPage.getCurrentWindowID(driver);

        //Khong logout => nen van dang o page Customer
        //From Admin page => back to User page
        adminCustomerPage.switchToWindowByID(driver, userWindowID);
        adminCustomerPage.sleepInSecond(3);

        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class, driver);

        userHomePage = userRegisterPage.openHomeLogo(driver);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //From User page => back to Admin page
        userMyAccountPage.switchToWindowByID(driver, adminWindowID);
        userMyAccountPage.sleepInSecond(3);

        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class, driver);

        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());
    }


    @AfterClass
    public void quit(){
        closeBrowser();
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
    String userWindowID, adminWindowID;
}
