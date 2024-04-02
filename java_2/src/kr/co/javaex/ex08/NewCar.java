package kr.co.javaex.ex08;

public class NewCar {
    private String name;
    private int speed;
    private boolean stop;
    private boolean start = false;

    NewCar() {
        System.out.println("차가 출발합니다.");
        this.setStart(true);
        this.setStop(false);
    }

    public boolean isStart() {
        return this.start;
    }

    public void setStart(boolean start) {
        this.start = start;
        this.setSpeed(10);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        if (speed < 0) {
            this.speed = 0;
        } else if (speed > 50) {
            this.speed = 50;
        } else {
            this.speed = speed;
        }

    }

    public boolean isStop() {
        return this.stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
        if (this.isStop()) {
            this.setSpeed(0);
        }

    }
}
