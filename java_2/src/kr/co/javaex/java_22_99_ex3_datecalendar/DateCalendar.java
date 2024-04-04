package kr.co.javaex.java_22_99_ex3_datecalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCalendar {
    public static void main(String[] args) {
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, nowDay + 100);
        Date futureDate = calendar.getTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("서기 yyyy년 MM월 dd일");
        String strNow = simpleDateFormat.format(futureDate);

        System.out.println(strNow);
    }
}
