package kr.co.javaex.ex08;

public class CarEx {
    public CarEx() {
    }

    public static void main(String[] args) {
        new Car();
        System.out.println("car1 변수가 Car 객체를 참조합니다.");
        new Car();
        System.out.println("car2 변수가 Car 객체를 참조합니다.");
        System.out.println("---------------------------------------------------");
        Car myCar = new Car();
        System.out.println("제조사 : " + myCar.company);
        System.out.println("모델명 : " + myCar.model);
        System.out.println("색상 : " + myCar.color);
        System.out.println("최고속도 : " + myCar.maxSpeed);
        System.out.println("현재속도 : " + myCar.speed);
        myCar.speed = 50;
        System.out.println("수정된 현재속도 : " + myCar.speed);
        System.out.println("---------------------------------------------------");
        KiaCar myKiaCar = new KiaCar("쏘렌토", "검정", 230);
        System.out.println("제조사 : " + myKiaCar.company);
        System.out.println("모델명 : " + myKiaCar.model);
        System.out.println("색상 : " + myKiaCar.color);
        System.out.println("최고속도 : " + myKiaCar.maxSpeed);
        System.out.println("현재속도 : " + myKiaCar.speed);
        System.out.println("---------------------------------------------------");
        KiaCar2 gCar2 = new KiaCar2("그랜져");
        System.out.println(gCar2.model);
        KiaCar2 sCar2 = new KiaCar2();
        System.out.println(sCar2.model);
    }
}
