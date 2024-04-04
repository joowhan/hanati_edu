package kr.co.kopo.classEx.inheritance_13_04w1;

public class Student extends Person1{
    public int studentNO;
    public Student(String name, int studentNO){
        super(name);
        this.studentNO = studentNO;
    }
    public void study(){
        System.out.println("공부를 합니다.");
    }

}
