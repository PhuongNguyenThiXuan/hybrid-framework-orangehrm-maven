package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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


public class Level_22_LiveCode extends BaseTest{
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        driver = getBrowserDriver(appUrl, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUsername = "automationfc";
        adminPassword = "A@a123456!";

        employeeFirstName = "Hello";
        employeeMiddleName = "Test";
        employeeLastName = "World!";
        employeeID = String.valueOf(getRandomNumber());
        employeeUsername = "phuong" + getRandomNumber();
        employeePassword = "A@a123456!#$";

        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Dashboard"));
    }

    @Test(enabled = false)
    public void Employee_01_NewEmployee(){
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver, "middleName", employeeMiddleName);
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);

        addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", employeeID);

        addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

        loginPage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailsPageObject.class, driver);
        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "middleName"), employeeMiddleName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastName);
        //verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        //logout
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

        //login with employee info
        loginPage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);
        dashboardPage.sleepInSecond(2);

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        //verify left menu
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "My Info"));
        dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
    }

    @Test(enabled = true)
    public void Employee_02_Upload_Avatar(){
        dashboardPage.clickToModuleByTextInMenuItem(driver, "My Info");
        personalDetailPage = PageGenerator.getPage(PersonalDetailsPageObject.class, driver);

        personalDetailPage.clickToProfileImage();

        personalDetailPage.uploadMultipleFiles(driver, "video.mov");
        verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(),"Attachment Size Exceeded");

        personalDetailPage.uploadMultipleFiles(driver, "testrail.cfg");
        verifyEquals(personalDetailPage.getErrorMessageAtProfileImage(),"File type not allowed");

        personalDetailPage.uploadMultipleFiles(driver, "VNG Calendar 2026.pdf");

    }

    @Test(enabled = false)
    public void Employee_03_Personl_Details(){
    }

    @AfterClass(alwaysRun = true)
    public void quit(){
        //closeBrowser();
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
