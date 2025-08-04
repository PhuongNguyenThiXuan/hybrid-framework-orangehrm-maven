package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminLoginPageUI;
import pageUIs.openCart.user.UserRegisterPageUI;

public class AdminLoginPO extends BasePage {
    WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToUserName(String username) {
        waitElementVisible(driver, AdminLoginPageUI.USERNAME_TEXT_BOX);
        sendKey(driver, AdminLoginPageUI.USERNAME_TEXT_BOX, username);
    }

    public void enterToPassword(String password) {
        waitElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXT_BOX);
        sendKey(driver, AdminLoginPageUI.PASSWORD_TEXT_BOX, password);
    }

    public AdminDashboardPO clickLoginButton() {
        waitElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(AdminDashboardPO.class,driver);
    }
}
