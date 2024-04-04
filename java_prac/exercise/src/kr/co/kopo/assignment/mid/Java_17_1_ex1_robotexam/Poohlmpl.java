package kr.co.kopo.assignment.mid.Java_17_1_ex1_robotexam;


public class Poohlmpl implements ArmLegr{
    String name = POOH;
    Poohlmpl(){
        System.out.println(name + " 입니다.");
        moveArmLeg();
        System.out.println("===================================");
    }
    @Override
    public void moveArmLeg() {
        System.out.println("팔다리를 움직일 수 있습니다.");
    }
}
