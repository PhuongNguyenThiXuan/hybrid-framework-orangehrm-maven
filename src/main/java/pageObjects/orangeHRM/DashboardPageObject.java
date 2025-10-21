package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.DashboardPageUI;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;

    public DashboardPageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Click to PIM Module then navigate to EmployeeList page")
    public EmployeeListPageObject clickToPIMModule() {
        waitElementClickable(driver, DashboardPageUI.PIM_MODULE);
        clickToElement(driver, DashboardPageUI.PIM_MODULE);
        return PageGeneratorManager.getEmployeeListPage(driver);
        //return PageGenerator.getPage(EmployeeListPageObject.class,driver);
    }
}
