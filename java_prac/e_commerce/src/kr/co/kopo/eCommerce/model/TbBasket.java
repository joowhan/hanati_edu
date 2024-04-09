package kr.co.kopo.eCommerce.model;

public class TbBasket {
    private int nbBasket;
    private String noUser;
    private String qtBasketAmount;

    public int getNbBasket() {
        return nbBasket;
    }

    public String getNoUser() {
        return noUser;
    }

    public String getQtBasketAmount() {
        return qtBasketAmount;
    }

    public void setNbBasket(int nbBasket) {
        this.nbBasket = nbBasket;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public void setQtBasketAmount(String qtBasketAmount) {
        this.qtBasketAmount = qtBasketAmount;
    }
}
