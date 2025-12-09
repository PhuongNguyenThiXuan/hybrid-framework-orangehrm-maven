package pageObjects.techpanda;

import core.BasePage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pageUIs.techpanda.MyAccountPageUI;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

public class MyAccountPO extends BasePage {
    private WebDriver driver;

    public MyAccountPO(WebDriver driver) {
        this.driver = driver;
    }

    public String getSuccessMessage() {
        waitElementVisible(driver, MyAccountPageUI.SUCCESS_MSG);
        return getElementText(driver, MyAccountPageUI.SUCCESS_MSG);
    }

    public String getMyAccountPageTitle() {
        waitElementVisible(driver, MyAccountPageUI.MY_DASHBOARD_TITLE);
        return getElementText(driver, MyAccountPageUI.MY_DASHBOARD_TITLE);
    }
}
