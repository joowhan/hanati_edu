package kr.co.kopo.classEx.static_11_04w1;

public class Calculator {
    static double pi = 3.141592;
    String color;
    static String company ="Samsung";
    static String model = "galaxy calculator";
    static String info;
    static final int W = 1;
    static int w = 1;
    final int w1 = 2;
    void powerOn(){
        System.out.println("Power On...");
    }

    static {
//        this.color = "black";
//        PowerOn();
        info = company +"-"+model;
        plus(10,20);

    }
    static int plus(int x, int y){
        return x+y;
    }
    static  int minus(int x, int y){
        return x-y;
    }
    static void println(int input){
        System.out.println(input);
    }
    static void println(boolean input){
        System.out.println(input);
    }
    static void println(double input){
        System.out.println(input);
    }
    static void println(String input){
        System.out.println(input);
    }
}
