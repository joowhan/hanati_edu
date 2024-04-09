package kr.co.kopo.eCommerce.model;

public class TbOrder {
    private String idOrder;
    private String noUser;
    private int qtOrderAmount;
    private int qtDeilMoney;
    private int qtDeilPeriod;
    private String nmOrderPerson;
    private String daOrder;
    private String stOrder;

    public String getIdOrder() {
        return idOrder;
    }

    public String getNoUser() {
        return noUser;
    }

    public int getQtOrderAmount() {
        return qtOrderAmount;
    }

    public int getQtDeilMoney() {
        return qtDeilMoney;
    }

    public int getQtDeilPeriod() {
        return qtDeilPeriod;
    }

    public String getNmOrderPerson() {
        return nmOrderPerson;
    }

    public String getDaOrder() {
        return daOrder;
    }

    public String getStOrder() {
        return stOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public void setQtOrderAmount(int qtOrderAmount) {
        this.qtOrderAmount = qtOrderAmount;
    }

    public void setQtDeilMoney(int qtDeilMoney) {
        this.qtDeilMoney = qtDeilMoney;
    }

    public void setQtDeilPeriod(int qtDeilPeriod) {
        this.qtDeilPeriod = qtDeilPeriod;
    }

    public void setNmOrderPerson(String nmOrderPerson) {
        this.nmOrderPerson = nmOrderPerson;
    }

    public void setDaOrder(String daOrder) {
        this.daOrder = daOrder;
    }

    public void setStOrder(String stOrder) {
        this.stOrder = stOrder;
    }
}
