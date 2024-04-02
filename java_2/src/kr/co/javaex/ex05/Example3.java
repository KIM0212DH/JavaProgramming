package kr.co.javaex.ex05;

public class Example3 {
    public Example3() {
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 5, 3, 8, 2};
        int maxNum = 0;
        int[] var3 = array;
        int var4 = array.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int arr = var3[var5];
            if (arr > maxNum) {
                maxNum = arr;
            }
        }

        System.out.println(maxNum);
    }
}
