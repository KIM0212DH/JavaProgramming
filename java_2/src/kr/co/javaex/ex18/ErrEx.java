package kr.co.javaex.ex18;

import java.io.IOException;

public class ErrEx {
    public static void main(String[] args) throws Exception {
        try {
            int iValue = Integer.parseInt("loo");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.err.println(e.getMessage());
        }

        int keyCode = 0;
        while (true) {
            if (keyCode != 13 && keyCode != 10) {
                if (keyCode == 49) {
                    System.out.println("입력 받은 keyCode 값: " + keyCode);
                    break;
                }
            }
            keyCode = System.in.read();
        }
        System.out.println("keyCode 입력 종료");


        for (int i = 0; i < 10; i++) {
            System.out.println("i: " + i);
            if (i == 5) {
                System.out.println("JVM 프로세스 강제 종료");
                System.exit(0);
            }
        }
    }
}
