package kr.co.kopo.classEx.class_08_03w4;

public class Korean extends Human{
    String sex;
    String location;
    String job;
    int age;
    Korean(){
        this.job = "Professor";
        this.age = 55;
        this("Professor", 55, "Busan","Female");
    }
    Korean(String job, int age, String location, String sex){
        this.job = job;
        this.location = location;
        this.age = age;
        this.sex = sex;
    }

}
