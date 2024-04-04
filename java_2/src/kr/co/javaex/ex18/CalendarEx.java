package kr.co.javaex.ex18;

import java.util.Calendar;
import java.util.Date;

public class CalendarEx {
    public static void main(String[] args) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        int year = calendar.get(Calendar.YEAR);
        System.out.println("올해는 " + year + "년 입니다.");

        calendar.set(2023,3,18,19,37,30);
        Date past1 = calendar.getTime();
        System.out.println("pase1: " + past1);

        calendar.set(Calendar.YEAR, 2024);
        Date past2 = calendar.getTime();
        System.out.println("past2: " + past2);
    }
}
