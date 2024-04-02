package kr.co.javaex.ex12;

public class ParentChildEx {
    public static void main(String[] args) {
        Child child = new Child("str", 1, 9);
        System.out.println("상속 받은 필드 : " + child.field1 + ", " + child.field2 + "\n자식 클래스의 필드 : " + child.c);

        System.out.println("------------상속 받은 메서드----------------");
        child.method1(1, 2);

        System.out.println("\n------------------------------------");
        Child child1 = new Child();
        child1.method1(3, 4);
    }

}
