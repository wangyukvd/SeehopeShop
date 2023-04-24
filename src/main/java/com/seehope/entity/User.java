package com.seehope.entity;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String telephone;
    private String name;
    private Date regTime;

    public User(String username, String password, String telephone) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
    }

    public User(String username, String password, String telephone, String name, Date regTime) {
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.name = name;
        this.regTime = regTime;
    }

    public User(int id, String username, String password, String telephone, String name, Date regTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.name = name;
        this.regTime = regTime;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telephope='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", regTime=" + regTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephope(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }


}
