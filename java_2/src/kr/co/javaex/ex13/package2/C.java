package kr.co.javaex.ex13.package2;

import kr.co.javaex.ex13.package1.A;

class C {
    public static void main(String[] args) {
        A aClass = new A();
//        aClass.aField = 10; // protect 클래스인 A는 다른 패키지 && 상속 안된 클래스에서 사용 불가
    }
}
