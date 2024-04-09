package kr.co.kopo.util;

import kr.co.kopo.user.model.TbUser;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean idValidation(String id){
        Pattern idPattern = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z\\d]{5,15}$"); //
        Matcher idMatcher = idPattern.matcher(id);
        if(!idMatcher.find()){
            return false;
        }
        return true;
    }
    //비밀번호 검증
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
    public static boolean login(TbUser tbUser){
        Scanner scanner = new Scanner(System.in);
        if(checkId(tbUser.getIdUser()) && checkPassword(tbUser.getNmPaswd())){
            return true;
        }
        System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
        return false;

    }
    private static boolean checkId(String id){
        System.out.println("아이디는 영문자, 숫자 사용 가능, 5~15자리여야 합니다.");
        Scanner scanner = new Scanner(System.in);
        String inputId;
        System.out.print("아이디를 입력하세요: ");
        inputId = scanner.next();
        if (inputId.equals(id)) {
//            System.out.println("아이디 일치!");
            return true;
        } else {
//            System.out.println("아이디가 일치하지 않습니다.");
            return false;
        }
    }
    private static boolean checkPassword(String password) {
        System.out.println("비밀번호는 영문자(대문자/소문자 1 이상 포함), 숫자 1이상 포함, 5~15자리여야 합니다.");
        Scanner scanner = new Scanner(System.in);
        String pwd;
        System.out.print("비밀번호를 입력하세요: ");
        pwd = scanner.next();
        if (pwd.equals(password)) {
//            System.out.println("비밀번호 일치!");
            return true;
        } else {
//            System.out.println("비밀번호가 일치하지 않습니다.");
            return false;
        }
    }
}
