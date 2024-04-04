package kr.co.javaex.ex19.java_25_7_ex4_alert;

import java.io.FileReader;
import java.io.IOException;

public class Alert {
    public static void main(String[] args) {
        System.out.println("프로그램 시작");
        FileReader fr = null;
        try {
            fr = new FileReader("ttt.txt");
        } catch (IOException e) {
            System.out.println("IOException 예외가 발생하여 종료합니다.");
            System.exit(0);
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                System.out.println("IOException 예외가 발생하여 종료합니다.");
                System.exit(0);
            }
        }
    }
}
