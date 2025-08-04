package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.UserLoginPageUI;

public class UserLoginPO extends BasePage {
    WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserRegisterPO clickToContinueButton() {
        waitElementClickable(driver, UserLoginPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserLoginPageUI.CONTINUE_BUTTON );
        return PageGenerator.getPage(UserRegisterPO.class, driver);
    }

    public void enterToEmailAddressTextbox(String userEmail) {
        waitElementVisible(driver, UserLoginPageUI.EMAIL_TEXT_BOX);
        sendKey(driver, UserLoginPageUI.EMAIL_TEXT_BOX, userEmail);
    }

    public void enterToPasswordTextbox(String userPassword) {
        waitElementVisible(driver, UserLoginPageUI.PASSWORD_TEXT_BOX);
        sendKey(driver, UserLoginPageUI.PASSWORD_TEXT_BOX, userPassword);
    }

    public UserMyAccountPO clickToLoginButton() {
        waitElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(UserMyAccountPO.class, driver);
    }
}
