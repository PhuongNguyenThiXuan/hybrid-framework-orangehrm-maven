package pageObjects.jquery;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.jquery.HomePageUI;

public class HomePageObject extends BasePage {

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public void openPageByNumber(String pageNumber) {
        waitElementClickable(driver, HomePageUI.DYNAMIC_PAGE_BY_NUMBER, pageNumber);
        clickToElement(driver, HomePageUI.DYNAMIC_PAGE_BY_NUMBER, pageNumber);
    }

    public boolean isPageActiveByNumber(String pageNumber) {
        waitListElementVisible(driver, HomePageUI.DYNAMIC_PAGE_ACTIVE_BY_NUMBER, pageNumber);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_PAGE_ACTIVE_BY_NUMBER, pageNumber);
    }

    public void enterToHeaderTextboxByName(String headerName, String value) {
        waitElementVisible(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, headerName);
        sendKey(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, value, headerName);
        sendKey(driver, HomePageUI.DYNAMIC_HEADER_TEXTBOX_BY_NAME, Keys.ENTER, headerName);
    }

    public boolean isPageInfoDisplayed(String female, String country, String male, String total) {
        waitElementVisible(driver, HomePageUI.DYNAMIC_PAGE_INFO, female, country, male, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_PAGE_INFO, female, country, male, total);
    }
}
