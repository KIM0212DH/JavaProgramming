package kr.co.javaex.ex04;

import java.util.Scanner;

public class Example6 {
    public Example6() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("[메뉴] 1: 검색, 2: 등록, 3: 삭제, 4: 변경, 5: 종료 >");
            int menuNo = scanner.nextInt();
            switch (menuNo) {
                case 1:
                    System.out.println("검색합니다.");
                    break;
                case 2:
                    System.out.println("등록합니다.");
                    break;
                case 3:
                    System.out.println("삭제합니다.");
                    break;
                case 4:
                    System.out.println("변경합니다.");
                    break;
                case 5:
                    System.out.println("종료합니다.");
                    return;
            }
        }
    }
}
