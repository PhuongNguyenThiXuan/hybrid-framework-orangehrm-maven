package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageUIs.EmployeeListPageUI;

import java.util.List;

public class EmployeeListPageObject extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy (xpath = "//a[text()='Add Employee']/parent::li")
    private WebElement addEmployeeButton;

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public EmployeeListPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddEmployeeButton() {
        waitElementClickable(driver, addEmployeeButton);
        clickToElement(addEmployeeButton);
    }

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
