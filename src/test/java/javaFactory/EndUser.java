package javaFactory;

public class EndUser {
    public static void main (String[] args){
        Car car = null;
        //morning
        car = getCar("Honda");
        car.viewCar();
        car.driveCar();

        //afternoon
        car = getCar("Huyndai");
        car.viewCar();
        car.driveCar();

        //evening
        car = getCar("Toyota");
        car.viewCar();
        car.driveCar();

    }

    public static Car getCar(String carName){
        Car car = null;
        switch (carName){
            case "Honda":
                car = new Honda();
                break;
            case "Huyndai":
                car = new Huyndai();
                break;
            case "Toyota":
                car = new Toyota();
                break;
            default:
                throw new RuntimeException("Car name is invalid");
        }
        return car;
    }
}
