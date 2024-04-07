package kr.co.kopo.assignment.weekend.week0401;

public interface Crud {
    boolean readTable();
    boolean readPost(int nb_board);
    boolean insertPost();
    void updatePost(int nb_board);
    boolean deletePost(int nb_board);
    boolean deletePost(String[] arr);


}
