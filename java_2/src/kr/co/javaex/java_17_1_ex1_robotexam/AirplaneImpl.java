package kr.co.javaex.java_17_1_ex1_robotexam;

public class AirplaneImpl implements Missile, Light{
    @Override
    public void explain() {
        System.out.println("비행기 입니다.");
    }

    @Override
    public void launchMissile() {
        System.out.println("미사일 발사 가능합니다.");
    }

    @Override
    public void lightOn() {
        System.out.println("불빛 발사 가능합니다.");
    }

    @Override
    public void play() {
        explain();
        lightOn();
        launchMissile();
    }
}
