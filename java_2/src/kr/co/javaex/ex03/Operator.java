package kr.co.javaex.ex03;

public class Operator {
    public Operator() {
    }

    public static void main(String[] args) {
        int a = 10;
        int var10001 = a++;
        System.out.println("b = " + var10001);
        int b = 10;
        b = b + 1;
        System.out.println("b = " + b);
        int x = 10;
        int result1 = ++x + 10;
        int y = 10;
        int result2 = y++ + 10;
        System.out.println("result1: " + result1);
        System.out.println("result2: " + result2);
        System.out.println("y = " + y);
        int s = 1;
        int t = 2;
        double result = (double)(s / t);
        System.out.println("result: " + result);

        double d = 3;
        String str = "ㅂㅔ스트 " + d;
        System.out.println(str);
        System.out.println(5+2L);

        short sh = (byte)2;
        System.out.println(sh);
    }
}
