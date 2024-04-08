package kr.co.kopo.user;

import kr.co.kopo.DbConnection;

public class UserEx {

    public static void main(String[] args) {
        UserCrud userCrud = new UserCrud();
        userCrud.readData();
    }
}
