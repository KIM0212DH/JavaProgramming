package kr.co.javaex.ex13;

public class Child extends Parent {
    String field2;

    @Override
    void method2() {
        System.out.println("Child 클래스의 method2()입니다.");
    }

    void method3() {
        System.out.println("Child 클래스의 method3() 입니다.");
    }
}
