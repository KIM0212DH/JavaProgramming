package kr.co.javaex.ex03;

import java.util.Scanner;

public class Example3 {
    public Example3() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        System.out.println("userInput : " + userInput);
        if (userInput % 3 == 0) {
            System.out.println("3의 배수입니다.");
        } else {
            System.out.println("3의 배수가 아닙니다.");
        }

    }
}
