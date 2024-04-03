package kr.co.javaex.ex16.java_16_2_ex3_interface;

public class SmartTVEx {
    public static void main(String[] args) {
        SmartTelevision sTV = new SmartTelevision();
        sTV.turnOn();
        sTV.search("www.naver.com");
        sTV.turnOff();

        System.out.println();

        RemoteControl rc = new SmartTelevision();
        Searchable s = new SmartTelevision();
        rc.turnOn();
        s.search("www.google.co.kr");
        rc.turnOff();

    }
}
