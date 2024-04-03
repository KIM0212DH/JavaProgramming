package kr.co.javaex.ex15.java_15_1_ex1_starcaroptionexam;

public abstract class StarCar {
    String color;
    String tire;
    int displacement;
    String handle;
    int tax;

    StarCar(String color, String tire, int displacement, String handle) {
        this.color = color;
        this.tire = tire;
        this.displacement = displacement;
        this.handle = handle;
    }

    abstract void getSpec();

}
