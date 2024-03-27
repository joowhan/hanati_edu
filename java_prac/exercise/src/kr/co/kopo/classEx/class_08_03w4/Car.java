package kr.co.kopo.classEx.class_08_03w4;

public class Car {
    private String name;
    private int speed;
    private boolean stop;

    private boolean start;
    Car(){
        System.out.println("차가 출발합니다.");
        this.start = true;
    }
    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isStop() {
        return stop;
    }

    public boolean isStart() {
        return start;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if(speed <0){
            this.speed = 0;
            return;
        }
        else if(speed >50){
            this.speed = 50;
        }
    }
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    public void setStart(boolean start) {
        this.start = start;
    }
}
