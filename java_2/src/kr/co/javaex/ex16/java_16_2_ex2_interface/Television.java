package kr.co.javaex.ex16.java_16_2_ex2_interface;

public class Television implements RemoteControl {
    @Override
    public void turnOn() {
        System.out.println("TV를 켭니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("TV를 끕니다.");
    }

    @Override
    public void setVolumn(int volumn) {
        if (volumn > MAX_VOLUMN){
            volumn = 10;
        } else if (volumn < 0) {
            volumn = 0;
        }
        System.out.println("현재 TV 볼륨: " + volumn);
    }


}
