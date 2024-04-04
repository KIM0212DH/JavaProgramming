package kr.co.javaex.ex19;

public class ExceptionThrowsEx {
    public static void main(String[] args) {
        try {
            findclass();
        } catch (ClassNotFoundException e) {
            System.out.println("예외 처리: " + e.getMessage());
        }
    }

    public static void findclass() throws ClassNotFoundException {
        Class.forName("java.lang.String2");
    }
}
