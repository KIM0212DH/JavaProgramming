package kr.co.javaex.ex19;

public class ExceptionExam {
    public static void main(String[] args) {
        String[] strArr = {"100", "200"};
//        for (int i = 0; i <= strArr.length; i++) {
//            int iValue = Integer.parseInt(strArr[i]);
//        }

        try {
            for (int i = 0; i <= strArr.length; i++) {
                int iValue = Integer.parseInt(strArr[i]);
                System.out.println("array[" + i + "]: " + iValue);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 초과: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("숫자로 변환할 수 없음: " + e.getMessage());
        }

        String[] strArr2 = {"a", "b"};
        try {
            for (int i = 0; i < strArr2.length; i++) {
                int iValue2 = Integer.parseInt(strArr2[i]);
                System.out.println("array[" + i + "]: " + iValue2);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 초과: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("숫자로 변환할 수 없음: " + e.getMessage());
        }
    }
}
