package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.*;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.DependencePageObject;
import pageObjects.orangeHRM.editNavigation.JobPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailsPageObject;


public class Level_23_Browser_Config extends BaseTest{
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        driver = getBrowserDriver(appUrl, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        //Selenium 4.x
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("144");
        chromeOptions.useWebView(true);
        chromeOptions.configureFromEnv();

        adminUsername = "automationfc";
        adminPassword = "A@a123456!";

        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
    }

    @Test(enabled = true)
    public void Employee_01_NewEmployee(){
    }

    @Test(enabled = true)
    public void Employee_02_Upload_Avatar(){
    }

    @Test(enabled = false)
    public void Employee_03_Personl_Details(){
    }

    @AfterClass(alwaysRun = true)
    public void quit(){
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private AddEmployeePageObject addEmployeePage;
    private EmployeeListPageObject employeeListPage;
    private PersonalDetailsPageObject personalDetailPage;
    private ContactDetailPageObject contactDetailPage;
    private PageGeneratorManager pageGeneratorManager;
    private JobPageObject jobPage;
    private DependencePageObject dependentPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeMiddleName, employeeLastName;
    private String employeeUsername, employeePassword;
}
