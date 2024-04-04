package kr.co.kopo.classEx.inheritance_13_04w1;

public class AnimalEx {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();
        Animal animal = new Animal();

        dog.sound();
        cat.sound();
        animal.sound();

        Dog dog1 = (Dog) dog;
        Cat cat1 = (Cat) cat;



    }
}
