package kr.co.kopo.classEx.inheritance_13_04w1;

public class StudentEx {
    public static void personInfo(Person1 person){
        System.out.println("name: "+ person.name);
        person.walk();

        if(person instanceof Student){
            Student student = (Student) person;
            System.out.println("studentNO: "+ student.studentNO);
            student.study();
        }

        if(person instanceof Student student){
            System.out.println("studentNO: "+student.studentNO);
            student.study();
        }
    }

    public static void main(String[] args) {
        Person1 person1 = new Person1("홍길이");
        personInfo(person1);

        Person1 person12 = new Student("홍순이", 30);
        personInfo(person12);
    }
}
