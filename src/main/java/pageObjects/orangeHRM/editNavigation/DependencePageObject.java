package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;

public class DependencePageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public DependencePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
