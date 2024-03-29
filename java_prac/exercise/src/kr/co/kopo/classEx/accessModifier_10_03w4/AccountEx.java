package kr.co.kopo.classEx.accessModifier_10_03w4;

public class AccountEx {
    public static void main(String[] args){
        Account account = new Account();
        account.setBalance(10000);
        account.setBalance(-100);
        account.setBalance(200000);
        account.setBalance(300000);
        System.out.println(account.getBalance());

    }
}
