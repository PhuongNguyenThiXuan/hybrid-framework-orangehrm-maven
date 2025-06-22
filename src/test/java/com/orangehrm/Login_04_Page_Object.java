package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;


public class Login_04_Page_Object extends BaseTest{
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        driver = getBrowserDriver(appUrl, browserName);

        loginPage = new LoginPageObject();
    }

    @Test
    public void Employee_01_CreateNewEmployee(){
        loginPage.enterToUserNameTextBox("Admin");
        loginPage.enterToPasswordTextBox("admin123");
        loginPage.clickLoginButton();

        dashboardPage = new DashboardPageObject();
        dashboardPage.clickToPIMModule();

        employeeListPage = new EmployeeListPageObject();
        employeeListPage.clickAddEmployeeButton();

        addEmployeePage = new AddEmployeePageObject();
        addEmployeePage.enterToFirstNameTextBox("");
        addEmployeePage.enterToMiddleNameTextBox("");
        addEmployeePage.enterToLastNameTextBox("");
        employeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clickSaveButton();

        personalDetailsPage = new PersonalDetailsPageObject();
        Assert.assertEquals(personalDetailsPage.getFirstNameTextBoxValue,"");
        Assert.assertEquals(personalDetailsPage.getMiddleNameTextBoxValue,"");
        Assert.assertEquals(personalDetailsPage.getLastNameTextBoxValue,"");
        Assert.assertEquals(personalDetailsPage.getEmployeeIDTextBoxValue,employeeID);
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
    private String employeeID;

}
