package kr.co.javaex.ex15.java_15_1_ex1_starcaroptionexam;

public class StarCarEx {
    public static void main(String[] args) {
        StarCar starCarLowGrade = new StarCarLowGrade();
        starCarLowGrade.getSpec();
        System.out.println();
        StarCar starCarHighGrade = new StarCarHighGrade();
        starCarHighGrade.getSpec();
    }
}
