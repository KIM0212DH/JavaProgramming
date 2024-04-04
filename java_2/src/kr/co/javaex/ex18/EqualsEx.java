package kr.co.javaex.ex18;

public class EqualsEx {
    public static void main(String[] args) {
        Str str1 = new Str("가나다");
        Str str2 = new Str("라마바");
        Str str3 = new Str("가나다");

        if (str1 == str3) {
            System.out.println("str1과 str3는 동등");
        } else {
            System.out.println("str1과 str3는 동등하지 않음");
        }

        if (str1.equals(str3)) {
            System.out.println("str1과 str3의 내용은 일치");
        } else {
            System.out.println("str1과 str3의 내용은 일치하지 않음");
        }

        if (str1.equals(str2)) {
            System.out.println("str1과 str2는 동등");
        } else {
            System.out.println("str1과 str2는 동등하지 않음");
        }

    }
}
