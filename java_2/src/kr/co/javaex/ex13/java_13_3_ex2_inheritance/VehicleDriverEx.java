package kr.co.javaex.ex13.java_13_3_ex2_inheritance;

public class VehicleDriverEx {
    public static void main(String[] args) {
        Driver driver = new Driver();

        Bus bus = new Bus();
        driver.driver(bus);

        Taxi taxi = new Taxi();
        driver.driver(taxi);
    }
}
