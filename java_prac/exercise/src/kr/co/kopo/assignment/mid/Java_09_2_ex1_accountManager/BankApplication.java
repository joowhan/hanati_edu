package kr.co.kopo.assignment.mid.Java_09_2_ex1_accountManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args){
        List<Account> accountList= new ArrayList<Account>(100);
        Scanner scanner = new Scanner(System.in);
        int inputData = 0;
        String accountID; //계좌 번호
        String name; //예금주
        int money; // 초기입금액
        //계좌 생성, 계좌 목록, 예금, 출금, 종료
        while(true){
            System.out.println("------------------------------------------------");
            System.out.println("1. 계좌 생성 | 2. 계좌 목록 | 3. 예금 | 4. 출금 | 5. 종료 ");
            System.out.println("------------------------------------------------");
            System.out.print("선택 > ");
            inputData = scanner.nextInt();
            System.out.println("--------------------");
            if(inputData==1){
                System.out.println("계좌 생성");
                System.out.println("--------------------");
                System.out.print("계좌번호: ");
                accountID = scanner.next();

                System.out.print("계좌주: ");
                name = scanner.next();

                System.out.print("초기 입금액: ");
                money = scanner.nextInt();
                Account account = new Account(accountID, name, money);

                int flag1 = 0;
                for(int i=0;i<accountList.size();i++){
                    if(accountList.get(i).getAccountID().equals(accountID)){
                        System.out.println("이미 존재하는 계좌입니다.");
                        flag1 =1;
                        break;
                    }
                }
                if (flag1==0){
                    accountList.add(account);
                    System.out.println("계좌가 생성 되었습니다.");
                }

            }
            //목록 출력
            else if(inputData==2){
                System.out.println("계좌 목록");
                System.out.println("--------------------");
                for(int i=0;i<accountList.size();i++){
                    Account a = accountList.get(i);
                    System.out.println(a.getAccountID()+"\t"+a.getName()+"\t"+a.getMoney());
                }

            }
            else if(inputData==3){
                System.out.println("예금");
                System.out.println("--------------------");
                System.out.print("계좌번호: ");
                accountID = scanner.next();

                System.out.print("입금액: ");
                money = scanner.nextInt();

                int flag = 0;
                for(int i=0;i<accountList.size();i++){
                    if(accountList.get(i).getAccountID().equals(accountID)){
                        flag =1;
                        accountList.get(i).deposit(money);
                        System.out.println("정상적으로 예금 되었습니다.");
                    }
                }
                if(flag==0){
                    System.out.println("계좌가 존재하지 않습니다.");
                }



            }
            else if(inputData==4){
                System.out.println("출금");
                System.out.println("--------------------");

                System.out.print("계좌번호: ");
                accountID = scanner.next();

                System.out.print("출금액: ");
                money = scanner.nextInt();
                int flag = 0;
                for(int i=0;i<accountList.size();i++){
                    if(accountList.get(i).getAccountID().equals(accountID)){
                        if(accountList.get(i).withdraw(money)){
                            System.out.println("정상적으로 출금 되었습니다.");
                        }
                        else{
                            System.out.println("출금을 실패했습니다.(금액 초과)");

                        }
                        flag = 1;
                        break;
                    }
                }
                if(flag ==0){
                    System.out.println("계좌가 존재하지 않습니다.");
                }


            }
            else if(inputData==5){
                break;

            }
            else{
                System.out.println("다시 입력하세요.");
                System.out.println("--------------------");

            }
        }
        System.out.println("계좌관리 프로그램 종료");
    }
}
