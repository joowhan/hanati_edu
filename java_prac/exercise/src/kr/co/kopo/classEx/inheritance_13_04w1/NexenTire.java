package kr.co.kopo.classEx.inheritance_13_04w1;

public class NexenTire extends Tire{
    NexenTire(){
        System.out.println(company);
    }
    @Override
    public void roll(){
        System.out.println("넥센타이어가 회전합니다.");
    }
}
