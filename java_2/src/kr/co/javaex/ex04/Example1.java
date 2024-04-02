package kr.co.javaex.ex04;

import java.util.Scanner;

public class Example1 {
    public Example1() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int korScore = scanner.nextInt();
        int engScore = scanner.nextInt();
        int mathScore = scanner.nextInt();
        int average = (korScore + engScore + mathScore) / 3;
        System.out.println("평균: " + average);
        if (korScore < average) {
            System.out.println("국어 점수는 평균 미만입니다.");
        } else {
            System.out.println("국어 점수는 평균 이상입니다.");
        }

        if (engScore < average) {
            System.out.println("영어 점수는 평균 미만입니다.");
        } else {
            System.out.println("영어 점수는 평균 이상입니다.");
        }

        if (mathScore < average) {
            System.out.println("수학 점수는 평균 미만입니다.");
        } else {
            System.out.println("수학 점수는 평균 이상입니다.");
        }

    }
}
