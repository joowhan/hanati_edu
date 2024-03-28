package kr.co.kopo.assignment.mid.Java_09_1_ex1_studentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int inputData = 0;
        String studentID;
        String name;
        int age;
        String major;
        List<Student> studentList = new ArrayList<>(100);
        while(true){
            System.out.println("------------------------------------------------");
            System.out.println("1. 학생 추가 | 2. 학생 목록 | 3. 학생 수정 | 4. 학생 삭제 | 5. 종료 ");
            System.out.println("------------------------------------------------");
            System.out.print("선택 > ");
            inputData = scanner.nextInt();
            System.out.println("--------------------");
            // 시스템 option
            // 학생 추가
            if(inputData==1){
                System.out.println("학생 추가");
                System.out.println("--------------------");

                System.out.print("학번(key): ");
                studentID = scanner.next();

                System.out.print("이름: ");
                name = scanner.next();

                System.out.print("나이: ");
                age = scanner.nextInt();

                System.out.print("전공:");
                major = scanner.next();

                Student student = new Student(studentID,name,age,major);
                int flag = 0;
                for(int i=0;i<studentList.size();i++){
                    if(studentList.get(i).getStudentID().equals(studentID)){
                        System.out.println("이미 존재하는 학생입니다.");
                        flag = 1;
                        break;
                    }
                }
                if(flag==0){
                    studentList.add(student);
                    System.out.println("학생을 추가하였습니다.");
                }
//                if(studentList.contains(student)){
//                    System.out.println("이미 존재하는 학생입니다.");
//                    // 객체 null
//                    student = null;
//                }
//                else{
//                    studentList.add(student);
//                    System.out.println("학생을 추가하였습니다.");
//                }

            }
            // 학생 목록
            else if(inputData==2){
                System.out.println("학생 목록");
                System.out.println("--------------------");
                for(int i=0;i<studentList.size();i++){
                    Student s = studentList.get(i);
                    System.out.println(s.getName()+"\t"+s.getAge()+"\t"+s.getStudentID()+"\t"+s.getMajor());
                }
                System.out.println("------------------------------------------------");
            }
            else if(inputData==3){
                System.out.println("학생 수정");
                System.out.println("--------------------");

                System.out.print("학번(key): ");
                studentID = scanner.next();

                System.out.print("이름: ");
                name = scanner.next();

                System.out.print("나이: ");
                age = scanner.nextInt();

                System.out.print("전공:");
                major = scanner.next();

                int flag = 0;

                for(int i=0;i<studentList.size();i++){
                    if(studentID.equals(studentList.get(i).getStudentID())){
                        studentList.get(i).setStudentID(studentID);
                        studentList.get(i).setName(name);
                        studentList.get(i).setAge(age);
                        studentList.get(i).setMajor(major);
                        flag =1;
                        System.out.println("학생을 수정하였습니다.");
                        break;
                    }
                }
                if(flag==0){
                    System.out.println("학생이 존재하지 않습니다.");
                }

            }
            else if(inputData==4){
                System.out.println("학생 삭제");
                System.out.println("--------------------");
                System.out.print("학번(key): ");
                studentID = scanner.next();
                int flag =0;
                for(int i =0;i<studentList.size();i++){
                    if(studentID.equals(studentList.get(i).getStudentID())) {
                        studentList.remove(studentList.get(i));
                        flag = 1;
                        System.out.println("학생을 학제하였습니다.");
                        break;
                    }
                }
                if(flag==0){
                    System.out.println("삭제할 학생이 존재하지 않습니다.");
                }


            }

            else if(inputData==5){
                System.out.println("시스템 종료");
                break;
            }
            else{
                System.out.println("다시 입력하세요.");
            }

        }
    }
}
