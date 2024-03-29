package kr.co.kopo.assignment.mid.Java_09_2_ex1_accountManager;

public class Account {
    private String accountID;
    private String name;
    private int money;
    Account(){}
    Account(String accountID, String name, int money){
        this.accountID = accountID;
        this.name = name;
        this.money = money;
    }

    /*getter & setter*/
    public String getAccountID() {
        return accountID;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //입출력
    public void deposit(int money){
        this.money += money;
    }
    public boolean withdraw(int money){
        if((this.money-money)<0){
            return false;
        }
        this.money-=money;
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
