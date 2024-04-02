package kr.co.javaex.weekend01;

import java.io.Serializable;

public class Post implements Serializable {
    private int postNum;
    private String title;
    private String content;
    private String writer;
    private String date;
    private int views = 0;
    private boolean secret;
    private String password = null;

    public String listToString() {
        String str = "식별번호: " + postNum + ", 제목: " + title + ", 작성자: " + writer + ", 작성일자: " + date + ", 조회수: " + views + ", 비밀 글 여부: " + secret;
        return str;
    }

    public String detailToString() {
        String str = "식별번호: " + postNum + ", 제목: " + title + ", 작성자: " + writer + ", 내용: " + content + ", 작성일자: " + date + ", 조회수: " + views + ", 비밀 글 여부: " + secret;
        return str;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
