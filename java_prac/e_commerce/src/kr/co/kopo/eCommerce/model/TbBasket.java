package kr.co.kopo.eCommerce.model;

public class TbBasket {
    private int nbBasket;
    private String noUser;
    private int qtBasketAmount;

    public int getNbBasket() {
        return nbBasket;
    }

    public String getNoUser() {
        return noUser;
    }

    public int getQtBasketAmount() {
        return qtBasketAmount;
    }

    public void setNbBasket(int nbBasket) {
        this.nbBasket = nbBasket;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public void setQtBasketAmount(int qtBasketAmount) {
        this.qtBasketAmount = qtBasketAmount;
    }
}
