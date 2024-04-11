package kr.co.kopo.util;

public interface Crud {
    void readData();
    boolean insertData();
    boolean updateData(String key);
    boolean deleteData(String[] keys);
    boolean deleteData(String key);
}
