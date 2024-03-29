package kr.co.kopo.assignment.weekend.week0304;
import java.util.*;
import java.io.*;

public class BoardEx {
    public static void main(String[] args) throws IOException {
        List<UserText> boardList = new ArrayList<>();
        int inputData = 0;
//        File file = new File("C:\\java_weekend_HW\\list.csv");
        Scanner scanner = new Scanner(System.in);
        BoardCrud boardCrud = new BoardCrud();
        while(true){
            System.out.println("===== 게시판 프로그램 =====");
            System.out.println("1. 목록 조회 2. 종료");
            inputData = scanner.nextInt();
            //파일로 저장된 글 모두 불러오기
            if(inputData==1){
                System.out.println("--------------------");
                System.out.println("1. 목록 조회");
                System.out.println("--------------------");
                //1. 목록 조회
                BoardCrud.readList(boardCrud);

            }
            else if(inputData==2){
                //2. 종료
                //데이터 목록을 실제 파일로 저장
                System.out.println("--------------------");
                if(storedData()){
                    System.out.println("시스템을 종료합니다.");
                    break;
                }
                else{
                    System.out.println("저장 실패!");
                    break;
                }


            }
            else{
                System.out.println("다시 입력하세요!");
            }
        }

    }


    public static boolean storedData(){
        return true;
    }

}
