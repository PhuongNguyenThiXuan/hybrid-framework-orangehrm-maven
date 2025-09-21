package com.goFile;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.goFile.HomePageObject;

public class Level_13_Upload_File extends BaseTest {
    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class, driver);
    }

    @Test ()
    public void Upload_01_Multiple () {
        Assert.assertTrue(homePage.isLoadingIconDisappear());

        homePage.uploadMultipleFiles(driver, image1, image2, image3);

        Assert.assertTrue(homePage.isProgressBarIconDisappear());

        String successUrl = homePage.getSucessLink();

        homePage.openPageUrl(driver, successUrl);

        Assert.assertTrue(homePage.isLoadingIconDisappear());
        homePage.sleepInSecond(3);

        Assert.assertTrue(homePage.isFileUploadedSuccess(image1));
        Assert.assertTrue(homePage.isFileUploadedSuccess(image2));
        Assert.assertTrue(homePage.isFileUploadedSuccess(image3));
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
