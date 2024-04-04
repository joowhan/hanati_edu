package kr.co.kopo.classEx.inheritance_12_04w1;

public class SmartPhone extends Phone{
    public SmartPhone(String model, String color){
        super();
        this.model = model;
        this.color = color;
        System.out.println("Smartphone 생성자 실행");
    }

    public double circleArea(double r){
        System.out.println("SmartPhone 실행");
        return Math.PI *r*r;
    }
}
