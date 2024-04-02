package kr.co.kopo.classEx.inheritance_13_04w1;

public class Car {
    public Tire tire;
    public void run(){
        tire.roll();
        System.out.println("차량이 달립니다아아아아아");
    }
    public void driver(Car car){
        car.run();
    }
    public static void main(String[] args) {
        Tire nexenTire = new NexenTire();

        System.out.println(nexenTire.company);

        Car myCar = new Car();
        myCar.tire = new Tire();
        myCar.run();

        myCar.tire = new HankookTire();
        myCar.run();

        myCar.tire = new NexenTire();
        myCar.run();

        Bus bus = new Bus();
        myCar.driver(bus);

        Taxi taxi = new Taxi();
        myCar.driver(taxi);
    }
}
