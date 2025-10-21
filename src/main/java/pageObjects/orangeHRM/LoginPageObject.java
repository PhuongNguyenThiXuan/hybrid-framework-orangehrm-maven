package pageObjects.orangeHRM;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Enter to Username textbox: {0}")
    public void enterToUserNameTextBox(String username) {
        waitElementVisible(driver, LoginPageUI.USER_NAME_TEXT_BOX);
        sendKey(driver, LoginPageUI.USER_NAME_TEXT_BOX, username);
    }

    @Step("Enter to Password textbox: {0}")
    public void enterToPasswordTextBox(String password) {
        waitElementVisible(driver, LoginPageUI.PASS_WORD_TEXT_BOX);
        sendKey(driver, LoginPageUI.PASS_WORD_TEXT_BOX, password);
    }

    @Step("Click Login button")
    public DashboardPageObject clickLoginButton() {
        waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getDashboardPage(driver);
        //return PageGenerator.getPage(DashboardPageObject.class, driver);
    }
}
