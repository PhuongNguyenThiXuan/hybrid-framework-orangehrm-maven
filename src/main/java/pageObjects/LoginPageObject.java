package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void enterToUserNameTextBox(String username) {
        waitElementVisible(driver, LoginPageUI.USER_NAME_TEXT_BOX);
        sendKey(driver, LoginPageUI.USER_NAME_TEXT_BOX, username);
    }

    public void enterToPasswordTextBox(String password) {
        waitElementVisible(driver, LoginPageUI.PASS_WORD_TEXT_BOX);
        sendKey(driver, LoginPageUI.PASS_WORD_TEXT_BOX, password);
    }

    public DashboardPageObject clickLoginButton() {
        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getDashboardPage(driver);
        //return PageGeneratorGeneric.getPage(DashboardPageObject.class, driver);
    }
}
