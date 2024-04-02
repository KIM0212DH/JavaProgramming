package kr.co.javaex.ex04;

public class Example2 {
    public Example2() {
    }

    public static void main(String[] args) {
        int jjak = 0;
        int hol = 0;

        for(int i = 0; i <= 100; ++i) {
            if (i % 2 == 0) {
                jjak += i;
            } else {
                hol += i;
            }
        }

        System.out.println("짝수 들의 합 : " + jjak);
        System.out.println("홀수 들의 합 : " + hol);
    }
}
