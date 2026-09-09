package com.orangehrm;

import core.BaseTest;
import core.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailsPageObject;
import testdata.orangehrm.EmployeeData;
import testdata.orangehrm.Employee_Data;


public class Level_25_Data_Test_IV_POJO extends BaseTest{
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        driver = getBrowserDriver(appUrl, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        employeeData = Employee_Data.getEmployeeData();
        employeeID = String.valueOf(getRandomNumber());
        employeeData.setFirstName("Hello");
        employeeData.setLastName("Test");
        employeeData.setUserName("hello_test");
        employeeData.setPassword("123456a@A");

        loginPage.enterToTextboxByLabel(driver, "Username", GlobalConstants.ADMIN_ORANGE_PASSWORD);
        loginPage.enterToTextboxByLabel(driver, "Password", GlobalConstants.ADMIN_ORANGE_PASSWORD);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Dashboard"));
    }

    @Test(enabled = true)
    public void Employee_01_NewEmployee(){
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeData.getFirstName());
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeData.getLastName());
        addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", employeeID);

        addEmployeePage.enterToForm(employeeData);

        addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

        loginPage.enterToTextboxByLabel(driver, "Username", employeeData.getUserName() + getRandomNumber());
        loginPage.enterToTextboxByLabel(driver, "Password", employeeData.getPassword());
        loginPage.enterToTextboxByLabel(driver, "Confirm Password", employeeData.getPassword());

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailsPageObject.class, driver);
        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeData.getFirstName());
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeData.getLastName());
        //verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        //logout
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

         
    }

    @AfterClass(alwaysRun = true)
    public void quit(){
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private Employee_Data employeeData;

    private DashboardPageObject dashboardPage;
    private AddEmployeePageObject addEmployeePage;
    private EmployeeListPageObject employeeListPage;
    private PersonalDetailsPageObject personalDetailPage;
    private String employeeID;
}
