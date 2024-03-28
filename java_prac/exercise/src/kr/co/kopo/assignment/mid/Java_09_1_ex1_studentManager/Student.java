package kr.co.kopo.assignment.mid.Java_09_1_ex1_studentManager;

public class Student {
    private String studentID;
    private String name;
    private int age;
    private String major;

    // constructor
    Student(){}

    Student(String studentID, String name, int age, String major){
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.major = major;
    }

    /*getter & setter*/

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
