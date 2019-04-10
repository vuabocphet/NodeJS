package com.nguyentinhdevelop.devos.nodejs.model;

public class hotelModel {


    public String id;
    public String city;
    public String address;
    public String owner;
    public String license_number;
    public String total_floor;
    public String image;
    public String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getTotal_floor() {
        return total_floor;
    }

    public void setTotal_floor(String total_floor) {
        this.total_floor = total_floor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public hotelModel(String id, String city, String address, String owner, String license_number, String total_floor, String image, String name) {

        this.id = id;
        this.city = city;
        this.address = address;
        this.owner = owner;
        this.license_number = license_number;
        this.total_floor = total_floor;
        this.image = image;
        this.name = name;
    }
}
