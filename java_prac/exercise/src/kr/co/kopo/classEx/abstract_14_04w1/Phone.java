package kr.co.kopo.classEx.abstract_14_04w1;

public abstract class Phone {
    String owner;
    Phone(String owner){
        this.owner = owner;
    }

    void turnOff(){
        System.out.println("전원을 끕니다.");
    }
    void turnOn(){
        System.out.println("전원을 켭니다.");
    }


}
