package kr.co.javaex.ex08;

public class Calculator {
    static double PI = 3.14159;

    static int plus(int x, int y) {
        return x + y;
    }

    static int minus(int x, int y) {
        return x - y;
    }

    Calculator() {
        this.powerOn();
    }

    void powerOn() {
        System.out.println("전원을 켭니다");
    }

    void powerOff(String name) {
        System.out.println(name + "가 전원을 끕니다.");
    }

    double plus(double x, double y) {
        return x + y;
    }

    double minus(double x, double y) {
        return x - y;
    }

    double product(double x, double y) {
        return x * y;
    }

    double divide(double x, double y) {
        if (y == 0.0) {
            System.out.println("0으로 나눌 수 없습니다.");
            return -1.0;
        } else {
            return x / y;
        }
    }
}
