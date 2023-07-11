package com.example.mainpage;

public class AboutHotel {
    private int image;
    private String name;
    private String des;
    private double phone;
    private String accomadtion;
    private String services;
    private String facilities;

    public AboutHotel(int image, String name, String des, double phone, String accomadtion, String services, String facilities) {
        this.image = image;
        this.name = name;
        this.des = des;
        this.phone = phone;
        this.accomadtion = accomadtion;
        this.services = services;
        this.facilities = facilities;
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

    public double getPhone() {
        return phone;
    }

    public String getAccomadtion() {
        return accomadtion;
    }

    public String getServices() {
        return services;
    }

    public String getFacilities() {
        return facilities;
    }

    @Override
    public String toString() {
        return "AboutHotel{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", des='" + des + '\'' +
                ", phone=" + phone +
                ", accomadtion='" + accomadtion + '\'' +
                ", services='" + services + '\'' +
                ", facilities='" + facilities + '\'' +
                '}';
    }
}
