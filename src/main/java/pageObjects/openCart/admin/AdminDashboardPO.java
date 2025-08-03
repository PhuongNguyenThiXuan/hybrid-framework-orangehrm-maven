package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminCustomerPageUI;
import pageUIs.openCart.admin.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {
    WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public AdminCustomerPO openCustomerPage() {
        waitElementVisible(driver, AdminDashboardPageUI.CUSTOMER_BUTTON);
        clickToElement(driver, AdminDashboardPageUI.CUSTOMER_BUTTON);
        return PageGenerator.getPage(AdminCustomerPO.class, driver);
    }
}
