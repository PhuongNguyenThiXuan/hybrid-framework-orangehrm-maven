package com.jquery;

import core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;

import java.util.List;

public class Level_13_Upload_File extends BaseTest {
    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);
    }

    @Test ()
    public void Upload_01_Single () {
        homePage.uploadMultipleFiles(driver, image1);
        homePage.uploadMultipleFiles(driver, image2);
        homePage.uploadMultipleFiles(driver, image3);

        Assert.assertTrue(homePage.isFileLoadedSuccess(image1));
        Assert.assertTrue(homePage.isFileLoadedSuccess(image2));
        Assert.assertTrue(homePage.isFileLoadedSuccess(image3));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUpLoadedSuccess(image1));
        Assert.assertTrue(homePage.isFileUpLoadedSuccess(image2));
        Assert.assertTrue(homePage.isFileUpLoadedSuccess(image3));
    }

    @Test ()
    public void Upload_02_Multiple () {
        homePage.refreshPage(driver);
        homePage.uploadMultipleFiles(driver, image1, image2, image3);
        //homePage.uploadMultipleFiles(driver, fileName);

        Assert.assertTrue(homePage.isFileLoadedSuccess(image1));
        Assert.assertTrue(homePage.isFileLoadedSuccess(image2));
        Assert.assertTrue(homePage.isFileLoadedSuccess(image3));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUpLoadedSuccess(image1));
        Assert.assertTrue(homePage.isFileUpLoadedSuccess(image2));
        Assert.assertTrue(homePage.isFileUpLoadedSuccess(image3));
    }

    @AfterClass
    public void quit(){
        closeBrowser(driver);
    }

    private WebDriver driver;
    private HomePageObject homePage;
    String image1 = "01.png";
    String image2 = "02.png";
    String image3 = "03.png";
    String[] fileName = {image1, image2, image3};
}
