package kr.co.kopo.classEx.class_08_03w4;

public class Korean extends Human{
    String sex;
    String location;
    String job;
    int age;
    final String hometown = "Seoul";
    Korean(){
        this("Professor", 55, "Busan","Female");
        this.job = "Professor";
        this.age = 55;

    }
    Korean(String job, int age, String location, String sex){
        this.job = job;
        this.location = location;
        this.age = age;
        this.sex = sex;

    }
    static final double EARTH_SURFACE_AREA;
    static {
        EARTH_SURFACE_AREA=4;
    }

}
