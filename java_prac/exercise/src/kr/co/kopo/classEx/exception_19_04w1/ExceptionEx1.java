package kr.co.kopo.classEx.exception_19_04w1;

import java.util.Scanner;

public class ExceptionEx1 {
    public static void main(String[] args) {
        String s= null;
        try{
            System.out.println(s.length());
        }
        catch(NullPointerException e){
            System.out.println("NullPointerException 예외를 catch 하였습니다");
            System.out.println("—stack trace 시작 –");
            System.out.println(e.getStackTrace());
            System.out.println("—stack trace 끝 –");
        }
        catch (RuntimeException e){
            System.out.println("RuntimeException 예외를 catch 하였습니다. ");
        }
        int a = 0;
        Scanner scanner = new Scanner(System.in);
        a =scanner.nextInt();

    }
}
