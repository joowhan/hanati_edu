package kr.co.kopo.util;

public interface ProductCrudInterface extends Crud {
    void readData(int nbCategory);
    boolean insertData(int nbCategory);
    boolean updateData(int nbCategory,String key);
    boolean deleteData(int nbCategory, String[] keys);
    boolean deleteData(int nbCategory, String key);
}
