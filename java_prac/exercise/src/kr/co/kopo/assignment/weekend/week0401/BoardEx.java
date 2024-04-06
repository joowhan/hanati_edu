package kr.co.kopo.assignment.weekend.week0401;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardEx {
    public static void main(String[] args) throws IOException {

        int inputData = 0;
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
                boardCrud.readTable();
//                BoardCrud.readList(boardCrud);

            }
            else if(inputData==2){
                //2. 종료
                //데이터 목록을 실제 파일로 저장
                System.out.println("--------------------");

                System.out.println("시스템을 종료합니다.");
                boardCrud.dbClose();
                break;

            }
            else{
                System.out.println("다시 입력하세요!");
            }
        }

    }
}
