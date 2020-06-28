package com.codegym.model;

public class Clothing {
    private Integer id;
    private String name;
    private String description;
    private String picture;
    private String price;
    private String origin;
    private Integer category_id;
    private String category;
    private String status;

    public Clothing() {
    }

    public Clothing(int id, String name, String description, String picture, String price, String origin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.origin = origin;
    }

    public Clothing(String name, String description, String picture, String price, String origin) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.origin = origin;
    }

    public Clothing(String name, String description, String picture, String price, String origin,  String category,
                    String status) {
        super();
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.origin = origin;
        this.category = category;
        this.status = status;
    }

    public Clothing(int id, String name, String description, String picture, String price, String origin, String category
            , String status) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.origin = origin;
        this.category = category;
        this.status = status;
    }

    public Clothing(String name, String description, String picture, String  price, String origin, int category_id) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.origin = origin;
        this.category_id = category_id;
    }

    public Clothing(int id, String name, String description, String picture, String price, String origin, int category_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.origin = origin;
        this.category_id = category_id;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
