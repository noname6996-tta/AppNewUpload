package com.example.btl.model;

import java.io.Serializable;

public class Category implements Serializable {
    private Integer id_category;
    private String type;
    private String image_category;

    public Category() {
    }

    public Category(Integer id_category, String type, String image_category) {
        this.id_category = id_category;
        this.type = type;
        this.image_category = image_category;
    }

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_category() {
        return image_category;
    }

    public void setImage_category(String image_category) {
        this.image_category = image_category;
    }

    @Override
    public String toString() {
        return "Catehory{" +
                "id_category=" + id_category +
                ", type='" + type + '\'' +
                ", image_category='" + image_category + '\'' +
                '}';
    }
}
