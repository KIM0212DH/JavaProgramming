package kr.co.javaex.ex13.package2;

import kr.co.javaex.ex13.package1.A;

class D extends A {
    public static void main(String[] args) {
        A aClass = new A();
//        aClass.aField = 10; //  다른 패키지에서 부모 클래스의 protect 필드는 직접 사용 불가.

        D dClass = new D();
        dClass.aField = 10; //  상속 받아서 사용하면 사용 가능.
    }
}
