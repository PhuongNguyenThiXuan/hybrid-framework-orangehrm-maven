package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;


public class Login_06_Page_Manager_III extends BaseTest{
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        driver = getBrowserDriver(appUrl, browserName);

        loginPage = PageGeneratorManager.getLoginPage(driver);

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

        personalDetailsPage = addEmployeePage.clickSaveButton();
        Assert.assertTrue(personalDetailsPage.isLoadingSpinnerDisappear(driver));
        personalDetailsPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailsPage.getFirstNameTextBoxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailsPage.getMiddleNameTextBoxValue(),employeeMiddleName);
        Assert.assertEquals(personalDetailsPage.getLastNameTextBoxValue(),employeeLastName);
        Assert.assertEquals(personalDetailsPage.getEmployeeIDTextBoxValue(),employeeID);
    }

    @Test
    public void Employee_02_Contact_Detail(){
        contactDetailPage = personalDetailPage.openContactDetailPage(driver);
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
    private PersonalDetailsPageObject personalDetailsPage;
    private ContactDetailPageObject contactDetailPage;
    private PageGeneratorManager pageGeneratorManager;
    private PersonalDetailsPageObject personalDetailPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstName, employeeMiddleName, employeeLastName;

}
