package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pageUIs.orangeHRM.EmployeeListPageUI;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;

    public EmployeeListPageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Click Add Employee button")
    public AddEmployeePageObject clickAddEmployeeButton() {
        waitElementClickable(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListPageUI.ADD_EMPLOYEE_BUTTON);
        return PageGeneratorManager.getAddEmployeePage(driver);
        //return PageGenerator.getPage(AddEmployeePageObject.class, driver);
    }
}
