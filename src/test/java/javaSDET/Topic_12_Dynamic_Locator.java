package javaSDET;

import org.testng.annotations.Test;

public class Topic_12_Dynamic_Locator {
    public static void main (String[] agr){
        System.out.println(String.format("//a[text()='%s']","Personal Details"));
        System.out.println(String.format("//a[text()='%s']","Dependents"));
        System.out.println(String.format("//a[text()='%s']","Jobs"));
    }

    @Test
    public void testDynamicLocator(){
        String personalDetailsPage = "//a[text()='Personal Details']";
        String dependentsPage = "//a[text()='Dependents']";
        String jobPage = "//a[text()='Jobs']";

        String dynamicPageOneParam = "//a[text()='%s']";
        String dynamicPageTwoParams = "//div[@class='orangehrm-%s-employee-navigation']//a[text()='%s']";
        String dynamicPageMultiParams = "//td[@data-key='females' and text()='%s']" +
                "//following::td[@data-key='country' and text()='%s']" +
                "//following::td[@data-key='males' and text()='%s']" +
                "//following::td[@data-key='total' and text()='%s']";

        //1. Mở trang cụ thể
        //Tái sử dụng hàm
        //Chưa tái sử dụng locator
        openPageByName(personalDetailsPage);
        openPageByName(dependentsPage);
        openPageByName(jobPage);

        //2. Mở trang theo tên trang truyền vào (re-usable)
        //Tham số động là tên page
        //Hàm + Locator
        openPageByName(dynamicPageOneParam, "Personal Details");
        openPageByName(dynamicPageOneParam, "Dependents");
        openPageByName(dynamicPageOneParam, "Jobs");

        //3. Locator có 2 tham số động truyền vào
        openPageByName(dynamicPageTwoParams,"edit","Jobs");
        openPageByName(dynamicPageTwoParams,"view","Jobs");

        //4. Locator có nhieu tham số động truyền vào
        openPageByName(dynamicPageMultiParams, "384187", "Afghanistan", "407124", "791312");
        openPageByName(dynamicPageMultiParams, "338282", "Argentina", "349238", "687522");
    }

//    public void openPageByName(String locator){
//        System.out.println("Click to page: " + locator);
//    }
//
//    public void openPageByName (String pageLocator, String pageName) {
//        System.out.println("Click to page: " + String.format(pageLocator, pageName));
//    }
//
//    public void openPageByName (String pageLocator, String pageFunction, String pageName) {
//        System.out.println("Click to page: " + String.format(pageLocator, pageFunction, pageName));
//    }
//
//    public void openPageByName (String pageLocator, String firstParam, String secondParam, String thirdParam, String fourParam) {
//        System.out.println("Click to page: " + String.format(pageLocator, firstParam, secondParam, thirdParam, fourParam));
//    }

    public void openPageByName (String pageLocator, String... restParams) {
        System.out.println("Click to page: " + String.format(pageLocator, (Object[]) restParams));
    }
}
