package pageObjects.orangeHRM.editNavigation;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.PersonalDetailsPageUI;

public class PersonalDetailsPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public PersonalDetailsPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    @Step("Get FirstName textBox value")
    public String getFirstNameTextBoxValue(){
        waitElementVisible(driver, PersonalDetailsPageUI.FIRST_NAME_TEXT_BOX);
        return getElementDOMProperty(driver,PersonalDetailsPageUI.FIRST_NAME_TEXT_BOX, "value");
    }

    @Step("Get MiddleName textBox value")
    public String getMiddleNameTextBoxValue(){
        waitElementVisible(driver, PersonalDetailsPageUI.MIDDLE_NAME_TEXT_BOX);
        return getElementDOMProperty(driver,PersonalDetailsPageUI.MIDDLE_NAME_TEXT_BOX, "value");
    }

    @Step("Get LastName textBox value")
    public String getLastNameTextBoxValue(){
        waitElementVisible(driver, PersonalDetailsPageUI.LAST_NAME_TEXT_BOX);
        return getElementDOMProperty(driver,PersonalDetailsPageUI.LAST_NAME_TEXT_BOX, "value");
    }

    @Step("Get EmployeeID textBox value")
    public String getEmployeeIDTextBoxValue(){
        waitElementVisible(driver, PersonalDetailsPageUI.EMPLOYEE_ID_TEXT_BOX);
        return getElementDOMProperty(driver,PersonalDetailsPageUI.EMPLOYEE_ID_TEXT_BOX, "value");
    }
}
