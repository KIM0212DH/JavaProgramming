package kr.co.javaex.ex12;

public class Parent {
    Parent() {

    }

    Parent(String a, int i) {
        field1 = i;
    }

    int field1 = 0;
    int field2 = 999;

    void method1(int a, int b) {
        System.out.println(a + " " + b + " 두개의 인자를 받음");
    }
}
