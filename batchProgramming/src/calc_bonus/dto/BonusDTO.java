package calc_bonus.dto;

public class BonusDTO {
    private String yyyymm;
    private String customerId;
    private String email;
    private String coupon;
    private String creditPoint;
    private String sendDt;
    private String receiveDt;
    private String useDt;

    public String getYyyymm() {
        return yyyymm;
    }

    public void setYyyymm(String yyyymm) {
        this.yyyymm = yyyymm;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCreditPoint() {
        return creditPoint;
    }

    public void setCreditPoint(String creditPoint) {
        this.creditPoint = creditPoint;
    }

    public String getSendDt() {
        return sendDt;
    }

    public void setSendDt(String sendDt) {
        this.sendDt = sendDt;
    }

    public String getReceiveDt() {
        return receiveDt;
    }

    public void setReceiveDt(String receiveDt) {
        this.receiveDt = receiveDt;
    }

    public String getUseDt() {
        return useDt;
    }

    public void setUseDt(String useDt) {
        this.useDt = useDt;
    }
}
