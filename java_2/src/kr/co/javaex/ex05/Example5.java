package kr.co.javaex.ex05;

public class Example5 {
    public Example5() {
    }

    public static void main(String[] args) {
        int[] moneyList = new int[]{1593200, 57800, 193500};

        for(int i = 0; i < moneyList.length; ++i) {
            System.out.print(moneyList[i] + " ");
        }

        System.out.println();
        int[] var6 = moneyList;
        int var3 = moneyList.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int money = var6[var4];
            System.out.print("" + money + " ");
        }

    }
}
