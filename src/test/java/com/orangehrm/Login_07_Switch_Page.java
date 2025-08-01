package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;


public class Login_07_Switch_Page extends BaseTest{
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        driver = getBrowserDriver(appUrl, browserName);

        loginPage = PageGeneratorGeneric.getPage(LoginPageObject.class, driver);

        adminUsername = "automationfc";
        adminPassword = "A@a123456!";
        employeeFirstName = "Hello";
        employeeMiddleName = "Test";
        employeeLastName = "World!";

    }

    @Test
    public void Employee_01_CreateNewEmployee(){
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

        Assert.assertEquals(personalDetailPage.getFirstNameTextBoxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getMiddleNameTextBoxValue(),employeeMiddleName);
        Assert.assertEquals(personalDetailPage.getLastNameTextBoxValue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextBoxValue(),employeeID);
    }

    @Test
    public void Employee_02_Switch_Page(){
        //Personal -> Contact
        contactDetailPage = personalDetailPage.openContactDetailPage(driver);

        //Contact -> Job
        jobPage = contactDetailPage.openJobPage(driver);

        //Job -> Dependent
        dependentPage = jobPage.openDependentPage(driver);

        //Dependent -> Personal
        personalDetailPage = dependentPage.openPersonalDetailsPage(driver);

        //Personal -> Job
        jobPage = personalDetailPage.openJobPage(driver);

        contactDetailPage = jobPage.openContactDetailPage(driver);
        contactDetailPage = dependentPage.openContactDetailPage(driver);
    }

    @AfterClass
    public void quit(){

        driver.quit();
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
