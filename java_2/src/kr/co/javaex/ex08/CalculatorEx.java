package kr.co.javaex.ex08;

public class CalculatorEx {
    public CalculatorEx() {
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        cal.powerOff("나");
        double result = (double)Calculator.plus(1, 2);
        result = cal.divide(2.0, 0.0);
        System.out.println(result);
        System.out.println("-------------------------------------------------");
        result = (double)plus(100, 50);
        System.out.println("result : " + result);
        System.out.println(plus(1, 2));
        CalculatorEx cx = new CalculatorEx();
        System.out.println(cx.plus2(1, 2));
        System.out.println("-------------------------------------------------");
        System.out.println("정사각형 넓이 : " + cx.areaRectangle(10.0));
        System.out.println("직사각형 넓이 : " + cx.areaRectangle(10.0, 20.0));
        System.out.println("정사각형 넓이 : " + staticAreaRectangle(10.0));
        System.out.println("직사각형 넓이 : " + staticAreaRectangle(10.0, 20.0));
        System.out.println("-------------------------------------------------");
        System.out.println("클래스의 static 메서드로 계산 : " + Calculator.plus(1, 2));
        System.out.println("클래스의 static 메서드로 계산 : " + Calculator.minus(2, 1));
    }

    private static int plus(int x, int y) {
        int sum = x + y;
        return sum;
    }

    private int plus2(int x, int y) {
        return x + y;
    }

    private static double staticAreaRectangle(double width) {
        return width * width;
    }

    private static double staticAreaRectangle(double width, double height) {
        return width * height;
    }

    private double areaRectangle(double width) {
        return width * width;
    }

    private double areaRectangle(double width, double height) {
        return width * height;
    }
}
