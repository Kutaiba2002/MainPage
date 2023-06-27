package com.example.mainpage;

public class Member {
//////Mohtade////////////////////////////////
    private String name;
    private String Email;
    private String pass;

    public Member(String name, String email, String pass) {
        this.name = name;
        Email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPass() {
        return pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
