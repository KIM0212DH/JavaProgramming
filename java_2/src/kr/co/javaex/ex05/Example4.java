package kr.co.javaex.ex05;

public class Example4 {
    public Example4() {
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{95, 86}, {83, 92, 96}, {78, 83, 93, 87, 88}};
        int sum = 0;
        int cnt = 0;

        for(int i = 0; i < array.length; ++i) {
            for(int j = 0; j < array[i].length; ++j) {
                sum += array[i][j];
                ++cnt;
            }
        }

        System.out.println(sum);
        double avg = (double)sum / (double)cnt;
        System.out.println(avg);
    }
}
