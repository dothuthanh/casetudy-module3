package com.codegym.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String pass_word;
    private String address;

    public User(){}

    public User(String name){
        this.name = name;
    }
    public User(int id, String name, String pass_word, String address){
        this.id = id;
        this.name = name;
        this.pass_word = pass_word;
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
