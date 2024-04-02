package kr.co.javaex.ex08;

public class KiaCar {
    String company = "기아자동차";
    String model;
    String color;
    int maxSpeed;
    int speed;

    KiaCar(String model, String color, int maxSpeed) {
        this.model = model;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    KiaCar() {
        this.speed = 50;
    }

    KiaCar(String model) {
        this.model = model;
        this.speed = 50;
    }
}
