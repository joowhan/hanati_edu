package kr.co.kopo.classEx.class_08_03w4;

public class Member {
    String name;
    String id;
    String password;
    int age;
    String telno;
    String address;
    Member(){}
    Member(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getTelno() {
        return telno;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    boolean login(String id, String password){
        if(id =="hong" && password=="12345"){
            return true;
        }
        return false;
    }
    void logout(String id){
        System.out.println(id+"님이 로그아웃 되었습니다.");
    }


}
