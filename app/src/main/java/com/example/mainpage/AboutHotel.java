package com.example.mainpage;


public class AboutHotel {
    private String Name;
    private String Des;
    private  int Img;

    public AboutHotel(String name, String des, int img) {
        Name = name;
        Des = des;
        Img = img;
    }

    public final static AboutHotel[]hotel={
            new AboutHotel("Describtion:","\nWelcome to our luxurious hotel, where elegance meets comfort. Experience impeccable service, stunning accommodations, and exquisite dining options. Relax in style and indulge in a memorable stay at our upscale haven.", R.drawable.des),
            new AboutHotel("phone:","\n+970 595 422 624 \n +972 566422624\n+972 59-821-9798\n+972 56-838-2926\n", R.drawable.phone),
            new AboutHotel("accomadtion:","\nAccommodation in the hotel context refers to providing a place to stay for guests. It involves offering rooms or spaces with amenities and services to meet their needs and ensure a comfortable stay.", R.drawable.acc),
            new AboutHotel("services:","\nServices in a hotel refer to the assistance and support provided to guests during their stay. This can include services such as housekeeping, room service, concierge assistance, luggage handling, and other amenities that aim to meet the guests' needs and enhance their overall experience.", R.drawable.services),
            new AboutHotel("facilities:","\nFacilities in a hotel are the amenities and services provided to guests for their convenience and enjoyment during their stay. This can include features like swimming pools, restaurants, fitness centers, conference rooms, and other offerings that enhance the guest experience.", R.drawable.facilities)


    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    @Override
    public String toString() {
        return Name;
    }
}
