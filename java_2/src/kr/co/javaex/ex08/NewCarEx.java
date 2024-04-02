package kr.co.javaex.ex08;

public class NewCarEx {
    public NewCarEx() {
    }

    public static void main(String[] args) {
        NewCar nCar = new NewCar();
        nCar.setName("내 차");
        System.out.println(nCar.getName());
        System.out.println("차가 출발했는지 확인 :" + nCar.isStart());
        System.out.println("차의 속도 확인 : " + nCar.getSpeed());
        System.out.println("-------------------------------------------------");
        System.out.println("속도 30 부여");
        nCar.setSpeed(30);
        System.out.println("변경된 차의 속도 확인 : " + nCar.getSpeed());
        System.out.println("-------------------------------------------------");
        System.out.println("속도 100 부여");
        nCar.setSpeed(100);
        System.out.println("변경된 차의 속도 확인 : " + nCar.getSpeed());
        System.out.println("-------------------------------------------------");
        System.out.println("차가 멈췄는지 확인 : " + nCar.isStop());
        System.out.println("setStop(true) 부여");
        System.out.println(nCar.isStart());
        nCar.setStop(true);
        System.out.println("차가 멈췄는지 확인 : " + nCar.isStop());
    }
}
