package kr.co.javaex.ex04;

public class Example5 {
    public Example5() {
    }

    public static void main(String[] args) {
        for(int x = 0; x < 11; ++x) {
            for(int y = 0; y < 11; ++y) {
                if (4 * x + 5 * y == 60) {
                    System.out.println("(" + x + ", " + y + ")");
                }
            }
        }

    }
}
