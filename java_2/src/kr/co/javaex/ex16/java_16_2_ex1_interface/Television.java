package kr.co.javaex.ex16.java_16_2_ex1_interface;

public class Television implements RemoteControl{
    @Override
    public void turnOn() {
        System.out.println("TV를 켭니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("TV를 끕니다.");
    }
}
