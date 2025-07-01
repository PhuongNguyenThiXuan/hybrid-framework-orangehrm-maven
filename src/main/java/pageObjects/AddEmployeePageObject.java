package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AddEmployeePageUI;
import pageUIs.LoginPageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver){
        this.driver = driver;
    }

    public void enterToFirstNameTextBox(String userName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.FIRST_NAME_TEXT_BOX, userName);
    }

    public void enterToMiddleNameTextBox(String password) {
        waitElementVisible(driver, AddEmployeePageUI.MIDDLE_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.MIDDLE_NAME_TEXT_BOX, password);
    }

    public void enterToLastNameTextBox(String lastname) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.LAST_NAME_TEXT_BOX, lastname);
    }

    public String getEmployeeID() {
        waitElementVisible (driver, AddEmployeePageUI.EMPLOYEE_ID_TEXT_BOX);
        return getDOMProperty(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXT_BOX, "value");
    }

    public void clickSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
    }
}
