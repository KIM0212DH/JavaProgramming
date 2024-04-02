package kr.co.javaex.ex08;

public class Car {
    String name;
    String company = "기아자동차";
    String model = "쏘렌토";
    String color = "검정";
    int maxSpeed = 220;
    int speed;

    Car() {
    }

    Car(String company, String model, String color) {
        this.company = company;
        this.model = model;
        this.color = color;
    }

    void run() {
    }
}
