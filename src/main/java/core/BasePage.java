package core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

//Parent class cho các class thuộc main.java.pageObjects
//La abstract class => cho phep cac class con dieu chinh de su dung lai
public class BasePage {

    //      1 - Access Modifier:
    //          public: tất cả các class trong cùng/không cùng package đều sử dụng được. Nếu muốn class nào kế thừa mới được dùng => thì ko khai báo public
    //          protected: chỉ class nào kế thừa mới dùng được
    //          private: chỉ cho các hàm trong cùng class này sử dụng
    //          default: Chỉ cho các class trong cùng package sử dụng
    //      2 - Kiểu dữ liệu của hàm (Data type): void/ int/ String/ boolean/ WebElement/ List<WebElement>/..
    //          Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
    //      3 - Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
    //          Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
    //          lowerCamelCase: từ đầu tiên viết thường - chữ cái đầu tiên của các từ tiếp theo sẽ viết hoa
    //      4 - Có tham số hay ko (tùy vào chức năng cần viết)
    //      5 - Kiểu dữ liệu trả về cho hàm
    //          Nếu như có return dữ liệu thì sẽ khớp vs kiểu dữ liệu ở số 2
    //          Nếu như có return thì nó là cái step cuối cùng

    public void openPageUrl (WebDriver driver, String pageUrl) {
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
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
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

}
