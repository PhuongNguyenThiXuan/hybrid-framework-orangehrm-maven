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
import reportConfig.ExtentManager;

import java.lang.reflect.Method;


public class Level_14_Extent3 extends BaseTest{
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
    }

    @Test (enabled = true)
    public void Employee_01_CreateNewEmployee(Method method){
        loginPage.enterToUserNameTextBox(adminUsername);
        loginPage.enterToPasswordTextBox(adminPassword);

        dashboardPage = loginPage.clickLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        addEmployeePage = employeeListPage.clickAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextBox(employeeFirstName);
        addEmployeePage.enterToMiddleNameTextBox(employeeMiddleName);
        addEmployeePage.enterToLastNameTextBox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        personalDetailPage = addEmployeePage.clickSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        //Assert.assertEquals(personalDetailPage.getFirstNameTextBoxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getFirstNameTextBoxValue(),employeeMiddleName);

        Assert.assertEquals(personalDetailPage.getMiddleNameTextBoxValue(),employeeMiddleName);

        Assert.assertEquals(personalDetailPage.getLastNameTextBoxValue(),employeeLastName);

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
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeMiddleName, employeeLastName;

}
