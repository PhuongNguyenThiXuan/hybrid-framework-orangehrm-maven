package com.saucelab;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.saucelab.LoginPO;
import pageObjects.saucelab.ProductPO;

public class Level_24_Sortable extends BaseTest{
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName){
        driver = getBrowserDriver(appUrl, browserName);
        loginPage = PageGenerator.getPage(LoginPO.class,driver);

        productPage = loginPage.loginToSauce("standard_user", "secret_sauce");
    }

    @Test(enabled = true)
    public void Sort_01_By_Name(){
        productPage.sortBy("Name (A to Z)");
        verifyEquals(productPage.getSortItemSelected(), "Name (A to Z)");
        productPage.sleepInSecond(2);

        verifyTrue(productPage.isProductNameSortAscending());

        productPage.sortBy("Name (Z to A)");
        verifyEquals(productPage.getSortItemSelected(), "Name (Z to A)");
        productPage.sleepInSecond(2);

        verifyTrue(productPage.isProductNameSortDescending());
    }

    @Test(enabled = true)
    public void Sort_02_By_Price(){
        productPage.sortBy("Price (low to high)");
        verifyEquals(productPage.getSortItemSelected(), "Price (low to high)");
        productPage.sleepInSecond(2);

        verifyTrue(productPage.isProductPriceSortByAscending());

        productPage.sortBy("Price (high to low)");
        verifyEquals(productPage.getSortItemSelected(), "Price (high to low)");

        verifyTrue(productPage.isProductPriceSortByDescending());
    }

    @AfterClass(alwaysRun = true)
    public void quit(){
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private ProductPO productPage;
}
