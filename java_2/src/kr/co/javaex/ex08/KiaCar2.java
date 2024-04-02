package kr.co.javaex.ex08;

public class KiaCar2 {
    String company;
    String model;
    String color;
    int maxSpeed;
    int speed;

    KiaCar2() {
        this("쏘렌토");
    }

    KiaCar2(String model) {
        this.company = "기아자동차";
        this.model = model;
        this.speed = 50;
    }
}
