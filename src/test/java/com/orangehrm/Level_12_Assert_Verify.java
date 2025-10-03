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


public class Level_12_Assert_Verify extends BaseTest{
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
    public void Employee_01_CreateNewEmployee(){
        loginPage.enterToUserNameTextBox(adminUsername);
        loginPage.enterToPasswordTextBox(adminPassword);
        dashboardPage = loginPage.clickLoginButton();

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        addEmployeePage = employeeListPage.clickAddEmployeeButton();
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextBox(employeeFirstName);
        addEmployeePage.enterToMiddleNameTextBox(employeeMiddleName);
        addEmployeePage.enterToLastNameTextBox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();

        personalDetailPage = addEmployeePage.clickSaveButton();
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        verifyEquals(personalDetailPage.getFirstNameTextBoxValue(),employeeMiddleName);
        verifyEquals(personalDetailPage.getMiddleNameTextBoxValue(),employeeFirstName);
        verifyEquals(personalDetailPage.getLastNameTextBoxValue(),employeeLastName);
        verifyEquals(personalDetailPage.getEmployeeIDTextBoxValue(),employeeID);
    }

    @Test (enabled = false)
    public void Employee_02_Page_Navigator(){
        //Personal -> Contact
        contactDetailPage = personalDetailPage.openContactDetailPage();

        //Contact -> Job
        jobPage = contactDetailPage.openJobPage();

        //Job -> Dependent
        dependentPage = jobPage.openDependentPage();

        //Dependent -> Personal
        personalDetailPage = dependentPage.openPersonalDetailsPage();

        //Personal -> Job
        jobPage = personalDetailPage.openJobPage();

        contactDetailPage = jobPage.openContactDetailPage();
        contactDetailPage = dependentPage.openContactDetailPage();
    }

    @Test (enabled = false)
    public void Employee_03_Dynamic_Page(){
        //Personal -> Contact
        contactDetailPage = (ContactDetailPageObject) personalDetailPage.openEditNavigatorPageByName("Contact Details");

        //Contact -> Job
        jobPage = (JobPageObject) contactDetailPage.openEditNavigatorPageByName("Job");

        //Job -> Dependent
        dependentPage = (DependencePageObject) jobPage.openEditNavigatorPageByName("Dependents");

        //Dependent -> Personal
        personalDetailPage = (PersonalDetailsPageObject) dependentPage.openEditNavigatorPageByName("Personal Details");

        //Personal -> Job
        jobPage = (JobPageObject) personalDetailPage.openEditNavigatorPageByName("Job");

        contactDetailPage = (ContactDetailPageObject) jobPage.openEditNavigatorPageByName("Contact Details");
        contactDetailPage = (ContactDetailPageObject) dependentPage.openEditNavigatorPageByName("Contact Details");
    }

    @Test (enabled = false)
    public void Employee_04_Dynamic_Page(){
        personalDetailPage.openEditNavigatorByName("Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);

        contactDetailPage.openEditNavigatorByName("Job");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);

        jobPage.openEditNavigatorByName("Dependents");
        dependentPage = PageGenerator.getPage(DependencePageObject.class, driver);

        dependentPage.openEditNavigatorByName("Personal Details");
        personalDetailPage = PageGenerator.getPage(PersonalDetailsPageObject.class, driver);

        personalDetailPage.openEditNavigatorByName("Job");
        jobPage = PageGenerator.getPage(JobPageObject.class, driver);

        jobPage.openEditNavigatorByName("Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);
        dependentPage.openEditNavigatorByName("Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);
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
