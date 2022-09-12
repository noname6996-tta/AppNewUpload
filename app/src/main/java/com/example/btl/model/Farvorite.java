package com.example.btl.model;

public class Farvorite {
    private Integer id_love;
    private Integer id_user;
    private Integer id_store;

    public Farvorite(Integer id_love, Integer id_user, Integer id_store) {
        this.id_love = id_love;
        this.id_user = id_user;
        this.id_store = id_store;
    }

    public Integer getId_love() {
        return id_love;
    }

    public void setId_love(Integer id_love) {
        this.id_love = id_love;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_store() {
        return id_store;
    }

    public void setId_store(Integer id_store) {
        this.id_store = id_store;
    }

    @Override
    public String toString() {
        return "Farvorite{" +
                "id_love=" + id_love +
                ", id_user=" + id_user +
                ", id_store=" + id_store +
                '}';
    }
}
