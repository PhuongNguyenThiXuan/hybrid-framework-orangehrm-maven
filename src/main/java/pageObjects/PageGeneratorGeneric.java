package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class PageGeneratorGeneric {
    public static <T extends BasePage> T getPage (Class<T> pageClass, WebDriver driver){
        try {
            //Lay constructor nhan WebDriver
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            //Tao instance moi cua page class
            return constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Cannot init Page Object class" + pageClass.getSimpleName(), e);
        }
    }
}
