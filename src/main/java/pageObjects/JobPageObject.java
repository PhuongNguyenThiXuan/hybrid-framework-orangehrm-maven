package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.JobPageUI;

public class JobPageObject extends BasePage {
    private WebDriver driver;

    public JobPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
