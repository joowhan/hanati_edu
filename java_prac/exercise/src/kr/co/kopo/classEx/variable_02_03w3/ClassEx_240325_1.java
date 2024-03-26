package kr.co.kopo.classEx.variable_02_03w3;

import java.util.*;

public class ClassEx_240325_1 {
    public static void main(String[] args) {

        int value1 = Integer.parseInt("10");
        double value2 = Double.parseDouble("3.14");
        boolean value3 = Boolean.parseBoolean("true");


        System.out.println("value1: "+value1);
        System.out.println("value1: "+value2);
        System.out.println("value1: "+value3);

        String str1 = String.valueOf(10);
        String str2 = String.valueOf(3.14);
        String str3 = String.valueOf(true);

        System.out.println("str1: "+str1);
        System.out.println("str2: "+str2);
        System.out.println("str3: "+ str3);

        Scanner scanner = new Scanner(System.in);
        String strScore = scanner.nextLine();
        System.out.println("입력 받은 score: "+ strScore);

        double pi = 3.14159;
        long ln = 314158265853979L;
        String str4 = "오늘 하루 열심히 걸어, 9000 걸음을 채웠다.";

    }
}
