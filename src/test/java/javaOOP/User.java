package javaOOP;

public class User {

    public static void main (String[] args){
        //Khoi tao tu public class
        Car car = new Car();
        car.setFullName();
        car.fullName = "javaFactory.Honda civic";

        Honda honda = new Honda();
        honda.brandName();
        honda.setFullName(); //ke thua car
        honda.fullName = "javaFactory.Honda Vision"; //ke thua car

        Dog dog = new Dog();
        dog.setColor();
        dog.setAge();

        //Khoi tao tu abstract class => khong cho khoi tao
        //Animal animal = new Animal();

        //Khoi tao tu final class
        Computer computer = new Computer();
        computer.setRam();

        //Person
        Person firstPerson = new Person("","","");
        Person secondPerson = new Person("","","");
    }
}
