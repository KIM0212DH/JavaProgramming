package kr.co.javaex.ex16.java_16_2_ex4_interface;

public class InterfaceCImpl implements C {
    @Override
    public void methodA() {
        System.out.println("InterfaceCImpl-methodA() 실행");
    }

    @Override
    public void methodB() {
        System.out.println("InterfaceCImpl-methodB() 실행");
    }

    @Override
    public void methodC() {
        System.out.println("InterfaceCImpl-methodC() 실행");
    }
}
