package kr.co.kopo.classEx.class_08_03w4;

public class ClassEx_240326_1 {

    public static int plus(int x, int y){
        int sum = x+y;
        return sum;
    }
    public static void main(String[] args){

        Human human1 = new Human();
        Human human2 = new Human();
        Korean korean1 = new Korean("Developer", 25, "Seoul","Male");
        Korean korean2 = new Korean();

        System.out.println(human1.name);
        System.out.println(human1.age);

        System.out.println(korean1.age);
        System.out.println(korean1.location);

        System.out.println(korean2.age);
        System.out.println(korean2.job);

        Calculator calculator = new Calculator();
        calculator.powerOn();
        calculator.powerOff("human");
        System.out.println("static으로 선언된 메소드를 그대로 쓸 수 있다. ");
        System.out.println(calculator.plus1(4,5));
        System.out.println(calculator.minus(4,5));
        System.out.println(calculator.multi(4,5));
        System.out.println(calculator.divide(4,5));
        System.out.println(plus(1,2));

        //overloading
        ClassEx_240326_1 ex = new ClassEx_240326_1();
        System.out.println(ex.areaRectangle(3));
        System.out.println(ex.areaRectangle(3,4));
        Calculator.Info();
        System.out.println("----------------------------------");
        System.out.println(Calculator.divide1(1,2));
        System.out.println(Calculator.minus1(2,3));
        System.out.println(Calculator.plus1(4,5));
        System.out.println(Calculator.multi1(5,6));
        System.out.println("반지름이 3인 원의 넓이는 "+(3*3*Calculator.PI));

        //Car
        Car car = new Car();
        for(int i=0;i<100;i++){
            car.setSpeed(i);
            System.out.println(car.getSpeed());
            if(car.getSpeed() == 50){
                car.setStart(false);
            }
            if(car.isStart() == false){
                car.setStop(true);
                break;
            }
        }
        if(car.isStop()==true){
            System.out.println("정상적으로 차가 멈추었습니다.");
        }

    }

    private double areaRectangle(double width){
        return width*width;
    }

    private double areaRectangle(double width, double height){
        return width*height;
    }

}
