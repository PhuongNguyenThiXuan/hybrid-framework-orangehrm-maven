package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Compare Topic_01 vs Topic_06
//Class Topic_01 ke thua Class Topic_06: extends
//Class Topic_01 ke thua Interface Topic_02: implements
public class Topic_01_Keywords extends Topic_06 implements Topic_02 {
    //Chi co han non-abstract method
    //KHONG co abstract method
    //Khoi tao binh thuong
    //Cho phep ke thua


    //Data type
    char a = 'A';
    byte bNumber = 10;
    short sNumber = 100;
    int iNumber = 1400;
    long lNumber = 231341;
    float fNumber = 14.2312F;
    double dNumber = 23.1323D;
    boolean maritalStatus = true;

    String fullName = null;

    //Access Modifier

    //Variable
    private String studentName = "";
    String studentAddress = "";
    protected int studentAge = 30;
    public double studentPoint = 9.5;

    //Method
    //void: khong co kieu du lieu tra ve
    private void TC_01() {
        WebDriver driver = new FirefoxDriver();

        Topic_01_Keywords topic01 = new Topic_01_Keywords();
        //Topic_06 topic06 = new Topic_06(); // => bao loi: abstract nen khong the khoi tao
    }

    void TC_02(){}

    protected void TC_03(){}

    public void TC_04(){}

    @Override
    public void clearStudent() {
        //do Topic_02 abstract => nen Topic_01 phai implement method => Override
        //tu implement lai o day
    }

    //Class/ Interface/ Enum/ Annotaion/


}

