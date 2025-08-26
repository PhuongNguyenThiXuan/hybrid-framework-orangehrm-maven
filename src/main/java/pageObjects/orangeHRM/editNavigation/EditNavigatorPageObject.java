package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v134.page.Page;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNavigation.EditNavigatorPageUI;

public class EditNavigatorPageObject extends BasePage {
    WebDriver driver;

    public EditNavigatorPageObject(WebDriver driver) {
        this.driver = driver;
    }

    //Cach 1: 10 ham cho 10 page
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

    //Cách 2: 1 hàm cho 10 page
    public EditNavigatorPageObject openEditNavigatorPageByName (String pageName) {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        System.out.println("Open Page: " + pageName);

        switch (pageName){
            case "Personal Details":
                return  PageGenerator.getPage(PersonalDetailsPageObject.class, driver);
            case "Contact Details":
                return  PageGenerator.getPage(ContactDetailPageObject.class, driver);
            case "Dependents":
                return  PageGenerator.getPage(DependencePageObject.class, driver);
            case "Job":
                return  PageGenerator.getPage(JobPageObject.class, driver);
            default:
                throw new IllegalArgumentException("Page name is not valid: " + pageName);
        }
    }

    //Cách 3: 1 hàm cho 10 page (không switch - case)
    public void openEditNavigatorByName (String pageName) {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
    }

}
