package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DependentPageUI;
import pageUIs.PersonalDetailsPageUI;

public class DependencePageObject extends BasePage {
    private WebDriver driver;

    public DependencePageObject(WebDriver driver) {
        this.driver = driver;
    }

}
