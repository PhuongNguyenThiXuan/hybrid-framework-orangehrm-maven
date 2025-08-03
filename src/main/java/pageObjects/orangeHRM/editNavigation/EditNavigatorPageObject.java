package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNavigation.EditNavigatorPageUI;

public class EditNavigatorPageObject extends BasePage {
    WebDriver driver;

    public EditNavigatorPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public ContactDetailPageObject openContactDetailPage() {
        waitElementClickable(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        return PageGenerator.getPage(ContactDetailPageObject.class, driver);
    }

    public JobPageObject openJobPage() {
        waitElementClickable(driver, EditNavigatorPageUI.JOB_LINK);
        clickToElement(driver, EditNavigatorPageUI.JOB_LINK);
        return PageGenerator.getPage(JobPageObject.class, driver);
    }

    public DependencePageObject openDependentPage() {
        waitElementVisible(driver, EditNavigatorPageUI.DEPENDENT_LINK);
        clickToElement(driver, EditNavigatorPageUI.DEPENDENT_LINK);
        return PageGenerator.getPage(DependencePageObject.class,driver);
    }

    public PersonalDetailsPageObject openPersonalDetailsPage() {
        waitElementClickable(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        return PageGenerator.getPage(PersonalDetailsPageObject.class, driver);
    }
}
