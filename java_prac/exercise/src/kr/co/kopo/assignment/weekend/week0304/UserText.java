package kr.co.kopo.assignment.weekend.week0304;

public class UserText {
    private int idx;
    private String title;
    private String writer;
    private String date;
    private int views;
    private boolean secret;
    private String text;
    private String password;

    public UserText(){

    }
    public UserText(int idx, String title, String writer, String date, int views, boolean secret, String text, String password) {
        this.idx = idx;
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.views = views;
        this.secret = secret;
        this.text = text;
        this.password = password;
    }

    public int getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getDate() {
        return date;
    }

    public int getViews() {
        return views;
    }

    public boolean isSecret() {
        return secret;
    }

    public String getText() {
        return text;
    }

    public String getPassword() {
        return password;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "글 번호: " + idx +"\t"+
                " 제목: '" + title + '\'' +"\t"+
                " 작성자: '" + writer + '\'' +"\t"+
                " 작성일: '" + date + '\''+"\t"+
                " 조회수: " + views +"\t"+
                " 비밀글 여부: " + secret
                ;
    }
}
