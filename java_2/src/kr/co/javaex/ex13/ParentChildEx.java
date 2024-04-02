package kr.co.javaex.ex13;

public class ParentChildEx {
    public static void main(String[] args) {
        Parent parent = new Child();
        parent.field1 = "홍길동";
        parent.method1();
        parent.method2();   //  부모 클래스의 메서드에 접근이 가능하지만, 자식 클래스에 오버라이드 되어 있으면 자식 클래스의 메서드에 접근한다.
//        parent.field2 = "홍순이";  // 불가 : 자식 인스턴스를 생성했지만 자동 타입 변환으로 Parent의 필드와 메서드만 사용 가능하다.
//        parent.method3();   //  불가 : 자식 인스턴스를 생성했지만 자동 타입 변환으로 Parent의 필드와 메서드만 사용 가능하다.

        Child child = (Child) parent;
        child.field2 = "홍돌이";
        child.method3();
    }
}
