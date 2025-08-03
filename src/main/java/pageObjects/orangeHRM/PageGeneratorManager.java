package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailsPageObject;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage (WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage (WebDriver driver){
        return new DashboardPageObject(driver);
    }

    public static EmployeeListPageObject getEmployeeListPage (WebDriver driver){
        return new EmployeeListPageObject(driver);
    }

    public static AddEmployeePageObject getAddEmployeePage (WebDriver driver){
        return new AddEmployeePageObject(driver);
    }

    public static PersonalDetailsPageObject getPersonalDetailsPage (WebDriver driver){
        return new PersonalDetailsPageObject(driver);
    }

    public static ContactDetailPageObject getContactDetailPage (WebDriver driver){
        return new ContactDetailPageObject(driver);
    }
}
