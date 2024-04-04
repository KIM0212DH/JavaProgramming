package kr.co.javaex.ex19.java_25_1_ex1_exception;

public class ExceptionEx1 {
    public static void main(String[] args) {
        String s = null;
        try {
            System.out.println(s.length());
        }
        catch (NullPointerException e) {
            System.err.println("NullPointerExceptoin 예외를 catch 하였습니다.");
            System.err.println("--stack trace 시작--");
            e.printStackTrace();
            System.err.println("--stack trace 끝--");
        }
    }
}
