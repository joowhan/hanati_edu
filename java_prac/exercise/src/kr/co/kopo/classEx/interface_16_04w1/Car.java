package kr.co.kopo.classEx.interface_16_04w1;

public class Car {
    public Tire tire;
    public void run(){
        tire.roll();
    }
    public void whichTire(Tire tire1){
        tire1.roll();
    }
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.tire = new HankookTire();
        myCar.run();

        NexenTire nexenTire = new NexenTire();
        myCar.whichTire(nexenTire);
    }
}
