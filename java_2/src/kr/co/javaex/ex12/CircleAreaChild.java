package kr.co.javaex.ex12;

public class CircleAreaChild extends CircleAreaParent {
    @Override
    public double circleArea(double r) {
        System.out.println("CircleAreaChild 객체의 circleArea() 실행");
        return Math.PI * r * r;
    }
}
