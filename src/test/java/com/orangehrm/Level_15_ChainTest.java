package com.orangehrm;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.Status;
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
import reportConfig.ChainTestReport;
import reportConfig.ExtentManager;

import java.lang.reflect.Method;


public class Level_15_ChainTest extends BaseTest{
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        this.browserName = browserName.toUpperCase();
        driver = getBrowserDriver(appUrl, browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        adminUsername = "automationfc";
        adminPassword = "A@a123456!";
        employeeFirstName = "Hello";
        employeeMiddleName = "Test";
        employeeLastName = "World!";
    }

    @Test (enabled = true)
    public void Employee_01_CreateNewEmployee(){
        ChainTestListener.log("Run on Browser: " + browserName);
        ChainTestListener.log("NewEmployee - STEP 01: Enter to Username and Password with info: " + adminUsername + " | " + adminPassword);
        loginPage.enterToUserNameTextBox(adminUsername);
        loginPage.enterToPasswordTextBox(adminPassword);

        ChainTestListener.log("NewEmployee - STEP 02: Navigate to Dashboard page");
        dashboardPage = loginPage.clickLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        
        ChainTestListener.log("NewEmployee - STEP 03: Navigate to Employee List page");
        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
    }

    @Test
    public void Employee_02_ViewEmployee(){
        ChainTestListener.log("NewEmployee - STEP 04: Navigate to Add Employee page");
        addEmployeePage = employeeListPage.clickAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        ChainTestListener.log("NewEmployee - STEP 05: Enter to First/Middle and Lastname with info: " + employeeFirstName + " | " + employeeMiddleName + " | " + employeeLastName);
        addEmployeePage.enterToFirstNameTextBox(employeeFirstName);
        addEmployeePage.enterToMiddleNameTextBox(employeeMiddleName);
        addEmployeePage.enterToLastNameTextBox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
    }

    @Test
    public void Employee_03_EditEmployee(){
        ChainTestListener.log("NewEmployee - STEP 06: Navigate to Personal Detail page");
        personalDetailPage = addEmployeePage.clickSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
    }

    @Test
    public void Employee_04_RemoveEmployee(){
        ChainTestListener.log("NewEmployee - STEP 07: Verify FirstName displayed: " + employeeFirstName);
        Assert.assertEquals(personalDetailPage.getFirstNameTextBoxValue(),employeeMiddleName);

        ChainTestListener.log("NewEmployee - STEP 08: Verify MiddleName displayed: " + employeeMiddleName);
        Assert.assertEquals(personalDetailPage.getMiddleNameTextBoxValue(),employeeMiddleName);

        ChainTestListener.log("NewEmployee - STEP 09: Verify LastName displayed: "+ employeeLastName);
        Assert.assertEquals(personalDetailPage.getLastNameTextBoxValue(),employeeLastName);

        ChainTestListener.log("NewEmployee - STEP 10: Verify EmployeeID displayed: " + employeeID);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextBoxValue(),employeeID);
    }


    @AfterClass
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
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeMiddleName, employeeLastName, browserName;

}
