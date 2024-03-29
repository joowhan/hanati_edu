package kr.co.kopo.classEx.accessModifier_10_03w4;

public class Account {
    private int balance;
    final static int MIN_BALANCE = 0;
    final static int MAX_BALANCE = 1000000;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if((this.balance+balance) >= MIN_BALANCE || (this.balance+balance)<=MAX_BALANCE){
            this.balance = balance;
        }
    }
}
