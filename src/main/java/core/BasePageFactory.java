package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {
    private final long SHORT_TIMEOUT = 10;
    private final long LONG_TIMEOUT = 30;

    public static BasePageFactory getInstance (){
        return new BasePageFactory();
    }

    public void openPageUrl (WebDriver driver, String pageUrl) {
        System.out.println("driver trong BasePage: " + driver.toString());
        driver.get(pageUrl);
    }

    public String getPageTitle (WebDriver driver){
        return driver.getTitle();
    }

    public String getCurrentUrl (WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource (WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage (WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage (WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshPage (WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence (WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert (WebDriver driver){
        waitAlertPresence(driver).accept();
    }

    public void cancelAlert (WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }

    public String getAlertText (WebDriver driver){
        return waitAlertPresence(driver).getText();
    }

    public void sendKeyToAlert (WebDriver driver, String text){
        waitAlertPresence(driver).sendKeys(text);
    }

    public void sleepInSecond(int timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title){
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows){
            driver.switchTo().window(runWindow);
            String currentWin = driver.getTitle();
            if(currentWin.equals(title)){
                break;
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByID(WebDriver driver, String parentID){
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows){
            if(runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByContainTitle (WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows){
            driver.switchTo().window(window);
            if(driver.getTitle().contains(expectedTitle)){
                break;
            }
        }
        sleepInSecond(2);
    }

    public boolean closeAllWindowsWithoutParent (WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size()==1)
            return true;
        else
            return false;
    }

    //Thao tac voi Element
    //PageFactory define kieu WebElement
    public void clickToElement (WebElement element){
        element.click();
    }

    public void sendKey (WebElement element, String keyToSend){
        element.clear();
        element.sendKeys(keyToSend);
    }

    public String getElementText (WebDriver driver, WebElement element){
        return element.getText();
    }

    public String getAttributeValue (WebDriver driver, WebElement element, String attributeName){
        return element.getAttribute(attributeName);
    }

    public String getDOMAttribute (WebElement element, String attributeName){
        return element.getDomAttribute(attributeName);
    }

    public String getDOMProperty (WebElement element, String propertyName){
        return element.getDomProperty(propertyName);
    }

    public boolean isElementDisplayed (WebDriver driver, WebElement element){
        return element.isDisplayed();
    }

    public boolean isElementSelected (WebDriver driver, WebElement element){
        return element.isSelected();
    }

    public WebElement waitElementVisible (WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitListElementVisible (WebDriver driver, List<WebElement> elements){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean waitElementSelected (WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(element));
    }

    public WebElement waitElementClickable (WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitElementInvisible (WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInvisible (WebDriver driver, List<WebElement> element){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(element));
    }

}
