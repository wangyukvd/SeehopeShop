package com.seehope.entity;

import java.util.Date;
//实体类封装评论
public class Comment {
    private int id;
    private String comments;

    private int score;
    private int proid;
    private int userid;
    private String username;
    private Date time;

    public Comment() {
    }

    public Comment(int id, String comments, int score, int proid, int userid, String username, Date time) {
        this.id = id;
        this.comments = comments;
        this.score = score;
        this.proid = proid;
        this.userid = userid;
        this.username = username;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comments='" + comments + '\'' +
                ", score=" + score +
                ", proid=" + proid +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", time=" + time +
                '}';
    }
}
