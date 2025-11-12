package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
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


public class Level_19_Element_Undisplayed extends BaseTest{
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

        employeeUsername = "phuong" + getRandomNumber();
        employeePassword = "&*3456a@A";
    }

    @Test(enabled = true)
    public void Employee_01_CreateNewEmployee(){
        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        //Trường hợp 1: Element có hiển thị ở trên UI và có xuất hiện ở trong DOM (Visible/ Displayed)
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "My Info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Maintenance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Buzz"));

        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver, "middleName", employeeMiddleName);
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");
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
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        //logout
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

        //login with employee info
        loginPage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        //verify left menu
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "My Info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed (driver, "Buzz"));

        //Trường hợp 3: Element không hiển thị ở trên UI và không có ở trong DOM (Invisible)=> false
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnDisplayed (driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnDisplayed (driver, "PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnDisplayed (driver, "Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnDisplayed (driver, "Maintenance"));
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
    private String employeeUsername, employeePassword;
}
