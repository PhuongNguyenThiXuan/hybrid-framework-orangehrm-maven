package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserRegisterPageUI;
import pageUIs.orangeHRM.AddEmployeePageUI;

public class UserRegisterPO extends BasePage {
    WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstName(String firstName) {
        waitElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXT_BOX);
        sendKey(driver, UserRegisterPageUI.FIRST_NAME_TEXT_BOX, firstName);
    }

    public void enterToLastName(String lastName) {
        waitElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXT_BOX);
        sendKey(driver, UserRegisterPageUI.LAST_NAME_TEXT_BOX, lastName);
    }

    public void enterToEmail(String email) {
        waitElementVisible(driver, UserRegisterPageUI.EMAIL_TEXT_BOX);
        sendKey(driver, UserRegisterPageUI.EMAIL_TEXT_BOX, email);
    }

    public void enterToPassword(String password) {
        waitElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXT_BOX);
        sendKey(driver, UserRegisterPageUI.PASSWORD_TEXT_BOX, password);
    }

    public void acceptPrivacyCheckbox() {
        waitElementClickable(driver, UserRegisterPageUI.PRIVACY_CHECKBOX);
        checkToCheckBox(driver, UserRegisterPageUI.PRIVACY_CHECKBOX);
    }

    public void clickContinueButton() {
        waitElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
    }

    public boolean isSuccessMessageDisplayed() {
        waitElementVisible(driver, UserRegisterPageUI.CREATE_ACCOUNT_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, UserRegisterPageUI.CREATE_ACCOUNT_SUCCESS_MESSAGE);
    }
}
