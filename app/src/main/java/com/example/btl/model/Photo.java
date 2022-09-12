package com.example.btl.model;

import java.io.Serializable;

public class Photo implements Serializable {
    private Integer id_photo;
    private String resoureId;
    private Integer id_store;


    public Photo(Integer id_photo, String resoureId, Integer id_store) {
        this.id_photo = id_photo;
        this.resoureId = resoureId;
        this.id_store = id_store;
    }

    public Photo() {
    }

    public Integer getId_photo() {
        return id_photo;
    }

    public void setId_photo(Integer id_photo) {
        this.id_photo = id_photo;
    }

    public String getResoureId() {
        return resoureId;
    }

    public void setResoureId(String resoureId) {
        this.resoureId = resoureId;
    }

    public Integer getId_store() {
        return id_store;
    }

    public void setId_store(Integer id_store) {
        this.id_store = id_store;
    }
}
