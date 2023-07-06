package com.example.mainpage;

public class Services {
    private int image;
    private String name;
    private String dec;

    public Services(int image, String name, String dec) {
        this.image = image;
        this.name = name;
        this.dec = dec;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDec() {
        return dec;
    }
}
