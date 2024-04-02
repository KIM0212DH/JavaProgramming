package kr.co.javaex.ex03;

import java.util.Random;
import java.util.Scanner;

public class Example4 {
    public Example4() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("점을 보세요");
        System.out.println("이름을 입력해 주세요");
        String name = scanner.nextLine();
        System.out.println("나이를 입력 해 주세요");
        String ageString = scanner.nextLine();
        int age = Integer.parseInt(ageString);
        int fortune = (new Random()).nextInt(0, 4);
        ++fortune;
        System.out.println("점꾀가 나왔습니다!");
        System.out.println("" + age + "살의 " + name + "씨 , 당신의 운세번호는 " + fortune + "입니다");
        if (fortune == 1) {
            System.out.println("" + fortune + ":대박");
        } else if (fortune == 2) {
            System.out.println("" + fortune + ":중박");
        } else if (fortune == 3) {
            System.out.println("" + fortune + ":보통");
        } else {
            System.out.println("" + fortune + ":망");
        }

        System.out.println("1:대박 2:중박 3:보통 4:망");
    }
}
