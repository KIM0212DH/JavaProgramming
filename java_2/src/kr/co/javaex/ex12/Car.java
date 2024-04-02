package kr.co.javaex.ex12;

public class Car {
    public int speed;

    public void speedup() {
        speed += 1;
    }

    public final void stop() {
        System.out.println("차를 멈춤");
        speed = 0;
    }
}
