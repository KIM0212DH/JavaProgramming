package kr.co.javaex.ex16.CarTireInterface;

public class NexenTire implements Tire {
    @Override
    public void roll() {
        System.out.println("넥센타이어가 회전합니다.");
    }
}
