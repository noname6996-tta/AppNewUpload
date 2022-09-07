package com.example.btl.Model;

public class Ratting {
    private Integer id_rating;
    private Integer id_store;
    private Integer id_user;
    private Float ratting;
    private String comment;

    public Ratting() {
    }

    public Ratting(Integer id_rating, Integer id_store, Integer id_user, Float ratting, String comment) {
        this.id_rating = id_rating;
        this.id_store = id_store;
        this.id_user = id_user;
        this.ratting = ratting;
        this.comment = comment;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_rating() {
        return id_rating;
    }

    public void setId_rating(Integer id_rating) {
        this.id_rating = id_rating;
    }

    public Integer getId_store() {
        return id_store;
    }

    public void setId_store(Integer id_store) {
        this.id_store = id_store;
    }

    public Float getRatting() {
        return ratting;
    }

    public void setRatting(Float ratting) {
        this.ratting = ratting;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Ratting{" +
                "id_rating=" + id_rating +
                ", id_store=" + id_store +
                ", id_user=" + id_user +
                ", ratting='" + ratting + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
