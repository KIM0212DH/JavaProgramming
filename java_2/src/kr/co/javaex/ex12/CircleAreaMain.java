package kr.co.javaex.ex12;

public class CircleAreaMain {
    public static void main(String[] args) {
        int r = 10;
        CircleAreaParent circleAreaParent = new CircleAreaParent();
        System.out.println("원 면적: " + circleAreaParent.circleArea(r));
        System.out.println();


        CircleAreaChild circleAreaChild = new CircleAreaChild();
        System.out.println("원 면적: " + circleAreaChild.circleArea(r));
    }
}

