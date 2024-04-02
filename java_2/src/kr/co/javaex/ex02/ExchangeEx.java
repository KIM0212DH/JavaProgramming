package kr.co.javaex.ex02;

import java.util.Scanner;

public class ExchangeEx {
    public ExchangeEx() {
    }

    public static void main(String[] args) {
        int x = 3;
        int y = 5;
        System.out.println("x: " + x + ", y: " + y);
        System.out.println("x: " + y + ", y: " + x);
        double tax = 1.1;
        int fax = 5;
        System.out.println("5만원에서 4만원으로 할인합니다.");
        System.out.println("FAX의 새로운 가격(세금포함) ");
        System.out.println((int)((double)fax * 1.1) + " 만원");
        System.out.println("\n\n");
        int value1 = Integer.parseInt("10");
        double value2 = Double.parseDouble("3.14");
        boolean value3 = Boolean.parseBoolean("true");
        System.out.println("value1: " + value1);
        System.out.println("value2: " + value2);
        System.out.println("value3: " + value3);
        String str1 = String.valueOf(10);
        String str2 = String.valueOf(3.14);
        String str3 = String.valueOf(true);
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str3: " + str3);
        System.out.print("이름은 ");
        System.out.println("홍길동 입니다.");
        System.out.println();
        System.out.println("이름은 홍길동 입니다.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Score 입력: ");
        String inputData = scanner.nextLine();
        System.out.println("하나 더 입력: ");
        int input2 = scanner.nextInt();
        System.out.println("입력 받은 score: " + inputData);
        System.out.println("입력 받은 score: " + input2);
    }

    public static String getType(Object obj) {
        return obj.getClass().getSimpleName();
    }
}
