package com.szyoo.entity;

import com.szyoo.io.InputController;

public class User {
    private String name;
    private String mail;
    private String password;

    public User(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

    public User() {
        if (InputController.chooseUser() == 1) {
            this.name = "楊聖哲";
            this.mail = "losiner.y@gmail.com";
            this.password = "ysz960411";
        } else {
            this.name = "王韻雅";
            this.mail = "wcatfish0606@gmail.com";
            this.password = "wangyunya0606";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
