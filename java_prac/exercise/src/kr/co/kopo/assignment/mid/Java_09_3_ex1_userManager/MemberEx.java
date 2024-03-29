package kr.co.kopo.assignment.mid.Java_09_3_ex1_userManager;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* MemberEx
*   ** method **
*   - idValidation: id 제약조건 확인 -> 영문자, 숫자 사용 가능, 5~15자리
*   - pwdValidation: password 제약조건 확인 -> 영문자(대문자/소문자 1 이상 포함), 숫자 1이상 포함, 5~15자리
*   - emailValidation: email 형식 확인
*   - phoneValidation: 핸드폰 번호 형식 확인
*   - main: 프로그램 로직 처리
*       1. 회원 추가: 기존 존재 회원 확인, 입력 데이터 제약조건 체크
*       2. 회원 삭제: 비밀번호 일치 여부 확인
*       3. 회원 조회
*       4. 로그인: 비밀번호 일치 여부 확인, 로그인 여부 확인(재로그인 방지)
*       5. 로그아웃: 로그아웃 여부 확인(재로그아웃 방지)
*       6. 종료
*
* */

public class MemberEx {
    public static boolean idValidation(String id){
        Pattern idPattern = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z\\d]{5,15}$"); //
        Matcher idMatcher = idPattern.matcher(id);
        if(!idMatcher.find()){
            return false;
        }
        return true;
    }
    public static boolean pwdValidation(String password){
        Pattern pwdPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,15}$");
        Matcher pwdMatcher = pwdPattern.matcher(password);
        if(!pwdMatcher.find()){
            return false;
        }
        return true;
    }
    public static boolean emailValidation(String password){
        Pattern pwdPattern = Pattern.compile("^\\w+@\\w+\\.\\w+(\\.\\w+)?+(\\.\\w+)?$");
        Matcher pwdMatcher = pwdPattern.matcher(password);
        if(!pwdMatcher.find()){
            return false;
        }
        return true;
    }
    public static boolean phoneValidation(String password){
        Pattern pwdPattern = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
        Matcher pwdMatcher = pwdPattern.matcher(password);
        if(!pwdMatcher.find()){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        //맴버 정보 저장 arraylist
        List<Member> memberList = new ArrayList<>(100);

        String id;
        String password;
        String name;
        String email;
        String phone;

        int inputData = 0;
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("===== 회원 관리 및 로그인 프로그램 =====");
            System.out.println("1. 회원 추가 \n2. 회원 삭제 \n3. 회원 조회 \n4. 로그인 \n5.로그아웃 \n6. 종료");
            System.out.print("메뉴를 선택하세요: ");
            inputData = scanner.nextInt();
            //회원 추가
            if(inputData==1){
                System.out.println("--------------------");
                System.out.println("회원 추가");
                System.out.println("--------------------");
                System.out.print("id: ");
                id = scanner.next();

                System.out.print("password: ");
                password = scanner.next();

                System.out.print("이름: ");
                name = scanner.next();

                System.out.print("이메일: ");
                email = scanner.next();

                System.out.print("핸드폰: ");
                phone = scanner.next();
                // id 제약조건 확인
                if(!idValidation(id)){
                    System.out.println("id는 영문자, 숫자 사용 가능, 5~15자리여야 합니다.");
                    continue;
                }
                // 비밀번호 제약조건 확인
                if(!pwdValidation(password)){
                    System.out.println("비밀번호는 영문자(대문자/소문자 1 이상 포함), 숫자 1이상 포함, 5~15자리여야 합니다.");
                    continue;
                }
                // 이메일 제약조건 확인
                if(!emailValidation(email)){
                    System.out.println("이메일 형식을 확인해주세요.");
                    continue;
                }
                // 핸드폰 번호 제약조건 확인
                if(!phoneValidation(phone)){
                    System.out.println("핸드폰 번호 형식을 확인해주세요.");
                    continue;
                }
                int flag =0;
                for(int i=0;i<memberList.size();i++){
                    //이미 아이디가 존재한다면 회원 추가 취소
                    if(memberList.get(i).getId().equals(id)){
                        System.out.println("이미 존재하는 id입니다.");
                        flag = 1;
                        break;
                    }
                }
                //회원 정상 추가
                if(flag ==0){
                    Member member = new Member(id, password,name,email,phone);
                    memberList.add(member);
                    System.out.println("정상적으로 회원이 추가되었습니다.");
                }


            }
            //회원 삭제
            else if(inputData==2){
                System.out.println("--------------------");
                System.out.println("회원 삭제");
                System.out.println("--------------------");
                
                System.out.print("id: ");
                id = scanner.next();

                System.out.print("password: ");
                password = scanner.next();
                
                int flag = 0;
                for(int i=0;i<memberList.size();i++){
                    //삭제할 ID 찾기
                    if(memberList.get(i).getId().equals(id)){
                        // 입력한 비빌번호가 회원의 비밀번호와 일치하지 않는지 check
                        if(memberList.get(i).checkPassword(password)){
                            memberList.remove(memberList.get(i));
                            System.out.println("삭제 성공");
                            flag = 1;
                            break;
                        }
                        else{
                            System.out.println("비밀번호를 확인하세요.");
                            flag = 1;
                            break;
                        }

                    }
                }
                //ID가 존재하지 않는다면 
                if(flag ==0){
                    System.out.println("회원이 존재하지 않습니다.");
                }


            }
            //회원 정보 조회
            else if(inputData==3){
                System.out.println("--------------------");
                System.out.println("회원 조회");
                System.out.println("--------------------");
                for (Member member : memberList) {
                    System.out.println(member.toString());
                }

            }
            // 로그인
            else if(inputData==4){
                System.out.println("--------------------");
                System.out.println("로그인");
                System.out.println("--------------------");
                System.out.print("id: ");
                id = scanner.next();

                System.out.print("password: ");
                password = scanner.next();

                int flag = 0;
                for(int i=0;i<memberList.size();i++){
                    //로그인할 ID 찾기
                    if(memberList.get(i).getId().equals(id)){
                        //입력한 비밀번호 일치 여부 확인 
                        if(memberList.get(i).checkPassword(password)){
                            //이미 로그인 되어 있는지 확인
                            if(memberList.get(i).isLogined()){
                                System.out.println("이미 로그인 되어 있습니다.");
                                flag = 1;
                                break;
                            }
                            else{
                                //로그아웃 되어 있다면 로그인하고 로그인 성공 메세지 출력
                                memberList.get(i).setLogined(true);
                                System.out.println("로그인 성공");
                                flag = 1;
                                break;
                            }

                        }
                        else{
                            System.out.println("비밀번호를 확인하세요.");
                            flag = 1;
                            break;
                        }
                    }
                }
                //아이디가 존재하지 않는다면
                if(flag==0){
                    System.out.println("다시 입력하세요.");
                }



            }
            //로그아웃
            else if(inputData==5){
                System.out.println("--------------------");
                System.out.println("로그 아웃");
                System.out.println("--------------------");

                System.out.print("id: ");
                id = scanner.next();

                int flag = 0;
                for(int i=0;i<memberList.size();i++){
                    if(memberList.get(i).getId().equals(id)){
                        //로그인 상태 확인
                        if(!memberList.get(i).isLogined()){
                            System.out.println("이미 로그아웃 되어 있습니다.");
                            flag = 1;
                            break;
                        }
                        else{
                            //로그 아웃으로 상태 변경
                            memberList.get(i).setLogined(false);
                            System.out.println("로그아웃 성공");
                            flag = 1;
                            break;
                        }

                    }
                }
                if(flag==0){
                    System.out.println("다시 입력하세요.");
                }

            }
            // 프로그램 종료
            else if(inputData==6){
                System.out.println("--------------------");
                break;
            }
            else{
                System.out.println("다시 입력하세요.");
                System.out.println("--------------------");
            }



        }
        System.out.println("프로그램 종료.");
    }
}
