package kr.co.javaex.ex16.CarTireInterface;

public class HankookTire implements Tire {
    @Override
    public void roll() {
        System.out.println("한국타이어가 회전합니다.");
    }
}
