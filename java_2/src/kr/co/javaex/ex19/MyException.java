package kr.co.javaex.ex19;

public class MyException extends RuntimeException {
    public MyException() {
        System.out.println("MyException 예외 클래스 내부");
    }

    public MyException(String message) {
        super(message);
    }


}
