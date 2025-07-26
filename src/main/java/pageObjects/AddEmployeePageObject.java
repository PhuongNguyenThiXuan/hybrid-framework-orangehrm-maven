package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AddEmployeePageUI;
import pageUIs.BasePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver){
        this.driver = driver;
    }

    public void enterToFirstNameTextBox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.FIRST_NAME_TEXT_BOX, firstName);
    }

    public void enterToMiddleNameTextBox(String middleName) {
        waitElementVisible(driver, AddEmployeePageUI.MIDDLE_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.MIDDLE_NAME_TEXT_BOX, middleName);
    }

    public void enterToLastNameTextBox(String lastName) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.LAST_NAME_TEXT_BOX, lastName);
    }

    public String getEmployeeID() {
        waitElementVisible (driver, AddEmployeePageUI.EMPLOYEE_ID_TEXT_BOX);
        return getDOMProperty(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXT_BOX, "value");
    }

    public PersonalDetailsPageObject clickSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, AddEmployeePageUI.SPINNER_ICON);
        //return PageGeneratorManager.getPersonalDetailsPage(driver);
        return PageGeneratorGeneric.getPage(PersonalDetailsPageObject.class, driver);
    }
}
