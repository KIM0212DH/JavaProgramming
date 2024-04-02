package kr.co.javaex.ex12;

public class AirplaneEx {
    public static void main(String[] args) {
        SupersonicAirplane supersonicAirplane = new SupersonicAirplane();
        supersonicAirplane.takeOff();
        supersonicAirplane.flight();
        supersonicAirplane.setFlyMode(SupersonicAirplane.SUPERSONIC);
        supersonicAirplane.flight();
        supersonicAirplane.setFlyMode(SupersonicAirplane.NORMAL);
        supersonicAirplane.flight();
        supersonicAirplane.land();
    }
}
