package kr.co.javaex.ex16.java_16_2_ex1_interface;

public class RemoteControlEx {
    public static void main(String[] args) {
        RemoteControl remoteControlTV = new Television();
//        RemoteControl remoteControlTV;
//        Television tv = new Television();
//        remoteControlTV = tv;
        remoteControlTV.turnOn();
        remoteControlTV.turnOff();

        RemoteControl remoteControlAudio = new Audio();
        remoteControlAudio.turnOn();
        remoteControlAudio.turnOff();
    }
}
