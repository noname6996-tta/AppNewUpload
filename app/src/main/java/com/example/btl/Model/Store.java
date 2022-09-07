package com.example.btl.Model;

import java.io.Serializable;

public class Store implements Serializable {
    private Integer id_store;
    private String name_store;
    private String address_store;
    private String type;
    private String timeopen;
    private String phone;
    private String lat;
    private String lot;
    private String linkWeb;
    private String image_store;
    private Float star_store;

    public Store() {
    }

    public Store(Integer id_store, String name_store, String address_store, String type, String timeopen, String phone, String lat, String lot, String linkWeb, String image_store, Float star_store) {
        this.id_store = id_store;
        this.name_store = name_store;
        this.address_store = address_store;
        this.type = type;
        this.timeopen = timeopen;
        this.phone = phone;
        this.lat = lat;
        this.lot = lot;
        this.linkWeb = linkWeb;
        this.image_store = image_store;
        this.star_store = star_store;
    }

    public Integer getId_store() {
        return id_store;
    }

    public void setId_store(Integer id_store) {
        this.id_store = id_store;
    }

    public String getName_store() {
        return name_store;
    }

    public void setName_store(String name_store) {
        this.name_store = name_store;
    }

    public String getAddress_store() {
        return address_store;
    }

    public void setAddress_store(String address_store) {
        this.address_store = address_store;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String gettimeopen() {
        return timeopen;
    }

    public void settimeopen(String timeopen) {
        this.timeopen = timeopen;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }

    public String getImage_store() {
        return image_store;
    }

    public void setImage_store(String image_store) {
        this.image_store = image_store;
    }

    public String getTimeopen() {
        return timeopen;
    }

    public void setTimeopen(String timeopen) {
        this.timeopen = timeopen;
    }

    public Float getStar_store() {
        return star_store;
    }

    public void setStar_store(Float star_store) {
        this.star_store = star_store;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id_store=" + id_store +
                ", name_store='" + name_store + '\'' +
                ", address_store='" + address_store + '\'' +
                ", type='" + type + '\'' +
                ", timeopen='" + timeopen + '\'' +
                ", phone='" + phone + '\'' +
                ", lat='" + lat + '\'' +
                ", lot='" + lot + '\'' +
                ", linkWeb='" + linkWeb + '\'' +
                ", image_store='" + image_store + '\'' +
                ", star_store=" + star_store +
                '}';
    }
}
