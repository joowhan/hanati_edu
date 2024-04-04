package kr.co.kopo.classEx.api_18_04w1;
import kr.co.kopo.classEx.static_11_04w1.Calculator;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
public class DateEx {
    public static void main(String[] args) {
        Date now = new Date();
        Calendar calendar =Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(2024, 4,4,10,16,30);
        Date past1 = calendar.getTime();
        System.out.println(past1);
        System.out.println(now);
        System.out.println(now.getTime());

        Date past = new Date(1712192862600L);
        System.out.println(past);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String strNow = sdf.format(now);
        System.out.println(strNow);
    }
}
