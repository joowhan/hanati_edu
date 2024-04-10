package kr.co.kopo.user;

import kr.co.kopo.eCommerce.model.TbBasket;
import kr.co.kopo.user.model.TbUser;

public class CurrUser {
    private static TbUser currUser =null;
    private static TbBasket currUserBasket=null;

    public static TbUser getCurrUser() {
        return currUser;
    }

    public static TbBasket getCurrUserBasket() {
        return currUserBasket;
    }

    public static void setCurrUser(TbUser currUser) {
        CurrUser.currUser = currUser;
    }

    public static void setCurrUserBasket(TbBasket currUserBasket) {
        CurrUser.currUserBasket = currUserBasket;
    }
}
