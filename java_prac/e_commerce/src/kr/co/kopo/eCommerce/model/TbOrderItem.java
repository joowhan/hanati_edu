package kr.co.kopo.eCommerce.model;

public class TbOrderItem {
    private String idOrderItem;
    private String idOrder;
    private int cnOrderItem;
    private String noProduct;
    private String noUser;
    private int qtUnitPrice;
    private int qtOrderItem;
    private int qtOrderItemAmount;
    private int qtOrderItemDeliveryFee;

    public String getIdOrderItem() {
        return idOrderItem;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public int getCnOrderItem() {
        return cnOrderItem;
    }

    public String getNoProduct() {
        return noProduct;
    }

    public String getNoUser() {
        return noUser;
    }

    public int getQtUnitPrice() {
        return qtUnitPrice;
    }

    public int getQtOrderItem() {
        return qtOrderItem;
    }

    public int getQtOrderItemAmount() {
        return qtOrderItemAmount;
    }

    public int getQtOrderItemDeliveryFee() {
        return qtOrderItemDeliveryFee;
    }

    public void setIdOrderItem(String idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public void setCnOrderItem(int cnOrderItem) {
        this.cnOrderItem = cnOrderItem;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public void setQtUnitPrice(int qtUnitPrice) {
        this.qtUnitPrice = qtUnitPrice;
    }

    public void setQtOrderItem(int qtOrderItem) {
        this.qtOrderItem = qtOrderItem;
    }

    public void setQtOrderItemAmount(int qtOrderItemAmount) {
        this.qtOrderItemAmount = qtOrderItemAmount;
    }

    public void setQtOrderItemDeliveryFee(int qtOrderItemDeliveryFee) {
        this.qtOrderItemDeliveryFee = qtOrderItemDeliveryFee;
    }
}
