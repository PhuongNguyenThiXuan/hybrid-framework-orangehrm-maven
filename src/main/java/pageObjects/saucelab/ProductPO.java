package pageObjects.saucelab;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPO extends BasePage {
    private WebDriver driver;

    public ProductPO(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy (String sortCriteria){
        waitElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN, sortCriteria);
    }

    public String getSortItemSelected (){
        waitElementVisible(driver, ProductPageUI.SORT_DROPDOWN);
        return getSelectItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN);
    }


    public boolean isProductNameSortAscending() {
        List<WebElement> productName = getListWebElements(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        ArrayList<String> productList = new ArrayList<String>();
        for (WebElement product : productName){
            System.out.println(product);
            productList.add(product.getText());
        }

        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product :productList){
            System.out.println(product);
            sortedList.add(product);
        }

        Collections.sort(sortedList);
        //Collections.sort(sortedList);

        return productList.equals(sortedList);
    }

    public boolean isProductNameSortDescending() {
        List<WebElement> productName = getListWebElements(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        ArrayList<String> productList = new ArrayList<String>();
        for (WebElement product : productName){
            System.out.println(product);
            productList.add(product.getText());
        }

        ArrayList<String> sortedList = new ArrayList<String>();
        for (String product :productList){
            System.out.println(product);
            sortedList.add(product);
        }

        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortByAscending() {
        List<WebElement> productPrice = getListWebElements(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        ArrayList<Float> productList = new ArrayList<Float>();
        for (WebElement product : productPrice){
            productList.add(Float.parseFloat(product.getText().replace("$", "")));
            System.out.println(product);
        }

        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product :productList){
            System.out.println(product);
            sortedList.add(product);
        }

        Collections.sort(sortedList);

        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortByDescending() {
        List<WebElement> productPrice = getListWebElements(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        ArrayList<Float> productList = new ArrayList<Float>();
        for (WebElement product : productPrice){
            productList.add(Float.parseFloat(product.getText().replace("$", "")));
            System.out.println(product);
        }

        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float product :productList){
            System.out.println(product);
            sortedList.add(product);
        }

        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        return productList.equals(sortedList);
    }
}
