package com.example.mainpage;

public class Reservation {
    private String name;
    private String email;
    private String date;

    public Reservation() {
        // Default constructor required for Firestore
    }

    public Reservation(String name, String email, String date) {
        this.name = name;
        this.email = email;
        this.date = date;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

