package kr.co.kopo.eCommerce.model;

public class TbBasketItem {
    private int nbBasketItem;
    private int nbBasket;
    private int cnBasketItemOrder;
    private String noProduct;
    private String noUser;
    private int qtBasketItemPrice;
    private int qtBasketItem;
    private int qtBasketItemAmount;

    public int getNbBasketItem() {
        return nbBasketItem;
    }

    public int getNbBasket() {
        return nbBasket;
    }

    public int getCnBasketItemOrder() {
        return cnBasketItemOrder;
    }

    public String getNoProduct() {
        return noProduct;
    }

    public String getNoUser() {
        return noUser;
    }

    public int getQtBasketItemPrice() {
        return qtBasketItemPrice;
    }

    public int getQtBasketItem() {
        return qtBasketItem;
    }

    public int getQtBasketItemAmount() {
        return qtBasketItemAmount;
    }

    public void setNbBasketItem(int nbBasketItem) {
        this.nbBasketItem = nbBasketItem;
    }

    public void setNbBasket(int nbBasket) {
        this.nbBasket = nbBasket;
    }

    public void setCnBasketItemOrder(int cnBasketItemOrder) {
        this.cnBasketItemOrder = cnBasketItemOrder;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public void setQtBasketItemPrice(int qtBasketItemPrice) {
        this.qtBasketItemPrice = qtBasketItemPrice;
    }

    public void setQtBasketItem(int qtBasketItem) {
        this.qtBasketItem = qtBasketItem;
    }

    public void setQtBasketItemAmount(int qtBasketItemAmount) {
        this.qtBasketItemAmount = qtBasketItemAmount;
    }
}
