package com.example.mainpage;

public class Reservation {

    private String name;
    private String email;
    private String startDate;
    private String endDate;
    private String roomName;

    public Reservation() {
        // Default constructor required for Firestore
    }

    public Reservation(String roomName,String name,String email,String startDate, String endDate) {
        this.roomName = roomName;
        this.name = name;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
