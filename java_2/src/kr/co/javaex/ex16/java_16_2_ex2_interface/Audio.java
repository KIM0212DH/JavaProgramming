package kr.co.javaex.ex16.java_16_2_ex2_interface;

public class Audio implements RemoteControl {
    @Override
    public void turnOn() {
        System.out.println("Audio를 켭니다.");
    }

    @Override
    public void turnOff() {
        System.out.println("Audio를 끕니다.");
    }

    @Override
    public void setVolumn(int volumn) {
        if (volumn > MAX_VOLUMN){
            volumn = 10;
        } else if (volumn < MIN_VOLUMN) {
            volumn = 0;
        }
        System.out.println("현재 Audio 볼륨: " + volumn);
    }


}
