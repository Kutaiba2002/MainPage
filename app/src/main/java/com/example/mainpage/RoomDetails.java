package com.example.mainpage;

public class RoomDetails {

    private final int image;
    private final int price;
    private final String name;

    private String description;
    private final int numberOfPerson;

    public RoomDetails(int image, int price, String name, int numberOfPerson,String description) {
        this.image = image;
        this.price = price;
        this.name = name;
        this.numberOfPerson = numberOfPerson;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPerson() {
        return numberOfPerson;
    }

}
