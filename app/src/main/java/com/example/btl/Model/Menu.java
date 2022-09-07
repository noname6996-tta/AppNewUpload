package com.example.btl.Model;

public class Menu {
    private Integer id;
    private Integer id_store;
    private String imageDish;
    private String nameDish;
    private String priceDish;

    public Menu() {
    }

    public Menu(Integer id, Integer id_store, String imageDish, String nameDish, String priceDish) {
        this.id = id;
        this.id_store = id_store;
        this.imageDish = imageDish;
        this.nameDish = nameDish;
        this.priceDish = priceDish;
    }

    public Integer getId_store() {
        return id_store;
    }

    public void setId_store(Integer id_store) {
        this.id_store = id_store;
    }

    public String getImageDish() {
        return imageDish;
    }

    public void setImageDish(String imageDish) {
        this.imageDish = imageDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public String getPriceDish() {
        return priceDish;
    }

    public void setPriceDish(String priceDish) {
        this.priceDish = priceDish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", imageDish='" + imageDish + '\'' +
                ", nameDish='" + nameDish + '\'' +
                ", priceDish='" + priceDish + '\'' +
                '}';
    }
}
