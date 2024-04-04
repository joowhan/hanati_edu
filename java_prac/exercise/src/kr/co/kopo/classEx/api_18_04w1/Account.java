package kr.co.kopo.classEx.api_18_04w1;

public class Account {
    String accountNumber;
    int balance;
    Account(String accountNumber){
        this.accountNumber = accountNumber;
    }
    Account(String accountNumber, int balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
//    public boolean equals(String s){
//        if(this.accountNumber.equals(s.trim()))
//            return true;
//        return false;
//    }

    public boolean equals(Object object){
        if(object==this){
            return true;
        }
        if(object instanceof Account){
            Account account = (Account) object;
            account.balance = this.balance;
            if(this.accountNumber.equals(account.accountNumber.trim())){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString(){

        return this.balance+"원"+" (계좌번호 = "+this.accountNumber +")";
    }
}
