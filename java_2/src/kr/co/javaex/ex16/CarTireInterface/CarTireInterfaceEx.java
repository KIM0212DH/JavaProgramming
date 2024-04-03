package kr.co.javaex.ex16.CarTireInterface;

public class CarTireInterfaceEx {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.tire = new HankookTire();
        myCar.run();

        myCar.tire = new NexenTire();
        myCar.run();
    }
}
