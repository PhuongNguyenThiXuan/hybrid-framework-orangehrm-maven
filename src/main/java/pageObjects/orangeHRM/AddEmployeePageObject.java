package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.editNavigation.PersonalDetailsPageObject;
import pageUIs.openCart.user.UserLoginPageUI;
import pageUIs.orangeHRM.AddEmployeePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;

    public AddEmployeePageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Enter to FirstName textbox: {0}")
    public void enterToFirstNameTextBox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.FIRST_NAME_TEXT_BOX, firstName);
    }

    @Step("Enter to MiddleName textbox: {0}")
    public void enterToMiddleNameTextBox(String middleName) {
        waitElementVisible(driver, AddEmployeePageUI.MIDDLE_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.MIDDLE_NAME_TEXT_BOX, middleName);
    }

    @Step("Enter to LastName textbox: {0}")
    public void enterToLastNameTextBox(String lastName) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXT_BOX);
        sendKey(driver, AddEmployeePageUI.LAST_NAME_TEXT_BOX, lastName);
    }

    @Step("Get EmployeeID")
    public String getEmployeeID() {
        waitElementVisible (driver, AddEmployeePageUI.EMPLOYEE_ID_TEXT_BOX);
        return getDOMProperty(driver, AddEmployeePageUI.EMPLOYEE_ID_TEXT_BOX, "value");
    }

    @Step("Click Save button and navigate to PersonalDetails page")
    public PersonalDetailsPageObject clickSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInvisible(driver, AddEmployeePageUI.SPINNER_ICON);
        return PageGeneratorManager.getPersonalDetailsPage(driver);
        //return PageGenerator.getPage(PersonalDetailsPageObject.class, driver);
    }
}
