package kr.co.kopo.classEx.exception_19_04w1;

public class Person {
    int age;
    public void setAge(int age) throws InsufficientException{
        if(age<0){
            throw new InsufficientException("제대로 입력하렴");
        }
        this.age = age;
    }
}
