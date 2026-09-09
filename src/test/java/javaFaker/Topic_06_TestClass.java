package javaFaker;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Topic_06_TestClass {
    public static void main(String[] agr){
        Topic_05_Getter_Setter topic = new Topic_05_Getter_Setter();

        //truy cap/ doc (get)
        System.out.println("Truy cap: " + topic.getFirstName());

        //Sua/ gan du lieu (set)
        topic.setFirstName("Automation");
        System.out.println("Gan du lieu: " + topic.getFirstName());
    }
}
