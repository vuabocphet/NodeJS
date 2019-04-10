package com.nguyentinhdevelop.devos.nodejs.model;

public class roomModel {

    public String hotelid;
    public String _id;
    public String single_room;
    public String price;
    public String image;
    public String detail;
    public String floor;
    public String room_number;
    public String status;

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSingle_room() {
        return single_room;
    }

    public void setSingle_room(String single_room) {
        this.single_room = single_room;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public roomModel(String hotelid, String _id, String single_room, String price, String image, String detail, String floor, String room_number, String status) {

        this.hotelid = hotelid;
        this._id = _id;
        this.single_room = single_room;
        this.price = price;
        this.image = image;
        this.detail = detail;
        this.floor = floor;
        this.room_number = room_number;
        this.status = status;
    }
}
