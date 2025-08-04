package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.openCart.user.UserMyAccountPageUI;
import pageUIs.openCart.user.UserRegisterPageUI;

public class UserMyAccountPO extends BasePage {
    private WebDriver driver;

    public UserMyAccountPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isMyAccountPageDisplayed() {
        waitElementVisible(driver, UserMyAccountPageUI.MY_ACCOUNT_BREADCRUMB);
        return isElementDisplayed(driver, UserMyAccountPageUI.MY_ACCOUNT_BREADCRUMB);
    }
}
