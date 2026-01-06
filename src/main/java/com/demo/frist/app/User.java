package com.demo.frist.app;

public class User {
    private int id;
    private String name;
    private String EMail;

    public User(int id, String name, String EMail) {
        this.id = id;
        this.name = name;
        this.EMail = EMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }
}
