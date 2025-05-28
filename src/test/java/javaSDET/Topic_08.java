package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Topic_08 {
    //Pham vi: non-static
    String address = "";

    //Pham vi: chia se cho toan bo system su dung
    static String name = "Automation FC";

    //Hang so
    static final String AGE = "45";

    @Test
    public void TC_01() throws InterruptedException {
        //Doi tuong tp
        Topic_08 tp = new Topic_08();
        tp.address ="";

        //Thuoc pham vi class
        Topic_08.name ="";

        //Topic_08.AGE="45"; //Khong the gan lai => do da khai bao hang so

        String osName = "Windows 11";
        String separator = null;
        WebDriver driver;

        //Condition statement
        //if - else
        if (osName.contains("Windows")){
            separator = "\\";
        } else {
            separator = "/";
        }

        //switch-case
        String browserName = "Chrome";
        switch (browserName){
            case "Chrome":
                driver = new ChromeDriver();
                break; //Khong co break se chay het tat ca cac case
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Egde":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser is not support");
        }

        //loop statement
        int studentNumber = 10;
        //classic for
        //for
        for (int i=0; i<studentNumber; i++){
            System.out.println(i);
        }

        for (int i=0; i<studentNumber; i++){
            if(i==5){
                System.out.println(i);
            }
        }

        //for - each
        List<String> stdName = new ArrayList<>();
        for (String std : stdName){
            System.out.println(std);
        }

        //while => kiem tra dk truoc roi moi thuc thi
        int x = 0;
        while (x < studentNumber){
            System.out.println(x);
        }

        //do while => thuc thi truoc roi moi kiem tra dieu kien
        int z = 11;
        do {
            System.out.println();
            z++;
        }while (z < studentNumber);


        //Neu element khong tim thay cung khong bi loi
        try {
            //Happy case
            driver.findElement(By.xpath("")).isDisplayed();
        } catch (NoSuchElementException exception){
            //Egde case: Khong tim thay se in ra loi
            System.out.println(exception.getMessage());
        } finally {
            //Close connection: DB/ File => neu co Finally bat buoc se chay qua
        }

        Thread.sleep(300); // add sleep tren ham se them "throws InterruptedException"

    }
}
