class Car {
    String model;
    String color;
    int year;

    // Constructor
    Car(String model, String color, int year) {
        this.model = model;
        this.color = color;
        this.year = year;
    }

    void CarInfo() {
        System.out.println("Model: " + model);
        System.out.println("Color: " + color);
        System.out.println("Year: " + year);
    }
}

public class Main {
    public static void main(String[] args) {
        Car ob1 = new Car("Bmw", "black", 2025);
        ob1.CarInfo();
    }
}