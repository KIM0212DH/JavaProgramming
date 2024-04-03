package kr.co.javaex.ex16.java_16_2_ex2_interface;

interface RemoteControl {
    public static final int MIN_VOLUMN = 0;
    public static final int MAX_VOLUMN = 10;
    public abstract void turnOn();

    public abstract void turnOff();

    public abstract void setVolumn(int volumn);

}
