package kr.co.javaex.java_22_99_ex1_standardclasses;

public class KeyInExam {
    int speed = 0;

    public static void main(String[] args) throws Exception {
        KeyInExam keyInExam = new KeyInExam();

        int keyCode = 0;
        while (true) {
            if (keyCode != 10 && keyCode != 13) {
                if (keyCode == 49) {
                    keyInExam.speed++;
                } else if (keyCode == 50) {
                    keyInExam.speed--;
                } else if (keyCode == 51) {
                    System.out.println("프로그램 종료");
                    break;
                }
                System.out.println("speed: " + keyInExam.speed);
            }
            keyCode = System.in.read();

        }

    }
}
