package com.example.parkinGenie.Entities;

public class CarPark {

    private int carParkId;
    private int userId;
    private String name;
    private String website;
    private String address;
    private String phone;
    private String gps;
    private int tot_spaces;
    private int free_spaces;
    private String height_restrictions;
    private String payment_methods;


    public CarPark(int carParkId, int userId, String name,  String website, String address,
                   String phone, String gps, int tot_spaces, int free_spaces,
                   String height_restrictions, String payment_methods) {

        this.carParkId = carParkId;
        this.userId = userId;
        this.name = name;
        this.website = website;
        this.address = address;
        this.phone = phone;
        this.gps = gps;
        this.tot_spaces = tot_spaces;
        this.free_spaces = free_spaces;
        this.height_restrictions = height_restrictions;
        this.payment_methods = payment_methods;
    }

    public CarPark(){

    }

    public int getCarParkId() {
        return carParkId;
    }

    public void setCarParkId(int carParkId) {
        this.carParkId = carParkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public int getTot_spaces() {
        return tot_spaces;
    }

    public void setTot_spaces(int tot_spaces) {
        this.tot_spaces = tot_spaces;
    }

    public int getFree_spaces() {
        return free_spaces;
    }

    public void setFree_spaces(int free_spaces) {
        this.free_spaces = free_spaces;
    }

    public String getHeight_restrictions() {
        return height_restrictions;
    }

    public void setHeight_restrictions(String height_restrictions) {
        this.height_restrictions = height_restrictions;
    }

    public String getPayment_methods() {
        return payment_methods;
    }

    public void setPayment_methods(String payment_methods) {
        this.payment_methods = payment_methods;
    }
}
