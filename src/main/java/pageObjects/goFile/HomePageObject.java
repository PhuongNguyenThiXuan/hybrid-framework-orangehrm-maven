package pageObjects.goFile;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.goFile.HomePageUI;

public class HomePageObject extends BasePage {

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public boolean isLoadingIconDisappear() {
        return waitListElementInvisible(driver, HomePageUI.LOADING_ICON);
    }

    public boolean isProgressBarIconDisappear() {
        return waitListElementInvisible(driver, HomePageUI.PROGRESS_BAR_ICON);
    }

    public String getSucessLink() {
        waitElementVisible(driver, HomePageUI.SUCCESS_CARD_LINK);
        return getElementText(driver, HomePageUI.SUCCESS_CARD_LINK);
    }

    public boolean isFileUploadedSuccess(String fileName) {
        waitElementVisible(driver, HomePageUI.UPLOAD_FILE_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.UPLOAD_FILE_NAME, fileName);
    }
}
