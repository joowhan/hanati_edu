package kr.co.kopo.classEx.api_18_04w1;

public class AccountEx {
    public static void main(String[] args) {
        Account a= new Account("4649", 1592);
        System.out.println(a);
        if(a.equals(new Account(" 4649", 1592))){
            System.out.println("계좌 일치");
        }
        else{
            System.out.println("불일치");
        }
    }
}
