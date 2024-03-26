package kr.co.kopo.classEx.class_08_03w4;

public class ClassEx_240326_1 {
    public static void main(String[] args){
        Human human1 = new Human();
        Human human2 = new Human();
        Korean korean1 = new Korean("Developer", 25, "Seoul","Male");
        Korean korean2 = new Korean();

        System.out.println(human1.name);
        System.out.println(human1.age);


        System.out.println(korean1.age);
        System.out.println(korean1.location);


        System.out.println(korean2.age);
        System.out.println(korean2.job);
    }
}
