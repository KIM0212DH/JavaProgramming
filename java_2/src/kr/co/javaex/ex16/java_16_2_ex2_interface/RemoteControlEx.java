package kr.co.javaex.ex16.java_16_2_ex2_interface;

public class RemoteControlEx {
    public static void main(String[] args) {
        RemoteControl remoteControlTV = new Television();
//        RemoteControl remoteControlTV;
//        Television tv = new Television();
//        remoteControlTV = tv;
        remoteControlTV.turnOn();
        remoteControlTV.setVolumn(5);
        remoteControlTV.turnOff();
        System.out.println();

        RemoteControl remoteControlAudio = new Audio();
        remoteControlAudio.turnOn();
        remoteControlAudio.setVolumn(5);
        remoteControlAudio.turnOff();
    }
}
