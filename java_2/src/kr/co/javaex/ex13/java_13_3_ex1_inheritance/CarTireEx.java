package kr.co.javaex.ex13.java_13_3_ex1_inheritance;

public class CarTireEx {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.tire = new HanKookTire();
        myCar.run();

        myCar.tire = new KumHoTire();
        myCar.run();

        myCar.tire = new NexenTire();
        myCar.run();

    }
}
