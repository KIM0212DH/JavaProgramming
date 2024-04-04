package kr.co.javaex.ex18;

public class StringEx {
    public static void main(String[] args) {
        String str1 = "Hello";

        System.out.println(str1.substring(2,3));

        System.out.println(str1.replaceAll("e","l"));
        System.out.println(str1.indexOf("lo"));
    }
}
