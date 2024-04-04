package kr.co.javaex.ex19;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionEx {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("data.txt");
            fw.write("나의 이름은 홍길동");
        } catch (IOException e) {
            System.err.println("오류가 발생했습니다. " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
