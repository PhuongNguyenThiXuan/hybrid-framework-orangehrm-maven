package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.editNavigation.PersonalDetailsPageUI;

public class PersonalDetailsPageObject extends EditNavigatorPageObject {
    private WebDriver driver;

    public PersonalDetailsPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public String getFirstNameTextBoxValue(){
        waitElementVisible(driver, PersonalDetailsPageUI.FIRST_NAME_TEXT_BOX);
        return getDOMProperty(driver,PersonalDetailsPageUI.FIRST_NAME_TEXT_BOX, "value");
    }

    public String getMiddleNameTextBoxValue(){
        waitElementVisible(driver, PersonalDetailsPageUI.MIDDLE_NAME_TEXT_BOX);
        return getDOMProperty(driver,PersonalDetailsPageUI.MIDDLE_NAME_TEXT_BOX, "value");
    }

    public String getLastNameTextBoxValue(){
        waitElementVisible(driver, PersonalDetailsPageUI.LAST_NAME_TEXT_BOX);
        return getDOMProperty(driver,PersonalDetailsPageUI.LAST_NAME_TEXT_BOX, "value");
    }

    public String getEmployeeIDTextBoxValue(){
        waitElementVisible(driver, PersonalDetailsPageUI.EMPLOYEE_ID_TEXT_BOX);
        return getDOMProperty(driver,PersonalDetailsPageUI.EMPLOYEE_ID_TEXT_BOX, "value");
    }


}
