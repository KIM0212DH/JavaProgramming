package kr.co.javaex.ex12;

public class SupersonicAirplane extends Airplane {
    static final int NORMAL = 1;
    static final int SUPERSONIC = 2;
    private int flyMode = NORMAL;    //0이면 일반 비행, 1이면 음속 비행

    public int getFlyMode() {
        return flyMode;
    }

    public void setFlyMode(int flyMode) {
        this.flyMode = flyMode;
    }

    @Override
    public void flight() {
        if (flyMode == NORMAL) {
            super.flight();
        } else {
            System.out.println("초음속 비행합니다.");
        }
    }
}
