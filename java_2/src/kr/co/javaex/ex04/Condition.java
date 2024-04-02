package kr.co.javaex.ex04;

public class Condition {
    public Condition() {
    }

    public static void main(String[] args) {
        int score = 91;
        System.out.println("score : " + score);
        if (score >= 90) {
            System.out.println("점수가 90보다 큽니다.");
        }

        if (score < 90) {
            System.out.println("점수가 90보다 작습니다.");
        }

        if (score >= 90) {
            System.out.println("점수가 90보다 큽니다.");
        } else {
            System.out.println("점수가 90봗 작습니다.");
        }

        score = 85;
        System.out.println("score : " + score);
        if (score >= 90) {
            System.out.println("점수가 100-90입니다.");
        } else if (score >= 80) {
            System.out.println("점수가 90-80입니다.");
        } else {
            System.out.println("점수가 80 미만입니다.");
        }


        int num = (int)(Math.random() * 5.0) + 1;
        System.out.println(Math.random());
        System.out.println(Math.random() * 5.0);
        switch (num) {
            case 1:
                System.out.println("1번이 나왔습니다.");
                break;
            case 2:
                System.out.println("2번이 나왔습니다.");
                break;
            case 3:
                System.out.println("3번이 나왔습니다.");
                break;
            case 4:
                System.out.println("4번이 나왔습니다.");
                break;
            default:
                System.out.println("5번이 나왔습니다.");
        }

    }
}
