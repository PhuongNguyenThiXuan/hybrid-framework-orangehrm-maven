package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_10 extends Topic_09 {
    //Pham vi: toan cuc
    String address;

    public Topic_10 (String name, String address){ //Pham vi: cuc bo
        super(name); //bat buoc phai khai bao dau tien
        this.address = address; //dung this => lay bien pham vi toan cuc
        address = address; //khong dung this => lay bien pham vi cuc bo
    }

}
