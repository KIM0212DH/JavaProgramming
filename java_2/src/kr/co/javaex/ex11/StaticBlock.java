package kr.co.javaex.ex11;

public class StaticBlock {
    static String company = "Samgsung";
    static String model = "Notebook";
    static String info;

    static {
        info = company + "-" + model;
    }
}
