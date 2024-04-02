package kr.co.javaex.ex12;

public class Child extends Parent {

    public Child() {
        super();
        System.out.println("매개 변수 없는 생성자 사용");
    }

    public Child(String a, int i, int j) {
        super(a, i);
        System.out.println("매개 변수 있는 생성자 사용");
        System.out.println(super.field1);
        System.out.println("j : " + j);
    }

    int c = 9999999;

}
