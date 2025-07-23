package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PersonalDetailsPageObject extends BasePageFactory {
    private WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameTextbox;

    @CacheLookup
    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleNameTextbox;

    @CacheLookup
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameTextbox;

    @CacheLookup
    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeID;

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public PersonalDetailsPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFirstNameTextBoxValue(){
        waitElementVisible(driver, firstNameTextbox);
        return getDOMProperty(firstNameTextbox, "value");
    }

    public String getMiddleNameTextBoxValue(){
        waitElementVisible(driver, middleNameTextbox);
        return getDOMProperty(middleNameTextbox, "value");
    }

    public String getLastNameTextBoxValue(){
        waitElementVisible(driver, lastNameTextbox);
        return getDOMProperty(lastNameTextbox, "value");
    }

    public String getEmployeeIDTextBoxValue(){
        waitElementVisible(driver, employeeID);
        return getDOMProperty(employeeID, "value");
    }

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
