package javaSDET;

public class Topic_11_String {
    public static void main (String[] args){
        String a = "Hello";
        String b = a;
        String c = b;


        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        String locator = "id = username";
        String locator2 = "xpath=//span[text()='PIM']/parent::a";

        locator = locator.substring(3);
        System.out.println(locator);

        String[] locatorArr = locator.split("=");
        System.out.println(locatorArr[0]);
        System.out.println(locatorArr[1]);
        System.out.println(locatorArr[1].trim());


        String[] locatorArr2 = locator2.split("=",2);
        System.out.println(locatorArr2[0]);
        System.out.println(locatorArr2[1]);

        //null
        locator = null;

        //empty
        locator = "";
    }
}
