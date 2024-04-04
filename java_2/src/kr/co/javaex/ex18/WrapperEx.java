package kr.co.javaex.ex18;

public class WrapperEx {
    public static void main(String[] args) {
        int iVal1 = 49;
        Integer obj1 = Integer.valueOf(iVal1);
        int iVal2 = obj1.intValue();
        System.out.println(iVal2);


        int iVal3 = 20;
        Integer obj = iVal3;
        System.out.println("값: " + obj.intValue());
        int value = obj;
        System.out.println("값: "+ value);
        int result = obj + iVal3;
        System.out.println("값: " + result);

        double dVal1 = 2.0;
        Double objd = Double.valueOf(dVal1);
        double dVal2 = objd.doubleValue();
        System.out.println(dVal2);

        double dVal3 = 3.0;
        Double objdd = dVal3;
        System.out.println(objdd.doubleValue());
        double dvalue = objdd;
        System.out.println(dvalue);
        double resultd = dvalue+ objdd;
        System.out.println(resultd);
    }
}
