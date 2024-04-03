package kr.co.javaex.ex16.java_16_2_ex4_interface;

public class InterfaceInheritanceEx {
    public static void main(String[] args) {
        InterfaceCImpl ici = new InterfaceCImpl();
        ici.methodA();
        System.out.println();

        ici.methodB();
        System.out.println();
        ici.methodA();
        ici.methodB();
        ici.methodC();
    }
}
