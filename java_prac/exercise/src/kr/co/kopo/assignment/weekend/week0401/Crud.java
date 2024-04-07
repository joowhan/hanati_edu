package kr.co.kopo.assignment.weekend.week0401;

public interface Crud {
    void readTable();
    void readPost(int nb_board);
    boolean insertPost();
    boolean updatePost(int nb_board);
    boolean deletePost(int nb_board);
    boolean deletePost(String[] arr);


}
