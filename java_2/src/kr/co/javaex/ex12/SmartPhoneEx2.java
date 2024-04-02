package kr.co.javaex.ex12;

public class SmartPhoneEx2 {
    public static void main(String[] args) {
        SmartPhone2 myPhone = new SmartPhone2("갤럭시 S23", "검정색");
        System.out.println("모델 : " + myPhone.model);
        System.out.println("색상 : " + myPhone.color);
    }
}
