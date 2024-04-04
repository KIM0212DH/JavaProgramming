package kr.co.javaex.java_17_1_ex1_robotexam;

public class MajangerImpl implements Missile, ArmLegr {
    @Override
    public void explain() {
        System.out.println("마징가 입니다.");
    }

    @Override
    public void launchMissile() {
        System.out.println("미사일 발사 가능합니다.");
    }

    @Override
    public void moveArmLeg() {
        System.out.println("팔다리를 움직일 수 있습니다.");
    }

    @Override
    public void play() {
        explain();
        launchMissile();
        moveArmLeg();
    }
}

