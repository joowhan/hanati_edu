package kr.co.kopo.product.model;

public class TbProduct {
    private String noProduct;
    private String nmProduct;
    private String nmDetailExplain;
    private String dtStartDate;
    private String idFile;
    private String dtEndDate;
    private int qtCustomer;
    private int qtSalePrice;
    private int qtStock;
    private int qtDeliveryFee;

    public String getNoProduct() {
        return noProduct;
    }

    public String getNmProduct() {
        return nmProduct;
    }

    public String getNmDetailExplain() {
        return nmDetailExplain;
    }

    public String getDtStartDate() {
        return dtStartDate;
    }

    public String getIdFile() {
        return idFile;
    }

    public String getDtEndDate() {
        return dtEndDate;
    }

    public int getQtCustomer() {
        return qtCustomer;
    }

    public int getQtSalePrice() {
        return qtSalePrice;
    }

    public int getQtStock() {
        return qtStock;
    }

    public int getQtDeliveryFee() {
        return qtDeliveryFee;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public void setNmProduct(String nmProduct) {
        this.nmProduct = nmProduct;
    }

    public void setNmDetailExplain(String nmDetailExplain) {
        this.nmDetailExplain = nmDetailExplain;
    }

    public void setDtStartDate(String dtStartDate) {
        this.dtStartDate = dtStartDate;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public void setDtEndDate(String dtEndDate) {
        this.dtEndDate = dtEndDate;
    }

    public void setQtCustomer(int qtCustomer) {
        this.qtCustomer = qtCustomer;
    }

    public void setQtSalePrice(int qtSalePrice) {
        this.qtSalePrice = qtSalePrice;
    }

    public void setQtStock(int qtStock) {
        this.qtStock = qtStock;
    }

    public void setQtDeliveryFee(int qtDeliveryFee) {
        this.qtDeliveryFee = qtDeliveryFee;
    }
}
