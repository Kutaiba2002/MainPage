package com.example.mainpage;

public class Facilites {
    private int image;
    private String name ;
    private String des;

    public Facilites(int image, String name, String des) {
        this.image = image;
        this.name = name;
        this.des = des;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }
}
