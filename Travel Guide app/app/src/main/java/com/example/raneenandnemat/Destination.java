package com.example.raneenandnemat;

//Model of Destinations
public class Destination {
    private String city;
    private String country;
    private String continent;
    private String longitude;
    private String latitude;
    private String cost;
    private String img;
    private String description;
    public Destination(){

    }

    public Destination(String city, String country, String continent, String longitude, String latitude, String cost, String img, String description) {
        this.city = city;
        this.country = country;
        this.continent = continent;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cost = cost;
        this.img = img;
        this.description = description;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
