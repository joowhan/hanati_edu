package kr.co.kopo;

public interface Crud {
    void readData();
    boolean insertData();
    boolean updateData(String noUser);
    boolean deleteData(String[] noUser);
}
