package core;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//mport com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Random;

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
                Path xpiPath = Paths.get(GlobalConstants.BROWSER_EXTENSION_PATH + "wappalyzer-firefox.xpi");
                FirefoxDriver ffDriver = (FirefoxDriver) driver;
                ffDriver. installExtension(xpiPath);
                break;
            case CHROME:
//                File file = new File(GlobalConstants.BROWSER_EXTENSION_PATH + "Wappalyzer-chrome.crx");
//                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addExtensions(file);
//                driver = new ChromeDriver(chromeOptions);

                ChromeOptions chromeOptions = new ChromeOptions();
                Path path = Paths.get(GlobalConstants.BROWSER_EXTENSION_PATH + "Wappalyzer-chrome.crx");
                File extensionFilePath = new File(path.toUri());
                chromeOptions.addExtensions(extensionFilePath);
                driver = new ChromeDriver(chromeOptions);
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            case HEAD_CHROME:
                ChromeOptions chromeHeadOptions = new ChromeOptions();
                chromeHeadOptions.addArguments("--headless");
                chromeHeadOptions.addArguments("window-size=1920x1080");
                driver = new ChromeDriver(chromeHeadOptions);
                break;
            case HEAD_FIREFOX:
                FirefoxOptions firefoxHeadOptions = new FirefoxOptions();
                firefoxHeadOptions.addArguments("--headless");
                firefoxHeadOptions.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(firefoxHeadOptions);
                break;
            case HEAD_EDGE:
                EdgeOptions edgeHeadOptions = new EdgeOptions();
                edgeHeadOptions.addArguments("--headless");
                edgeHeadOptions.addArguments("window-size=1920x1080");
                driver = new EdgeDriver(edgeHeadOptions);
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }
        driver.get(appURL);
        //driver.manage().window().setPosition(new Point(0,0));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)); // chạy vs BasePageFactory phải cmt implicit
        //driver.manage().window().maximize();
        //log.info("=============== INIT BROWSER & DRIVER ===============");
        return driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    protected void closeBrowser(){
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME.toLowerCase();
            String driverInstanceName = driver.toString().toLowerCase();
            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("safari")) {
                browserDriverName = "safaridriver";
            } else {
                throw new RuntimeException("Driver instance is not supported!");
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        //deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("htmlAllure");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}

