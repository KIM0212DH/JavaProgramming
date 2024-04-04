package kr.co.javaex.java_17_1_ex1_robotexam;

public class ToyEx {
    public static void main(String[] args) {
//        AirplaneImpl airplane = new AirplaneImpl();
//        airplane.explain();
//        airplane.lightOn();
//        airplane.launchMissile();
//        System.out.println("====================================");
//
//        MajangerImpl majanger = new MajangerImpl();
//        majanger.explain();
//        majanger.launchMissile();
//        majanger.moveArmLeg();
//        System.out.println("====================================");
//
//        PoohImpl pooh = new PoohImpl();
//        pooh.explain();
//        pooh.moveArmLeg();
//        System.out.println("====================================");

        Playing playing = new Playing();
        playing.play(new AirplaneImpl());
        System.out.println("====================================");
        playing.play(new MajangerImpl());
        System.out.println("====================================");
        playing.play(new PoohImpl());
        System.out.println("====================================");
    }
}
