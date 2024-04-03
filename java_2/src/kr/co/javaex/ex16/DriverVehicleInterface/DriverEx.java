package kr.co.javaex.ex16.DriverVehicleInterface;

import kr.co.javaex.ex16.java_16_2_ex4_interface.B;

public class DriverEx {
    public static void main(String[] args) {
        Driver driver = new Driver();

        Bus bus = new Bus();
        driver.drive(bus);

        Taxi taxi = new Taxi();
        driver.drive(taxi);
    }
}
