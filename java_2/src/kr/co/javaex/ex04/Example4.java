package kr.co.javaex.ex04;

public class Example4 {
    public Example4() {
    }

    public static void main(String[] args) {
        int dice1;
        int dice2;
        do {
            dice1 = (int)(Math.random() * 6.0) + 1;
            dice2 = (int)(Math.random() * 6.0) + 1;
            System.out.println("주사위1의 수 : " + dice1 + " 주사위2의 수 :" + dice2);
        } while(dice1 + dice2 != 5);

    }
}
