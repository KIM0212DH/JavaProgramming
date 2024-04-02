package kr.co.javaex.ex12;

public class SportsCar extends Car {
    @Override
    public void speedup() {
        speed += 60;
    }
//    @Override
//    public void stop(){
//        System.out.println("스포츠카를 멈춤");
//        speed = 0;
//    }
//    부모 클래스에서 final로 정의된 메서드는 자식 클래스에서 재정의 불가
}
