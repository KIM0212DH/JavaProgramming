package kr.co.javaex.ex18;

public class MethodChain {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("abcde").insert(1,"12345").delete(5,7);
        System.out.println(sb);
    }
}
