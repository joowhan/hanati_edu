package kr.co.kopo.assignment.mid.Java_17_1_ex1_robotexam;

public class Majangerlmpl implements Missile,ArmLegr{
    String name = MAJANGER;
    Majangerlmpl(){
        System.out.println(name + " 입니다.");
        shootMissile();
        moveArmLeg();
        System.out.println("===================================");
    }
    @Override
    public void moveArmLeg() {
        System.out.println("팔다리를 움직일 수 있습니다.");
    }

    @Override
    public void shootMissile() {
        System.out.println("미사일 발사 가능합니다.");
    }
}
