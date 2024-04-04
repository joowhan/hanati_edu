package kr.co.kopo.classEx.inheritance_12_04w1;

public class Children1 extends Parent{
     private String major;

     Children1(){
         super();
     }

    Children1(String id, String name, String major) {
        super(id, name);
        this.major = major;
    }

    public static void main(String[] args){
        Children1 a = new Children1("S", "wow", "ss");
        Children1 b = new Children1();
    }
}
