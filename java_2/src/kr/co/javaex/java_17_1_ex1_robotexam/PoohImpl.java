package kr.co.javaex.java_17_1_ex1_robotexam;

public class PoohImpl implements ArmLegr {
    @Override
    public void explain() {
        System.out.println("곰돌이 입니다.");
    }

    @Override
    public void moveArmLeg() {
        System.out.println("팔다리를 움직일 수 있습니다.");
    }

    @Override
    public void play() {
        explain();
        moveArmLeg();
    }
}
