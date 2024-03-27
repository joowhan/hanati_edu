package kr.co.kopo.classEx.class_08_03w4;

public class Calculator {
    static double PI = 3.141592;

    public static double getPI() {
        return PI;
    }

    public boolean isStop() {
        return stop;
    }

    private String name;
    private int speed;
    private boolean stop;
    public Calculator(){
        powerOn();
        this.name = "hello";
    }
    void powerOn(){
        System.out.println("전원을 켭니다. ");
    }
    void powerOff(String name){
        System.out.println(name+"가 전원을 끕니다.");
    }
    int plus(int x, int y){
        int result = x+y;
        return result;
    }
    int minus(int x, int y){
        plus(1,2);
        return x-y;
    }
    int multi(int x, int y){
        return x*y;
    }
    double divide(int x, int y){
        return (double)x/y;
    }
    static void Info(){
        System.out.println("더하기, 빼기, 곱하기, 나누기 가능");
    }
    String getName(){
        return this.name;
    }

    static int plus1(int x, int y){
        int result = x+y;
        return result;
    }
    static int minus1(int x, int y){
//        plus1(1,2);
        return x-y;
    }
    static int multi1(int x, int y){
        return x*y;
    }
    static double divide1(int x, int y){
        return (double)x/y;
    }
    static double calc(double radious){
        double area = radious * radious*PI;

        return area;
    }

}
