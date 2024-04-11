package kr.co.kopo.user.model;

public class TbUser {
    private String noUser;
    private String idUser;
    private String nmUser;
    private String nmPaswd;
    private String nmEmail;
    private String stStatus;

    public String getNoUser() {
        return noUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNmUser() {
        return nmUser;
    }

    public String getNmPaswd() {
        return nmPaswd;
    }

    public String getNmEmail() {
        return nmEmail;
    }

    public String getStStatus() {
        return stStatus;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public void setNmPaswd(String nmPaswd) {
        this.nmPaswd = nmPaswd;
    }

    public void setNmEmail(String nmEmail) {
        this.nmEmail = nmEmail;
    }

    public void setStStatus(String stStatus) {
        this.stStatus = stStatus;
    }
}
