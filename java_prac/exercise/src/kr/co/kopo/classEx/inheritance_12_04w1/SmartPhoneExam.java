package kr.co.kopo.classEx.inheritance_12_04w1;

public class SmartPhoneExam {
    public static void main(String[] args){
        SmartPhone myPhone = new SmartPhone("갤럭시 S23", "검정색");
        System.out.println("모델: "+ myPhone.model);
        System.out.println("색상: "+ myPhone.color);

        myPhone.circleArea(3);
    }
}
