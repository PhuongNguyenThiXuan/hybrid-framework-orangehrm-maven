package javaOOP;

public abstract class Animal {
    String color;

    //non-abstract
    public void setColor(){
        this.color = "phuong";
    }

    //abstract
    public abstract void setAge(); //bat buoc nhung thang extend ham abtract nay phai tu implement cho chinh no
}
