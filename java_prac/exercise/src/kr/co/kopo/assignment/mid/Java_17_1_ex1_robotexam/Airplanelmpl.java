package kr.co.kopo.assignment.mid.Java_17_1_ex1_robotexam;

public class Airplanelmpl implements Missile,Light{
    String name = AIRPLANE;
    Airplanelmpl(){
        System.out.println(name+ " 입니다. ");
        shootLight();
        shootMissile();
        System.out.println("===================================");
    }
    @Override
    public void shootLight() {
        System.out.println("불빛 발사 가능합니다.");
    }

    @Override
    public void shootMissile() {
        System.out.println("미사일 발사 가능합니다.");
    }

}
