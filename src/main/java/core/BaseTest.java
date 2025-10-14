package core;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//mport com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.manager.SeleniumManagerOutput;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import reportConfig.ExtentManager;

import java.time.Duration;
import java.util.Random;

import static core.BrowserList.*;

//Parent class cho các test.java.com.orangehrm
public class BaseTest {
    private WebDriver driver;
//    protected final Logger log;
//
//    public BaseTest() {
//        //this.log = LogFactory.getLog(getClass()); //Run with log4j
//        this.log = LogManager.getLogger(getClass());
//    }

    protected WebDriver getBrowserDriver(String appURL, String browserName){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser is not valid");
        }
        driver.get(appURL);
        //driver.manage().window().setPosition(new Point(0,0));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)); // chạy vs BasePageFactory phải cmt implicit
        driver.manage().window().maximize();
        //log.info("=============== INIT BROWSER & DRIVER ===============");
        return driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    protected void closeBrowser(){
        if (null != driver){
            driver.quit();
        }
        //log.info("=============== CLOSE BROWSER & DRIVER ===============");
    }

    protected void closeBrowser (WebDriver driver){
        if (null != driver){
            driver.quit();
        }
        //log.info("=============== CLOSE BROWSER & DRIVER ===============");
    }

    protected int getRandomNumber(){
        return new Random().nextInt(99999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            //log.info("--------------- PASSED ---------------");
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            //log.info("--------------- FAILED ---------------");
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            //log.info("--------------- PASSED ---------------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            //log.info("--------------- FAILED ---------------");
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            //log.info("--------------- PASSED ---------------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            //log.info("--------------- FAILED ---------------");
        }
        return pass;
    }

    protected void takeScreenshot (){
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        //ExtentManager.getTest().log(LogStatus.INFO, "Test Failed", ExtentManager.getTest().addBase64ScreenShot(base64Screenshot));
    }
}

