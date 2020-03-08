package com.example.projectandroid;

public class Items {

    private String id;
    private String numberTV;
    private String urlIMG;

    public Items(){

    }

    public Items(String name, String nb, String url){
        this.id = name;
        this.numberTV = nb;
        this.urlIMG = url;
    }

    public String getNameItem() {
        return id;
    }

    public void setNameItem(String nameItem) {
        this.id = nameItem;
    }

    public String getNumberTV() {
        return numberTV;
    }

    public void setNumberTV(String numberTV) {
        this.numberTV = numberTV;
    }

    public String getUrlIMG() {
        return urlIMG;
    }

    public void setUrlIMG(String urlIMG) {
        this.urlIMG = urlIMG;
    }
}
