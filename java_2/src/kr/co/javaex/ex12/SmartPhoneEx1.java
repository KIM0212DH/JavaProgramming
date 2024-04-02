package kr.co.javaex.ex12;

public class SmartPhoneEx1 {
    public static void main(String[] args) {
        SmartPhone1 myPhone = new SmartPhone1("갤럭시 S23", "검정색");
        System.out.println("모델 : " + myPhone.model);
        System.out.println("색상 : " + myPhone.color);
    }
}
