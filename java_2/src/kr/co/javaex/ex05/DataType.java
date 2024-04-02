package kr.co.javaex.ex05;

public class DataType {
    public DataType() {
    }

    public void a() {
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{1, 2, 3};
        System.out.println(arr1 == arr2);
        System.out.println(arr2 == arr2);
        System.out.println("-------------------------------------------------");
        String refVal1 = "자바";
        String refVal2 = null;
        refVal2 = "자바";
        System.out.println(System.identityHashCode(refVal1));
        System.out.println(System.identityHashCode(refVal2));
        System.out.println("-------------------------------------------------");
        int[] intArray1 = new int[]{1, 2, 3};
        int[] intArray2 = new int[]{1, 2, 3};
        System.out.println(System.identityHashCode(intArray1));
        System.out.println(System.identityHashCode(intArray2));
        System.out.println(System.identityHashCode(intArray2));
        System.out.println("-------------------------------------------------");
        int[] intArray = null;
        String str = null;
        String hobby = "여행";
        hobby = null;
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = new String("홍길동");
        System.out.println(name1 == name2);
        System.out.println(name1 == name3);
        System.out.println(System.identityHashCode(name1));
        System.out.println(System.identityHashCode(name2));
        System.out.println(System.identityHashCode(name3));
        System.out.println("-------------------------------------------------");
        String[] strArr = new String[]{"abc", "def", "ghi", "jkl"};
        printItem(strArr);
        printItem(new String[]{"mno", "pqr", "stu", "vwx", "yz"});
        System.out.println("-------------------------------------------------");
        int[] scoreArr = new int[]{83, 90, 87, 84, 95};
        int sum = 0;
        int idx = 0;

        double len;
        for(len = (double)scoreArr.length; (double)idx < len; ++idx) {
            sum += scoreArr[idx];
        }

        System.out.println("sum: " + sum);
        double avg = (double)sum / len;
        System.out.println("avg: " + avg);
        System.out.println("-------------------------------------------------");
        int[] scores = new int[]{83, 90, 87, 85, 95};
        scores[0] = 100;
        System.out.println(scores[0]);
        System.out.println("-------------------------------------------------");
        int[][] scores2D = new int[2][3];
        scores2D[0][0] = 10;
        scores2D[0][1] = 20;
        scores2D[0][2] = 30;
        scores2D[1][0] = 40;
        scores2D[1][1] = 50;
        scores2D[1][2] = 60;
        System.out.println(scores2D[1][0]);
        System.out.println();
        int[][] scores2D2 = scores2D;
        int i = scores2D.length;

        int j;
        for(j = 0; j < i; ++j) {
            int[] scores1D = scores2D2[j];
            int[] var30 = scores1D;
            int var31 = scores1D.length;

            for(int var32 = 0; var32 < var31; ++var32) {
                int score = var30[var32];
                System.out.print("" + score + " ");
            }

            System.out.println();
        }

        System.out.println("-------------------------------------------------");
        scores2D2 = new int[][]{{10, 20, 30}, {40, 50, 60}};
        System.out.println("행 길이: " + scores2D2.length);
        System.out.println("열 길이: " + scores2D2[0].length);

        for(i = 0; i < scores2D2.length; ++i) {
            for(j = 0; j < scores2D2[i].length; ++j) {
                System.out.println("scores2d2[" + i + "][" + j + "]: " + scores2D2[i][j]);
            }
        }

        System.out.println("-------------------------------------------------");
    }

    public static void printItem(String[] sArr) {
        System.out.println("length:" + sArr.length);
    }
}
