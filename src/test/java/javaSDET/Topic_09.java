package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_09 {
    String name;

    //Constructor
    public Topic_09(String name){
        this.name = name;
    }


    //Them synchronized => khi chay da luong va goi den ham nay
    // Bat buoc phai chay theo thu tu
    public synchronized WebDriver getDriver() {
        WebDriver driver = null;

        if (driver instanceof WebDriver) { //instanceof
            driver = new FirefoxDriver();
        } return driver;
    }
}
