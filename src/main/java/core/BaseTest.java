package core;


import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Random;

import static core.BrowserList.*;

//Parent class cho các test.java.com.orangehrm
public class BaseTest {
    private WebDriver driver;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // chạy vs BasePageFactory phải cmt implicit
        //driver.manage().window().maximize();
        System.out.println("driver trong BaseTest: " + driver.toString());
        return driver;
    }

    protected void closeBrowser(){
        if (!(null == driver)){
            driver.quit();
        }
    }

    protected void closeBrowser (WebDriver driver){
        if (!(null == driver)){
            driver.quit();
        }
    }

    protected int getRandomNumber(){
        return new Random().nextInt(99999);
    }
}
